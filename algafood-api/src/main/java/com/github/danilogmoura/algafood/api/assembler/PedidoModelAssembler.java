package com.github.danilogmoura.algafood.api.assembler;

import com.github.danilogmoura.algafood.api.model.PedidoModel;
import com.github.danilogmoura.algafood.domain.model.Pedido;
import java.util.Collection;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoModel toModel(Pedido pedido) {
        return modelMapper.map(pedido, PedidoModel.class);
    }

    public List<PedidoModel> toCollectionModel(Collection<Pedido> pedidos) {
        return pedidos.stream().map(this::toModel).toList();
    }
}
