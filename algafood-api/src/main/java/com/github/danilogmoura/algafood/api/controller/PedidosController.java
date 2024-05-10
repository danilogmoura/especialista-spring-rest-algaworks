package com.github.danilogmoura.algafood.api.controller;

import com.github.danilogmoura.algafood.api.assembler.PedidoModelAssembler;
import com.github.danilogmoura.algafood.api.model.PedidoModel;
import com.github.danilogmoura.algafood.domain.repository.PedidoRepository;
import com.github.danilogmoura.algafood.domain.service.PedidoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @GetMapping
    public List<PedidoModel> listar() {
        return pedidoModelAssembler.toCollectionModel(pedidoRepository.findAll());
    }

    @GetMapping("/{pedidoId}")
    public PedidoModel buscar(@PathVariable Long pedidoId) {
        var pedido = pedidoService.buscarOuFalhar(pedidoId);
        return pedidoModelAssembler.toModel(pedido);
    }
}
