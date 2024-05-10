package com.github.danilogmoura.algafood.domain.service;

import com.github.danilogmoura.algafood.domain.exception.NegocioException;
import com.github.danilogmoura.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.github.danilogmoura.algafood.domain.model.Usuario;
import com.github.danilogmoura.algafood.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GrupoService grupoService;


    public Usuario buscarOuFalhar(Long id) {
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
        usuarioRepository.detach(usuario);

        var usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

        if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
            throw new NegocioException(String.format("Já existe um usuário cadastrado com o e-mail '%s'",
                usuario.getEmail()));
        }

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void atualizarSenha(Long id, String senhaAtual, String novaSenha) {
        var usuario = buscarOuFalhar(id);

        if (!usuario.getSenha().equals(senhaAtual)) {
            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
        }

        usuario.setSenha(novaSenha);
    }

    @Transactional
    public void remover(Long id) {
        try {
            usuarioRepository.deleteById(id);
            usuarioRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new UsuarioNaoEncontradoException(id);
        }
    }

    @Transactional
    public void adicionarGrupo(Long usuarioId, Long grupoId) {
        var usuario = buscarOuFalhar(usuarioId);
        var grupo = grupoService.buscarOuFalhar(grupoId);
        usuario.adicionarGrupo(grupo);
    }

    @Transactional
    public void removerGrupo(Long usuarioId, Long grupoId) {
        var usuario = buscarOuFalhar(usuarioId);
        var grupo = grupoService.buscarOuFalhar(grupoId);
        usuario.removerGrupo(grupo);
    }
}
