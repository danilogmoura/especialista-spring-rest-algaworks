package com.github.danilogmoura.algafood.domain.service;

import com.github.danilogmoura.algafood.domain.filter.VendaDiariaFilter;


public interface VendaReportService {

    byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
}
