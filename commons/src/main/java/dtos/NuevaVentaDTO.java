/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import enums.MetodoPago;
import java.time.LocalDate;

public class NuevaVentaDTO {
    private String id;
    private NuevaOrdenDTO orden;
    private MetodoPago metodoPago;
    private LocalDate fechaVenta;
    private Double total;

    public NuevaVentaDTO() {
    }

    public NuevaVentaDTO(NuevaOrdenDTO orden, MetodoPago metodoPago, LocalDate fechaVenta, Double total) {
        this.orden = orden;
        this.metodoPago = metodoPago;
        this.fechaVenta = fechaVenta;
        this.total = total;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    
    public NuevaOrdenDTO getOrden() {
        return orden;
    }

    public void setOrden(NuevaOrdenDTO orden) {
        this.orden = orden;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "NuevaVentaDTO{" + "orden=" + orden + ", metodoPago=" + metodoPago + ", fechaVenta=" + fechaVenta + ", total=" + total + '}';
    }

    
    
}
