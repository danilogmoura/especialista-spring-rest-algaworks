package com.github.danilogmoura.algafood.core.security.authotizationserver;

import com.nimbusds.jose.jwk.JWKSet;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwKSetController {

    @Autowired
    private JWKSet jwkSet;

    @GetMapping("/.well-known/jwks.json")
    public Map<String, Object> keys() {
        System.out.println("JWKS Endpoint");
        return jwkSet.toJSONObject();
    }
}
