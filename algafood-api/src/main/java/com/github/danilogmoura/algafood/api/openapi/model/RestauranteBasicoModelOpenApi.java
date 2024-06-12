package com.github.danilogmoura.algafood.api.openapi.model;

import com.github.danilogmoura.algafood.api.model.CozinhaModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@ApiModel("RestauranteBasicoModel")
@Getter
@Setter
public class RestauranteBasicoModelOpenApi {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Thai Gourmet")
    private String nome;

    @ApiModelProperty(example = "12.00")
    private BigDecimal taxaFrete;

    private CozinhaModel cozinha;

}
