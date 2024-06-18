package com.github.danilogmoura.algafood.api.openapi.controller;

import com.github.danilogmoura.algafood.api.model.PermissaoModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.CollectionModel;


@Api(tags = "Permissões")
public interface PermissoesControllerOpenApi {

    @ApiOperation(value = "Lista as permissões")
    CollectionModel<PermissaoModel> listar();
}
