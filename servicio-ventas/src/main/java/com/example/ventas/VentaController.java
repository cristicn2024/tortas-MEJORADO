/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */package com.example.ventas;

import dtos.NuevaVentaDTO;
import exception.VentaException;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/v1/ventas")
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

    @GetMapping("/filtrar")
    public ResponseEntity<List<NuevaVentaDTO>> obtenerVentasPorFechas(
            @RequestParam LocalDate desde,
            @RequestParam LocalDate hasta) {
        
        List<NuevaVentaDTO> ventas = ventaService.obtenerVentasPorRango(desde, hasta);
        return ResponseEntity.ok(ventas);
    }
    
    @GetMapping("/reporte")
    public ResponseEntity<byte[]> generarReporteVentas(
            @RequestParam LocalDate desde,
            @RequestParam LocalDate hasta) throws IOException {

        List<Venta> ventas = ventaService.obtenerVentasEntreFechas(desde, hasta);
        int totalVentas = ventas.size();
        double totalGanado = ventas.stream().mapToDouble(Venta::getTotal).sum();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        PDPageContentStream stream = new PDPageContentStream(document, page);
        stream.beginText();
        stream.setFont(PDType1Font.HELVETICA_BOLD, 14);
        stream.newLineAtOffset(100, 700);
        stream.showText("REPORTE DE VENTAS");
        stream.newLineAtOffset(0, -20);
        stream.showText("Desde: " + desde.toString());
        stream.newLineAtOffset(0, -20);
        stream.showText("Hasta: " + hasta.toString());
        stream.newLineAtOffset(0, -20);
        stream.showText("Total de ventas: " + totalVentas);
        stream.newLineAtOffset(0, -20);
        stream.showText("Total ganado: $" + totalGanado);
        stream.endText();
        stream.close();

        document.save(output);
        document.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "reporte_ventas.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(output.toByteArray());
    }

    
}
