package com.piksenia.thymeleaf.pdfCreator;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;

@Service
public class ProfilePrintService {

    private final ServletContext servletContext;

    private final TemplateEngine templateEngine;

    public ProfilePrintService(TemplateEngine templateEngine, ServletContext servletContext){
        this.templateEngine = templateEngine;
        this.servletContext = servletContext;
    }

    public byte[] generateFormular(HttpServletRequest request, HttpServletResponse response, Profile generateContent) {
        WebContext context = new WebContext(request, response, servletContext);
        context.setVariable("profile", generateContent);
        String labelHtml = templateEngine.process("profile", context);

        return converterStringToPdf(labelHtml);
    }

    private byte[] converterStringToPdf(String html){
        ByteArrayOutputStream target = new ByteArrayOutputStream();
        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri("localhost:8080");
        HtmlConverter.convertToPdf(html, target, converterProperties);

        return target.toByteArray();
    }
}
