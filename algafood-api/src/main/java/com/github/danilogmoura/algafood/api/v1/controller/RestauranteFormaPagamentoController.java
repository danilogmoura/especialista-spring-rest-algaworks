package com.github.danilogmoura.algafood.api.v1.controller;

import com.github.danilogmoura.algafood.api.v1.AlgaLinks;
import com.github.danilogmoura.algafood.api.v1.assembler.FormaPagamentoModelAssembler;
import com.github.danilogmoura.algafood.api.v1.model.FormaPagamentoModel;
import com.github.danilogmoura.algafood.api.v1.openapi.controller.RestauranteFormaPagamentoControllerOpenApi;
import com.github.danilogmoura.algafood.core.security.AlgaSecurity;
import com.github.danilogmoura.algafood.core.security.CheckSecurity;
import com.github.danilogmoura.algafood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController implements RestauranteFormaPagamentoControllerOpenApi {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    @CheckSecurity.Restaurantes.PodeConsultar
    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<FormaPagamentoModel> listar(@PathVariable Long restauranteId) {
        var restaurante = restauranteService.buscarOuFalhar(restauranteId);

        CollectionModel<FormaPagamentoModel> formasPagamentoModel
            = formaPagamentoModelAssembler.toCollectionModel(restaurante.getFormasPagamento())
            .removeLinks();

        formasPagamentoModel.add(algaLinks.linkToRestauranteFormasPagamento(restauranteId));

        if (algaSecurity.podeGerenciarFuncionamentoRestaurantes(restauranteId)) {
            formasPagamentoModel.add(algaLinks.linkToRestauranteFormaPagamentoAssociacao(restauranteId, "associar"));

            formasPagamentoModel.getContent().forEach(formaPagamentoModel ->
                formaPagamentoModel.add(algaLinks.linkToRestauranteFormaPagamentoDesassociacao(
                    restauranteId, formaPagamentoModel.getId(), "desassociar")));
        }

        return formasPagamentoModel;
    }

    @CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
    @Override
    @DeleteMapping(path = "/{formaPagamentoId}")
    public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        restauranteService.desassociarFormaPagamento(restauranteId, formaPagamentoId);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
    @Override
    @PutMapping("/{formaPagamentoId}")
    public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        restauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);

        return ResponseEntity.noContent().build();
    }
}
