package com.github.danilogmoura.algafood.api.controller;

import com.github.danilogmoura.algafood.api.assembler.CidadeInputDisassembler;
import com.github.danilogmoura.algafood.api.assembler.CidadeModelAssembler;
import com.github.danilogmoura.algafood.api.exceptionhandler.Problem;
import com.github.danilogmoura.algafood.api.model.CidadeModel;
import com.github.danilogmoura.algafood.api.model.input.CidadeInput;
import com.github.danilogmoura.algafood.domain.exception.EstadoNaoEncontradoException;
import com.github.danilogmoura.algafood.domain.exception.NegocioException;
import com.github.danilogmoura.algafood.domain.repository.CidadeRepository;
import com.github.danilogmoura.algafood.domain.service.CidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Cidades")
@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private CidadeModelAssembler cidadeModelAssembler;

    @Autowired
    private CidadeInputDisassembler cidadeInputDisassembler;

    @ApiOperation("Lista as cidades")
    @GetMapping
    public List<CidadeModel> listar() {
        return cidadeModelAssembler.toCollectionModel(cidadeRepository.findAll());
    }

    @ApiOperation("Busca uma cidade por ID")
    @ApiResponses({
        @ApiResponse(code = 400, message = "ID da cidade inválido", response = Problem.class),
        @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
    @GetMapping("/{cidadeId}")
    public CidadeModel buscar(
        @ApiParam(value = "ID de uma cidade", example = "1")
        @PathVariable Long cidadeId) {

        var cidade = cidadeService.buscarOuFalhar(cidadeId);
        return cidadeModelAssembler.toModel(cidade);
    }

    @ApiOperation("Cadastra uma cidade")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
        @ApiResponse(code = 201, message = "Cidade cadastrada")
    })
    public CidadeModel adicionar(
        @ApiParam(name = "corpo", value = "Representação de uma nova cidade")
        @RequestBody @Valid CidadeInput cidadeInput) {

        try {
            var cidade = cidadeInputDisassembler.toDomainObjetct(cidadeInput);
            return cidadeModelAssembler.toModel(cidadeService.salvar(cidade));
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @ApiOperation("Cadastra uma cidade por ID")
    @PutMapping("/{cidadeId}")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Cidade Atualizada"),
        @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
    public CidadeModel atualizar(
        @ApiParam(value = "ID de uma cidade", example = "1")
        @PathVariable Long cidadeId,
        @ApiParam(name = "corpo", value = "Representação de uma nova cidade com novos dados")
        @RequestBody @Valid CidadeInput cidadeInput) {

        try {
            var cidadeAtual = cidadeService.buscarOuFalhar(cidadeId);

            cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);

            return cidadeModelAssembler.toModel(cidadeService.salvar(cidadeAtual));
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @ApiOperation("Exclui uma cidade por ID")
    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses({
        @ApiResponse(code = 204, message = "Cidade excluída"),
        @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
    public void remover(
        @ApiParam(value = "ID de uma cidade", example = "1")
        @PathVariable Long cidadeId) {

        cidadeService.remover(cidadeId);
    }
}
