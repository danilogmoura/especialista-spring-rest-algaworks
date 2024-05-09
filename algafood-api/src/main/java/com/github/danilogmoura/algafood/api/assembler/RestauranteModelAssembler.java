package com.github.danilogmoura.algafood.api.assembler;

import com.github.danilogmoura.algafood.api.model.RestauranteModel;
import com.github.danilogmoura.algafood.domain.model.Restaurante;
import java.util.Collection;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public RestauranteModel toModel(Restaurante restaurante) {
        return modelMapper.map(restaurante, RestauranteModel.class);
    }

    public List<RestauranteModel> toCollectionModel(Collection<Restaurante> restaurantes) {
        return restaurantes.stream().map(this::toModel).toList();
    }
}
