/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.reporte;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "reportes")
public class Reporte {

    @Id
    private String id;

    private LocalDate fechaGeneracion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    private int totalVentas;
    private double totalGanado;

    private List<DetalleVenta> detalleVentas;

    // Constructor vacío
    public Reporte() {
    }

    // Constructor completo
    public Reporte(LocalDate fechaGeneracion, LocalDate fechaInicio, LocalDate fechaFin,
                   int totalVentas, double totalGanado, List<DetalleVenta> detalleVentas) {
        this.fechaGeneracion = fechaGeneracion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.totalVentas = totalVentas;
        this.totalGanado = totalGanado;
        this.detalleVentas = detalleVentas;
    }

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDate fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
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

    public List<DetalleVenta> getDetalleVentas() {
        return detalleVentas;
    }

    public void setDetalleVentas(List<DetalleVenta> detalleVentas) {
        this.detalleVentas = detalleVentas;
    }

    // Clase interna para representar los detalles de cada venta (puedes moverla a su propia clase si prefieres)
    public static class DetalleVenta {
        private String idVenta;
        private LocalDate fecha;
        private double total;

        public DetalleVenta() {
        }

        public DetalleVenta(String idVenta, LocalDate fecha, double total) {
            this.idVenta = idVenta;
            this.fecha = fecha;
            this.total = total;
        }

        public String getIdVenta() {
            return idVenta;
        }

        public void setIdVenta(String idVenta) {
            this.idVenta = idVenta;
        }

        public LocalDate getFecha() {
            return fecha;
        }

        public void setFecha(LocalDate fecha) {
            this.fecha = fecha;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }
    }
}

