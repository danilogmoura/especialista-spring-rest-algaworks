package com.github.danilogmoura.algafood.api.model.input;

import com.github.danilogmoura.algafood.core.validation.ValorZeroIncluiDescricao;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@ValorZeroIncluiDescricao(valorField = "taxaFrete", descricaoFiel = "nome", descricaoObrigatoria = "Frete Grátis")
@Getter
@Setter
public class RestauranteInput {

    @ApiModelProperty(example = "Thai Gourmet", required = true)
    @NotBlank
    private String nome;

    @ApiModelProperty(example = "12.00", required = true)
    @NotNull
    @PositiveOrZero
    private BigDecimal taxaFrete;

    @Valid
    @NotNull
    private CozinhaIdInput cozinha;

    @Valid
    @NotNull
    private EnderecoInput endereco;
}
