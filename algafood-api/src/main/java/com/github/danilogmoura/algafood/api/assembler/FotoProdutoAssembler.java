package com.github.danilogmoura.algafood.api.assembler;

import com.github.danilogmoura.algafood.api.model.FotoProdutoModel;
import com.github.danilogmoura.algafood.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FotoProdutoAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public FotoProdutoModel toModel(FotoProduto fotoProduto) {
        return modelMapper.map(fotoProduto, FotoProdutoModel.class);
    }
}
