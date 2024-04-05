package com.pruebatecnica.controller;

import com.pruebatecnica.model.Caracteristica;
import com.pruebatecnica.service.CaracteristicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secure/caracteristicas")
public class CaracteristicaController {

    @Autowired
    private CaracteristicaService caracteristicaService;

    @PostMapping("/save")
    public ResponseEntity<?> saveCaracteristica(@RequestBody Caracteristica caracteristica) {
        return caracteristicaService.saveCaracteristicaService(caracteristica);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCaracteristicas() {
        return caracteristicaService.getAllCaracteristicasService();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getCaracteristicaById(@PathVariable Long id) {
        return caracteristicaService.getCaracteristicaByIdService(id);
    }
}
