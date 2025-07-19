/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import enums.Estado;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * @author crist
 */

@Document(collection = "productos")
public class Producto {
    
     @Id
    private String id;
    
    @Field("cantidad")
    private int cantidad;
    
    @Field("precio")
    private double precio;
    
    @Field("etiquetas")
    private List<NotaPersonalizacion> notaPersonalizacion;
    
    @Field("nombre")
    private String nombre;
    
    @Field("nombrePrevio")
    private String nombrePrevio;
    
    @Field("descripcion")
    private String descripcion;
    
    @Field("categoria")
    private String categoria;
    
    @Field("notas")
    private String notas;
    
    public Producto() {
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getNombrePrevio() { return nombrePrevio; }
    public void setNombrePrevio(String nombrePrevio) { this.nombrePrevio = nombrePrevio; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas; }

    public List<NotaPersonalizacion> getNotaPersonalizacion() { return notaPersonalizacion; }
    public void setNotaPersonalizacion(List<NotaPersonalizacion> notaPersonalizacion) { this.notaPersonalizacion = notaPersonalizacion; }
}

