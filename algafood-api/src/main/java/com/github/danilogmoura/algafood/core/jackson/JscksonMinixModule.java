package com.github.danilogmoura.algafood.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.danilogmoura.algafood.api.model.mixin.GrupoMixin;
import com.github.danilogmoura.algafood.api.model.mixin.ItemPedidoMixin;
import com.github.danilogmoura.algafood.api.model.mixin.PedidoMixin;
import com.github.danilogmoura.algafood.api.model.mixin.PermissaoMixin;
import com.github.danilogmoura.algafood.api.model.mixin.ProdutoMixin;
import com.github.danilogmoura.algafood.api.model.mixin.UsuarioMixin;
import com.github.danilogmoura.algafood.domain.model.Grupo;
import com.github.danilogmoura.algafood.domain.model.ItemPedido;
import com.github.danilogmoura.algafood.domain.model.Pedido;
import com.github.danilogmoura.algafood.domain.model.Permissao;
import com.github.danilogmoura.algafood.domain.model.Produto;
import com.github.danilogmoura.algafood.domain.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class JscksonMinixModule extends SimpleModule {

    public JscksonMinixModule() {
        setMixInAnnotation(Grupo.class, GrupoMixin.class);
        setMixInAnnotation(ItemPedido.class, ItemPedidoMixin.class);
        setMixInAnnotation(Pedido.class, PedidoMixin.class);
        setMixInAnnotation(Permissao.class, PermissaoMixin.class);
        setMixInAnnotation(Produto.class, ProdutoMixin.class);
        setMixInAnnotation(Usuario.class, UsuarioMixin.class);
    }
}
