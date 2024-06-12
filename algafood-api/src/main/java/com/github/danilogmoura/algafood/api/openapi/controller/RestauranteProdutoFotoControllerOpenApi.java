package com.github.danilogmoura.algafood.api.openapi.controller;

import com.github.danilogmoura.algafood.api.exceptionhandler.Problem;
import com.github.danilogmoura.algafood.api.model.FotoProdutoModel;
import com.github.danilogmoura.algafood.api.model.input.FotoProdutoInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

@Api(tags = "Produtos")
public interface RestauranteProdutoFotoControllerOpenApi {


    @ApiOperation(value = "Busca a foto do produto de um restaurante",
        produces = "application/json, image/jpeg, image/png")
    @ApiResponses({
        @ApiResponse(code = 400, message = "ID do restaurante ou produto inválido", response = Problem.class),
        @ApiResponse(code = 404, message = "Foto de produto não encontrada", response = Problem.class)
    })
    FotoProdutoModel buscar(
        @ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,
        @ApiParam(value = "ID do produto", example = "1", required = true) Long produtoId);

    @ApiOperation(value = "Busca a foto do produto de um restaurante", hidden = true,
        produces = "application/json, image/jpeg, image/png")
    ResponseEntity<?> servir(
        @ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,
        @ApiParam(value = "ID do produto", example = "1", required = true) Long produtoId,
        String acceptHeader) throws HttpMediaTypeNotAcceptableException;

    @ApiOperation("Atualiza a foto do produto de um restaurante")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Foto do produto atualizada"),
        @ApiResponse(code = 404, message = "Produto de restaurante não encontrado", response = Problem.class)
    })
    FotoProdutoModel atualizarFoto(
        @ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,
        @ApiParam(value = "ID do produto", example = "1", required = true) Long produtoId,
        FotoProdutoInput fotoProdutoInput) throws IOException;

    @ApiOperation("Exclui a foto do produto de um restaurante")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Foto do produto excluída"),
        @ApiResponse(code = 400, message = "ID do restaurante ou produto inválido", response = Problem.class),
        @ApiResponse(code = 404, message = "Foto de produto não encontrada", response = Problem.class)
    })
    void remover(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,
        @ApiParam(value = "ID do produto", example = "1", required = true) Long produtoId);

}
