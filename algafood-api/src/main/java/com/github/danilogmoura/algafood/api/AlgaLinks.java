package com.github.danilogmoura.algafood.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import com.github.danilogmoura.algafood.api.controller.PedidoController;
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
        new TemplateVariable("sort", VariableType.REQUEST_PARAM)
    );

    public Link linkToPedidos() {
        var filtroVariables = new TemplateVariables(
            new TemplateVariable("clienteId", VariableType.REQUEST_PARAM),
            new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
            new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
            new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM)
        );

        var pedidosUrl = linkTo(PedidoController.class).toUri().toString();

        return Link.of(UriTemplate.of(pedidosUrl, PAGE_VARIABLES.concat(filtroVariables)), "pedidos");
    }
}
