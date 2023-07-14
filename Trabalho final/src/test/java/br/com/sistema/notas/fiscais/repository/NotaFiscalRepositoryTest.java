package br.com.sistema.notas.fiscais.repository;

import br.com.sistema.notas.fiscais.model.ModelNotaFiscal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class NotaFiscalRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    NotaFiscalRepository repository;

    @Test
    @DisplayName("Vai retornar verdadeiro se existir uma url já cadastrada no banco de dados")
    public void retornaVerdadeiroUrlExistente(){

        String url = "www.aws-123.com.br";

        ModelNotaFiscal modelNotaFiscal = ModelNotaFiscal.builder().url(url).build();

        entityManager.persist(modelNotaFiscal);

        boolean existente = repository.existsByUrl(url);

        assertThat(existente).isTrue();

    }

    @Test
    @DisplayName("Vai retornar falso se existir uma url já cadastrada no banco de dados")
    public void retornaFalsoUrlInexistente(){

        String isbn = "123";

        boolean existente = repository.existsByUrl(isbn);

        assertThat(existente).isFalse();

    }

}
