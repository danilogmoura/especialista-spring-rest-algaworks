package com.github.danilogmoura.algafood.infrastructure.repository;

import com.github.danilogmoura.algafood.domain.model.FotoProduto;
import com.github.danilogmoura.algafood.domain.repository.ProdutoRepositoryQueries;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public FotoProduto save(FotoProduto foto) {
        return manager.merge(foto);
    }

    @Transactional
    @Override
    public void delete(FotoProduto foto) {
        manager.remove(foto);
    }

}
