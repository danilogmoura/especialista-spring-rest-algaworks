package com.github.danilogmoura.algafood.api.v1.controller;

import com.github.danilogmoura.algafood.api.v1.assembler.EstadoInputDisassembler;
import com.github.danilogmoura.algafood.api.v1.assembler.EstadoModelAssembler;
import com.github.danilogmoura.algafood.api.v1.model.EstadoModel;
import com.github.danilogmoura.algafood.api.v1.model.input.EstadoInput;
import com.github.danilogmoura.algafood.api.v1.openapi.controller.EstadoControllerOpenApi;
import com.github.danilogmoura.algafood.core.security.CheckSecurity;
import com.github.danilogmoura.algafood.domain.repository.EstadoRepository;
import com.github.danilogmoura.algafood.domain.service.EstadoService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/estados")
public class EstadoController implements EstadoControllerOpenApi {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private EstadoModelAssembler estadoModelAssembler;

    @Autowired
    private EstadoInputDisassembler estadoInputDisassembler;

    @CheckSecurity.Estados.PodeConsultar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<EstadoModel> listar() {
        return estadoModelAssembler.toCollectionModel(estadoRepository.findAll());
    }

    @CheckSecurity.Estados.PodeConsultar
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EstadoModel buscar(@PathVariable Long id) {
        return estadoModelAssembler.toModel(estadoService.buscarOuFalhar(id));
    }

    @CheckSecurity.Estados.PodeEditar
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {
        var estado = estadoInputDisassembler.toDomainObject(estadoInput);
        return estadoModelAssembler.toModel(estadoService.salvar(estado));
    }

    @CheckSecurity.Estados.PodeEditar
    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EstadoModel atualizar(@PathVariable Long id, @RequestBody @Valid EstadoInput estadoInput) {
        var estadoAtual = estadoService.buscarOuFalhar(id);
        estadoInputDisassembler.copyToCollectionModel(estadoInput, estadoAtual);
        return estadoModelAssembler.toModel(estadoService.salvar(estadoAtual));
    }

    @CheckSecurity.Estados.PodeEditar
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        estadoService.remover(id);
    }
}
