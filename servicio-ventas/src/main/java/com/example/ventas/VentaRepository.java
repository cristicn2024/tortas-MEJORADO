/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ventas;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDate;

@Repository
public interface VentaRepository extends MongoRepository<clases.Venta, String> {
    List<clases.Venta> findAll();
    
    clases.Venta findByNumeroVenta(int numeroVenta);
    
    long count();
    
    List<clases.Venta> findByFechaVentaBetween(LocalDate desde, LocalDate hasta);
}