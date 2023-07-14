package br.com.sistema.notas.fiscais.service;

import br.com.sistema.notas.fiscais.exception.RegraNegocioException;
import br.com.sistema.notas.fiscais.implementacao.NotaFiscalServiceImpl;
import br.com.sistema.notas.fiscais.repository.NotaFiscalRepository;
import br.com.sistema.notas.fiscais.model.ModelNotaFiscal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class NotaFiscalServiceTest {

    NotaFiscalService service;

    @MockBean
    NotaFiscalRepository repository;

    @BeforeEach
    public void init(){

        this.service = new NotaFiscalServiceImpl(repository);

    }

    @Test
    @DisplayName("Deve salvar uma nota fiscal")
    public void salvarNotaFiscalTest(){

        ModelNotaFiscal modelNotaFiscal = criarNotaFiscal();

        Mockito.when(repository.save(modelNotaFiscal)).thenReturn(ModelNotaFiscal.builder().id(1L).url("www.aws-123.com.br").build());

        ModelNotaFiscal notaFiscalDados = service.save(modelNotaFiscal);

        assertThat(notaFiscalDados.getId()).isNotNull();
        assertThat(notaFiscalDados.getUrl()).isEqualTo("www.aws-123.com.br");
    }

    @Test
    @DisplayName("Deve lançar Erro quando usuario informar uma url já cadastrada")
    public void naoSalvarNotaFiscalComUrlJaCadastrada(){

        ModelNotaFiscal modelNotaFiscal = criarNotaFiscal();
        Mockito.when(repository.existsByUrl(Mockito.anyString())).thenReturn(true);

       Throwable exception =  Assertions.catchThrowable(() -> service.save(modelNotaFiscal));
       assertThat(exception)
               .isInstanceOf(RegraNegocioException.class)
               .hasMessage("Url já cadastrada");

       Mockito.verify(repository, Mockito.never()).save(modelNotaFiscal);

    }

    public ModelNotaFiscal criarNotaFiscal(){
        return ModelNotaFiscal.builder().url("www.aws-123.com.br").build();
    }

}
