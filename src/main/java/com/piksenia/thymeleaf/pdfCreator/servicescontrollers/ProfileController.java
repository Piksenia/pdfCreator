package com.piksenia.thymeleaf.pdfCreator.servicescontrollers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(path = "/api/profile")
@AllArgsConstructor
public class ProfileController {

    private final ProfileCreationService profileCreationService;

    private final ProfilePrintService profilePrintService;

    @GetMapping(path= "/{profileName}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> generateSingleProfile(@PathVariable(required = false, name = "profileName") String profileName, HttpServletRequest request, HttpServletResponse response) {
        List<String> profileNames = new ArrayList<>();
        profileNames.add(profileName);
        byte[] profilePdf = profilePrintService.generateFormular(request, response, profileCreationService.initializeProfiles(profileNames));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=profile.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(profilePdf);
    }

    //Uncomment to send a post request with json array full of names, else test to see multiple profile generated (as much as profilenames are in the list)
    //@PostMapping(path="/", produces = MediaType.APPLICATION_PDF_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    //public ResponseEntity<?> generateMultipleProfile(List<String> profileNames, HttpServletRequest request, HttpServletResponse response) {
    @GetMapping(path="/", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> generateMultipleProfile(HttpServletRequest request, HttpServletResponse response) {
        List<String> profileNames = new ArrayList<>();
        profileNames.add("test01");
        profileNames.add("test02");
        byte[] profilePdf = profilePrintService.generateFormular(request, response, profileCreationService.initializeProfiles(profileNames));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=profile.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(profilePdf);
    }

}
