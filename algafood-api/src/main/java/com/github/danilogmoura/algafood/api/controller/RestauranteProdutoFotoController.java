package com.github.danilogmoura.algafood.api.controller;

import com.github.danilogmoura.algafood.api.model.input.FotoProdutoInput;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @PutMapping
    public void atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId,
        @Valid FotoProdutoInput fotoProdutoInput) {

        var nomeArquivo = UUID.randomUUID() + "_" + fotoProdutoInput.getArquivo().getOriginalFilename();

        var arquivoFoto = Path.of("D:\\upload\\catalogo", nomeArquivo);

        System.out.println(fotoProdutoInput.getDescricao());
        System.out.println(arquivoFoto);
        System.out.println(fotoProdutoInput.getArquivo().getContentType());

        try {
            fotoProdutoInput.getArquivo().transferTo(arquivoFoto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
