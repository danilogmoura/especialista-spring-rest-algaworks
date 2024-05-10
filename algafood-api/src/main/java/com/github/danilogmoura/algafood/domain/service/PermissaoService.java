package com.github.danilogmoura.algafood.domain.service;

import com.github.danilogmoura.algafood.domain.exception.PermissaoNaoEncontradoException;
import com.github.danilogmoura.algafood.domain.model.Permissao;
import com.github.danilogmoura.algafood.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;


    public Permissao buscarOuFalhar(Long permissaoId) {
        return permissaoRepository.findById(permissaoId)
            .orElseThrow(() -> new PermissaoNaoEncontradoException(permissaoId));
    }
}
