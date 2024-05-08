package com.github.danilogmoura.algafood.domain.service;

import com.github.danilogmoura.algafood.domain.exception.EntidadeEmUsoException;
import com.github.danilogmoura.algafood.domain.exception.EstadoNaoEcontradoException;
import com.github.danilogmoura.algafood.domain.model.Estado;
import com.github.danilogmoura.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstadoService {

    private static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removido, pois está em uso";

    @Autowired
    private EstadoRepository estadoRepository;

    @Transactional
    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    @Transactional
    public void remover(Long id) {
        try {
            estadoRepository.deleteById(id);
            estadoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EstadoNaoEcontradoException(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO, id));
        }
    }

    public Estado buscarOuFalhar(Long id) {
        return estadoRepository.findById(id)
            .orElseThrow(() -> new EstadoNaoEcontradoException(id));
    }
}
