package com.github.danilogmoura.algafood.api.v1.controller;

import com.github.danilogmoura.algafood.api.v1.AlgaLinks;
import com.github.danilogmoura.algafood.api.v1.assembler.PermissaoModelAssembler;
import com.github.danilogmoura.algafood.api.v1.model.PermissaoModel;
import com.github.danilogmoura.algafood.api.v1.openapi.controller.GrupoPermissaoControllerOpenApi;
import com.github.danilogmoura.algafood.domain.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {


    @Autowired
    private GrupoService grupoService;

    @Autowired
    private PermissaoModelAssembler permissaoModelAssembler;

    @Autowired
    private AlgaLinks algaLinks;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<PermissaoModel> listar(@PathVariable Long grupoId) {
        var grupo = grupoService.buscarOuFalhar(grupoId);

        var permissoesModel = permissaoModelAssembler.toCollectionModel(grupo.getPermissoes());

        permissoesModel.forEach(permissaoModel -> permissaoModel
            .add(algaLinks.linkToGrupoPermissaoDesassociar(grupoId, permissaoModel.getId(), "desassociar")));

        return permissoesModel
            .removeLinks()
            .add(algaLinks.linkToGrupoPermissoes(grupoId))
            .add(algaLinks.linkToGrupoPermissaoAssociar(grupoId, "associar"));
    }

    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        grupoService.associarPermissao(grupoId, permissaoId);
    }

    @DeleteMapping("/{permissaoId}")
    public ResponseEntity<Void> desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        grupoService.desassociarPermissao(grupoId, permissaoId);

        return ResponseEntity.noContent().build();
    }
}
