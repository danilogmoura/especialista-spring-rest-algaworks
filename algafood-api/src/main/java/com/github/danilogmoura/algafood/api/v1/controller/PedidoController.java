package com.github.danilogmoura.algafood.api.v1.controller;

import com.github.danilogmoura.algafood.api.v1.assembler.PedidoInputDisassembler;
import com.github.danilogmoura.algafood.api.v1.assembler.PedidoModelAssembler;
import com.github.danilogmoura.algafood.api.v1.assembler.PedidoResumoModelAssembler;
import com.github.danilogmoura.algafood.api.v1.model.PedidoModel;
import com.github.danilogmoura.algafood.api.v1.model.PedidoResumoModel;
import com.github.danilogmoura.algafood.api.v1.model.input.PedidoInput;
import com.github.danilogmoura.algafood.api.v1.openapi.controller.PedidoControllerOpenApi;
import com.github.danilogmoura.algafood.core.data.PageWrapper;
import com.github.danilogmoura.algafood.core.data.PageableTranslator;
import com.github.danilogmoura.algafood.core.security.AlgaSecurity;
import com.github.danilogmoura.algafood.core.security.CheckSecurity;
import com.github.danilogmoura.algafood.domain.filter.PedidoFilter;
import com.github.danilogmoura.algafood.domain.model.Pedido;
import com.github.danilogmoura.algafood.domain.model.Usuario;
import com.github.danilogmoura.algafood.domain.repository.PedidoRepository;
import com.github.danilogmoura.algafood.domain.service.EmissaoPedidoService;
import com.github.danilogmoura.algafood.domain.service.PedidoService;
import com.github.danilogmoura.algafood.infrastructure.repository.spec.PedidoSpecs;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/pedidos")
public class PedidoController implements PedidoControllerOpenApi {

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

    @Autowired
    private PagedResourcesAssembler<Pedido> pagedResourcesAssembler;

    @Autowired
    private AlgaSecurity algaSecurity;


    @CheckSecurity.Pedidos.PodePesquisar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro, @PageableDefault(size = 10) Pageable pageable) {
        var pageableTraduzido = traduzirPageable(pageable);

        var pedidosPage = pedidoRepository.findAll(PedidoSpecs.comFreteGratis(filtro), pageableTraduzido);

        pedidosPage = new PageWrapper<>(pedidosPage, pageable);

        return pagedResourcesAssembler.toModel(pedidosPage, pedidoResumoModelAssembler);
    }

    @CheckSecurity.Pedidos.PodeBuscar
    @GetMapping(path = "/{codigoPedido}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PedidoModel buscar(@PathVariable String codigoPedido) {
        var pedido = pedidoService.buscarOuFalhar(codigoPedido);
        return pedidoModelAssembler.toModel(pedido);
    }

    @CheckSecurity.Pedidos.PodeCriar
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModel adicionar(@RequestBody @Valid PedidoInput pedidoInput) {
        var novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

        var cliente = new Usuario();
        cliente.setId(algaSecurity.getUsuarioId());

        novoPedido.setCliente(cliente);
        novoPedido = emissaoPedidoService.emitir(novoPedido);
        return pedidoModelAssembler.toModel(novoPedido);
    }

    private Pageable traduzirPageable(Pageable apiPageable) {
        var mapeamento = Map.of(
            "codio", "codigo",
            "subtotal", "subtotal",
            "restaurante.nome", "restaurante.nome",
            "nomeCliente", "cliente.nome",
            "valorTotal", "valorTotal"
        );

        return PageableTranslator.translate(apiPageable, mapeamento);
    }
}
