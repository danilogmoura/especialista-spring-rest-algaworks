package com.github.danilogmoura.algafood.client;

import com.github.danilogmoura.algafood.client.api.RestauranteClient;
import org.springframework.web.client.RestTemplate;

public class ListagemRestaurantesMain {


    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080";

        var restauranteClient = new RestauranteClient(restTemplate, url);

        restauranteClient.listar().forEach(System.out::println);
    }
}
