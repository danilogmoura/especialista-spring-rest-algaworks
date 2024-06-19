package com.github.danilogmoura.algafood.api.v1.openapi.model;

import com.github.danilogmoura.algafood.api.v1.model.ProdutoModel;
import io.swagger.annotations.ApiModel;
import java.util.List;
import lombok.Data;
import org.springframework.hateoas.Links;

@ApiModel("ProdutosModel")
@Data
public class ProdutosModelOpenApi {

    private ProdutosEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("ProdutosEmbeddedModel")
    @Data
    public class ProdutosEmbeddedModelOpenApi {

        private List<ProdutoModel> produtos;

    }
}
