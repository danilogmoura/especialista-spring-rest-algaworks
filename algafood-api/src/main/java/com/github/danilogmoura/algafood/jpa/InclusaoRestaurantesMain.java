package com.github.danilogmoura.algafood.jpa;


import com.github.danilogmoura.algafood.AlgafoodApiApplication;
import com.github.danilogmoura.algafood.domain.model.Restaurante;
import com.github.danilogmoura.algafood.domain.repository.RestauranteRepository;
import java.math.BigDecimal;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class InclusaoRestaurantesMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);

        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Brasil-sil-sil");
        restaurante.setTaxaFrete(BigDecimal.TEN);

        RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
        restaurante = restauranteRepository.salvar(restaurante);

        System.out.println(restaurante);
    }

}
