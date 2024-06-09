package com.github.danilogmoura.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@ApiModel("Problema")
@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {

    @ApiModelProperty(example = "400", position = 1)
    private Integer status;

    @ApiModelProperty(example = "https://algafood.com.br/dados-invalidos", position = 5)
    private String type;

    @ApiModelProperty(example = "Dados inválidos", position = 10)
    private String title;

    @ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.",
        position = 15)
    private String detail;

    @ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.",
        position = 20)
    private String userMessage;

    @ApiModelProperty(example = "2024-06-09T06:56:38.7817777Z", position = 25)
    private OffsetDateTime timestamp;

    @ApiModelProperty(value = "Lista de objetos ou campos que geraram o erro(opcional)", position = 30)
    private List<Object> objects;

    @ApiModel("Objeto Problema")
    @Getter
    @Builder
    public static class Object {

        @ApiModelProperty("nome")
        private String name;

        @ApiModelProperty("Nome da cidade é obrigatório")
        private String userMessage;
    }
}
