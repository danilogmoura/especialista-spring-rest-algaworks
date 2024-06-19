package com.github.danilogmoura.algafood.api.v1.controller;


import com.github.danilogmoura.algafood.api.v1.AlgaLinks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class RoorEntryPointControoler {

    @Autowired
    private AlgaLinks algaLinks;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public RoorEntryPointModel root() {
        var roorEntryPointModel = new RoorEntryPointModel();
        roorEntryPointModel.add(algaLinks.linkToCozinhas("cozinhas"));
        roorEntryPointModel.add(algaLinks.linkToPedidos("pedidos"));
        roorEntryPointModel.add(algaLinks.linkToRestaurantes("restaurantes"));
        roorEntryPointModel.add(algaLinks.linkToGrupos("grupos"));
        roorEntryPointModel.add(algaLinks.linkToUsuarios("usuarios"));
        roorEntryPointModel.add(algaLinks.linkToPermissoes("permissoes"));
        roorEntryPointModel.add(algaLinks.linkToFormasPagamento("formas-pagamento"));
        roorEntryPointModel.add(algaLinks.linkToEstados("estados"));
        roorEntryPointModel.add(algaLinks.linkToCidades("cidades"));
        roorEntryPointModel.add(algaLinks.linkToEstatisticas("estatisticas"));

        return roorEntryPointModel;
    }

    private static class RoorEntryPointModel extends RepresentationModel<RoorEntryPointModel> {

    }

}
