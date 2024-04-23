package com.github.danilogmoura.algafood.jpa;


import com.github.danilogmoura.algafood.AlgafoodApiApplication;
import com.github.danilogmoura.algafood.domain.model.Restaurante;
import com.github.danilogmoura.algafood.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class ExcliusaoRestaurantesMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);

        Restaurante restaurante = new Restaurante();
        restaurante.setId(2L);

        RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
        restauranteRepository.remover(restaurante);
    }

}
