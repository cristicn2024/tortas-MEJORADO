/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.reporte;

/*
 * Objeto de transferencia de datos (DTO) para la entidad Reporte.
 *
 * Esta clase se utiliza para transferir información relevante de un reporte
 * entre capas de la aplicación, especialmente entre el backend y el frontend,
 * sin exponer directamente la entidad JPA.
 *
 * Contiene datos básicos del reporte como su identificador, lote asociado,
 * inspector responsable, costo total calculado, fecha del reporte, y una lista
 * de defectos relacionados representados como DefectoDTO.
 *
 * Utiliza anotaciones Lombok para generar automáticamente los métodos
 * getters, setters, constructores sin y con argumentos.
 *
 * @author cristi
 */
import java.time.LocalDate;
import java.util.List;

public class ReporteDTO {

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int totalVentas;
    private double totalGanado;
    private List<DetalleVentaDTO> detalleVentas;

    public ReporteDTO() {
    }

    public ReporteDTO(LocalDate fechaInicio, LocalDate fechaFin, int totalVentas, double totalGanado, List<DetalleVentaDTO> detalleVentas) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.totalVentas = totalVentas;
        this.totalGanado = totalGanado;
        this.detalleVentas = detalleVentas;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(int totalVentas) {
        this.totalVentas = totalVentas;
    }

    public double getTotalGanado() {
        return totalGanado;
    }

    public void setTotalGanado(double totalGanado) {
        this.totalGanado = totalGanado;
    }

    public List<DetalleVentaDTO> getDetalleVentas() {
        return detalleVentas;
    }

    public void setDetalleVentas(List<DetalleVentaDTO> detalleVentas) {
        this.detalleVentas = detalleVentas;
    }
}