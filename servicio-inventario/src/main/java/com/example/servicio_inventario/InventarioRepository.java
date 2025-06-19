/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.servicio_inventario;

import com.tuservicio.inventario.model.Inventario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface InventarioRepository extends MongoRepository<Inventario, String> {
    Optional<Inventario> findByProductoId(String productoId);
    void deleteByProductoId(String productoId);
}

