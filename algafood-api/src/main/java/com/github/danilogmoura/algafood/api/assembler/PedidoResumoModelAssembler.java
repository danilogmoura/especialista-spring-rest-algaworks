package com.github.danilogmoura.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.github.danilogmoura.algafood.api.controller.PedidoController;
import com.github.danilogmoura.algafood.api.controller.RestauranteController;
import com.github.danilogmoura.algafood.api.controller.UsuarioController;
import com.github.danilogmoura.algafood.api.model.PedidoResumoModel;
import com.github.danilogmoura.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PedidoResumoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel> {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoResumoModelAssembler() {
        super(PedidoController.class, PedidoResumoModel.class);
    }

    public PedidoResumoModel toModel(Pedido pedido) {
        var pedidoResumoModel = createModelWithId(pedido.getId(), pedido);

        modelMapper.map(pedido, pedidoResumoModel);

        pedidoResumoModel.add(linkTo(PedidoController.class).withSelfRel());

        pedidoResumoModel.getRestaurante()
            .add(linkTo(methodOn(RestauranteController.class).buscar(pedido.getRestaurante().getId())).withSelfRel());

        pedidoResumoModel.getCliente()
            .add(linkTo(methodOn(UsuarioController.class).buscar(pedido.getCliente().getId())).withSelfRel());

        return pedidoResumoModel;
    }
}
