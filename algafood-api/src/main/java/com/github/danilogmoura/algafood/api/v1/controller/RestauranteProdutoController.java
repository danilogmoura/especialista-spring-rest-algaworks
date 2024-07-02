package com.github.danilogmoura.algafood.api.v1.controller;

import com.github.danilogmoura.algafood.api.v1.AlgaLinks;
import com.github.danilogmoura.algafood.api.v1.assembler.ProdutoInputDisassembler;
import com.github.danilogmoura.algafood.api.v1.assembler.ProdutoModelAssembler;
import com.github.danilogmoura.algafood.api.v1.model.ProdutoModel;
import com.github.danilogmoura.algafood.api.v1.model.input.ProdutoInput;
import com.github.danilogmoura.algafood.api.v1.openapi.controller.RestauranteProdutoControllerOpenApi;
import com.github.danilogmoura.algafood.core.security.CheckSecurity;
import com.github.danilogmoura.algafood.domain.model.Produto;
import com.github.danilogmoura.algafood.domain.repository.ProdutoRepository;
import com.github.danilogmoura.algafood.domain.service.ProdutoService;
import com.github.danilogmoura.algafood.domain.service.RestauranteService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController implements RestauranteProdutoControllerOpenApi {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private ProdutoModelAssembler produtoModelAssembler;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoInputDisassembler produtoInputDisassembler;

    @Autowired
    private AlgaLinks algaLinks;


    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<ProdutoModel> listar(
        @PathVariable Long restauranteId,
        @RequestParam(required = false, defaultValue = "false") Boolean incluirInativos) {
        var restaurante = restauranteService.buscarOuFalhar(restauranteId);

        List<Produto> produtos;

        if (incluirInativos) {
            produtos = produtoRepository.findAllByRestaurante(restaurante);
        } else {
            produtos = produtoRepository.findAtivosByRestaurante(restaurante);
        }

        return produtoModelAssembler.toCollectionModel(produtos)
            .add(algaLinks.linkToRestauranteProdutos(restauranteId));
    }

    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping(path = "/{produtoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        var produto = produtoService.buscarOuFalhar(restauranteId, produtoId);
        return produtoModelAssembler.toModel(produto);
    }

    @CheckSecurity.Restaurantes.PodeEditar
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoModel adicionar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoInput produtoInput) {
        var restaurante = restauranteService.buscarOuFalhar(restauranteId);

        var produto = produtoInputDisassembler.toDomainObject(produtoInput);
        produto.setRestaurante(restaurante);
        produto = produtoService.salvar(produto);

        return produtoModelAssembler.toModel(produto);
    }

    @CheckSecurity.Restaurantes.PodeEditar
    @PutMapping(path = "/{produtoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProdutoModel atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
        @RequestBody @Valid ProdutoInput produtoInput) {
        var produto = produtoService.buscarOuFalhar(restauranteId, produtoId);
        produtoInputDisassembler.copyToDomainObject(produtoInput, produto);
        return produtoModelAssembler.toModel(produtoService.salvar(produto));
    }
}
