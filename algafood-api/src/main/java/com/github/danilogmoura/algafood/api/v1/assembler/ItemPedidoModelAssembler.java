package com.github.danilogmoura.algafood.api.v1.assembler;

import com.github.danilogmoura.algafood.api.v1.model.ItemPedidoModel;
import com.github.danilogmoura.algafood.domain.model.ItemPedido;
import java.util.Collection;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemPedidoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ItemPedidoModel toModel(ItemPedido itemPedido) {
        return modelMapper.map(itemPedido, ItemPedidoModel.class);
    }

    public List<ItemPedidoModel> toCollectionModel(Collection<ItemPedido> itensPedidos) {
        return itensPedidos.stream().map(this::toModel).toList();
    }
}
