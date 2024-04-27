package com.github.danilogmoura.algafood.api.controller;

import com.github.danilogmoura.algafood.api.exceptionhandler.Problema;
import com.github.danilogmoura.algafood.domain.exception.EntidadeNaoEcontradaException;
import com.github.danilogmoura.algafood.domain.exception.EstadoNaoEcontradoException;
import com.github.danilogmoura.algafood.domain.exception.NegocioException;
import com.github.danilogmoura.algafood.domain.model.Cidade;
import com.github.danilogmoura.algafood.domain.repository.CidadeRepository;
import com.github.danilogmoura.algafood.domain.service.CidadeService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cidade buscar(@PathVariable Long id) {
        return cidadeService.buscarOuFalhar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade adicionar(@RequestBody Cidade cidade) {
        try {
            return cidadeService.salvar(cidade);
        } catch (EstadoNaoEcontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{id}")
    public Cidade atualizar(@PathVariable Long id, @RequestBody Cidade cidade) {
        try {
            var cidadeAtual = cidadeService.buscarOuFalhar(id);
            BeanUtils.copyProperties(cidade, cidadeAtual, "id");
            return cidadeService.salvar(cidadeAtual);
        } catch (EstadoNaoEcontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        cidadeService.remover(id);
    }

    @ExceptionHandler(EntidadeNaoEcontradaException.class)
    public ResponseEntity<?> tratarEntidadeNaoEcontradaException(EntidadeNaoEcontradaException e) {
        var problema = Problema.builder()
            .dataHora(LocalDateTime.now())
            .mensagem(e.getMessage()).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(problema);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> tratarNegocioException(NegocioException e) {
        var problema = Problema.builder()
            .dataHora(LocalDateTime.now())
            .mensagem(e.getMessage()).build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(problema);
    }
}
