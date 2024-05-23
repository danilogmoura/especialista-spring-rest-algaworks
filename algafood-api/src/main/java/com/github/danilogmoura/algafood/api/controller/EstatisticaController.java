package com.github.danilogmoura.algafood.api.controller;

import com.github.danilogmoura.algafood.domain.filter.VendaDiariaFilter;
import com.github.danilogmoura.algafood.domain.model.dto.VendaDiaria;
import com.github.danilogmoura.algafood.domain.service.VendaQueryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticaController {

    @Autowired
    private VendaQueryService vendaQueryService;


    @GetMapping("/vendas-diarias")
    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter) {
        return vendaQueryService.consultarVendasDIarias(filter);
    }
}
