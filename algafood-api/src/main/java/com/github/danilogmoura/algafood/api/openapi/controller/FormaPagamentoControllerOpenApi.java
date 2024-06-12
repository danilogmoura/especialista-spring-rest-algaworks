package com.github.danilogmoura.algafood.api.openapi.controller;

import com.github.danilogmoura.algafood.api.exceptionhandler.Problem;
import com.github.danilogmoura.algafood.api.model.FormaPagamentoModel;
import com.github.danilogmoura.algafood.api.model.input.FormaPagamentoInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

@Api(tags = "Formas Pagamento")
public interface FormaPagamentoControllerOpenApi {

    @ApiOperation("Lista as formas de pagamanto")
    ResponseEntity<List<FormaPagamentoModel>> listar(ServletWebRequest request);

    @ApiOperation("Busca uma forma de pagamento por ID")
    @ApiResponses({
        @ApiResponse(code = 400, message = "ID da forma de pagamento inválido", response = Problem.class),
        @ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
    })
    ResponseEntity<FormaPagamentoModel> buscar(
        @ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true) Long formaPagamentoId,
        ServletWebRequest request);

    @ApiOperation("Cadastra uma forma de pagamento")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Forma de pagamento cadastrata")
    })
    FormaPagamentoModel adicionar(
        @ApiParam(name = "corpo", value = "Representação de uma nova forma de pagamento", required = true) FormaPagamentoInput formaPagamentoInput);

    @ApiOperation("Atualiza uma forma de pagamento por ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Forma de pagamento Atualizada"),
        @ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
    })
    FormaPagamentoModel atualizar(
        @ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true) Long id,
        FormaPagamentoInput formaPagamentoInput);

    @ApiOperation("Exclui uma forma de pagamento por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Forma de pagamento excluída"),
        @ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
    })
    void deletar(@ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true) Long id);
}
