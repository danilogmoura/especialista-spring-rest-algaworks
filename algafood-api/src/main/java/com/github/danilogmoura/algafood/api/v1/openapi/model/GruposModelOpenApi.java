package com.github.danilogmoura.algafood.api.v1.openapi.model;

import com.github.danilogmoura.algafood.api.v1.model.GrupoModel;
import io.swagger.annotations.ApiModel;
import java.util.List;
import lombok.Data;
import org.springframework.hateoas.Links;

@ApiModel("GruposModel")
@Data
public class GruposModelOpenApi {

    private GruposEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("GruposEmbeddedModel")
    @Data
    public class GruposEmbeddedModelOpenApi {

        private List<GrupoModel> grupos;

    }
}
