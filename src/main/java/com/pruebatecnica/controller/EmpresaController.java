package com.pruebatecnica.controller;

import com.pruebatecnica.model.Empresa;
import com.pruebatecnica.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secure/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @PostMapping("/save")
    public ResponseEntity<?> saveEmpresa(@RequestBody Empresa empresa) {
        return empresaService.saveEmpresaService(empresa);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllEmpresas() {
        return empresaService.getAllEmpresasService();
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<?> getEmpresaById(@PathVariable String id) {
        return empresaService.getEmpresaByIdService(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmpresaById(@PathVariable String id) {
        return empresaService.deleteEmpresaByIdService(id);
    }

}
