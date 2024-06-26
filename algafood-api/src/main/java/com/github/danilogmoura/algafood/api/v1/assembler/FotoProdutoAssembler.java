package com.github.danilogmoura.algafood.api.v1.assembler;

import com.github.danilogmoura.algafood.api.v1.AlgaLinks;
import com.github.danilogmoura.algafood.api.v1.controller.RestauranteProdutoFotoController;
import com.github.danilogmoura.algafood.api.v1.model.FotoProdutoModel;
import com.github.danilogmoura.algafood.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class FotoProdutoAssembler extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public FotoProdutoAssembler() {
        super(RestauranteProdutoFotoController.class, FotoProdutoModel.class);
    }

    @Override
    public FotoProdutoModel toModel(FotoProduto fotoProduto) {
        var fotoProdutoModel = modelMapper.map(fotoProduto, FotoProdutoModel.class);

        fotoProdutoModel.add(algaLinks.linkToRestauranteProdutosFoto(
            fotoProduto.getRestauranteId(), fotoProduto.getId()));

        fotoProdutoModel.add(
            algaLinks.linkToRestauranteProduto(fotoProduto.getRestauranteId(), fotoProduto.getId(), "produto"));

        return fotoProdutoModel;
    }
}
