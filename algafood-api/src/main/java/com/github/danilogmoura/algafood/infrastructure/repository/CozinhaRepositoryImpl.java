package com.github.danilogmoura.algafood.infrastructure.repository;

import com.github.danilogmoura.algafood.domain.model.Cozinha;
import com.github.danilogmoura.algafood.domain.repository.CozinhaRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cozinha> listar() {
        return manager.createQuery("from Cozinha ", Cozinha.class)
            .getResultList();
    }

    @Override
    public Cozinha buscar(Long id) {
        return manager.find(Cozinha.class, id);
    }

    @Override
    public Cozinha salvar(Cozinha cozinha) {
        return manager.merge(cozinha);
    }

    @Override
    public void remover(Cozinha cozinha) {
        cozinha = buscar(1L);
        manager.remove(cozinha);
    }
}
