/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author crist
 */
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "categoria"
)
@JsonSubTypes({
  @JsonSubTypes.Type(value = TortaDTO.class, name = "Torta")
})
public class NuevoProductoDTO {

    private int id;
    private int cantidad;
    private String nombre;
    private double precio;
    private String categoria;
    private String notas;

    public NuevoProductoDTO() {
    }

    public NuevoProductoDTO(String nombre) {
        this.nombre = nombre;
    }

    public NuevoProductoDTO(int cantidad, String nombre, double precio, String categoria) {
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }


    public NuevoProductoDTO(String nombre, int cantidad, double precio, String categoria) {
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public NuevoProductoDTO(String nombre, int cantidad, String categoria) {
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.categoria = categoria;
    }

    public NuevoProductoDTO(int id, int cantidad, String nombre, double precio, String categoria, String notas) {
        this.id = id;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.notas = notas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

}
