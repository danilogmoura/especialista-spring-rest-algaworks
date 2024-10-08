package com.github.danilogmoura.algafood.core.springfox;

import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.danilogmoura.algafood.api.exceptionhandler.Problem;
import com.github.danilogmoura.algafood.api.v1.model.CidadeModel;
import com.github.danilogmoura.algafood.api.v1.model.CozinhaModel;
import com.github.danilogmoura.algafood.api.v1.model.EstadoModel;
import com.github.danilogmoura.algafood.api.v1.model.FormaPagamentoModel;
import com.github.danilogmoura.algafood.api.v1.model.GrupoModel;
import com.github.danilogmoura.algafood.api.v1.model.PedidoResumoModel;
import com.github.danilogmoura.algafood.api.v1.model.PermissaoModel;
import com.github.danilogmoura.algafood.api.v1.model.ProdutoModel;
import com.github.danilogmoura.algafood.api.v1.model.RestauranteBasicoModel;
import com.github.danilogmoura.algafood.api.v1.model.UsuarioModel;
import com.github.danilogmoura.algafood.api.v1.openapi.model.CidadesModelOpenApi;
import com.github.danilogmoura.algafood.api.v1.openapi.model.CozinhasModelOpenApi;
import com.github.danilogmoura.algafood.api.v1.openapi.model.EstadosModelOpenApi;
import com.github.danilogmoura.algafood.api.v1.openapi.model.FormasPagamentoModelOpenApi;
import com.github.danilogmoura.algafood.api.v1.openapi.model.GruposModelOpenApi;
import com.github.danilogmoura.algafood.api.v1.openapi.model.LinksModelOpenApi;
import com.github.danilogmoura.algafood.api.v1.openapi.model.PageableModelOpenApi;
import com.github.danilogmoura.algafood.api.v1.openapi.model.PedidosModelOpenApi;
import com.github.danilogmoura.algafood.api.v1.openapi.model.PedidosResumoModelOpenApi;
import com.github.danilogmoura.algafood.api.v1.openapi.model.PermissoesModelOpenApi;
import com.github.danilogmoura.algafood.api.v1.openapi.model.ProdutosModelOpenApi;
import com.github.danilogmoura.algafood.api.v1.openapi.model.RestaurantesBasicoModelOpenApi;
import com.github.danilogmoura.algafood.api.v1.openapi.model.UsuariosModelOpenApi;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.ServletWebRequest;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RepresentationBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.HttpAuthenticationScheme;
import springfox.documentation.service.Response;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {

    @Bean
    public Docket apiDocketV1() {
        var typeResolver = new TypeResolver();

        return new Docket(DocumentationType.OAS_30)
            .groupName("V1")
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.github.danilogmoura.algafood.api"))
            .paths(PathSelectors.ant("/v1/**"))
            .paths(PathSelectors.any())
            .build()
            .securityContexts(Collections.singletonList(securityContext()))
            .securitySchemes(List.of(authenticationScheme()))
            .securityContexts(List.of(securityContext()))
            .useDefaultResponseMessages(false)
            .globalResponses(HttpMethod.GET, globalGetResponseMessages())
            .globalResponses(HttpMethod.POST, globalPostPutResponseMessages())
            .globalResponses(HttpMethod.PUT, globalPostPutResponseMessages())
            .globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
            .additionalModels(typeResolver.resolve(Problem.class))
            .ignoredParameterTypes(ServletWebRequest.class,
                URL.class, URI.class, URLStreamHandler.class, Resource.class, File.class, InputStream.class)
            .directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
            .directModelSubstitute(Links.class, LinksModelOpenApi.class)
            .alternateTypeRules(AlternateTypeRules.newRule(typeResolver
                .resolve(PagedModel.class, CozinhaModel.class), CozinhasModelOpenApi.class))
            .alternateTypeRules(AlternateTypeRules.newRule(typeResolver
                .resolve(Page.class, PedidoResumoModel.class), PedidosModelOpenApi.class))
            .alternateTypeRules(AlternateTypeRules.newRule(typeResolver
                .resolve(CollectionModel.class, CidadeModel.class), CidadesModelOpenApi.class))
            .alternateTypeRules(AlternateTypeRules.newRule(typeResolver
                .resolve(CollectionModel.class, EstadoModel.class), EstadosModelOpenApi.class))
            .alternateTypeRules(AlternateTypeRules.newRule(typeResolver
                .resolve(CollectionModel.class, FormaPagamentoModel.class), FormasPagamentoModelOpenApi.class))
            .alternateTypeRules(AlternateTypeRules.newRule(typeResolver
                .resolve(CollectionModel.class, GrupoModel.class), GruposModelOpenApi.class))
            .alternateTypeRules(AlternateTypeRules.newRule(typeResolver
                .resolve(CollectionModel.class, PermissaoModel.class), PermissoesModelOpenApi.class))
            .alternateTypeRules(AlternateTypeRules.newRule(typeResolver
                .resolve(PagedModel.class, PedidoResumoModel.class), PedidosResumoModelOpenApi.class))
            .alternateTypeRules(AlternateTypeRules.newRule(typeResolver
                .resolve(CollectionModel.class, ProdutoModel.class), ProdutosModelOpenApi.class))
            .alternateTypeRules(AlternateTypeRules.newRule(typeResolver
                .resolve(CollectionModel.class, RestauranteBasicoModel.class), RestaurantesBasicoModelOpenApi.class))
            .alternateTypeRules(AlternateTypeRules.newRule(typeResolver
                .resolve(CollectionModel.class, UsuarioModel.class), UsuariosModelOpenApi.class))

            .apiInfo(apiInfoV1())

            .tags(new Tag("Cidades", "Gerencia as cidades"),
                new Tag("Grupos", "Gerencia os grupos de usuários"),
                new Tag("Cozinhas", "Gerencia as cozinhas"),
                new Tag("Formas Pagamento", "Gerencia as formas de pagamento"),
                new Tag("Pedidos", "Gerencia os pedidos"),
                new Tag("Restaurantes", "Gerencia os restaurante"),
                new Tag("Estados", "Gerencia os estados"),
                new Tag("Produtos", "Gerencia os produtos"),
                new Tag("Usuários", "Gerencia os usuários"),
                new Tag("Estatísticas", "Estatísticas da AlgaFood"),
                new Tag("Permissões", "Gerencia as permissões")
            );
    }

    private List<Response> globalGetResponseMessages() {
        return Arrays.asList(
            new ResponseBuilder()
                .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .description("Erro interno do Servidor")
                .build(),
            new ResponseBuilder()
                .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                .description("Recurso não possui representação que pode ser aceita pelo consumidor")
                .build()
        );
    }

    private List<Response> globalPostPutResponseMessages() {
        return Arrays.asList(
            new ResponseBuilder()
                .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .description("Requisição inválida (erro do cliente)")
                .representation(MediaType.APPLICATION_JSON)
                .apply(getProblemaModelReference())
                .build(),
            new ResponseBuilder()
                .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .description("Erro interno no servidor")
                .representation(MediaType.APPLICATION_JSON)
                .apply(getProblemaModelReference())
                .build(),
            new ResponseBuilder()
                .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                .description("Recurso não possui representação que poderia ser aceita pelo consumidor")
                .build(),
            new ResponseBuilder()
                .code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
                .description("Requisição recusada porque o corpo está em um formato não suportado")
                .representation(MediaType.APPLICATION_JSON)
                .apply(getProblemaModelReference())
                .build()
        );
    }

    private List<Response> globalDeleteResponseMessages() {
        return Arrays.asList(
            new ResponseBuilder()
                .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .description("Requisição inválida (erro do cliente)")
                .representation(MediaType.APPLICATION_JSON)
                .apply(getProblemaModelReference())
                .build(),
            new ResponseBuilder()
                .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .description("Erro interno no servidor")
                .representation(MediaType.APPLICATION_JSON)
                .apply(getProblemaModelReference())
                .build()
        );
    }

    public ApiInfo apiInfoV1() {
        return new ApiInfoBuilder()
            .title("Algafood API")
            .description("API aberta para clientes e restaurantes.<br>")
            .version("1.0")
            .contact(new Contact("Algaworks", "https://www.algaworks.com", "contato@algaworks.com"))
            .build();
    }

    public ApiInfo apiInfoV2() {
        return new ApiInfoBuilder()
            .title("Algafood API")
            .description("API aberta para clientes e restaurantes")
            .version("2.0")
            .contact(new Contact("Algaworks", "https://www.algaworks.com", "contato@algaworks.com"))
            .build();
    }

    @Bean
    public JacksonModuleRegistrar springFoxJacksonConfig() {
        return objectMapper -> objectMapper.registerModule(new JavaTimeModule());
    }

    private Consumer<RepresentationBuilder> getProblemaModelReference() {
        return r -> r.model(m -> m.name("Problema")
            .referenceModel(ref -> ref.key(k -> k.qualifiedModelName(
                q -> q.name("Problema").namespace("com.github.danilogmoura.algafood.api.exceptionhandler")))));
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
            .securityReferences(securityReference()).build();
    }

    private List<SecurityReference> securityReference() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(new SecurityReference("Authorization", authorizationScopes));
    }

    private HttpAuthenticationScheme authenticationScheme() {
        return HttpAuthenticationScheme.JWT_BEARER_BUILDER.name("Authorization").build();
    }

}
