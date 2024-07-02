package com.github.danilogmoura.algafood.api.v1;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.github.danilogmoura.algafood.api.v1.controller.CidadeController;
import com.github.danilogmoura.algafood.api.v1.controller.CozinhaController;
import com.github.danilogmoura.algafood.api.v1.controller.EstadoController;
import com.github.danilogmoura.algafood.api.v1.controller.EstatisticaController;
import com.github.danilogmoura.algafood.api.v1.controller.FluxoPedidosController;
import com.github.danilogmoura.algafood.api.v1.controller.FormaPagamentoController;
import com.github.danilogmoura.algafood.api.v1.controller.GrupoController;
import com.github.danilogmoura.algafood.api.v1.controller.GrupoPermissaoController;
import com.github.danilogmoura.algafood.api.v1.controller.PedidoController;
import com.github.danilogmoura.algafood.api.v1.controller.PermissoesController;
import com.github.danilogmoura.algafood.api.v1.controller.RestauranteController;
import com.github.danilogmoura.algafood.api.v1.controller.RestauranteFormaPagamentoController;
import com.github.danilogmoura.algafood.api.v1.controller.RestauranteProdutoController;
import com.github.danilogmoura.algafood.api.v1.controller.RestauranteProdutoFotoController;
import com.github.danilogmoura.algafood.api.v1.controller.RestauranteUsuarioResponsavelController;
import com.github.danilogmoura.algafood.api.v1.controller.UsuarioController;
import com.github.danilogmoura.algafood.api.v1.controller.UsuarioGrupoController;
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
        var filtroVariables = new TemplateVariables(new TemplateVariable("clienteId", VariableType.REQUEST_PARAM),
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
        return linkTo(methodOn(RestauranteController.class).abrir(restauranteId)).withRel(rel);
    }

    public Link linkToRestauranteFechamento(Long restauranteId, String rel) {
        return linkTo(methodOn(RestauranteController.class).fechar(restauranteId)).withRel(rel);
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

    public Link linkToRestauranteFormaPagamento(Long restauranteId) {
        return linkToRestauranteFormaPagamento(restauranteId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToRestauranteFormaPagamento(Long restauranteId, String rel) {
        return linkTo(methodOn(RestauranteFormaPagamentoController.class).listar(restauranteId)).withRel(rel);
    }

    public Link linkToRestauranteFormaPagamentoDesassociacao(Long restauranteId, Long formPagamentoId, String rel) {
        return linkTo(
            methodOn(RestauranteFormaPagamentoController.class).desassociar(restauranteId, formPagamentoId)).withRel(
            rel);
    }

    public Link linkToRestauranteFormaPagamentoAssociacao(Long restauranteId, String rel) {
        return linkTo(methodOn(RestauranteFormaPagamentoController.class).associar(restauranteId, null)).withRel(rel);
    }

    public Link linkToRestauranteProduto(Long restauranteId, Long produtoId) {
        return linkToRestauranteProduto(restauranteId, produtoId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToRestauranteProduto(Long restauranteId, Long produtoId, String rel) {
        return linkTo(methodOn(RestauranteProdutoController.class).buscar(restauranteId, produtoId)).withRel(rel);
    }

    public Link linkToRestauranteProdutos(Long restauranteId) {
        return linkToRestauranteProdutos(restauranteId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToRestauranteProdutos(Long restauranteId, String rel) {
        return linkTo(methodOn(RestauranteProdutoController.class).listar(restauranteId, null)).withRel(rel);
    }

    public Link linkToRestauranteProdutosFoto(Long restauranteId, Long produtoId) {
        return linkToRestauranteProdutosFoto(restauranteId, produtoId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToRestauranteProdutosFoto(Long restauranteId, Long produtoId, String rel) {
        return linkTo(methodOn(RestauranteProdutoFotoController.class).buscar(restauranteId, produtoId)).withRel(rel);
    }

    public Link linkToResponsaveisRestaurante(Long restauranteId) {
        return linkToResponsaveisRestaurante(restauranteId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToResponsaveisRestaurante(Long restauranteId, String rel) {
        return linkTo(methodOn(RestauranteUsuarioResponsavelController.class).listar(restauranteId)).withRel(rel);
    }

    public Link linkToResponsaveisRestauranteAssociar(Long restauranteId, String rel) {
        return linkTo(methodOn(RestauranteUsuarioResponsavelController.class).associar(restauranteId, null)).withRel(
            rel);
    }

    public Link linkToResponsaveisRestauranteDesassociar(Long restauranteId, Long usuarioId, String rel) {
        return linkTo(
            methodOn(RestauranteUsuarioResponsavelController.class).desassociar(restauranteId, usuarioId)).withRel(rel);
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

    public Link linkToGrupos() {
        return linkToGrupos(IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToGrupos(String rel) {
        return linkTo(GrupoController.class).withRel(rel);
    }

    public Link linkToFormasPagamento() {
        return linkToFormasPagamento(IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToFormasPagamento(String rel) {
        return linkTo(FormaPagamentoController.class).withRel(rel);
    }

    public Link linkToFormaPagamento(Long formaPagamentoId) {
        return linkToFormaPagamento(formaPagamentoId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToFormaPagamento(Long formaPagamentoId, String rel) {
        return linkTo(methodOn(FormaPagamentoController.class).buscar(formaPagamentoId, null)).withRel(rel);
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

    public Link linkToEstados() {
        return linkToEstados(IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToEstados(String rel) {
        return linkTo(EstadoController.class).withRel(rel);
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

    public Link linkToGrupoPermissoes(Long grupoId) {
        return linkToGrupoPermissoes(grupoId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToGrupoPermissoes(Long grupoId, String rel) {
        return linkTo(methodOn(GrupoPermissaoController.class).listar(grupoId)).withRel(rel);
    }

    public Link linkToPermissoes() {
        return linkToPermissoes(IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToPermissoes(String rel) {
        return linkTo(PermissoesController.class).withRel(rel);
    }

    public Link linkToGrupoPermissaoAssociar(Long grupoId, String rel) {
        return linkTo(methodOn(GrupoPermissaoController.class).desassociar(grupoId, null)).withRel(rel);
    }

    public Link linkToGrupoPermissaoDesassociar(Long grupoId, Long permissaoId, String rel) {
        return linkTo(methodOn(GrupoPermissaoController.class).desassociar(grupoId, permissaoId)).withRel(rel);
    }

    public Link linkToUsuarioGrupoAssociar(Long usuarioId, String rel) {
        return linkTo(methodOn(UsuarioGrupoController.class).desassociar(usuarioId, null)).withRel(rel);
    }

    public Link linkToUsuarioGrupoDesassociar(Long usuarioId, Long grupoId, String rel) {
        return linkTo(methodOn(UsuarioGrupoController.class).desassociar(usuarioId, grupoId)).withRel(rel);
    }

    public Link linkToEstatisticas() {
        return linkToEstatisticas(IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToEstatisticas(String rel) {
        return linkTo(EstatisticaController.class).withRel(rel);
    }

    public Link linkToEstatisticasVendasDiarias(String rel) {
        var filtroVariables = new TemplateVariables(new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
            new TemplateVariable("timeOffset", VariableType.REQUEST_PARAM),
            new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
            new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM));

        var estatisticaVendasDiariasUrl = linkTo(methodOn(EstatisticaController.class)
            .consultarVendasDiarias(null, null)).toUri().toString();

        return Link.of(UriTemplate.of(estatisticaVendasDiariasUrl, filtroVariables), rel);
    }
}
