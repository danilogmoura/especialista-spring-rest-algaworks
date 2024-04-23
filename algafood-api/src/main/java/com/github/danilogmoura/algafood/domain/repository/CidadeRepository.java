package com.github.danilogmoura.algafood.domain.repository;

import com.github.danilogmoura.algafood.domain.model.Cidade;
import java.util.List;

public interface CidadeRepository {

    List<Cidade> listar();

    Cidade buscar(Long id);

    Cidade salvar(Cidade cidade);

    void remover(Cidade cidade);
}
