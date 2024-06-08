package com.github.danilogmoura.algafood.client.api;

import com.github.danilogmoura.algafood.client.model.RestauranteModel;
import com.github.danilogmoura.algafood.client.model.RestauranteResumoModel;
import com.github.danilogmoura.algafood.client.model.input.RestauranteInput;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
public class RestauranteClient {

    private static final String RESOURCE_PATH = "/restaurantes";

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

    public RestauranteModel adicionar(RestauranteInput restaurante) {
        URI resourceUri = URI.create(url + RESOURCE_PATH);

        try {
            return restTemplate.postForObject(resourceUri, restaurante, RestauranteModel.class);
        } catch (HttpClientErrorException e) {
            throw new ClientApiException(e.getMessage(), e);
        }
    }

}
