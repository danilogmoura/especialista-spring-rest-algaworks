package com.github.danilogmoura.algafood.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.danilogmoura.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.github.danilogmoura.algafood.domain.exception.EntidadeEmUsoException;
import com.github.danilogmoura.algafood.domain.model.Cozinha;
import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CozinhaServiceIT {

    @Autowired
    private CozinhaService cozinhaService;

    @Test
    void deveRetornarSucesso_QuandoCadastrarCozinha() {
        var novaCozinha = new Cozinha();
        novaCozinha.setNome("Chinesa");

        novaCozinha = cozinhaService.salvar(novaCozinha);

        assertThat(novaCozinha).isNotNull();
        assertThat(novaCozinha.getId()).isNotNull();
    }

    @Test
    void deveFalharAoCadastrar_QuandoCozinhaSemNome() {
        var novaCozinha = new Cozinha();
        novaCozinha.setNome(null);
        assertThrows(ConstraintViolationException.class, () -> cozinhaService.salvar(novaCozinha));
    }

    @Test
    void deveFalhar_QuandoExcluirCozinhaEmUso() {
        var cozinhaId = 1L;
        assertThrows(EntidadeEmUsoException.class, () -> cozinhaService.remover(cozinhaId));
    }

    @Test
    void deveFalhar_QuandoExcluirCozinhaInexistente() {
        var cozinhaId = 100L;
        assertThrows(CozinhaNaoEncontradaException.class, () -> cozinhaService.remover(cozinhaId));
    }

}
