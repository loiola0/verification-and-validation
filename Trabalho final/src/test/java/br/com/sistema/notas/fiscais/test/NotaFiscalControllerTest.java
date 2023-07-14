package br.com.sistema.notas.fiscais.test;

import br.com.sistema.notas.fiscais.exception.RegraNegocioException;
import br.com.sistema.notas.fiscais.dto.NotaFiscalDTO;
import br.com.sistema.notas.fiscais.model.ModelNotaFiscal;
import br.com.sistema.notas.fiscais.service.NotaFiscalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class NotaFiscalControllerTest {

    static String API_NOTAFISCAL = "/api/nota-fiscal";

    @Autowired
    MockMvc mvc;

    @MockBean
    NotaFiscalService service;

    @Test
    @DisplayName("Esse controller deve salvar uma nota fiscal")
    public void salvarNotaFiscalTest() throws Exception{

        NotaFiscalDTO notaFiscalDTO = criarNotaFiscal();

        ModelNotaFiscal notaFiscalDados = ModelNotaFiscal.builder().id(1L).url("www.aws-123.com.br").build();

        BDDMockito.given(service.save(Mockito.any(ModelNotaFiscal.class))).willReturn(notaFiscalDados);

        String jsonTeste = new ObjectMapper().writeValueAsString(notaFiscalDTO);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(API_NOTAFISCAL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonTeste);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("url").value(notaFiscalDTO.getUrl()));
    }

    @Test
    @DisplayName("Vai retornar o erro se o usuario não informar os campos obrigatórios")
    public void validandoNotaFiscalTest() throws Exception{

        String json = new ObjectMapper().writeValueAsString(new NotaFiscalDTO());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(API_NOTAFISCAL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("erros", hasSize(1)));

    }

    @Test
    @DisplayName("Lançar Erro quando a nota fiscal possui url duplicada")
    public void validarUrlDuplicadoTest() throws Exception{

        NotaFiscalDTO dto = criarNotaFiscal();

        String msgErro = "Url já cadastrada, Por Favor informe outra!";

        String json = new ObjectMapper().writeValueAsString(new NotaFiscalDTO());
        BDDMockito.given(service.save(Mockito.any(ModelNotaFiscal.class)))
                .willThrow(new RegraNegocioException(msgErro));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(API_NOTAFISCAL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("erros", hasSize(1)));
     }

    public NotaFiscalDTO criarNotaFiscal(){
        return NotaFiscalDTO.builder().id(1L).url("www.aws-123.com.br").build();
    }

}