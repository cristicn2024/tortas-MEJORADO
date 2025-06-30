/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.servicio_ordenes;

import clases.Producto;
import enums.Estado;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

@Document(collection = "ordenes")
public class Orden {

    @Id
    private ObjectId id;
    
    @Field("numeroOrden")
    private int numeroOrden;
    
    @Field("total")
    private float total;
    
    @Field("listaProductos")
    private List<Producto> listaProductos;
    
    @Field("nombreCliente")
    private String nombreCliente;
    
    @Field("estado")
    private Estado estado;
    
    @Field("fecha")
    private Date fecha;

    public Orden() {
    }

    // Constructor con parámetros principales
    public Orden(int numeroOrden, float total, List<Producto> listaProductos, 
                 String nombreCliente, Estado estado, Date fecha) {
        this.numeroOrden = numeroOrden;
        this.total = total;
        this.listaProductos = listaProductos;
        this.nombreCliente = nombreCliente;
        this.estado = estado;
        this.fecha = fecha;
    }

    // Getters y Setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Orden{" +
                "id=" + id +
                ", numeroOrden=" + numeroOrden +
                ", total=" + total +
                ", listaProductos=" + listaProductos +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", estado=" + estado +
                ", fecha=" + fecha +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Orden orden = (Orden) obj;
        return numeroOrden == orden.numeroOrden && 
               Float.compare(orden.total, total) == 0 &&
               id != null ? id.equals(orden.id) : orden.id == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + numeroOrden;
        result = 31 * result + (total != +0.0f ? Float.floatToIntBits(total) : 0);
        return result;
    }
}