/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */package com.example.ventas;

import dtos.NuevaVentaDTO;
import exception.VentaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.util.List;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/ventas")
public class VentaController {

    private final VentaService ventaService;

    @Autowired
    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public ResponseEntity<List<clases.Venta>> obtenerTodasLasVentas() {
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

    @GetMapping("/filtrar")
    public ResponseEntity<List<NuevaVentaDTO>> obtenerVentasPorFechas(
            @RequestParam LocalDate desde,
            @RequestParam LocalDate hasta) {
        
        List<NuevaVentaDTO> ventas = ventaService.obtenerVentasPorRango(desde, hasta);
        return ResponseEntity.ok(ventas);
    }
    
    @GetMapping("/reporte/pdf")
    public ResponseEntity<byte[]> generarReportePDF(
            @RequestParam("desde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam("hasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        try {
            byte[] pdf = ventaService.generarReporteVentasPorFecha(desde, hasta);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ventas_" + desde + "_a_" + hasta + ".pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdf);
        } catch (VentaException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarVenta(@PathVariable String id, @RequestBody NuevaVentaDTO ventaDTO) {
        try {
            clases.Venta actualizada = ventaService.actualizarVenta(id, ventaDTO);
            return ResponseEntity.ok(actualizada);
        } catch (VentaException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

}
