package com.github.danilogmoura.algafood.api.v2.model.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@ApiModel("CozinhaInput")
@Getter
@Setter
public class CozinhaInputV2 {

    @NotBlank
    @ApiModelProperty(example = "Brasileira", required = true)
    private String nomeCozinha;
}
