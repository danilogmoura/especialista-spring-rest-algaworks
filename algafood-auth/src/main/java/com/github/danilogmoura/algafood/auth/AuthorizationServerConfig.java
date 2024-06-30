package com.github.danilogmoura.algafood.auth;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
            .inMemory()

            .withClient("algafood-web")
            .secret(passwordEncoder.encode("web123"))
            .authorizedGrantTypes("password", "refresh_token")
            .scopes("write", "read")
            .accessTokenValiditySeconds(60 * 60 * 6)        // 6 horas (padrão são 12 horas)
            .refreshTokenValiditySeconds(60 * 24 * 60 * 60) // 60 dias

            .and()
            .withClient("foodanalytics")
            .secret(passwordEncoder.encode(""))
            .authorizedGrantTypes("authorization_code")
            .scopes("write", "read")
            .redirectUris("http://localhost:8082")

            //localhost:8081/oauth/authorize?response_type=code&client_id=foodanalytics&state=abc&redirect_uri=http://localhost:8082

            /*
             * PKCE - Plain
             * Code Verifier:  9djqash1NKdmqalLN
             * Code Challenge: 9djqash1NKdmqalLN
             *
             * localhost:8081/oauth/authorize?response_type=code&client_id=foodanalytics&redirect_uri=http://localhost:8082&code_challenge=9djqash1NKdmqalLN&code_challenge_method=plain
             *
             * PKCE - SHA-256
             * Code Verifier:   fAySfEj74uQNOxzuEqMUHk1kYyTYoDYZ39nQDipZakw
             * Code Challenge:  base64url(sha256("fAySfEj74uQNOxzuEqMUHk1kYyTYoDYZ39nQDipZakw"))
             *                  hxJEjAxtLwpUGzYvgSRLZu0-GTNKSF6BRiVgAIJM8N8
             *
             * localhost:8081/oauth/authorize?response_type=code&client_id=foodanalytics&redirect_uri=http://localhost:8082&code_challenge=hxJEjAxtLwpUGzYvgSRLZu0-GTNKSF6BRiVgAIJM8N8&code_challenge_method=s256
             */

            .and()
            .withClient("webadmin")
            .authorizedGrantTypes("implicit")
            .scopes("write", "read")
            .redirectUris("http://aplicacao-cliente")

            //localhost:8081/oauth/authorize?response_type=token&client_id=webadmin&state=abc&redirect_uri=http://aplicacao-cliente

            .and()
            .withClient("faturamento")
            .secret(passwordEncoder.encode("faturamento123"))
            .authorizedGrantTypes("client_credentials")
            .scopes("write", "read")

            .and()
            .withClient("ckecktoken")
            .secret(passwordEncoder.encode("ckeck123"));
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security.checkTokenAccess("isAuthenticated()");
        security.checkTokenAccess("permitAll()")
            .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
            .authenticationManager(authenticationManager)
            .userDetailsService(userDetailsService)
            .reuseRefreshTokens(false)
            .accessTokenConverter(jwtAccessTokenConverter())
            .tokenGranter(tokenGranter(endpoints));
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();

        jwtAccessTokenConverter.setSigningKey("algaworks");

        return jwtAccessTokenConverter;
    }

    private TokenGranter tokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
        var pkceAuthorizationCodeTokenGranter = new PkceAuthorizationCodeTokenGranter(endpoints.getTokenServices(),
            endpoints.getAuthorizationCodeServices(), endpoints.getClientDetailsService(),
            endpoints.getOAuth2RequestFactory());

        var granters = Arrays.asList(
            pkceAuthorizationCodeTokenGranter, endpoints.getTokenGranter());

        return new CompositeTokenGranter(granters);
    }

}
