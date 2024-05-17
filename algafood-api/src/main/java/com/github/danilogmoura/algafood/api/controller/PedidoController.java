package com.github.danilogmoura.algafood.api.controller;

import com.github.danilogmoura.algafood.api.assembler.PedidoInputDisassembler;
import com.github.danilogmoura.algafood.api.assembler.PedidoModelAssembler;
import com.github.danilogmoura.algafood.api.assembler.PedidoResumoModelAssembler;
import com.github.danilogmoura.algafood.api.model.PedidoModel;
import com.github.danilogmoura.algafood.api.model.PedidoResumoModel;
import com.github.danilogmoura.algafood.api.model.input.PedidoInput;
import com.github.danilogmoura.algafood.domain.model.Usuario;
import com.github.danilogmoura.algafood.domain.repository.PedidoRepository;
import com.github.danilogmoura.algafood.domain.repository.filter.PedidoFilter;
import com.github.danilogmoura.algafood.domain.service.EmissaoPedidoService;
import com.github.danilogmoura.algafood.domain.service.PedidoService;
import com.github.danilogmoura.algafood.infrastructure.repository.spec.PedidoSpecs;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
public class PedidoController {

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
    public Page<PedidoResumoModel> pesquisar(PedidoFilter filtro, @PageableDefault(size = 10) Pageable pageable) {
        var pedidosPage = pedidoRepository.findAll(PedidoSpecs.comFreteGratis(filtro), pageable);
        var pedidosModel = pedidoResumoModelAssembler.toCollectionModel(pedidosPage.getContent());
        return new PageImpl<>(pedidosModel, pageable, pedidosPage.getTotalElements());
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
