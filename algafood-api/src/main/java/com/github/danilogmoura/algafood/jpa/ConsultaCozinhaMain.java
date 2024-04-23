package com.github.danilogmoura.algafood.jpa;

import com.github.danilogmoura.algafood.AlgafoodApiApplication;
import com.github.danilogmoura.algafood.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class ConsultaCozinhaMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);

        CozinhaRepository todasCozinhas = applicationContext.getBean(CozinhaRepository.class);
        todasCozinhas.listar().forEach(System.out::println);
    }

}
