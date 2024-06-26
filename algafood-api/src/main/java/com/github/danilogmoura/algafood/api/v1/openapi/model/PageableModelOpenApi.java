package com.github.danilogmoura.algafood.api.v1.openapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@ApiModel("Pageable")
@Getter
@Setter
public class PageableModelOpenApi {

    @ApiModelProperty(example = "0", value = "Número da página (começa em 0)")
    private int size;

    @ApiModelProperty(example = "10", value = "Quantidade de elementos por página")
    private int page;

    @ApiModelProperty(example = "nome,asc", value = "Nome da propriedade para ordenação")
    private List<String> sort;

}
