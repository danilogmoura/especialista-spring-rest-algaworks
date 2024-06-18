package com.github.danilogmoura.algafood.api.controller;

import com.github.danilogmoura.algafood.api.assembler.PermissaoModelAssembler;
import com.github.danilogmoura.algafood.api.model.PermissaoModel;
import com.github.danilogmoura.algafood.api.openapi.controller.PermissoesControllerOpenApi;
import com.github.danilogmoura.algafood.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/permissoes")
public class PermissoesController implements PermissoesControllerOpenApi {

    @Autowired
    private PermissaoModelAssembler permissaoModelAssembler;

    @Autowired
    private PermissaoRepository permissaoRepository;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<PermissaoModel> listar() {
        return permissaoModelAssembler.toCollectionModel(permissaoRepository.findAll());
    }
}
