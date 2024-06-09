package com.github.danilogmoura.algafood.api.controller;

import com.github.danilogmoura.algafood.api.assembler.GrupoInputDisassembler;
import com.github.danilogmoura.algafood.api.assembler.GrupoModeAssembler;
import com.github.danilogmoura.algafood.api.model.GrupoModel;
import com.github.danilogmoura.algafood.api.model.input.GrupoInput;
import com.github.danilogmoura.algafood.api.openapi.controller.GrupoControllerOpenApi;
import com.github.danilogmoura.algafood.domain.repository.GrupoRepository;
import com.github.danilogmoura.algafood.domain.service.GrupoService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(path = "/grupos")
public class GrupoController implements GrupoControllerOpenApi {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private GrupoModeAssembler grupoModeAssembler;

    @Autowired
    private GrupoInputDisassembler grupoInputDisassembler;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GrupoModel> listar() {
        return grupoModeAssembler.toCollectionModel(grupoRepository.findAll());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GrupoModel buscar(@PathVariable Long id) {
        return grupoModeAssembler.toModel(grupoService.buscarOuFalhar(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoModel adicionar(@RequestBody @Valid GrupoInput grupoInput) {
        var grupo = grupoInputDisassembler.toDomainObject(grupoInput);
        return grupoModeAssembler.toModel(grupoService.salvar(grupo));
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GrupoModel atualizar(@PathVariable Long id, @RequestBody @Valid GrupoInput grupoInput) {
        var grupoAtual = grupoService.buscarOuFalhar(id);
        grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtual);
        return grupoModeAssembler.toModel(grupoAtual);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        grupoService.remover(id);
    }
}
