package com.github.danilogmoura.algafood.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.github.danilogmoura.algafood.api.controller.CidadeController;
import com.github.danilogmoura.algafood.api.controller.CozinhaController;
import com.github.danilogmoura.algafood.api.controller.EstadoController;
import com.github.danilogmoura.algafood.api.controller.FluxoPedidosController;
import com.github.danilogmoura.algafood.api.controller.FormaPagamentoController;
import com.github.danilogmoura.algafood.api.controller.PedidoController;
import com.github.danilogmoura.algafood.api.controller.RestauranteController;
import com.github.danilogmoura.algafood.api.controller.RestauranteFormaPagamentoController;
import com.github.danilogmoura.algafood.api.controller.RestauranteProdutoController;
import com.github.danilogmoura.algafood.api.controller.RestauranteUsuarioResponsavelController;
import com.github.danilogmoura.algafood.api.controller.UsuarioController;
import com.github.danilogmoura.algafood.api.controller.UsuarioGrupoController;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

@Component
public class AlgaLinks {

    public static final TemplateVariables PAGE_VARIABLES = new TemplateVariables(
        new TemplateVariable("page", VariableType.REQUEST_PARAM),
        new TemplateVariable("size", VariableType.REQUEST_PARAM),
        new TemplateVariable("sort", VariableType.REQUEST_PARAM));

    public Link linkToPedidos() {
        return linkToPedidos(IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToPedidos(String rel) {
        var filtroVariables = new TemplateVariables(
            new TemplateVariable("clienteId", VariableType.REQUEST_PARAM),
            new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
            new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
            new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM));

        var pedidosUrl = linkTo(PedidoController.class).toUri().toString();

        return Link.of(UriTemplate.of(pedidosUrl, PAGE_VARIABLES.concat(filtroVariables)), rel);
    }

    public Link linkToRestauranteAtivacao(Long restauranteId, String rel) {
        return linkTo(methodOn(RestauranteController.class).ativar(restauranteId)).withRel(rel);
    }

    public Link linkToRestauranteInativacao(Long restauranteId, String rel) {
        return linkTo(methodOn(RestauranteController.class).inativar(restauranteId)).withRel(rel);
    }

    public Link linkToRestauranteAbertura(Long restauranteId, String rel) {
        return linkTo(methodOn(RestauranteController.class).abertura(restauranteId)).withRel(rel);
    }

    public Link linkToRestauranteFechamento(Long restauranteId, String rel) {
        return linkTo(methodOn(RestauranteController.class).fechamento(restauranteId)).withRel(rel);
    }

    public Link linkToConfirmacaoPedido(String codigoPedido, String rel) {
        return linkTo(methodOn(FluxoPedidosController.class).confirmar(codigoPedido)).withRel(rel);
    }

    public Link linkToEntregaPedido(String codigoPedido, String rel) {
        return linkTo(methodOn(FluxoPedidosController.class).entregar(codigoPedido)).withRel(rel);
    }

    public Link linkToCancelamentoPedido(String codigoPedido, String rel) {
        return linkTo(methodOn(FluxoPedidosController.class).cancelar(codigoPedido)).withRel(rel);
    }

    public Link linkToPedido(String pedidoCodigo) {
        return linkToPedido(pedidoCodigo, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToPedido(String pedidoCodigo, String rel) {
        return linkTo(methodOn(PedidoController.class).buscar(pedidoCodigo)).withRel(rel);
    }

    public Link linkToRestaurantes() {
        return linkToRestaurantes(IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToRestaurantes(String rel) {
        var filtroVariables = new TemplateVariables(new TemplateVariable("apenas-nome", VariableType.REQUEST_PARAM));

        var pedidosUrl = linkTo(RestauranteController.class).toUri().toString();

        return Link.of(UriTemplate.of(pedidosUrl, filtroVariables), rel);
    }

    public Link linkToRestaurante(Long restauranteId) {
        return linkToRestaurante(restauranteId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToRestaurante(Long restauranteId, String rel) {
        return linkTo(methodOn(RestauranteController.class).buscar(restauranteId)).withRel(rel);
    }

    public Link linkToUsuarios() {
        return linkToUsuarios(IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToUsuarios(String rel) {
        return linkTo(UsuarioController.class).withRel(rel);
    }

    public Link linkToUsuario(Long usuarioId) {
        return linkToUsuario(usuarioId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToUsuario(Long usuarioId, String rel) {
        return linkTo(methodOn(UsuarioController.class).buscar(usuarioId)).withRel(rel);
    }

    public Link linkToUsuarioGrupos(Long usuarioId) {
        return linkToUsuarioGrupos(usuarioId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToUsuarioGrupos(Long usuarioId, String rel) {
        return linkTo(methodOn(UsuarioGrupoController.class).listar(usuarioId)).withRel(rel);
    }

    public Link linkToFormaPagamento(Long formaPagamentoId) {
        return linkToFormaPagamento(formaPagamentoId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToFormaPagamento(Long formaPagamentoId, String rel) {
        return linkTo(methodOn(FormaPagamentoController.class).buscar(formaPagamentoId, null)).withRel(rel);
    }

    public Link linkToRestauranteFormaPagamento(Long restauranteId) {
        return linkToRestauranteFormaPagamento(restauranteId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToRestauranteFormaPagamento(Long restauranteId, String rel) {
        return linkTo(methodOn(RestauranteFormaPagamentoController.class).listar(restauranteId)).withRel(rel);
    }

    public Link linkToCidades() {
        return linkToCidades(IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToCidades(String rel) {
        return linkTo(CidadeController.class).withRel(rel);
    }

    public Link linkToCidade(Long cidadeId) {
        return linkToCidade(cidadeId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToCidade(Long cidadeId, String rel) {
        return linkTo(methodOn(CidadeController.class).buscar(cidadeId)).withRel(rel);
    }

    public Link linkToRestauranteProduto(Long restauranteId, Long produtoId) {
        return linkToRestauranteProduto(restauranteId, produtoId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToRestauranteProduto(Long restauranteId, Long produtoId, String rel) {
        return linkTo(methodOn(RestauranteProdutoController.class).buscar(restauranteId, produtoId)).withRel(rel);
    }

    public Link linkToEstados() {
        return linkTo(CidadeController.class).withSelfRel();
    }

    public Link linkToEstado(Long estadoId) {
        return linkToEstado(estadoId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToEstado(Long estadoId, String rel) {
        return linkTo(methodOn(EstadoController.class).buscar(estadoId)).withRel(rel);
    }

    public Link linkToCozinhas() {
        return linkToCozinhas(IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToCozinhas(String rel) {
        return linkTo(CozinhaController.class).withRel(rel);
    }

    public Link linkToCozinha(Long cozinhaId) {
        return linkToCozinha(cozinhaId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToCozinha(Long cozinhaId, String rel) {
        return linkTo(methodOn(CozinhaController.class).buscar(cozinhaId)).withRel(rel);
    }

    public Link linkToResponsaveisRestaurante(Long restauranteId) {
        return linkToResponsaveisRestaurante(restauranteId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToResponsaveisRestaurante(Long restauranteId, String rel) {
        return linkTo(methodOn(RestauranteUsuarioResponsavelController.class).listar(restauranteId)).withRel(rel);
    }
}
