package com.github.danilogmoura.algafood.auth.core;

import com.github.danilogmoura.algafood.auth.domain.Usuario;
import java.util.Collections;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;

@Getter
public class AuthUser extends User {

    private static final long serialVersionUID = 1L;

    private Long userId;
    private String fullName;

    public AuthUser(Usuario usuario) {
        super(usuario.getEmail(), usuario.getSenha(), Collections.emptyList());

        this.userId = usuario.getId();
        this.fullName = usuario.getNome();
    }

}
