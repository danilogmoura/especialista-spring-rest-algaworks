package com.github.danilogmoura.algafood.api.assembler;

import com.github.danilogmoura.algafood.api.model.GrupoModel;
import com.github.danilogmoura.algafood.domain.model.Grupo;
import java.util.Collection;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrupoModeAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public GrupoModel toModel(Grupo grupo) {
        return modelMapper.map(grupo, GrupoModel.class);
    }

    public List<GrupoModel> toCollectionModel(Collection<Grupo> grupos) {
        return grupos.stream().map(this::toModel).toList();
    }
}
