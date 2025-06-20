/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.ventas;

/**
 *
 * @author crist
 */
import java.util.List;
import java.util.Date;

public class Orden {

    private String id;
    private int numeroOrden;
    private float total;
    private List<Producto> listaProductos;
    private String nombreCliente;
    private Estado estado;
    private Date fecha;

    public Orden() {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Orden{" +
                "id='" + id + '\'' +
                ", numeroOrden=" + numeroOrden +
                ", total=" + total +
                ", listaProductos=" + listaProductos +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", estado=" + estado +
                ", fecha=" + fecha +
                '}';
    }
}
