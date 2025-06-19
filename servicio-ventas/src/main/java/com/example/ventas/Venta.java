/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ventas;

package com.example.ventas.model;

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

    // Constructores, getters y setters
    public Venta() {
    }

    // ... (todos los getters y setters como en tu código original)
}

// Enumeración para MétodoPago
enum MetodoPago {
    EFECTIVO, TARJETA_CREDITO, TARJETA_DEBITO, TRANSFERENCIA
}