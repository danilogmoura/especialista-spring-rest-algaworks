package com.github.danilogmoura.algafood.domain.service;

import com.github.danilogmoura.algafood.domain.exception.EntidadeEmUsoException;
import com.github.danilogmoura.algafood.domain.exception.EntidadeNaoEcontradaException;
import com.github.danilogmoura.algafood.domain.model.Estado;
import com.github.danilogmoura.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public void remover(Long id) {
        try {
            estadoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEcontradaException(String.format("Estado com código %d não foi cadastrado", id));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                String.format("Estado de código %d não pode ser removida, pois está em uso", id));
        }
    }
}
