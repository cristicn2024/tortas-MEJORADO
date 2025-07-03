/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.reporte;

import dtos.NuevaVentaDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VentaRepository extends MongoRepository<NuevaVentaDTO, String> {
   List<NuevaVentaDTO> findByFechaVentaBetween(LocalDate desde, LocalDate hasta);

}
