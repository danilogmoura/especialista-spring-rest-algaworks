package com.github.danilogmoura.algafood.core.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

    @interface Cozinhas {

        @PreAuthorize("hasAnyAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface PodeConsultar {

        }

        @PreAuthorize("hasAnyAuthority('SCOPE_WRITE') and hasAnyAuthority('EDITAR_COZINHAS')")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface PodeEditar {

        }
    }

    @interface Restaurantes {

        @PreAuthorize("hasAnyAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface PodeConsultar {

        }

        @PreAuthorize("hasAnyAuthority('SCOPE_WRITE') and hasAnyAuthority('EDITAR_RESTAURANTES')")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface PodeGerenciarCadastro {

        }

        @PreAuthorize("hasAnyAuthority('SCOPE_WRITE') and "
            + "(hasAnyAuthority('EDITAR_RESTAURANTES') or"
            + "@algaSecurity.gerenciaRestaurante(#restauranteId))")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface PodeGerenciarFuncionamento {

        }

    }

    @interface Pedidos {

        @PreAuthorize("hasAnyAuthority('SCOPE_READ') and isAuthenticated()")
        @PostAuthorize("hasAnyAuthority('CONSULTAR_PEDIDOS') or "
            + "@algaSecurity.usuarioId == returnObject.cliente.id or "
            + "@algaSecurity.gerenciaRestaurante(returnObject.restaurante.id)")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface PodeBuscar {

        }

        @PreAuthorize("hasAnyAuthority('SCOPE_READ') and isAuthenticated() and ("
            + "hasAnyAuthority('CONSULTAR_PEDIDOS') or"
            + "@algaSecurity.gerenciaRestaurante(#filtro.restauranteId)) or "
            + "@algaSecurity.usuarioId == #filtro.clienteId")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface PodePesquisar {

        }
    }

}
