package com.github.danilogmoura.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Porco com molho agridoce")
    private String nome;

    @ApiModelProperty(example = "Deliciosa carne suína ao molho especial")
    private String descricao;

    @ApiModelProperty(example = "78.9")
    private BigDecimal preco;

    @ApiModelProperty(example = "true")
    private Boolean ativo;
}
