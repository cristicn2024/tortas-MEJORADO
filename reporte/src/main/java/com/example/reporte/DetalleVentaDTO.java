/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.reporte;


import java.time.LocalDateTime;
/**
 *
 * @author crist
 */
public class DetalleVentaDTO {
    private String idVenta;
    private LocalDateTime fecha;
    private double total;

    public DetalleVentaDTO() {}

    public DetalleVentaDTO(String idVenta, LocalDateTime fecha, double total) {
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

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
