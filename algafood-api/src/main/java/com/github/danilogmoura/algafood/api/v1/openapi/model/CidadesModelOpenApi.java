package com.github.danilogmoura.algafood.api.v1.openapi.model;

import com.github.danilogmoura.algafood.api.v1.model.CidadeModel;
import io.swagger.annotations.ApiModel;
import java.util.List;
import lombok.Data;
import org.springframework.hateoas.Links;

@ApiModel("CidadesModel")
@Data
public class CidadesModelOpenApi {

    private CidadesEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("CidadesEmbeddedModel")
    @Data
    public static class CidadesEmbeddedModelOpenApi {

        private List<CidadeModel> cidades;

    }
}
