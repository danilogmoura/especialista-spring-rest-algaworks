package com.github.danilogmoura.algafood.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "produtos")
@Getter
@Setter
public class ProdutoModel extends RepresentationModel<ProdutoModel> {

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
