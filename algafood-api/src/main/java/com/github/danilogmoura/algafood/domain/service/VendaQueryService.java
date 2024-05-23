package com.github.danilogmoura.algafood.domain.service;

import com.github.danilogmoura.algafood.domain.filter.VendaDiariaFilter;
import com.github.danilogmoura.algafood.domain.model.dto.VendaDiaria;
import java.util.List;


public interface VendaQueryService {

    List<VendaDiaria> consultarVendasDIarias(VendaDiariaFilter filtro, String timeOffset);
}
