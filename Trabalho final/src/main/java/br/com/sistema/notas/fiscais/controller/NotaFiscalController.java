package br.com.sistema.notas.fiscais.controller;

import br.com.sistema.notas.fiscais.exception.RegraNegocioException;
import br.com.sistema.notas.fiscais.exception.ValidaCamposException;
import br.com.sistema.notas.fiscais.dto.NotaFiscalDTO;
import br.com.sistema.notas.fiscais.model.ModelNotaFiscal;
import br.com.sistema.notas.fiscais.service.NotaFiscalService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/nota-fiscal")
public class NotaFiscalController {

    private NotaFiscalService notaFiscalService;

    private ModelMapper modelMapper;

    public NotaFiscalController(NotaFiscalService notaFiscalService, ModelMapper mapper) {
        this.notaFiscalService = notaFiscalService;
        this.modelMapper =mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NotaFiscalDTO salvarNotaFiscal(@RequestBody @Valid NotaFiscalDTO notaFiscalDTO){

        ModelNotaFiscal dados = modelMapper.map(notaFiscalDTO, ModelNotaFiscal.class);

        dados = notaFiscalService.save(dados);

        return modelMapper.map(dados, NotaFiscalDTO.class);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidaCamposException validaCamposException(MethodArgumentNotValidException exception){

        BindingResult bindingResult = exception.getBindingResult();

        return new ValidaCamposException(bindingResult);

    }

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidaCamposException validarRegraNegocioException(RegraNegocioException regra){

         return new ValidaCamposException(regra);

    }

}
