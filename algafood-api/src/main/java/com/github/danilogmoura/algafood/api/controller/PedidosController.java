package com.github.danilogmoura.algafood.api.controller;

import com.github.danilogmoura.algafood.api.assembler.PedidoInputDisassembler;
import com.github.danilogmoura.algafood.api.assembler.PedidoModelAssembler;
import com.github.danilogmoura.algafood.api.assembler.PedidoResumoModelAssembler;
import com.github.danilogmoura.algafood.api.model.PedidoModel;
import com.github.danilogmoura.algafood.api.model.PedidoResumoModel;
import com.github.danilogmoura.algafood.api.model.input.PedidoInput;
import com.github.danilogmoura.algafood.domain.model.Usuario;
import com.github.danilogmoura.algafood.domain.repository.PedidoRepository;
import com.github.danilogmoura.algafood.domain.service.EmissaoPedidoService;
import com.github.danilogmoura.algafood.domain.service.PedidoService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @Autowired
    private PedidoResumoModelAssembler pedidoResumoModelAssembler;

    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;

    @Autowired
    private EmissaoPedidoService emissaoPedidoService;

    @GetMapping
    public List<PedidoResumoModel> listar() {
        return pedidoResumoModelAssembler.toCollectionModel(pedidoRepository.findAll());
    }

    @GetMapping("/{codigo}")
    public PedidoModel buscar(@PathVariable String codigo) {
        var pedido = pedidoService.buscarOuFalhar(codigo);
        return pedidoModelAssembler.toModel(pedido);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModel adicionar(@RequestBody @Valid PedidoInput pedidoInput) {
        var novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);
        var cliente = new Usuario();
        cliente.setId(1L);

        novoPedido.setCliente(cliente);
        novoPedido = emissaoPedidoService.emitir(novoPedido);
        return pedidoModelAssembler.toModel(novoPedido);
    }
}
