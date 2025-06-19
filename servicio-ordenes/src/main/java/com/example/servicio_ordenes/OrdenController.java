/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.servicio_ordenes;
@RestController
@RequestMapping("/ordenes")
public class OrdenController {
    
    @Autowired
    private OrdenService ordenService;
    
    @GetMapping
    public List<Orden> obtenerOrdenes() throws FindException {
        return ordenService.obtenerOrdenes();
    }
    
    @PostMapping
    public Orden registrarOrden(@RequestBody NuevaOrdenDTO ordenDTO) 
            throws PersistenciaException {
        return ordenService.registrarOrden(ordenDTO);
    }
}