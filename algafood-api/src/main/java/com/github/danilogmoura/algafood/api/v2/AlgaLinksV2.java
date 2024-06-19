package com.github.danilogmoura.algafood.api.v2;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.github.danilogmoura.algafood.api.v2.controller.CidadeControllerV2;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

@Component
public class AlgaLinksV2 {

    public Link linkToCidades() {
        return linkToCidades(IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToCidades(String rel) {
        return linkTo(CidadeControllerV2.class).withRel(rel);
    }

    public Link linkToCidade(Long cidadeId) {
        return linkToCidade(cidadeId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToCidade(Long cidadeId, String rel) {
        return linkTo(methodOn(CidadeControllerV2.class).buscar(cidadeId)).withRel(rel);
    }

}
