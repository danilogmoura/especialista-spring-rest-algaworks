package com.github.danilogmoura.algafood.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.OffsetDateTime;

public class ItemPedidoMixin {

    @JsonIgnore
    private OffsetDateTime dataCriacao;

    @JsonIgnore
    private OffsetDateTime dataAtualizacao;
}
