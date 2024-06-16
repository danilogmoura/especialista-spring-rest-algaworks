package com.github.danilogmoura.algafood.api.assembler;

import com.github.danilogmoura.algafood.api.AlgaLinks;
import com.github.danilogmoura.algafood.api.controller.RestauranteController;
import com.github.danilogmoura.algafood.api.model.RestauranteModel;
import com.github.danilogmoura.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestauranteModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public RestauranteModelAssembler() {
        super(RestauranteController.class, RestauranteModel.class);
    }

    @Override
    public RestauranteModel toModel(Restaurante restaurante) {
        var restauranteModel = createModelWithId(restaurante.getId(), restaurante);

        modelMapper.map(restaurante, restauranteModel);

        restauranteModel.add(algaLinks.linkToRestaurantes("restaurantes"));

        restauranteModel.getCozinha().add(algaLinks.linkToCozinha(restaurante.getCozinha().getId()));

        restauranteModel.getEndereco().getCidade()
            .add(algaLinks.linkToCidade(restaurante.getEndereco().getCidade().getId()));

        restauranteModel.add(algaLinks.linkToRestauranteFormaPagamento(restaurante.getId(), "formas-pagamento"));

        restauranteModel.add(algaLinks.linkToResponsaveisRestaurante(restaurante.getId(), "responsaveis"));

        if (restaurante.getAtivo()) {
            restauranteModel.add(algaLinks.linkToRestauranteInativacao(restaurante.getId(), "inativar"));
        } else {
            restauranteModel.add(algaLinks.linkToRestauranteAtivacao(restaurante.getId(), "ativar"));
        }

        if (restaurante.getAberto()) {
            restauranteModel.add(algaLinks.linkToRestauranteFechamento(restaurante.getId(), "fechar"));
        } else {
            restauranteModel.add(algaLinks.linkToRestauranteAbertura(restaurante.getId(), "abrir"));
        }

        return restauranteModel;
    }

    @Override
    public CollectionModel<RestauranteModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
        return super.toCollectionModel(entities).add(algaLinks.linkToRestaurantes());
    }
}
