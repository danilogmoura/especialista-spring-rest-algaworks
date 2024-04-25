package com.github.danilogmoura.algafood.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.danilogmoura.algafood.domain.exception.EntidadeNaoEcontradaException;
import com.github.danilogmoura.algafood.domain.model.Restaurante;
import com.github.danilogmoura.algafood.domain.repository.CozinhaRepository;
import com.github.danilogmoura.algafood.domain.repository.RestauranteRepository;
import com.github.danilogmoura.algafood.domain.service.CadastroRestauranteService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @GetMapping
    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
        return restauranteRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
        try {
            restaurante = cadastroRestaurante.salvar(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
        } catch (EntidadeNaoEcontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        var restauranteAtualOptional = restauranteRepository.findById(id);

        try {
            if (restauranteAtualOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            var restauranteAtual = restauranteAtualOptional.get();
            BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco",
                "dataCadastro", "produtos");
            restaurante = cadastroRestaurante.salvar(restauranteAtual);

            return ResponseEntity.ok(restaurante);
        } catch (EntidadeNaoEcontradaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> dados) {
        var restauranteAtualOptional = restauranteRepository.findById(id);

        if (restauranteAtualOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var restauranteAtual = restauranteAtualOptional.get();
        merge(dados, restauranteAtual);
        return atualizar(id, restauranteAtual);
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
        var objectMapper = new ObjectMapper();
        var restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

        dadosOrigem.forEach((key, value) -> {
            var field = ReflectionUtils.findField(Restaurante.class, key);
            field.setAccessible(true);
            var novoValor = ReflectionUtils.getField(field, restauranteOrigem);
            ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        try {
            cadastroRestaurante.remover(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEcontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
