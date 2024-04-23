package com.github.danilogmoura.algafood.infrastructure.repository;

import com.github.danilogmoura.algafood.domain.model.Permissao;
import com.github.danilogmoura.algafood.domain.repository.PermissaoRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PermissaoRepositoryRepositoryImpl implements PermissaoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Permissao> listar() {
        return manager.createQuery("from Permissao ", Permissao.class)
            .getResultList();
    }

    @Override
    public Permissao buscar(Long id) {
        return manager.find(Permissao.class, id);
    }

    @Override
    @Transactional
    public Permissao salvar(Permissao permissao) {
        return manager.merge(permissao);
    }

    @Override
    @Transactional
    public void remover(Permissao permissao) {
        permissao = buscar(permissao.getId());
        manager.remove(permissao);
    }
}
