package com.github.danilogmoura.algafood.api.controller;

import com.github.danilogmoura.algafood.api.assembler.PermissaoModelAssembler;
import com.github.danilogmoura.algafood.api.model.PermissaoModel;
import com.github.danilogmoura.algafood.domain.repository.PermissaoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private PermissaoModelAssembler permissaoModelAssembler;

    @GetMapping
    public List<PermissaoModel> listar() {
        return permissaoModelAssembler.toCollectionModel(permissaoRepository.findAll());
    }
}
