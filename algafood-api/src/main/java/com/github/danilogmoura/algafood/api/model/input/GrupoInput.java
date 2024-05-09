package com.github.danilogmoura.algafood.api.model.input;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoInput {

    @NotBlank
    private String nome;
}
