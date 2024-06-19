package com.github.danilogmoura.algafood.api.v1.model.input;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoIdInput {

    @ApiModelProperty(example = "1")
    @NotNull
    private Long id;
}
