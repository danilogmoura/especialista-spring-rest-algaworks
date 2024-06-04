package com.github.danilogmoura.algafood.domain.service;

import com.github.danilogmoura.algafood.domain.model.FotoProduto;
import com.github.danilogmoura.algafood.domain.repository.ProdutoRepository;
import com.github.danilogmoura.algafood.domain.service.FotoStorageService.NovaFoto;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CatalogoFotoProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FotoStorageService fotoStorage;

    @Transactional
    public FotoProduto salvar(FotoProduto foto, InputStream dadosArquivo) {
        var restauranteId = foto.getRestauranteId();
        var produtoId = foto.getProduto().getId();
        var nomeNovoArquivo = fotoStorage.gerarNomeArquivo(foto.getNomeArquivo());
        String nomeArquivoExistente = null;

        var fotoExistente = produtoRepository.findFotoById(restauranteId, produtoId);
        if (fotoExistente.isPresent()) {
            nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
            produtoRepository.delete(fotoExistente.get());
        }

        foto.setNomeArquivo(nomeNovoArquivo);
        foto = produtoRepository.save(foto);
        produtoRepository.flush();

        NovaFoto novaFoto = NovaFoto.builder()
            .nomeArquivo(foto.getNomeArquivo())
            .inputStream(dadosArquivo)
            .build();

        fotoStorage.substituir(nomeArquivoExistente, novaFoto);

        return foto;
    }
}
