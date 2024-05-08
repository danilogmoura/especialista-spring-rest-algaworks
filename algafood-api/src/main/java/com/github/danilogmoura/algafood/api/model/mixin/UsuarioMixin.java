package com.github.danilogmoura.algafood.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.OffsetDateTime;

public class UsuarioMixin {

    @JsonIgnore
    private OffsetDateTime dataCadastro;

    @JsonIgnore
    private OffsetDateTime dataAtualizacao;
}
