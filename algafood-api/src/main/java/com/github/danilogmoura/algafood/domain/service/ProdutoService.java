package com.github.danilogmoura.algafood.domain.service;

import com.github.danilogmoura.algafood.domain.exception.ProdutoNaoEncontradoException;
import com.github.danilogmoura.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.github.danilogmoura.algafood.domain.model.Produto;
import com.github.danilogmoura.algafood.domain.repository.ProdutoRepository;
import com.github.danilogmoura.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private RestauranteService restauranteService;

    public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {
        if (!restauranteRepository.existsRestauranteById(restauranteId)) {
            throw new RestauranteNaoEncontradoException(restauranteId);
        }

        return produtoRepository.findById(restauranteId, produtoId)
            .orElseThrow(() -> new ProdutoNaoEncontradoException(restauranteId, produtoId));
    }

    @Transactional
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }
}
