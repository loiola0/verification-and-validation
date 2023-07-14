package br.com.sistema.notas.fiscais.implementacao;

import br.com.sistema.notas.fiscais.exception.RegraNegocioException;
import br.com.sistema.notas.fiscais.model.ModelNotaFiscal;
import br.com.sistema.notas.fiscais.repository.NotaFiscalRepository;
import br.com.sistema.notas.fiscais.service.NotaFiscalService;
import org.springframework.stereotype.Service;

@Service
public class NotaFiscalServiceImpl implements NotaFiscalService {

    private NotaFiscalRepository notaFiscalRepository;

    public NotaFiscalServiceImpl(NotaFiscalRepository notaFiscalRepository) {
        this.notaFiscalRepository = notaFiscalRepository;
    }

    @Override
    public ModelNotaFiscal save(ModelNotaFiscal modelNotaFiscal) {
        if (notaFiscalRepository.existsByUrl(modelNotaFiscal.getUrl())){
            throw new RegraNegocioException("Url já cadastrada");
        }
        return notaFiscalRepository.save(modelNotaFiscal);
    }
}
