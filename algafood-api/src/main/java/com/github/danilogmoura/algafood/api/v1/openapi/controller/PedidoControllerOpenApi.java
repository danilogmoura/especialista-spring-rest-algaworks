package com.github.danilogmoura.algafood.api.v1.openapi.controller;

import com.github.danilogmoura.algafood.api.exceptionhandler.Problem;
import com.github.danilogmoura.algafood.api.v1.model.PedidoModel;
import com.github.danilogmoura.algafood.api.v1.model.PedidoResumoModel;
import com.github.danilogmoura.algafood.api.v1.model.input.PedidoInput;
import com.github.danilogmoura.algafood.domain.filter.PedidoFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {

    @ApiImplicitParams({
        @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
            name = "campos", paramType = "query", type = "string")
    })
    @ApiOperation("Pesquisa os pedidos")
    PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro, Pageable pageable);

    @ApiImplicitParams({
        @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
            name = "campos", paramType = "query", type = "string")
    })
    @ApiOperation("Busca um pedido por código")
    @ApiResponses({
        @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
    })
    PedidoModel buscar(@ApiParam(value = "Código de um pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55",
        required = true) String codigo);

    @ApiOperation("Registra um pedido")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Pedido registrado"),
    })
    PedidoModel adicionar(
        @ApiParam(name = "corpo", value = "Representação de um novo pedido", required = true) PedidoInput pedidoInput);
}
