package com.github.danilogmoura.algafood.auth.core;

import com.github.danilogmoura.algafood.auth.domain.Usuario;
import java.util.Collections;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;

@Getter
public class AuthUser extends User {

    private final String fullName;

    public AuthUser(Usuario usuario) {
        super(usuario.getEmail(), usuario.getSenha(), Collections.EMPTY_LIST);

        this.fullName = usuario.getNome();
    }
}
