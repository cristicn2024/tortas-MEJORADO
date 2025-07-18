/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.servicio_ordenes;

import clases.Orden;
import enums.Estado;
import dtos.NuevaOrdenDTO;
import dtos.NuevoProductoDTO;
import dtos.TortaDTO;
import clases.Producto;
import clases.Ingrediente;
import exception.FindException;
import exception.PersistenciaException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.bson.Document;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class OrdenRepository {

    private static final Logger logger = LoggerFactory.getLogger(OrdenRepository.class);
    private static final String COLLECTION_NAME = "ordenes";
    private static final String PRODUCTOS_COLLECTION = "productos";

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Obtiene todas las órdenes almacenadas en el sistema.
     *
     * @return una lista de todas las órdenes almacenadas
     * @throws FindException si ocurre algún error al buscar las órdenes
     */
    public List<Orden> obtenerOrdenes() throws FindException {
        try {
            List<Orden> ordenes = mongoTemplate.findAll(Orden.class, COLLECTION_NAME);
            logger.info("Se consultaron {} ordenes", ordenes.size());
            return ordenes;
        } catch (Exception ex) {
            logger.error("Error al obtener ordenes", ex);
            throw new FindException("Error al obtener ordenes", ex);
        }
    }

    /**
     * Registra una nueva orden en el sistema.
     *
     * @param ordenDTO los detalles de la nueva orden a registrar
     * @return la orden registrada
     * @throws PersistenciaException si ocurre algún error durante la persistencia de la orden
     */
    public Orden registrarOrden(NuevaOrdenDTO ordenDTO) throws PersistenciaException {
        try {
            Orden orden = new Orden();
            orden.setNombreCliente(ordenDTO.getNombreCliente());
            orden.setNumeroOrden(obtenerNumeroOrdenes() + 1);
            
            List<Producto> productos = convertirProductosDTO(ordenDTO.getListaProductos());
            orden.setListaProductos(productos);
            orden.setTotal(ordenDTO.getTotal());
            orden.setFecha(ordenDTO.getFecha());
            orden.setEstado(Estado.PENDIENTE);

            Orden ordenGuardada = mongoTemplate.save(orden, COLLECTION_NAME);
            logger.info("Se insertó la orden: {}", ordenGuardada.toString());

            return ordenGuardada;
        } catch (Exception ex) {
            logger.error("Error al registrar orden", ex);
            throw new PersistenciaException("Error al registrar orden", ex);
        }
    }

    /**
     * Convierte la lista de DTOs de productos a entidades Producto
     */
    private List<Producto> convertirProductosDTO(List<NuevoProductoDTO> productosDTO) {
        List<Producto> productos = new ArrayList<>();
        
        for (NuevoProductoDTO productoDTO : productosDTO) {
            Producto producto = new Producto();
            producto.setCantidad(productoDTO.getCantidad());
            producto.setCategoria(productoDTO.getCategoria());
            producto.setDescripcion(productoDTO.getDescripcion());
            producto.setNombre(productoDTO.getNombre());
            producto.setNotas(productoDTO.getNotas());
            producto.setPrecio(productoDTO.getPrecio());
            
            if (productoDTO instanceof TortaDTO) {
                TortaDTO torta = (TortaDTO) productoDTO;
                List<Ingrediente> ingredientes = Arrays.asList(
                    new Ingrediente("cantCarne", torta.getCantCarne()),
                    new Ingrediente("cantCebolla", torta.getCantCebolla()),
                    new Ingrediente("cantJalapeño", torta.getCantJalapeno()),
                    new Ingrediente("cantMayonesa", torta.getCantMayonesa()),
                    new Ingrediente("cantMostaza", torta.getCantMostaza()),
                    new Ingrediente("cantRepollo", torta.getCantRepollo()),
                    new Ingrediente("cantTomate", torta.getCantTomate())
                );
                producto.setIngredientes(ingredientes);
            } else {
                producto.setIngredientes(new ArrayList<>());
            }
            productos.add(producto);
        }
        return productos;
    }

    /**
     * Obtiene el precio de un producto por su nombre.
     *
     * @param nombreProducto el nombre del producto del cual se desea obtener el precio
     * @return el precio del producto
     * @throws FindException si ocurre algún error al buscar el precio del producto
     */
    public Double obtenerPrecioPorNombre(String nombreProducto) throws FindException {
        try {
            Query query = new Query(Criteria.where("nombre").is(nombreProducto));
            Document result = mongoTemplate.findOne(query, Document.class, PRODUCTOS_COLLECTION);
            
            return result != null ? result.getDouble("precio") : null;
        } catch (Exception ex) {
            logger.error("Error al obtener precio por nombre", ex);
            throw new FindException("Error al obtener precio por nombre", ex);
        }
    }

    /**
     * Obtiene el número de órdenes existentes
     *
     * @return regresa el número de órdenes
     * @throws PersistenciaException si ocurre un error al buscar las órdenes
     */
    public int obtenerNumeroOrdenes() throws PersistenciaException {
        try {
            long cantidad = mongoTemplate.count(new Query(), COLLECTION_NAME);
            logger.debug("Número de órdenes: {}", cantidad);
            return (int) cantidad;
        } catch (Exception ex) {
            logger.error("Error al obtener número de órdenes", ex);
            throw new PersistenciaException("Error al obtener número de órdenes", ex);
        }
    }

    /**
     * Obtiene todas las órdenes que ya han sido completadas.
     *
     * @return una lista de las órdenes completadas
     */
    public List<Orden> obtenerOrdenesCompletadas() {
        try {
            Query query = new Query(Criteria.where("estado").is("COMPLETADA"));
            List<Orden> ordenesCompletadas = mongoTemplate.find(query, Orden.class, COLLECTION_NAME);
            logger.info("Se consultaron {} órdenes completadas", ordenesCompletadas.size());
            return ordenesCompletadas;
        } catch (Exception ex) {
            logger.error("Error al obtener órdenes completadas", ex);
            return new ArrayList<>();
        }
    }

    /**
     * Obtiene una orden mediante su número de orden.
     *
     * @param numeroOrden el número de la orden que se desea obtener
     * @return la orden encontrada
     * @throws PersistenciaException si ocurre algún error durante la búsqueda de la orden
     */
    public Orden obtenerOrdenPorNumeroOrden(Integer numeroOrden) throws PersistenciaException {
        try {
            Query query = new Query(Criteria.where("numeroOrden").is(numeroOrden));
            Orden orden = mongoTemplate.findOne(query, Orden.class, COLLECTION_NAME);
            
            if (orden == null) {
                throw new PersistenciaException("No se encontró la orden con el número especificado");
            }
            return orden;
        } catch (Exception ex) {
            logger.error("Error al obtener orden por número", ex);
            throw new PersistenciaException("Error al obtener orden por número", ex);
        }
    }

    /**
     * Cancela una orden existente en el sistema.
     *
     * @param ordenDTO los detalles de la orden que se desea cancelar
     * @return la orden cancelada
     * @throws PersistenciaException si ocurre algún error durante la cancelación de la orden
     */
    public Orden cancelarOrden(NuevaOrdenDTO ordenDTO) throws PersistenciaException {
        try {
            Orden ordenEncontrada = obtenerOrdenPorNumeroOrden(ordenDTO.getNumeroOrden());
            
            Query query = new Query(Criteria.where("id").is(ordenEncontrada.getId()));
            Update update = new Update().set("estado", "CANCELADA");
            
            mongoTemplate.updateFirst(query, update, COLLECTION_NAME);
            
            logger.info("Orden cancelada con éxito: {}", ordenEncontrada.toString());
            ordenEncontrada.setEstado(Estado.CANCELADA);
            
            // Restaurar inventario
            for (Producto producto : ordenEncontrada.getListaProductos()) {
                if (cancelarProducto(producto.getNombre(), producto.getCantidad())) {
                    logger.info("Producto inventariado con éxito: {}", producto.toString());
                } else {
                    logger.warn("No se sumó al inventario el producto: {}", producto.toString());
                }
            }
            
            return ordenEncontrada;
        } catch (Exception ex) {
            logger.error("Error al cancelar orden", ex);
            throw new PersistenciaException("Error al cancelar orden con número " + ordenDTO.getNumeroOrden(), ex);
        }
    }

    /**
     * Cancela un producto mediante el nombre del producto y la cantidad
     *
     * @param nombreProducto nombre del producto a cancelar
     * @param cantidad cantidad del producto a cancelar
     * @return true si se actualizó correctamente
     * @throws PersistenciaException si ocurre un error
     */
    public boolean cancelarProducto(String nombreProducto, int cantidad) throws PersistenciaException {
        try {
            Query query = new Query(Criteria.where("nombre").is(nombreProducto));
            Producto producto = mongoTemplate.findOne(query, Producto.class, PRODUCTOS_COLLECTION);
            
            if (producto != null) {
                Update update = new Update().inc("cantidad", cantidad);
                mongoTemplate.updateFirst(query, update, PRODUCTOS_COLLECTION);
                
                logger.info("Se actualizó el inventario de {} sumando {}", nombreProducto, cantidad);
                return true;
            } else {
                logger.warn("No se encontró el producto {} en el inventario", nombreProducto);
                return false;
            }
        } catch (Exception ex) {
            logger.error("Error al actualizar inventario", ex);
            throw new PersistenciaException("Error al actualizar inventario", ex);
        }
    }

    /**
     * Cambia el estado de una orden a completada mediante su número de orden.
     *
     * @param numeroOrden el número de la orden que se desea marcar como completada
     */
    public void cambiarEstadoCompletada(int numeroOrden) {
        try {
            Query query = new Query(Criteria.where("numeroOrden").is(numeroOrden));
            Update update = new Update().set("estado", Estado.COMPLETADA.toString());
            mongoTemplate.updateFirst(query, update, COLLECTION_NAME);
            logger.info("Orden {} marcada como lista", numeroOrden);
        } catch (Exception ex) {
            logger.error("Error al cambiar estado a lista", ex);
        }
    }

    /**
     * Cambia el estado de una orden a cancelada mediante su número de orden.
     *
     * @param numeroOrden el número de la orden que se desea marcar como cancelada
     */
    public void cambiarEstadoCancelada(int numeroOrden) {
        try {
            Query query = new Query(Criteria.where("numeroOrden").is(numeroOrden));
            Update update = new Update().set("estado", Estado.CANCELADA.toString());
            mongoTemplate.updateFirst(query, update, COLLECTION_NAME);
            logger.info("Orden {} marcada como cancelada", numeroOrden);
        } catch (Exception ex) {
            logger.error("Error al cambiar estado a cancelada", ex);
        }
    }
    
     /**
     * Cambia el estado de una orden a ENTREGADO mediante su número de orden.
     *
     * @param numeroOrden el número de la orden que se desea marcar como cancelada
     */
    public void cambiarEstadoEntregada(int numeroOrden) {
        try {
            Query query = new Query(Criteria.where("numeroOrden").is(numeroOrden));
            Update update = new Update().set("estado", Estado.ENTREGADA.toString());
            mongoTemplate.updateFirst(query, update, COLLECTION_NAME);
            logger.info("Orden {} marcada como entregado", numeroOrden);
        } catch (Exception ex) {
            logger.error("Error al cambiar estado a entregado", ex);
        }
    }
    
     /**
     * Cambia el estado de una orden a cancelada mediante su número de orden.
     *
     * @param numeroOrden el número de la orden que se desea marcar como cancelada
     */
    public void cambiarEstadoPreparacion(int numeroOrden) {
        try {
            Query query = new Query(Criteria.where("numeroOrden").is(numeroOrden));
            Update update = new Update().set("estado", Estado.PREPARACION.toString());
            mongoTemplate.updateFirst(query, update, COLLECTION_NAME);
            logger.info("Orden {} marcada como en preparacion", numeroOrden);
        } catch (Exception ex) {
            logger.error("Error al cambiar estado a en preparacion", ex);
        }
    }

    /**
     * Obtiene todas las órdenes pendientes almacenadas en el sistema.
     *
     * @return una lista de órdenes pendientes
     */
    public List<Orden> obtenerOrdenesPendientes() {
        try {
            Query query = new Query(Criteria.where("estado").is("PENDIENTE"));
            List<Orden> ordenesPendientes = mongoTemplate.find(query, Orden.class, COLLECTION_NAME);
            logger.info("Se consultaron {} órdenes pendientes", ordenesPendientes.size());
            return ordenesPendientes;
        } catch (Exception ex) {
            logger.error("Error al obtener órdenes pendientes", ex);
            return new ArrayList<>();
        }
    }

    /**
     * Obtiene todas las órdenes pendientes ordenadas por la cantidad de tortas.
     *
     * @return una lista de órdenes pendientes ordenadas por la cantidad de tortas
     */
    public List<Orden> obtenerOrdenesPendientesPorCantidadTortas() {
        List<Orden> ordenesPendientes = obtenerOrdenesPendientes();
        
        return ordenesPendientes.stream()
                .sorted((orden1, orden2) -> {
                    int cantidadTortas1 = calcularCantidadTortas(orden1);
                    int cantidadTortas2 = calcularCantidadTortas(orden2);
                    return Integer.compare(cantidadTortas2, cantidadTortas1); // Descendente
                })
                .collect(Collectors.toList());
    }

    private int calcularCantidadTortas(Orden orden) {
        return orden.getListaProductos().stream()
                .filter(producto -> "Torta".equals(producto.getCategoria()))
                .mapToInt(Producto::getCantidad)
                .sum();
    }

    /**
     * Obtiene todas las órdenes pendientes ordenadas por fecha de manera ascendente.
     *
     * @return una lista de órdenes ordenadas por fecha ascendente
     */
    public List<Orden> obtenerOrdenesPorFechaAscendente() {
        try {
            Query query = new Query(Criteria.where("estado").is("PENDIENTE"));
            query.with(org.springframework.data.domain.Sort.by(
                org.springframework.data.domain.Sort.Direction.ASC, "fecha"));
            
            List<Orden> ordenes = mongoTemplate.find(query, Orden.class, COLLECTION_NAME);
            logger.info("Se consultaron {} órdenes por fecha ascendente", ordenes.size());
            return ordenes;
        } catch (Exception ex) {
            logger.error("Error al obtener órdenes por fecha ascendente", ex);
            return new ArrayList<>();
        }
    }

    /**
     * Marca una orden como completada en el sistema.
     *
     * @param ordenDTO los detalles de la orden que se desea marcar como completada
     * @throws PersistenciaException si ocurre algún error durante la actualización del estado de la orden
     */
    public void ordenCompletada(NuevaOrdenDTO ordenDTO) throws PersistenciaException {
        cambiarEstadoCompletada(ordenDTO.getNumeroOrden());
    }
}