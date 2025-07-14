/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.servicio_ordenes;
import java.util.List;
import exception.FindException;
import exception.PersistenciaException;
import dtos.NuevaOrdenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/v1/ordenes")
public class OrdenController {
    
    @Autowired
    private OrdenService ordenService;
    
    @GetMapping
    public List<clases.Orden> obtenerOrdenes() throws FindException {
        return ordenService.obtenerOrdenes();
    }
    
    @PostMapping
    public clases.Orden registrarOrden(@RequestBody NuevaOrdenDTO ordenDTO) 
            throws PersistenciaException {
        return ordenService.registrarOrden(ordenDTO);
    }
}