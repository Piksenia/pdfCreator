package com.piksenia.thymeleaf.pdfCreator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(path = "/api/profile")
public class ProfileController {

    @Autowired
    ProfileCreationService profileCreationService;

    @Autowired
    ProfilePrintService profilePrintService;

    @GetMapping(path= "/{profileName}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> generatePikseniaProfile(@PathVariable(required = false, name = "profileName") String profileName, HttpServletRequest request, HttpServletResponse response) {
        byte[] profilePdf = profilePrintService.generateFormular(request, response, profileCreationService.generateContent(profileName));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=order.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(profilePdf);
    }
}
