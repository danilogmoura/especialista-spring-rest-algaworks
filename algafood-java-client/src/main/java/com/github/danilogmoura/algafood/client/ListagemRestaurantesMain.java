package com.github.danilogmoura.algafood.client;

import com.github.danilogmoura.algafood.client.api.ClientApiException;
import com.github.danilogmoura.algafood.client.api.RestauranteClient;
import org.springframework.web.client.RestTemplate;

public class ListagemRestaurantesMain {

    public static void main(String[] args) {

        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080";

            var restauranteClient = new RestauranteClient(restTemplate, url);

            restauranteClient.listar().forEach(System.out::println);
        } catch (ClientApiException e) {
            if (e.getProblem() != null) {
                System.out.println(e.getProblem());
            } else {
                System.out.println("Erro desconhecido");
                e.printStackTrace();
            }
        }
    }
}
