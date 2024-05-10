package com.github.danilogmoura.algafood.api.assembler;

import com.github.danilogmoura.algafood.api.model.PermissaoModel;
import com.github.danilogmoura.algafood.domain.model.Permissao;
import java.util.Collection;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PermissaoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PermissaoModel toModel(Permissao permissao) {
        return modelMapper.map(permissao, PermissaoModel.class);
    }

    public List<PermissaoModel> toCollectionModel(Collection<Permissao> permissoes) {
        return permissoes.stream().map(this::toModel).toList();
    }
}
