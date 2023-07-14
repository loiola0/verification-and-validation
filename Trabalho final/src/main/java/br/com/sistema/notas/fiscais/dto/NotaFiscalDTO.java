package br.com.sistema.notas.fiscais.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotaFiscalDTO implements Serializable {

    private Long id;

    @NotEmpty
    private String url;
}
