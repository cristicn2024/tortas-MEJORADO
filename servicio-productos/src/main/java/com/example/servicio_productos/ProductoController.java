/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.servicio_productos;

import com.example.servicio_productos.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.util.List;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<clases.Producto> obtenerTodos() {
        return productoService.obtenerTodos();
    }

    @PostMapping
    public clases.Producto guardar(@RequestBody clases.Producto producto) {
        return productoService.guardar(producto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<clases.Producto> obtenerPorId(@PathVariable String id) {
        return productoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable String id) {
        productoService.eliminar(id);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<clases.Producto> actualizarProducto(@PathVariable String id, @RequestBody clases.Producto producto) {
        clases.Producto actualizado = productoService.actualizarProducto(id, producto);
        return ResponseEntity.ok(actualizado);
    }
    
    @GetMapping("/reporte/pdf")
    public ResponseEntity<byte[]> descargarReportePDF() {
        byte[] pdf = productoService.generarReporteProductosPDF();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=productos.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }


}