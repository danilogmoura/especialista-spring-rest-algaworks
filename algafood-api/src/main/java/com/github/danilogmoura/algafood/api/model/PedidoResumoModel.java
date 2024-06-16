package com.github.danilogmoura.algafood.api.model;

import com.github.danilogmoura.algafood.domain.model.Pedido.StatusPedido;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "pedidos")
@Getter
@Setter
public class PedidoResumoModel extends RepresentationModel<PedidoResumoModel> {

    @ApiModelProperty(example = "8d774bcf-b238-42f3-aef1-5fb388754d63")
    private String codigo;

    @ApiModelProperty(example = "87.2")
    private BigDecimal subtotal;

    @ApiModelProperty(example = "10")
    private BigDecimal taxaFrete;

    @ApiModelProperty(example = "97.2")
    private BigDecimal valorTotal;

    @ApiModelProperty(example = "ENTREGUE")
    private StatusPedido status;

    @ApiModelProperty(example = "2019-11-03T02:00:30Z")
    private OffsetDateTime dataCriacao;

    private RestauranteApenasNomeModel restaurante;

    private UsuarioModel cliente;
}
