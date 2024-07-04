package com.github.danilogmoura.algafood.infrastructure.email;

import com.github.danilogmoura.algafood.domain.service.EnvioEmailService.Mensagem;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Component
public class ProcessadorEmailTemplate {

    @Autowired
    private Configuration freemarkerCondfig;

    protected String processarTemplate(Mensagem mensagem) {
        try {
            var template = freemarkerCondfig.getTemplate(mensagem.getCorpo());

            return FreeMarkerTemplateUtils.processTemplateIntoString(template, mensagem.getVariaveis());
        } catch (Exception e) {
            throw new EmailException("Não foi possível montar o template do e-mail", e);
        }
    }
}
