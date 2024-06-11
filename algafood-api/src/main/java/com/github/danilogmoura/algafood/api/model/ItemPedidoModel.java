package com.github.danilogmoura.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Salada picante com carne grelhada")
    private String produtoNome;

    @ApiModelProperty(example = "1")
    private Integer quantidade;

    @ApiModelProperty(example = "87.2")
    private BigDecimal precoUnitario;

    @ApiModelProperty(example = "87.2")
    private BigDecimal precoTotal;

    @ApiModelProperty(example = "Uma deliciosa salada picante com a melhor carne grelhada")
    private String observacao;
}
