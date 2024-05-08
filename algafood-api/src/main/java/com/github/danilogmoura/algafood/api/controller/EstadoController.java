package com.github.danilogmoura.algafood.api.controller;

import com.github.danilogmoura.algafood.api.assembler.EstadoInputDisassembler;
import com.github.danilogmoura.algafood.api.assembler.EstadoModelAssembler;
import com.github.danilogmoura.algafood.api.model.EstadoModel;
import com.github.danilogmoura.algafood.api.model.input.EstadoInput;
import com.github.danilogmoura.algafood.domain.repository.EstadoRepository;
import com.github.danilogmoura.algafood.domain.service.EstadoService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private EstadoModelAssembler estadoModelAssembler;

    @Autowired
    private EstadoInputDisassembler estadoInputDisassembler;

    @GetMapping
    public List<EstadoModel> listar() {
        return estadoModelAssembler.toCollectionModel(estadoRepository.findAll());
    }

    @GetMapping("/{id}")
    public EstadoModel buscar(@PathVariable Long id) {
        return estadoModelAssembler.toModel(estadoService.buscarOuFalhar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {
        var estado = estadoInputDisassembler.toDomainObject(estadoInput);
        return estadoModelAssembler.toModel(estadoService.salvar(estado));
    }

    @PutMapping("/{id}")
    public EstadoModel atualizar(@PathVariable Long id, @RequestBody @Valid EstadoInput estadoInput) {
        var estadoAtual = estadoService.buscarOuFalhar(id);
        estadoInputDisassembler.copyToCollectionModel(estadoInput, estadoAtual);
        return estadoModelAssembler.toModel(estadoService.salvar(estadoAtual));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        estadoService.remover(id);
    }
}
