package com.github.danilogmoura.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.github.danilogmoura.algafood.api.controller.CidadeController;
import com.github.danilogmoura.algafood.api.controller.FormaPagamentoController;
import com.github.danilogmoura.algafood.api.controller.PedidoController;
import com.github.danilogmoura.algafood.api.controller.RestauranteProdutoController;
import com.github.danilogmoura.algafood.api.controller.UsuarioController;
import com.github.danilogmoura.algafood.api.model.PedidoModel;
import com.github.danilogmoura.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoModelAssembler() {
        super(PedidoController.class, PedidoModel.class);
    }

    @Override
    public PedidoModel toModel(Pedido pedido) {
        var pedidoModel = createModelWithId(pedido.getId(), pedido);

        modelMapper.map(pedido, pedidoModel);

        pedidoModel.add(linkTo(PedidoController.class).withRel("pedidos"));

        TemplateVariables pageVariables = new TemplateVariables(
            new TemplateVariable("page", VariableType.REQUEST_PARAM),
            new TemplateVariable("size", VariableType.REQUEST_PARAM),
            new TemplateVariable("sort", VariableType.REQUEST_PARAM)
        );

        var pedidosUrl = linkTo(PedidoController.class).toUri().toString();

        pedidoModel.add(Link.of(UriTemplate.of(pedidosUrl, pageVariables), "pedidos"));

//        pedidoModel.getRestaurante()
//            .add(linkTo(methodOn(RestauranteController.class).buscar(pedido.getRestaurante().getId())).withSelfRel());

        pedidoModel.getCliente()
            .add(linkTo(methodOn(UsuarioController.class).buscar(pedido.getCliente().getId())).withSelfRel());

        pedidoModel.getFormaPagamento().add(linkTo(
            methodOn(FormaPagamentoController.class).buscar(pedido.getFormaPagamento().getId(), null)).withSelfRel());

        pedidoModel.getEnderecoEntrega().getCidade().add(linkTo(
            methodOn(CidadeController.class).buscar(pedido.getEnderecoEntrega().getCidade().getId())).withSelfRel());

        pedidoModel.getItens().forEach(item -> item.add(linkTo(methodOn(RestauranteProdutoController.class)
            .buscar(pedido.getRestaurante().getId(), item.getId())).withRel("produto"))
        );

        return pedidoModel;
    }

    @Override
    public CollectionModel<PedidoModel> toCollectionModel(Iterable<? extends Pedido> entities) {
        return super.toCollectionModel(entities)
            .add(linkTo(PedidoController.class).withSelfRel());
    }
}
