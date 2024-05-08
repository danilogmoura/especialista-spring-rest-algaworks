package com.github.danilogmoura.algafood.api.assembler;

import com.github.danilogmoura.algafood.api.model.CozinhaModel;
import com.github.danilogmoura.algafood.domain.model.Cozinha;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CozinhaModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CozinhaModel toModel(Cozinha cozinha) {
        return modelMapper.map(cozinha, CozinhaModel.class);
    }

    public List<CozinhaModel> toCollectionModel(List<Cozinha> cozinhas) {
        return cozinhas.stream().map(this::toModel).toList();
    }
}
