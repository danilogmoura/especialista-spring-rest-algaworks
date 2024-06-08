package com.github.danilogmoura.algafood.client;

import com.github.danilogmoura.algafood.client.api.ClientApiException;
import com.github.danilogmoura.algafood.client.api.RestauranteClient;
import com.github.danilogmoura.algafood.client.model.input.CidadeInput;
import com.github.danilogmoura.algafood.client.model.input.CozinhaInput;
import com.github.danilogmoura.algafood.client.model.input.EnderecoInput;
import com.github.danilogmoura.algafood.client.model.input.RestauranteInput;
import java.math.BigDecimal;
import org.springframework.web.client.RestTemplate;

public class AdicionarRestaurantesMain {

    public static void main(String[] args) {

        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080";

            var restauranteClient = new RestauranteClient(restTemplate, url);

            var cidade = CidadeInput.builder()
                .id(2L)
                .build();

            var endereco = EnderecoInput.builder()
                .cep("38400-999")
                .logradouro("Rua João Pinheiro")
                .numero("1000")
                .bairro("Centro")
                .cidade(cidade)
                .build();

            var cozinha = CozinhaInput.builder()
                .id(1L).
                build();

            var restaurante = RestauranteInput.builder()
                .nome("Cozinha Mineira Frete Grátis")
                .taxaFrete(BigDecimal.ZERO)
                .cozinha(cozinha)
                .endereco(endereco)
                .build();

            var restauranteAdicionado = restauranteClient.adicionar(restaurante);
            System.out.println(restauranteAdicionado);

        } catch (ClientApiException e) {
            if (e.getProblem() != null) {
                System.out.println(e.getProblem().getUserMessage());

                e.getProblem().getObjects().forEach(problem -> System.out.println("- " + problem.getUserMessage()));
            } else {
                System.out.println("Erro desconhecido");
                e.printStackTrace();
            }
        }
    }
}
