package com.github.danilogmoura.algafood.domain.service;

import com.github.danilogmoura.algafood.domain.exception.EntidadeEmUsoException;
import com.github.danilogmoura.algafood.domain.exception.GrupoNaoEncontradoException;
import com.github.danilogmoura.algafood.domain.model.Grupo;
import com.github.danilogmoura.algafood.domain.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GrupoService {

    private static final String MSG_GRUPO_EM_USO = "Grupo de código %d não pode ser removido, pois está em uso";

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private PermissaoService permissaoService;

    public Grupo buscarOuFalhar(Long id) {
        return grupoRepository.findById(id).orElseThrow(() -> new GrupoNaoEncontradoException(id));
    }

    @Transactional
    public Grupo salvar(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    @Transactional
    public void remover(Long id) {
        try {
            grupoRepository.deleteById(id);
            grupoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new GrupoNaoEncontradoException(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_GRUPO_EM_USO, id));
        }
    }

    @Transactional
    public void associarPermissao(Long grupoId, Long permissaoId) {
        var grupo = buscarOuFalhar(grupoId);
        var permissao = permissaoService.buscarOuFalhar(permissaoId);
        grupo.adicionarPermissao(permissao);
    }

    @Transactional
    public void desassociarPermissao(Long grupoId, Long permissaoId) {
        var grupo = buscarOuFalhar(grupoId);
        var permissao = permissaoService.buscarOuFalhar(permissaoId);
        grupo.removerPermissao(permissao);
    }
}
