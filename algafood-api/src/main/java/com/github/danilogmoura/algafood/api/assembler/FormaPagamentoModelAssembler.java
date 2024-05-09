package com.github.danilogmoura.algafood.api.assembler;

import com.github.danilogmoura.algafood.api.model.FormaPagamentoModel;
import com.github.danilogmoura.algafood.domain.model.FormaPagamento;
import java.util.Collection;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
        return modelMapper.map(formaPagamento, FormaPagamentoModel.class);
    }

    public List<FormaPagamentoModel> toCollectionModel(Collection<FormaPagamento> formaPagamentos) {
        return formaPagamentos.stream().map(this::toModel).toList();
    }
}
