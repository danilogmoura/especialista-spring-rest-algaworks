package com.github.danilogmoura.algafood.api.v1.model.input;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaIdInput {

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    private Long id;
}
