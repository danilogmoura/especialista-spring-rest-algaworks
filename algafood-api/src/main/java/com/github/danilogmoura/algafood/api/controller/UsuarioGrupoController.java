package com.github.danilogmoura.algafood.api.controller;

import com.github.danilogmoura.algafood.api.AlgaLinks;
import com.github.danilogmoura.algafood.api.assembler.GrupoModeAssembler;
import com.github.danilogmoura.algafood.api.model.GrupoModel;
import com.github.danilogmoura.algafood.api.openapi.controller.UsuarioGrupoControllerOpenApi;
import com.github.danilogmoura.algafood.domain.service.UsuarioService;
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
@RequestMapping("/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private GrupoModeAssembler grupoModeAssembler;

    @Autowired
    private AlgaLinks algaLinks;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<GrupoModel> listar(@PathVariable Long usuarioId) {
        var usuario = usuarioService.buscarOuFalhar(usuarioId);

        var gruposModel = grupoModeAssembler.toCollectionModel(usuario.getGrupos());

        gruposModel.forEach(grupoModel -> grupoModel
            .add(algaLinks.linkToUsuarioGrupoDesassociar(usuarioId, grupoModel.getId(), "desassociar")));

        return gruposModel
            .removeLinks()
            .add(algaLinks.linkToUsuarioGrupos(usuarioId))
            .add(algaLinks.linkToUsuarioGrupoAssociar(usuarioId, "associar"));
    }


    @PutMapping("/{grupoId}")
    public ResponseEntity<Void> associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.adicionarGrupo(usuarioId, grupoId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{grupoId}")
    public ResponseEntity<Void> desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.removerGrupo(usuarioId, grupoId);

        return ResponseEntity.noContent().build();
    }
}
