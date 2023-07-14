package br.com.sistema.notas.fiscais.repository;

import br.com.sistema.notas.fiscais.model.ModelNotaFiscal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaFiscalRepository extends JpaRepository<ModelNotaFiscal, Long> {

    boolean existsByUrl (String url);
}
