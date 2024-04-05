package com.pruebatecnica.controller;

import com.pruebatecnica.DTO.EmailRequestDTO;
import com.pruebatecnica.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/secure/emails")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequestDTO emailRequest) throws IOException, MessagingException {
        return emailService.sendEmail(emailRequest.getTo(), emailRequest.getBody());
    }
}
