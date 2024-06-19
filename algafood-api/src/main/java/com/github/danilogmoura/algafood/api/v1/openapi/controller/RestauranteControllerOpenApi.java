package com.github.danilogmoura.algafood.api.v1.openapi.controller;

import com.github.danilogmoura.algafood.api.exceptionhandler.Problem;
import com.github.danilogmoura.algafood.api.v1.model.RestauranteApenasNomeModel;
import com.github.danilogmoura.algafood.api.v1.model.RestauranteBasicoModel;
import com.github.danilogmoura.algafood.api.v1.model.RestauranteModel;
import com.github.danilogmoura.algafood.api.v1.model.input.RestauranteInput;
import com.github.danilogmoura.algafood.api.v1.openapi.model.RestauranteBasicoModelOpenApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;


@Api(tags = "Restaurantes")
@RestController
@RequestMapping("/restaurantes")
public interface RestauranteControllerOpenApi {

    @ApiOperation(value = "Lista restaurantes", response = RestauranteBasicoModelOpenApi.class)
    @ApiImplicitParams({
        @ApiImplicitParam(value = "Nome da projeção de pedidos", name = "projecao", paramType = "query",
            type = "string", allowableValues = "apenas-nome")
    })
    CollectionModel<RestauranteBasicoModel> listar();

    @ApiIgnore
    @ApiOperation(value = "Lista restaurantes", hidden = true)
    CollectionModel<RestauranteApenasNomeModel> listarApenasNomes();

    @ApiOperation("Busca um restaurante por ID")
    RestauranteModel buscar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long id);

    @ApiOperation("Cadastra um restaurante")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Restaurante Cadastrado")
    })
    RestauranteModel adicionar(
        @ApiParam(name = "corpo", value = "Respresentação de um novo retaurante", required = true) RestauranteInput restauranteInput);

    @ApiOperation("Atualiza um restaurante por ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Restaurante atualizado"),
        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    RestauranteModel atualizar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long id,
        @ApiParam(name = "corpo", value = "Representação de um restaurante com novos dados", required = true) RestauranteInput restauranteInput);

    @ApiOperation(value = "Atualiza um restaurante por ID", hidden = true)
    RestauranteModel atualizarParcial(@ApiParam(value = "ID de um restaurante", example = "1") Long id,
        Map<String, Object> dados, HttpServletRequest request);

    @ApiOperation("Exclui um restaurante por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Cidade excluída"),
        @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
    void remover(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long id);

    @ApiOperation("Ativa um restaurante por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurante ativado com sucesso"),
        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> ativar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long id);

    @ApiOperation("Ativa múltiplos restaurantes")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurantes ativados com sucesso")
    })
    void ativarMultiplos(
        @ApiParam(name = "corpo", value = "IDs de restaurantes", required = true) List<Long> restauranteIds);

    @ApiOperation("Inativa um restaurante por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurante inativado com sucesso"),
        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> inativar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long id);

    @ApiOperation("Inativa múltiplos restaurantes")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurantes ativados com sucesso")
    })
    void inativarMultiplos(
        @ApiParam(name = "corpo", value = "IDs de restaurantes", required = true) List<Long> restauranteIds);

    @ApiOperation("Abre um restaurante por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurante aberto com sucesso"),
        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> abertura(
        @ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

    @ApiOperation("Fecha um restaurante por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurante fechado com sucesso"),
        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> fechamento(
        @ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

}
