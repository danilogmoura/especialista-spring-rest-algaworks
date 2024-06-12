package com.github.danilogmoura.algafood.api.controller;

import com.github.danilogmoura.algafood.api.assembler.UsuarioInputDisassembler;
import com.github.danilogmoura.algafood.api.assembler.UsuarioModelAssembler;
import com.github.danilogmoura.algafood.api.model.UsuarioModel;
import com.github.danilogmoura.algafood.api.model.input.SenhaInput;
import com.github.danilogmoura.algafood.api.model.input.UsuarioComSenhaInput;
import com.github.danilogmoura.algafood.api.model.input.UsuarioInput;
import com.github.danilogmoura.algafood.api.openapi.controller.UsuarioControllerOpenApi;
import com.github.danilogmoura.algafood.domain.repository.UsuarioRepository;
import com.github.danilogmoura.algafood.domain.service.UsuarioService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController implements UsuarioControllerOpenApi {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Autowired
    private UsuarioInputDisassembler usuarioInputDisassembler;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UsuarioModel> listar() {
        return usuarioModelAssembler.toCollectionModel(usuarioRepository.findAll());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioModel buscar(@PathVariable Long id) {
        var usuario = usuarioService.buscarOuFalhar(id);
        return usuarioModelAssembler.toModel(usuario);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModel adicionar(@RequestBody @Valid UsuarioComSenhaInput usuarioComSenhaInput) {
        var usuario = usuarioInputDisassembler.toDomainObject(usuarioComSenhaInput);
        return usuarioModelAssembler.toModel(usuarioService.salvar(usuario));
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioModel atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioInput usuarioInput) {
        var usuarioAtual = usuarioService.buscarOuFalhar(id);
        usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
        return usuarioModelAssembler.toModel(usuarioService.salvar(usuarioAtual));
    }

    @PutMapping("/{id}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarSenha(@PathVariable Long id, @RequestBody @Valid SenhaInput senha) {
        usuarioService.atualizarSenha(id, senha.getSenhaAtual(), senha.getNovaSenha());
    }
}
