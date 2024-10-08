package com.github.danilogmoura.algafood.api.v1.controller;

import com.github.danilogmoura.algafood.api.v1.AlgaLinks;
import com.github.danilogmoura.algafood.api.v1.assembler.UsuarioModelAssembler;
import com.github.danilogmoura.algafood.api.v1.model.UsuarioModel;
import com.github.danilogmoura.algafood.api.v1.openapi.controller.RestauranteUsuarioResponsavelControllerOpenApi;
import com.github.danilogmoura.algafood.core.security.AlgaSecurity;
import com.github.danilogmoura.algafood.core.security.CheckSecurity;
import com.github.danilogmoura.algafood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/restaurantes/{restauranteId}/responsaveis")
public class RestauranteUsuarioResponsavelController implements RestauranteUsuarioResponsavelControllerOpenApi {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<UsuarioModel> listar(@PathVariable Long restauranteId) {
        var restaurante = restauranteService.buscarOuFalhar(restauranteId);

        CollectionModel<UsuarioModel> usuariosModel = usuarioModelAssembler
            .toCollectionModel(restaurante.getResponsaveis())
            .removeLinks();

        usuariosModel.add(algaLinks.linkToRestauranteResponsaveis(restauranteId));

        if (algaSecurity.podeGerenciarCadastroRestaurantes()) {
            usuariosModel.add(algaLinks.linkToRestauranteResponsavelAssociacao(restauranteId, "associar"));

            usuariosModel.getContent().stream().forEach(usuarioModel -> {
                usuarioModel.add(algaLinks.linkToRestauranteResponsavelDesassociacao(
                    restauranteId, usuarioModel.getId(), "desassociar"));
            });
        }

        return usuariosModel;
    }

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @Override
    @PutMapping("/{usuarioId}")
    public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        restauranteService.associarResponsavel(restauranteId, usuarioId);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @Override
    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        restauranteService.desassociarResponsavel(restauranteId, usuarioId);

        return ResponseEntity.noContent().build();
    }
}
