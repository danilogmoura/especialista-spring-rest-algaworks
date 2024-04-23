package com.github.danilogmoura.algafood.jpa;

import com.github.danilogmoura.algafood.AlgafoodApiApplication;
import com.github.danilogmoura.algafood.domain.model.Cozinha;
import com.github.danilogmoura.algafood.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class InclusaoCozinhaMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(
            AlgafoodApiApplication.class).web(WebApplicationType.NONE).run(args);

        CozinhaRepository cadastroCozinha = applicationContext.getBean(CozinhaRepository.class);

        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Brasileira");

        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("Japonesa");

        cozinha1 = cadastroCozinha.salvar(cozinha1);
        cozinha2 = cadastroCozinha.salvar(cozinha2);

        System.out.println(cozinha1);
        System.out.println(cozinha2);
    }
}
