package com.pruebatecnica.service;

import com.pruebatecnica.DTO.EmailRequestDTO;
import com.pruebatecnica.config.AWSSESConfig;
import com.pruebatecnica.model.Empresa;
import com.pruebatecnica.model.Producto;
import com.pruebatecnica.repository.EmpresaRepository;
import com.pruebatecnica.repository.ProductoRepository;
import com.pruebatecnica.util.PDFGenerator;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class EmailService {

    @Autowired
    private AWSSESConfig awsSesConfig;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    public ResponseEntity<?> sendEmail(String to, String body) throws IOException, MessagingException {
        EmailRequestDTO emailRequest = new EmailRequestDTO();
        emailRequest.setTo(to);
        Optional<Empresa> empresa = empresaRepository.findById(body);
        if (empresa.isEmpty()) {
            return ResponseEntity.badRequest().body("Empresa no encontrada");
        }
        Producto[] products = productoRepository.findByEmpresaNit(empresa.get());
        String filePath = "products.pdf";
        try {
            PDFGenerator.generatePDF(products, filePath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("Error al generar el PDF");
        }
        File file = new File(filePath);
        emailRequest.setFile(file);
        return ResponseEntity.ok(awsSesConfig.sendSESEmail(emailRequest, filePath));
    }
}
