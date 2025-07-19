/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author crist
 */

import enums.Estado;
import java.util.List;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "ordenes")
public class Orden {

    @Id
    private String id;
    
    @Field("numeroOrden")
    private int numeroOrden;
    
    @Field("total")
    private double total;
    
    @Field("listaProductos")
    private List<Producto> listaProductos;
    
    @Field("nombreCliente")
    private String nombreCliente;
    
    @Field("estado")
    private Estado estado;
    
    @Field("fecha")
    private LocalDate fecha;

    public Orden() {
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
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

