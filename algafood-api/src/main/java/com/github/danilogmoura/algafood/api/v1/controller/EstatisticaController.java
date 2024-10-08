package com.github.danilogmoura.algafood.api.v1.controller;

import com.github.danilogmoura.algafood.api.v1.AlgaLinks;
import com.github.danilogmoura.algafood.api.v1.openapi.controller.EstatisticaControllerOpenApi;
import com.github.danilogmoura.algafood.core.security.CheckSecurity;
import com.github.danilogmoura.algafood.domain.filter.VendaDiariaFilter;
import com.github.danilogmoura.algafood.domain.model.dto.VendaDiaria;
import com.github.danilogmoura.algafood.domain.service.VendaQueryService;
import com.github.danilogmoura.algafood.domain.service.VendaReportService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/estatisticas")
public class EstatisticaController implements EstatisticaControllerOpenApi {

    @Autowired
    private VendaQueryService vendaQueryService;

    @Autowired
    private VendaReportService vendaReportService;

    @Autowired
    private AlgaLinks algaLinks;

    @CheckSecurity.Estatisticas.PodeConsultar
    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public EstatisticasEntryPointModel estatisticas() {
        var estatisticasEntryPointModel = new EstatisticasEntryPointModel();

        estatisticasEntryPointModel.add(algaLinks.linkToEstatisticasVendasDiarias("vendas-diarias"));

        return estatisticasEntryPointModel;
    }

    @CheckSecurity.Estatisticas.PodeConsultar
    @Override
    @GetMapping(value = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro,
        @RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {
        return vendaQueryService.consultarVendasDIarias(filtro, timeOffset);
    }

    @CheckSecurity.Estatisticas.PodeConsultar
    @Override
    @GetMapping(value = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtro,
        @RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {
        var bytesPdf = vendaReportService.emitirVendasDiarias(filtro, timeOffset);

        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diarias.pdf");

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).headers(headers).body(bytesPdf);
    }

    public static class EstatisticasEntryPointModel extends RepresentationModel<EstatisticasEntryPointModel> {

    }
}
