package com.github.danilogmoura.algafood.api.assembler;

import com.github.danilogmoura.algafood.api.model.CidadeModel;
import com.github.danilogmoura.algafood.domain.model.Cidade;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CidadeModel toModel(Cidade cidade) {
        return modelMapper.map(cidade, CidadeModel.class);
    }

    public List<CidadeModel> toCollectionModel(List<Cidade> cidades) {
        return cidades.stream().map(this::toModel).toList();
    }
}
