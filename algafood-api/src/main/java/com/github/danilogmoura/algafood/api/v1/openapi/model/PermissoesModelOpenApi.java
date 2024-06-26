package com.github.danilogmoura.algafood.api.v1.openapi.model;

import com.github.danilogmoura.algafood.api.v1.model.PermissaoModel;
import io.swagger.annotations.ApiModel;
import java.util.List;
import lombok.Data;
import org.springframework.hateoas.Links;

@ApiModel("PermissoesModel")
@Data
public class PermissoesModelOpenApi {

    private PermissoesEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("PermissoesEmbeddedModel")
    @Data
    public class PermissoesEmbeddedModelOpenApi {

        private List<PermissaoModel> permissoes;

    }
}
