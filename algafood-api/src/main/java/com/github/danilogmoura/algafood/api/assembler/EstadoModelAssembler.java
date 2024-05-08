package com.github.danilogmoura.algafood.api.assembler;

import com.github.danilogmoura.algafood.api.model.EstadoModel;
import com.github.danilogmoura.algafood.domain.model.Estado;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstadoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public EstadoModel toModel(Estado estado) {
        return modelMapper.map(estado, EstadoModel.class);
    }

    public List<EstadoModel> toCollectionModel(List<Estado> estados) {
        return estados.stream().map(this::toModel).toList();
    }
}
