package com.github.danilogmoura.algafood.infrastructure.service.report;

import com.github.danilogmoura.algafood.domain.filter.VendaDiariaFilter;
import com.github.danilogmoura.algafood.domain.service.VendaQueryService;
import com.github.danilogmoura.algafood.domain.service.VendaReportService;
import java.util.HashMap;
import java.util.Locale;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PdfVendaReportService implements VendaReportService {

    @Autowired
    private VendaQueryService vendaQueryService;

    @Override
    public byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
        try {
            var inputStream = this.getClass().getResourceAsStream("/relatorios/vendas-diarias.jasper");

            var parametros = new HashMap<String, Object>();
            parametros.put("REPORT_LOCALE", Locale.of("pt", "BR"));

            var vendasDiarias = vendaQueryService.consultarVendasDIarias(filtro, timeOffset);
            var dataSource = new JRBeanCollectionDataSource(vendasDiarias);

            var jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new ReportException("Não foi possível emitir relatório de vendas diários", e);
        }
    }
}
