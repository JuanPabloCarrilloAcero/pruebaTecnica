package com.pruebatecnica.service;

import com.pruebatecnica.model.Empresa;
import com.pruebatecnica.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public ResponseEntity<?> saveEmpresaService(Empresa empresa) {
        try {
            Empresa savedEmpresa = empresaRepository.save(empresa);
            return ResponseEntity.ok(savedEmpresa);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error guardando empresa: " + e.getMessage());
        }
    }

    public ResponseEntity<?> getAllEmpresasService() {
        try {
            return ResponseEntity.ok(empresaRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error recuperando empresas: " + e.getMessage());
        }
    }

    public ResponseEntity<?> getEmpresaByIdService(String id) {
        try {
            return ResponseEntity.ok(empresaRepository.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error recuperando empresa: " + e.getMessage());
        }
    }

    public ResponseEntity<?> deleteEmpresaByIdService(String id) {
        try {
            empresaRepository.deleteById(id);
            return ResponseEntity.ok("Empresa eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error recuperando empresa: " + e.getMessage());
        }
    }

}
