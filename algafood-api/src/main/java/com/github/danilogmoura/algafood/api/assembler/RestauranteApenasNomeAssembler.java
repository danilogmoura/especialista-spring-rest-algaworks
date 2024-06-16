package com.github.danilogmoura.algafood.api.assembler;

import com.github.danilogmoura.algafood.api.AlgaLinks;
import com.github.danilogmoura.algafood.api.controller.RestauranteController;
import com.github.danilogmoura.algafood.api.model.RestauranteApenasNomeModel;
import com.github.danilogmoura.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestauranteApenasNomeAssembler extends
    RepresentationModelAssemblerSupport<Restaurante, RestauranteApenasNomeModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public RestauranteApenasNomeAssembler() {
        super(RestauranteController.class, RestauranteApenasNomeModel.class);
    }

    public RestauranteApenasNomeModel toModel(Restaurante restaurante) {
        var restauranteModel = createModelWithId(restaurante.getId(), restaurante);

        modelMapper.map(restaurante, restauranteModel);

        restauranteModel.add(algaLinks.linkToRestaurantes("restaurantes"));

        restauranteModel.add(algaLinks.linkToRestauranteFormaPagamento(restaurante.getId(), "formas-pagamento"));

        return restauranteModel;
    }

    @Override
    public CollectionModel<RestauranteApenasNomeModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
        return super.toCollectionModel(entities).add(algaLinks.linkToRestaurantes());
    }
}
