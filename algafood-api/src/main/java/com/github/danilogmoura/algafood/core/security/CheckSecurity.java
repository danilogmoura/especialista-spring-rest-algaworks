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
            + "hasAnyAuthority('CONSULTAR_PEDIDOS') or "
            + "@algaSecurity.gerenciaRestaurante(#filtro.restauranteId)) or "
            + "@algaSecurity.usuarioId == #filtro.clienteId")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface PodePesquisar {

        }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('GERENCIAR_PEDIDOS') or "
            + "@algaSecurity.gerenciaRestauranteDoPedido(#codigoPedido))")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface PodeGerenciar {

        }

        @PreAuthorize("hasAnyAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface PodeCriar {

        }
    }

    @interface Cidades {

        @PreAuthorize("hasAnyAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface PodeConsultar {

        }

        @PreAuthorize("hasAnyAuthority('SCOPE_WRITE') and hasAnyAuthority('EDITAR_CIDADES')")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface PodeEditar {

        }
    }


    @interface Estados {

        @PreAuthorize("hasAnyAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface PodeConsultar {

        }

        @PreAuthorize("hasAnyAuthority('SCOPE_WRITE') and hasAnyAuthority('EDITAR_ESTADOS')")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface PodeEditar {

        }
    }

    @interface FormasPagamento {

        @PreAuthorize("hasAnyAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface PodeConsultar {

        }

        @PreAuthorize("hasAnyAuthority('SCOPE_WRITE') and hasAnyAuthority('EDITAR_FORMAS_PAGAMENTO')")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface PodeEditar {

        }
    }

    @interface UsuariosGruposPermissoes {

        @PreAuthorize("hasAnyAuthority('SCOPE_WRITE') and @algaSecurity.usuarioId == #usuarioId")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface PodeAlterarPropriaSenha {

        }

        @PreAuthorize("hasAnyAuthority('SCOPE_WRITE') and ("
            + "@algaSecurity.usuarioId == #usuarioId or "
            + "hasAnyAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES'))")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface PodeAlterarUsuario {

        }

        @PreAuthorize("hasAnyAuthority('SCOPE_READ') and hasAnyAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES')")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface PodeEditar {

        }

        @PreAuthorize("hasAnyAuthority('SCOPE_WRITE') and hasAnyAuthority('CONSULTAR_USUARIOS_GRUPOS_PERMISSOES')")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface PodeConsultar {

        }
    }

}
