package com.github.danilogmoura.algafood.auth;

import javax.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@Component
@ConfigurationProperties("algafood.jwt.keystore")
public class JwtKeyStoreProperties {

    @NotBlank
    private String path;

    @NotBlank
    private String password;

    @NotBlank
    private String keypairAlias;


    public @NotBlank String getPath() {
        return path;
    }

    public void setPath(@NotBlank String path) {
        this.path = path;
    }

    public @NotBlank String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }

    public @NotBlank String getKeypairAlias() {
        return keypairAlias;
    }

    public void setKeypairAlias(@NotBlank String keypairAlias) {
        this.keypairAlias = keypairAlias;
    }
}
