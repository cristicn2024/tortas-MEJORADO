/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import enums.Estado;
import java.time.LocalDate;
import java.util.List;

public class NuevaOrdenDTO {

    private String id;
    private int numeroOrden;
    private double total;
    private List<NuevoProductoDTO> listaProductos;
    private String nombreCliente;
    private Estado estado;
    private LocalDate fecha;
    private boolean envioDomicilio;
    private String numeroTelefono;      // solo si envioDomicilio == true
    private String direccionEntrega;    // solo si envioDomicilio == true
    private Double costoEnvio;          // puede sumarse al total

    public NuevaOrdenDTO() {
        this.estado = Estado.PENDIENTE;
        numeroOrden = -1;
    }

    public NuevaOrdenDTO(String nombreCliente, List<NuevoProductoDTO> listaProductos, double total, LocalDate fecha) {
        this.total = total;
        this.numeroOrden = -1;
        this.listaProductos = listaProductos;
        this.nombreCliente = nombreCliente;
        this.estado = Estado.PENDIENTE;
        this.fecha = fecha;
    }

    public NuevaOrdenDTO(String id, String nombreCliente, List<NuevoProductoDTO> listaProductos, double total, LocalDate fecha) {
        this.id = id;
        this.total = total;
        this.numeroOrden = -1;
        this.listaProductos = listaProductos;
        this.nombreCliente = nombreCliente;
        this.estado = Estado.PENDIENTE;
        this.fecha = fecha;
    }

    public NuevaOrdenDTO(int numeroOrden, double total, List<NuevoProductoDTO> listaProductos, String nombreCliente, Estado estado, LocalDate fecha, boolean envioDomicilio, String numeroTelefono, String direccionEntrega, Double costoEnvio) {
        this.numeroOrden = numeroOrden;
        this.total = total;
        this.listaProductos = listaProductos;
        this.nombreCliente = nombreCliente;
        this.estado = Estado.PENDIENTE;
        this.fecha = fecha;
        this.envioDomicilio = envioDomicilio;
        this.numeroTelefono = numeroTelefono;
        this.direccionEntrega = direccionEntrega;
        this.costoEnvio = costoEnvio;
    }
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<NuevoProductoDTO> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<NuevoProductoDTO> listaProductos) {
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

    public boolean isEnvioDomicilio() {
        return envioDomicilio;
    }

    public void setEnvioDomicilio(boolean envioDomicilio) {
        this.envioDomicilio = envioDomicilio;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public Double getCostoEnvio() {
        return costoEnvio;
    }

    public void setCostoEnvio(Double costoEnvio) {
        this.costoEnvio = costoEnvio;
    }
    
    

}
