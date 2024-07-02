package com.github.danilogmoura.algafood.api.v1.assembler;

import com.github.danilogmoura.algafood.api.v1.AlgaLinks;
import com.github.danilogmoura.algafood.api.v1.controller.RestauranteProdutoController;
import com.github.danilogmoura.algafood.api.v1.model.ProdutoModel;
import com.github.danilogmoura.algafood.core.security.AlgaSecurity;
import com.github.danilogmoura.algafood.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ProdutoModelAssembler extends RepresentationModelAssemblerSupport<Produto, ProdutoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    public ProdutoModelAssembler() {
        super(RestauranteProdutoController.class, ProdutoModel.class);
    }

    @Override
    public ProdutoModel toModel(Produto produto) {
        var restauranteId = produto.getRestaurante().getId();

        var produtoModel = createModelWithId(produto.getId(), produto, restauranteId);

        modelMapper.map(produto, produtoModel);

        if (algaSecurity.podeConsultarRestaurantes()) {
            produtoModel.add(algaLinks.linkToProdutos(restauranteId, "produtos"));

            produtoModel.add(algaLinks.linkToFotoProduto(produto.getId(), restauranteId, "foto"));
        }

        return produtoModel;
    }
}
