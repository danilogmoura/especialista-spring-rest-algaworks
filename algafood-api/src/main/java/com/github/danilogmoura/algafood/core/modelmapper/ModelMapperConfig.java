package com.github.danilogmoura.algafood.core.modelmapper;

import com.github.danilogmoura.algafood.api.v1.model.EnderecoModel;
import com.github.danilogmoura.algafood.api.v1.model.input.ItemPedidoInput;
import com.github.danilogmoura.algafood.domain.model.Endereco;
import com.github.danilogmoura.algafood.domain.model.ItemPedido;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(
            Endereco.class, EnderecoModel.class);

        enderecoToEnderecoModelTypeMap.<String>addMapping(
            enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
            (enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));

        modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
            .addMappings(mapper -> mapper.skip(ItemPedido::setId));

        return modelMapper;
    }
}
