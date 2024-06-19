package com.github.danilogmoura.algafood.api.v1.controller;

import com.github.danilogmoura.algafood.api.v1.assembler.FormaPagamentoInputDisassembler;
import com.github.danilogmoura.algafood.api.v1.assembler.FormaPagamentoModelAssembler;
import com.github.danilogmoura.algafood.api.v1.model.FormaPagamentoModel;
import com.github.danilogmoura.algafood.api.v1.model.input.FormaPagamentoInput;
import com.github.danilogmoura.algafood.api.v1.openapi.controller.FormaPagamentoControllerOpenApi;
import com.github.danilogmoura.algafood.domain.repository.FormaPagamentoRepository;
import com.github.danilogmoura.algafood.domain.service.FormaPagamentoService;
import java.util.concurrent.TimeUnit;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@RestController
@RequestMapping(path = "/formas-pagamento")
public class FormaPagamentoController implements FormaPagamentoControllerOpenApi {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @Autowired
    private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

    @Autowired
    private FormaPagamentoInputDisassembler formaPagamentoInputDisassembler;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<FormaPagamentoModel>> listar(ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        var dataUltimaAtualizacao = formaPagamentoRepository.getDataUltimaAtualizacao();

        if (dataUltimaAtualizacao != null) {
            eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
        }

        if (request.checkNotModified(eTag)) {
            return null;
        }

        var formasPagamentoModel = formaPagamentoModelAssembler.toCollectionModel(formaPagamentoRepository.findAll());

        return ResponseEntity.ok()
            .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
            .eTag(eTag)
            .body(formasPagamentoModel);
    }

    @GetMapping(path = "/{formaPagamentoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FormaPagamentoModel> buscar(@PathVariable Long formaPagamentoId, ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        var eTag = "0";

        var dataUltimaAtualizacao = formaPagamentoRepository.getDataUltimaAtualizacaoById(formaPagamentoId);

        if (dataUltimaAtualizacao != null) {
            eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
        }

        if (request.checkNotModified(eTag)) {
            return null;
        }

        var formaPagamentoModel = formaPagamentoModelAssembler.toModel(formaPagamentoService.buscarOuFalhar(
            formaPagamentoId));

        return ResponseEntity.ok()
            .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
            .eTag(eTag)
            .body(formaPagamentoModel);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoModel adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        var formaPagamento = formaPagamentoInputDisassembler.toDomainObject(formaPagamentoInput);
        return formaPagamentoModelAssembler.toModel(formaPagamentoService.salvar(formaPagamento));
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FormaPagamentoModel atualizar(@PathVariable Long id,
        @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        var formaPagamentoAtual = formaPagamentoService.buscarOuFalhar(id);
        formaPagamentoInputDisassembler.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);
        return formaPagamentoModelAssembler.toModel(formaPagamentoService.salvar(formaPagamentoAtual));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        formaPagamentoService.remover(id);
    }
}
