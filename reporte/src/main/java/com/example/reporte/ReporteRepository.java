/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.reporte;


import com.example.reporte.Reporte;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReporteRepository extends MongoRepository<Reporte, String> {

    // Ejemplo de consulta personalizada: buscar por rango de fechas
    List<Reporte> findByFechaGeneracionBetween(LocalDate desde, LocalDate hasta);

    // Puedes agregar más métodos según campos en tu entidad Reporte
}
