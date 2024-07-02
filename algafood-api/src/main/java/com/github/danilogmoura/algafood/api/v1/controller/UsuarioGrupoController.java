package com.github.danilogmoura.algafood.api.v1.controller;

import com.github.danilogmoura.algafood.api.v1.AlgaLinks;
import com.github.danilogmoura.algafood.api.v1.assembler.GrupoModeAssembler;
import com.github.danilogmoura.algafood.api.v1.model.GrupoModel;
import com.github.danilogmoura.algafood.api.v1.openapi.controller.UsuarioGrupoControllerOpenApi;
import com.github.danilogmoura.algafood.core.security.CheckSecurity;
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
@RequestMapping("/v1/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private GrupoModeAssembler grupoModeAssembler;

    @Autowired
    private AlgaLinks algaLinks;


    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
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


    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @PutMapping("/{grupoId}")
    public ResponseEntity<Void> associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.adicionarGrupo(usuarioId, grupoId);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @DeleteMapping("/{grupoId}")
    public ResponseEntity<Void> desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.removerGrupo(usuarioId, grupoId);

        return ResponseEntity.noContent().build();
    }
}
