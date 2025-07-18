/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.reporte;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api/v1/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @PostMapping("/generar")
    public ResponseEntity<byte[]> generarReporte(
        @RequestBody FiltroReporteDTO filtro,
        @RequestHeader("Authorization") String authorizationHeader) throws IOException {

        byte[] pdf = reporteService.generarReportePDF(
                filtro.getFechaInicio(),
                filtro.getFechaFin(),
                authorizationHeader // <- PASAMOS el token
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "reporte.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdf);
    }


}
