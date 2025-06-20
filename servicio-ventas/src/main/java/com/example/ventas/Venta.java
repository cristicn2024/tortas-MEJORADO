/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ventas;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "ventas")
public class Venta {

    @Id
    private ObjectId id;
    private ObjectId idOrden;
    private int numeroVenta;
    private MetodoPago metodoPago;
    private Date fechaVenta;
    private Double total;

    // Constructor vacío
    public Venta() {
    }

    // Getters y setters

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(ObjectId idOrden) {
        this.idOrden = idOrden;
    }

    public int getNumeroVenta() {
        return numeroVenta;
    }

    public void setNumeroVenta(int numeroVenta) {
        this.numeroVenta = numeroVenta;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
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
        return "Venta{" +
                "id=" + id +
                ", idOrden=" + idOrden +
                ", numeroVenta=" + numeroVenta +
                ", metodoPago=" + metodoPago +
                ", fechaVenta=" + fechaVenta +
                ", total=" + total +
                '}';
    }
}
