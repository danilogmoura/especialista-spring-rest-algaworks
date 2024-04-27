package com.github.danilogmoura.algafood.domain.service;

import com.github.danilogmoura.algafood.domain.exception.CidadeNaoEcontradaException;
import com.github.danilogmoura.algafood.domain.model.Cidade;
import com.github.danilogmoura.algafood.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoService estadoService;

    public Cidade salvar(Cidade cidade) {
        var estadoId = cidade.getEstado().getId();
        var estado = estadoService.buscarOuFalhar(estadoId);
        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);
    }

    public void remover(Long id) {
        try {
            cidadeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new CidadeNaoEcontradaException(id);
        }
    }

    public Cidade buscarOuFalhar(Long id) {
        return cidadeRepository.findById(id)
            .orElseThrow(() -> new CidadeNaoEcontradaException(id));
    }
}
