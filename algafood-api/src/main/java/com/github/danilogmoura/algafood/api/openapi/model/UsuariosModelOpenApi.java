package com.github.danilogmoura.algafood.api.openapi.model;

import com.github.danilogmoura.algafood.api.model.UsuarioModel;
import io.swagger.annotations.ApiModel;
import java.util.List;
import lombok.Data;
import org.springframework.hateoas.Links;

@ApiModel("UsuariosModel")
@Data
public class UsuariosModelOpenApi {

    private UsuariosEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("UsuariosEmbeddedModel")
    @Data
    public class UsuariosEmbeddedModelOpenApi {

        private List<UsuarioModel> usuarios;

    }
}
