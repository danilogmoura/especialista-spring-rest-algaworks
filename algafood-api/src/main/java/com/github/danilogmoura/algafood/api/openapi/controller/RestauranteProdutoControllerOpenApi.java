package com.github.danilogmoura.algafood.api.openapi.controller;

import com.github.danilogmoura.algafood.api.model.ProdutoModel;
import com.github.danilogmoura.algafood.api.model.input.ProdutoInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import java.util.Collection;


@Api(tags = "Produtos")
public interface RestauranteProdutoControllerOpenApi {

    Collection<ProdutoModel> listar(boolean incluirInativos,
        @ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId);

    ProdutoModel buscar(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,
        @ApiParam(value = "ID do produto", example = "1", required = true) Long produtoId);

    ProdutoModel adicionar(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,
        @ApiParam(name = "corpo", value = "Representação de um novo produto", required = true) ProdutoInput produtoInput);

    ProdutoModel atualizar(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,
        @ApiParam(value = "ID do produto", example = "1", required = true) Long produtoId,
        @ApiParam(name = "corpo", value = "Representação de um produto com novos dados", required = true) ProdutoInput produtoInput);
}
