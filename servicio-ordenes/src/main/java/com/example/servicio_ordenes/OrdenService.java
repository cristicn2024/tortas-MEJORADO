/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.servicio_ordenes;

import dtos.NuevaOrdenDTO;
import exception.FindException;
import exception.PersistenciaException;

// Para stream y colecciones
import java.util.stream.Collectors;
import java.util.Comparator;

// Para fechas
import java.time.LocalDate;

// Para manejo de PDF con iText
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;

// Para escribir en memoria
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * @throws PersistenciaException si ocurre algún error durante la
     * persistencia de la orden
     */
    public clases.Orden registrarOrden(NuevaOrdenDTO ordenDTO) throws PersistenciaException {
        return ordenRepository.registrarOrden(ordenDTO);
    }

    /**
     * Obtiene el precio de un producto por su nombre.
     *
     * @param nombreProducto el nombre del producto del cual se desea obtener el
     * precio
     * @return el precio del producto
     * @throws FindException si ocurre algún error al buscar el precio del
     * producto
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
     * @throws PersistenciaException si ocurre algún error durante la búsqueda
     * de la orden
     */
    public clases.Orden obtenerOrdenPorNumeroOrden(Integer numeroOrden) throws PersistenciaException {
        return ordenRepository.obtenerOrdenPorNumeroOrden(numeroOrden);
    }

    /**
     * Cancela una orden existente en el sistema.
     *
     * @param ordenDTO los detalles de la orden que se desea cancelar
     * @return la orden cancelada
     * @throws PersistenciaException si ocurre algún error durante la
     * cancelación de la orden
     */
    public clases.Orden cancelarOrden(NuevaOrdenDTO ordenDTO) throws PersistenciaException {
        return ordenRepository.cancelarOrden(ordenDTO);
    }

    /**
     * Cambia el estado de una orden a completada mediante su número de orden.
     *
     * @param numeroOrden el número de la orden que se desea marcar como
     * completada
     */
    public void cambiarEstadoCompletada(int numeroOrden) {
        ordenRepository.cambiarEstadoCompletada(numeroOrden);
    }

    /**
     * Cambia el estado de una orden a cancelada mediante su número de orden.
     *
     * @param numeroOrden el número de la orden que se desea marcar como
     * cancelada
     */
    public void cambiarEstadoCancelada(int numeroOrden) {
        ordenRepository.cambiarEstadoCancelada(numeroOrden);
    }

    /**
     * Cambia el estado de una orden a completada mediante su número de orden.
     *
     * @param numeroOrden el número de la orden que se desea marcar como
     * completada
     */
    public void cambiarEstadoEntregada(int numeroOrden) {
        ordenRepository.cambiarEstadoEntregada(numeroOrden);
    }

    /**
     * Cambia el estado de una orden a cancelada mediante su número de orden.
     *
     * @param numeroOrden el número de la orden que se desea marcar como
     * cancelada
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
     * @return una lista de órdenes pendientes ordenadas por la cantidad de
     * tortas
     */
    public List<clases.Orden> obtenerOrdenesPendientesPorCantidadTortas() {
        return ordenRepository.obtenerOrdenesPendientesPorCantidadTortas();
    }

    /**
     * Obtiene todas las órdenes pendientes ordenadas por fecha de manera
     * ascendente.
     *
     * @return una lista de órdenes ordenadas por fecha ascendente
     */
    public List<clases.Orden> obtenerOrdenesPorFechaAscendente() {
        return ordenRepository.obtenerOrdenesPorFechaAscendente();
    }

    /**
     * Marca una orden como completada en el sistema.
     *
     * @param ordenDTO los detalles de la orden que se desea marcar como
     * completada
     * @throws PersistenciaException si ocurre algún error durante la
     * actualización del estado de la orden
     */
    public void ordenCompletada(NuevaOrdenDTO ordenDTO) throws PersistenciaException {
        ordenRepository.ordenCompletada(ordenDTO);
    }

    public byte[] generarReporteOrdenesPorRango(LocalDate desde, LocalDate hasta) throws FindException {
        List<clases.Orden> ordenes = obtenerOrdenes(); // usa tu método actual que trae todas

        // Filtrar por fechas
        List<clases.Orden> ordenesFiltradas = ordenes.stream()
                .filter(orden -> {
                    LocalDate fecha = orden.getFecha();
                    return (fecha != null && !fecha.isBefore(desde) && !fecha.isAfter(hasta));
                })
                .sorted(Comparator.comparing(clases.Orden::getFecha))
                .collect(Collectors.toList());

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph("Reporte de Órdenes Registradas por Rango de Fecha").setBold().setFontSize(16));
            document.add(new Paragraph("Desde: " + desde + "  Hasta: " + hasta).setFontSize(10));
            document.add(new Paragraph("\n"));

            float[] columnWidths = {100F, 100F, 100F, 100F, 100F};
            Table tabla = new Table(columnWidths);

            tabla.addCell(new Cell().add(new Paragraph("N° Orden")));
            tabla.addCell(new Cell().add(new Paragraph("Cliente")));
            tabla.addCell(new Cell().add(new Paragraph("Fecha")));
            tabla.addCell(new Cell().add(new Paragraph("Total")));
            tabla.addCell(new Cell().add(new Paragraph("Estado")));

            for (clases.Orden orden : ordenesFiltradas) {
                tabla.addCell(new Cell().add(new Paragraph(String.valueOf(orden.getNumeroOrden()))));
                tabla.addCell(new Cell().add(new Paragraph(orden.getNombreCliente())));
                tabla.addCell(new Cell().add(new Paragraph(orden.getFecha().toString())));
                tabla.addCell(new Cell().add(new Paragraph(String.format("$%.2f", orden.getTotal()))));
                tabla.addCell(new Cell().add(new Paragraph(orden.getEstado().toString())));
            }

            document.add(tabla);
            document.close();
            return baos.toByteArray();

        } catch (IOException e) {
            throw new FindException("Error al generar el reporte PDF", e);
        }
    }

}
