/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.servicio_ordenes;
import java.util.List;
import exception.FindException;
import exception.PersistenciaException;
import dtos.NuevaOrdenDTO;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/v1/ordenes")
public class OrdenController {
    
    @Autowired
    private OrdenService ordenService;
    
    @GetMapping
    public List<clases.Orden> obtenerOrdenes() throws FindException {
        return ordenService.obtenerOrdenes();
    }
    
    @PostMapping
    public clases.Orden registrarOrden(@RequestBody NuevaOrdenDTO ordenDTO) 
            throws PersistenciaException {
        return ordenService.registrarOrden(ordenDTO);
    }
  
    @GetMapping("/completadas")
    public List<clases.Orden> obtenerOrdenesCompletadas() {
        return ordenService.obtenerOrdenesCompletadas();
    }

    @GetMapping("/pendientes")
    public List<clases.Orden> obtenerOrdenesPendientes() {
        return ordenService.obtenerOrdenesPendientes();
    }

    @GetMapping("/{numeroOrden}")
    public clases.Orden obtenerPorNumero(@PathVariable Integer numeroOrden) throws PersistenciaException {
        return ordenService.obtenerOrdenPorNumeroOrden(numeroOrden);
    }

    @PutMapping("/cancelar")
    public clases.Orden cancelarOrden(@RequestBody NuevaOrdenDTO ordenDTO) throws PersistenciaException {
        return ordenService.cancelarOrden(ordenDTO);
    }

    @PutMapping("/estado/{numeroOrden}/{nuevoEstado}")
    public void cambiarEstado(@PathVariable int numeroOrden, @PathVariable String nuevoEstado) {
        switch (nuevoEstado.toUpperCase()) {
            case "LISTO":
                ordenService.cambiarEstadoCompletada(numeroOrden);
                break;
            case "CANCELADO":
                ordenService.cambiarEstadoCancelada(numeroOrden);
                break;
            case "ENTREGADO":
                ordenService.cambiarEstadoEntregada(numeroOrden);
                break;
            case "PREPARACION":
                ordenService.cambiarEstadoPreparacion(numeroOrden);
                break;
        }
    }

    @GetMapping("/pendientes/fecha")
    public List<clases.Orden> obtenerOrdenesPorFecha() {
        return ordenService.obtenerOrdenesPorFechaAscendente();
    }

    @GetMapping("/pendientes/tortas")
    public List<clases.Orden> obtenerOrdenesPorTortas() {
        return ordenService.obtenerOrdenesPendientesPorCantidadTortas();
    }

    @GetMapping("/reporte/pdf")
    public ResponseEntity<byte[]> generarReporteFiltrado(
        @RequestParam("desde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
        @RequestParam("hasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) throws FindException {

    byte[] pdfBytes = ordenService.generarReporteOrdenesPorRango(desde, hasta);

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ordenes_" + desde + "_a_" + hasta + ".pdf")
        .contentType(MediaType.APPLICATION_PDF)
        .body(pdfBytes);
}
    
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarOrden(@PathVariable String id, @RequestBody NuevaOrdenDTO ordenDTO) {
        try {
            clases.Orden actualizada = ordenService.actualizarOrden(id, ordenDTO);
            return ResponseEntity.ok(actualizada);
        } catch (PersistenciaException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }



}