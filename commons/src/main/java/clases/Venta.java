/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;
import enums.MetodoPago;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "ventas")
public class Venta {

    @Id
    private String id;
    
    @Field("idOrden")
    private String idOrden;
    
    @Field("numeroVenta")
    private int numeroVenta;
     
    @Field("metodoPago")
    private MetodoPago metodoPago;
    
    @Field("fechaVenta")
    private LocalDate fechaVenta;
    
    @Field("total")
    private Double total;

    // Constructor vacío
    public Venta() {
    }

    // Getters y setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(String idOrden) {
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
