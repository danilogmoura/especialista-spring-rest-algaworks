package com.github.danilogmoura.algafood.api.assembler;

import com.github.danilogmoura.algafood.api.model.ProdutoModel;
import com.github.danilogmoura.algafood.domain.model.Produto;
import java.util.Collection;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ProdutoModel toModel(Produto produto) {
        return modelMapper.map(produto, ProdutoModel.class);
    }

    public Collection<ProdutoModel> toCollectionModel(Collection<Produto> produtos) {
        return produtos.stream().map(this::toModel).toList();
    }
}
