package com.github.danilogmoura.algafood.api.v1.model.input;

import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoInput {

    @ApiModelProperty(example = "Porco com molho agridoce", required = true)
    @NotBlank
    private String nome;

    @ApiModelProperty(example = "Deliciosa carne suína ao molho especial", required = true)
    @NotBlank
    private String descricao;

    @ApiModelProperty(example = "78.9", required = true)
    @NotNull
    @PositiveOrZero
    private BigDecimal preco;

    @ApiModelProperty(example = "true", required = true)
    @NotNull
    private Boolean ativo;
}
