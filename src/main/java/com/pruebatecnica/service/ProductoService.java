package com.pruebatecnica.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pruebatecnica.model.Caracteristica;
import com.pruebatecnica.model.Empresa;
import com.pruebatecnica.model.Producto;
import com.pruebatecnica.repository.CaracteristicaRepository;
import com.pruebatecnica.repository.EmpresaRepository;
import com.pruebatecnica.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private CaracteristicaRepository caracteristicaRepository;

    public ResponseEntity<?> saveProductoService(String productoString) {
        try {
            JsonObject producto = JsonParser.parseString(productoString).getAsJsonObject();

            Producto newProducto = new Producto();

            newProducto.setCaracteristicas(new java.util.HashSet<>());

            newProducto.setCodigo(producto.get("codigo").getAsString());
            newProducto.setNombre(producto.get("nombre").getAsString());
            newProducto.setMoneda(producto.get("moneda").getAsString());
            newProducto.setPrecio(producto.get("precio").getAsString());
            Optional<Empresa> empresa = empresaRepository.findById(producto.get("empresa").getAsString());
            if (empresa.isEmpty()) {
                newProducto.setEmpresaNit(null);
            } else {
                newProducto.setEmpresaNit(empresa.get());
            }
            JsonArray caracteristicas = producto.get("caracteristicas").getAsJsonArray();
            for (int i = 0; i < caracteristicas.size(); i++) {
                Optional<Caracteristica> caracteristica = caracteristicaRepository.findById(caracteristicas.get(i).getAsLong());
                caracteristica.ifPresent(value -> newProducto.getCaracteristicas().add(value));
            }
            Producto savedProducto = productoRepository.save(newProducto);
            return ResponseEntity.ok(savedProducto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error guardando producto: " + e.getMessage());
        }
    }

    public ResponseEntity<?> getAllProductosService() {
        try {
            return ResponseEntity.ok(productoRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error recuperando productos: " + e.getMessage());
        }
    }

    public ResponseEntity<?> getProductoByIdService(String id) {
        try {
            return ResponseEntity.ok(productoRepository.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error recuperando producto: " + e.getMessage());
        }
    }

    public ResponseEntity<?> getProductosByEmpresaService(String id) {
        try {
            Optional<Empresa> empresa = empresaRepository.findById(id);

            if (empresa.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa no encontrada");
            } else {
                List<Producto> productos = List.of(productoRepository.findByEmpresaNit(empresa.get()));
                return ResponseEntity.ok(productos);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error recuperando productos: " + e.getMessage());
        }
    }

}
