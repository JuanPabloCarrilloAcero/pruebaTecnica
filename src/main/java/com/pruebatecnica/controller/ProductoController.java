package com.pruebatecnica.controller;

import com.pruebatecnica.model.Producto;
import com.pruebatecnica.service.ProductoService;
import org.apache.tomcat.util.json.JSONFilter;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.json.JsonObject;

@RestController
@RequestMapping("/secure/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping("/save")
    public ResponseEntity<?> saveProducto(@RequestBody String productoString) {
        return productoService.saveProductoService(productoString);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllProductos() {
        return productoService.getAllProductosService();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getProductoById(@PathVariable String id) {
        return productoService.getProductoByIdService(id);
    }

    @GetMapping("/byEmpresa/{id}")
    public ResponseEntity<?> getProductosByEmpresa(@PathVariable String id) {
        return productoService.getProductosByEmpresaService(id);
    }

}
