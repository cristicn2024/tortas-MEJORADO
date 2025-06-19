/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.servicio_inventario;

import com.tuservicio.inventario.model.Inventario;
import com.tuservicio.inventario.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {
    @Autowired
    private InventarioRepository inventarioRepository;

    public List<Inventario> obtenerTodos() {
        return inventarioRepository.findAll();
    }

    public Inventario guardar(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    public Optional<Inventario> obtenerPorProductoId(String productoId) {
        return inventarioRepository.findByProductoId(productoId);
    }

    public void eliminarPorProductoId(String productoId) {
        inventarioRepository.deleteByProductoId(productoId);
    }
}