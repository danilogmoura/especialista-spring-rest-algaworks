package com.github.danilogmoura.algafood.api.v1.openapi.model;

import com.github.danilogmoura.algafood.domain.model.Cozinha;
import io.swagger.annotations.ApiModel;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

@ApiModel("CozinhasModel")
@Getter
@Setter
public class CozinhasModelOpenApi {

    private CozinhasEmbeddedModelOpenApi _embedded;
    private Links _links;
    private PagedModelOpenApi page;

    @ApiModel("CozinhasEmbeddedModel")
    @Data
    public class CozinhasEmbeddedModelOpenApi {

        private List<Cozinha> cozinhas;

    }
}
