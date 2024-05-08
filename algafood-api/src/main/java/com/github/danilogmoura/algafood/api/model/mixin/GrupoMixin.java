package com.github.danilogmoura.algafood.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.OffsetDateTime;

public class GrupoMixin {

    @JsonIgnore
    private OffsetDateTime dataAtualizacao;
}
