package com.github.danilogmoura.algafood.domain.repository;

import com.github.danilogmoura.algafood.domain.model.Permissao;
import java.util.List;

public interface PermissaoRepository {

    List<Permissao> listar();

    Permissao buscar(Long id);

    Permissao salvar(Permissao permissao);

    void remover(Permissao permissao);
}
