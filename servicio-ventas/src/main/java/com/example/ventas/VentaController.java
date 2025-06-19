/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ventas.controller;

import com.example.ventas.dto.NuevaVentaDTO;
import com.example.ventas.exception.VentaException;
import com.example.ventas.model.Venta;
import com.example.ventas.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {
    private final VentaService ventaService;
    
    @Autowired
    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }
    
    @GetMapping
    public ResponseEntity<List<Venta>> obtenerTodasLasVentas() {
        return ResponseEntity.ok(ventaService.obtenerTodasLasVentas());
    }
    
    @PostMapping
    public ResponseEntity<Venta> registrarVenta(@RequestBody NuevaVentaDTO nuevaVentaDTO) {
        try {
            return ResponseEntity.ok(ventaService.registrarVenta(nuevaVentaDTO));
        } catch (VentaException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    @GetMapping("/count")
    public ResponseEntity<Long> contarVentas() {
        return ResponseEntity.ok(ventaService.contarVentas());
    }
}