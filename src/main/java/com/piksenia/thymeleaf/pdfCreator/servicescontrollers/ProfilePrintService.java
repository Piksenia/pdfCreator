package com.piksenia.thymeleaf.pdfCreator.servicescontrollers;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.piksenia.thymeleaf.pdfCreator.models.Profile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@AllArgsConstructor
public class ProfilePrintService {

    private final ServletContext servletContext;

    private final TemplateEngine templateEngine;

    public byte[] generateFormular(HttpServletRequest request, HttpServletResponse response, List<Profile> generateContent) {
        WebContext context = new WebContext(request, response, servletContext);
        context.setVariable("profileList", generateContent);
        String labelHtml = templateEngine.process("profile", context);

        return converterStringToPdf(labelHtml);
    }

    private byte[] converterStringToPdf(String html){
        ByteArrayOutputStream target = new ByteArrayOutputStream();
        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri("http://localhost:8080");
        HtmlConverter.convertToPdf(html, target, converterProperties);

        return target.toByteArray();
    }
}
