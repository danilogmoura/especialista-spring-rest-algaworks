package com.github.danilogmoura.algafood.client.api;

import com.github.danilogmoura.algafood.client.model.RestauranteResumoModel;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
public class RestauranteClient {

    private static final String RESOURCE_PATH = "/restaurantess";

    private RestTemplate restTemplate;

    private String url;

    public List<RestauranteResumoModel> listar() {

        try {
            URI resourceUri = URI.create(url + RESOURCE_PATH);

            RestauranteResumoModel[] restaurantes = restTemplate.getForObject(resourceUri,
                RestauranteResumoModel[].class);

            assert restaurantes != null;
            return Arrays.asList(restaurantes);
        } catch (RestClientResponseException e) {
            throw new ClientApiException(e.getMessage(), e);
        }
    }
}
