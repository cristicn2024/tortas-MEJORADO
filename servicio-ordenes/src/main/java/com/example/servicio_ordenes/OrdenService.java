/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.servicio_ordenes;

import com.example.servicio_ordenes.Orden;
import dtos.NuevaOrdenDTO;
import exception.FindException;
import exception.PersistenciaException;
import com.example.servicio_ordenes.OrdenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    /**
     * Obtiene todas las órdenes almacenadas en el sistema.
     *
     * @return una lista de todas las órdenes almacenadas
     * @throws FindException si ocurre algún error al buscar las órdenes
     */
    public List<clases.Orden> obtenerOrdenes() throws FindException {
        return ordenRepository.obtenerOrdenes();
    }

    /**
     * Registra una nueva orden en el sistema.
     *
     * @param ordenDTO los detalles de la nueva orden a registrar
     * @return la orden registrada
     * @throws PersistenciaException si ocurre algún error durante la persistencia de la orden
     */
    public clases.Orden registrarOrden(NuevaOrdenDTO ordenDTO) throws PersistenciaException {
        return ordenRepository.registrarOrden(ordenDTO);
    }

    /**
     * Obtiene el precio de un producto por su nombre.
     *
     * @param nombreProducto el nombre del producto del cual se desea obtener el precio
     * @return el precio del producto
     * @throws FindException si ocurre algún error al buscar el precio del producto
     */
    public Double obtenerPrecioPorNombre(String nombreProducto) throws FindException {
        return ordenRepository.obtenerPrecioPorNombre(nombreProducto);
    }

    /**
     * Obtiene el número de órdenes existentes
     *
     * @return regresa el número de órdenes
     * @throws PersistenciaException si ocurre un error al buscar las órdenes
     */
    public int obtenerNumeroOrdenes() throws PersistenciaException {
        return ordenRepository.obtenerNumeroOrdenes();
    }

    /**
     * Obtiene todas las órdenes que ya han sido completadas.
     *
     * @return una lista de las órdenes completadas
     */
    public List<clases.Orden> obtenerOrdenesCompletadas() {
        return ordenRepository.obtenerOrdenesCompletadas();
    }

    /**
     * Obtiene una orden mediante su número de orden.
     *
     * @param numeroOrden el número de la orden que se desea obtener
     * @return la orden encontrada
     * @throws PersistenciaException si ocurre algún error durante la búsqueda de la orden
     */
    public clases.Orden obtenerOrdenPorNumeroOrden(Integer numeroOrden) throws PersistenciaException {
        return ordenRepository.obtenerOrdenPorNumeroOrden(numeroOrden);
    }

    /**
     * Cancela una orden existente en el sistema.
     *
     * @param ordenDTO los detalles de la orden que se desea cancelar
     * @return la orden cancelada
     * @throws PersistenciaException si ocurre algún error durante la cancelación de la orden
     */
    public clases.Orden cancelarOrden(NuevaOrdenDTO ordenDTO) throws PersistenciaException {
        return ordenRepository.cancelarOrden(ordenDTO);
    }

    /**
     * Cambia el estado de una orden a completada mediante su número de orden.
     *
     * @param numeroOrden el número de la orden que se desea marcar como completada
     */
    public void cambiarEstadoCompletada(int numeroOrden) {
        ordenRepository.cambiarEstadoCompletada(numeroOrden);
    }

    /**
     * Cambia el estado de una orden a cancelada mediante su número de orden.
     *
     * @param numeroOrden el número de la orden que se desea marcar como cancelada
     */
    public void cambiarEstadoCancelada(int numeroOrden) {
        ordenRepository.cambiarEstadoCancelada(numeroOrden);
    }
    
    /**
     * Cambia el estado de una orden a completada mediante su número de orden.
     *
     * @param numeroOrden el número de la orden que se desea marcar como completada
     */
    public void cambiarEstadoEntregada(int numeroOrden) {
        ordenRepository.cambiarEstadoEntregada(numeroOrden);
    }

    /**
     * Cambia el estado de una orden a cancelada mediante su número de orden.
     *
     * @param numeroOrden el número de la orden que se desea marcar como cancelada
     */
    public void cambiarEstadoPreparacion(int numeroOrden) {
        ordenRepository.cambiarEstadoPreparacion(numeroOrden);
    }

    /**
     * Obtiene todas las órdenes pendientes almacenadas en el sistema.
     *
     * @return una lista de órdenes pendientes
     */
    public List<clases.Orden> obtenerOrdenesPendientes() {
        return ordenRepository.obtenerOrdenesPendientes();
    }

    /**
     * Obtiene todas las órdenes pendientes ordenadas por la cantidad de tortas.
     *
     * @return una lista de órdenes pendientes ordenadas por la cantidad de tortas
     */
    public List<clases.Orden> obtenerOrdenesPendientesPorCantidadTortas() {
        return ordenRepository.obtenerOrdenesPendientesPorCantidadTortas();
    }

    /**
     * Obtiene todas las órdenes pendientes ordenadas por fecha de manera ascendente.
     *
     * @return una lista de órdenes ordenadas por fecha ascendente
     */
    public List<clases.Orden> obtenerOrdenesPorFechaAscendente() {
        return ordenRepository.obtenerOrdenesPorFechaAscendente();
    }

    /**
     * Marca una orden como completada en el sistema.
     *
     * @param ordenDTO los detalles de la orden que se desea marcar como completada
     * @throws PersistenciaException si ocurre algún error durante la actualización del estado de la orden
     */
    public void ordenCompletada(NuevaOrdenDTO ordenDTO) throws PersistenciaException {
        ordenRepository.ordenCompletada(ordenDTO);
    }
}