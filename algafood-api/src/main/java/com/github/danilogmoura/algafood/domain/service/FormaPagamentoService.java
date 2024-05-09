package com.github.danilogmoura.algafood.domain.service;

import com.github.danilogmoura.algafood.domain.exception.EntidadeEmUsoException;
import com.github.danilogmoura.algafood.domain.exception.FormaPagamentoNaoEcontradoException;
import com.github.danilogmoura.algafood.domain.model.FormaPagamento;
import com.github.danilogmoura.algafood.domain.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FormaPagamentoService {

    private static final String MSG_FORMA_PAGAMENTO_EM_USO = "Forma de pagamento de código %d não pode ser removida, "
        + "pois está em uso";

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    public FormaPagamento buscarOuFalhar(Long id) {
        return formaPagamentoRepository.findById(id)
            .orElseThrow(() -> new FormaPagamentoNaoEcontradoException(id));
    }

    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return formaPagamentoRepository.save(formaPagamento);
    }

    @Transactional
    public void remover(Long id) {
        try {
            formaPagamentoRepository.deleteById(id);
            formaPagamentoRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_FORMA_PAGAMENTO_EM_USO, id));
        } catch (EmptyResultDataAccessException e) {
            throw new FormaPagamentoNaoEcontradoException(id);
        }
    }
}
