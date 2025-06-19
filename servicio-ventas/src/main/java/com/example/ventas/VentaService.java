/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ventas;

import com.example.ventas.dto.NuevaVentaDTO;
import com.example.ventas.exception.VentaException;
import com.example.ventas.model.Venta;
import com.example.ventas.repository.VentaRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VentaService {
    private final VentaRepository ventaRepository;
    
    @Autowired
    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }
    
    public List<Venta> obtenerTodasLasVentas() {
        return ventaRepository.findAll();
    }
    
    public Venta registrarVenta(NuevaVentaDTO nuevaVentaDTO) throws VentaException {
        try {
            Venta venta = new Venta();
            venta.setNumeroVenta((int) ventaRepository.count() + 1);
            venta.setFechaVenta(nuevaVentaDTO.getFechaVenta() != null ? 
                nuevaVentaDTO.getFechaVenta() : new Date());
            venta.setIdOrden(new ObjectId(nuevaVentaDTO.getOrden().getId()));
            venta.setMetodoPago(nuevaVentaDTO.getMetodoPago());
            venta.setTotal(nuevaVentaDTO.getTotal());
            
            return ventaRepository.save(venta);
        } catch (Exception e) {
            throw new VentaException("Error al registrar la venta: " + e.getMessage());
        }
    }
    
    public long contarVentas() {
        return ventaRepository.count();
    }
}