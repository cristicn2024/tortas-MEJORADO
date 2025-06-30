/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ventas;

import dtos.NuevaVentaDTO;
import exception.VentaException;
import com.example.ventas.Venta;
import com.example.ventas.VentaService;
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
    public ResponseEntity<?> registrarVenta(@RequestBody NuevaVentaDTO nuevaVentaDTO) {
        try {
            return ResponseEntity.ok(ventaService.registrarVenta(nuevaVentaDTO));
        } catch (VentaException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Long> contarVentas() {
        return ResponseEntity.ok(ventaService.contarVentas());
    }
}
