package com.pruebatecnica.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pruebatecnica.model.Orden;
import com.pruebatecnica.model.Producto;
import com.pruebatecnica.repository.OrdenRepository;
import com.pruebatecnica.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public ResponseEntity<?> saveOrdenService(String ordenString) {
        try {
            JsonObject ordenJson = JsonParser.parseString(ordenString).getAsJsonObject();

            Orden orden = new Orden();

            if (ordenJson.get("id") != null){
                orden.setId(ordenJson.get("id").getAsLong());
            }
            orden.setNombre(ordenJson.get("nombre").getAsString());

            orden.setProductos(new java.util.HashSet<>());

            JsonArray productos = ordenJson.get("productos").getAsJsonArray();
            System.out.println(productos);
            for (int i = 0; i < productos.size(); i++) {
                Optional<Producto> producto = productoRepository.findById(productos.get(i).getAsString());
                producto.ifPresent(value -> orden.getProductos().add(value));
            }

            Orden savedOrden = ordenRepository.save(orden);
            return ResponseEntity.ok(savedOrden);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error guardando orden: " + e.getMessage());
        }
    }

    public ResponseEntity<?> getAllOrdenesService() {
        try {
            return ResponseEntity.ok(ordenRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error recuperando ordenes: " + e.getMessage());
        }
    }

    public ResponseEntity<?> getOrdenByIdService(Long id) {
        try {
            return ResponseEntity.ok(ordenRepository.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error recuperando orden: " + e.getMessage());
        }
    }
}
