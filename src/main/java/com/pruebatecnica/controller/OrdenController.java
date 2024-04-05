package com.pruebatecnica.controller;


import com.pruebatecnica.model.Orden;
import com.pruebatecnica.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secure/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @PostMapping("/save")
    public ResponseEntity<?> saveOrden(@RequestBody String ordenString) {
        return ordenService.saveOrdenService(ordenString);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllOrdenes() {
        return ordenService.getAllOrdenesService();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getOrdenById(@PathVariable Long id) {
        return ordenService.getOrdenByIdService(id);
    }
}
