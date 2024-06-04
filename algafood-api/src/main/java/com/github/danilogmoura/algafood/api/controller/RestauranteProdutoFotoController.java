package com.github.danilogmoura.algafood.api.controller;

import com.github.danilogmoura.algafood.api.assembler.FotoProdutoAssembler;
import com.github.danilogmoura.algafood.api.model.FotoProdutoModel;
import com.github.danilogmoura.algafood.api.model.input.FotoProdutoInput;
import com.github.danilogmoura.algafood.domain.model.FotoProduto;
import com.github.danilogmoura.algafood.domain.service.CatalogoFotoProdutoService;
import com.github.danilogmoura.algafood.domain.service.ProdutoService;
import java.io.IOException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @Autowired
    private CatalogoFotoProdutoService catalogoFotoProdutoService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private FotoProdutoAssembler fotoProdutoAssembler;

    @PutMapping
    public FotoProdutoModel atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId,
        @Valid FotoProdutoInput fotoProdutoInput) throws IOException {
        var produto = produtoService.buscarOuFalhar(restauranteId, produtoId);

        MultipartFile arquivo = fotoProdutoInput.getArquivo();

        FotoProduto foto = new FotoProduto();
        foto.setProduto(produto);
        foto.setNomeArquivo(arquivo.getOriginalFilename());
        foto.setContentType(arquivo.getContentType());
        foto.setTamanho(arquivo.getSize());
        foto.setDescricao(fotoProdutoInput.getDescricao());

        var fotoProdutoSalva = catalogoFotoProdutoService.salvar(foto, arquivo.getInputStream());
        return fotoProdutoAssembler.toModel(fotoProdutoSalva);
    }

}
