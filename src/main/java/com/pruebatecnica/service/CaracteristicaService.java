package com.pruebatecnica.service;

import com.pruebatecnica.model.Caracteristica;
import com.pruebatecnica.repository.CaracteristicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CaracteristicaService {

    @Autowired
    private CaracteristicaRepository caracteristicaRepository;

    public ResponseEntity<?> saveCaracteristicaService(Caracteristica caracteristica) {
        try {
            Caracteristica savedCaracteristica = caracteristicaRepository.save(caracteristica);
            return ResponseEntity.ok(savedCaracteristica);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error guardando caracteristica: " + e.getMessage());
        }
    }

    public ResponseEntity<?> getAllCaracteristicasService() {
        try {
            return ResponseEntity.ok(caracteristicaRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error recuperando caracteristicas: " + e.getMessage());
        }
    }

    public ResponseEntity<?> getCaracteristicaByIdService(Long id) {
        try {
            return ResponseEntity.ok(caracteristicaRepository.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error recuperando caracteristica: " + e.getMessage());
        }
    }
}
