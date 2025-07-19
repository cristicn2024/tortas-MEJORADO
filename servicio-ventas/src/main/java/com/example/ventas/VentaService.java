package com.example.ventas;

import dtos.NuevaVentaDTO;
import clases.Venta;
import clases.Orden;
import exception.VentaException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;


@Service
public class VentaService {

    private final VentaRepository ventaRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public List<Venta> obtenerTodasLasVentas() {
        return ventaRepository.findAll();
    }

    public Venta registrarVenta(NuevaVentaDTO nuevaVentaDTO) throws VentaException {
        try {
            Venta venta = new clases.Venta();

            venta.setNumeroVenta((int) ventaRepository.count() + 1);

            LocalDate fecha = nuevaVentaDTO.getFechaVenta() != null
                    ? nuevaVentaDTO.getFechaVenta()
                    : LocalDate.now();
            venta.setFechaVenta(fecha);

            venta.setIdOrden(nuevaVentaDTO.getOrden().getId());

            venta.setMetodoPago(nuevaVentaDTO.getMetodoPago());
            venta.setTotal(nuevaVentaDTO.getTotal());

            return ventaRepository.save(venta);

        } catch (Exception e) {
            throw new VentaException("Error al registrar la venta: " + e.getMessage());
        }
    }

    public List<NuevaVentaDTO> obtenerVentasPorRango(LocalDate desde, LocalDate hasta) {
        List<Venta> ventas = ventaRepository.findByFechaVentaBetween(desde, hasta);

        return ventas.stream()
                .map(v -> new NuevaVentaDTO(
                        null, // si no guardas la orden completa
                        v.getMetodoPago(),
                        v.getFechaVenta(),
                        v.getTotal()
                ))
                .collect(Collectors.toList());
    }

    public long contarVentas() {
        return ventaRepository.count();
    }
    
    public Venta actualizarVenta(String id, NuevaVentaDTO ventaDTO) throws VentaException {
        return ventaRepository.findById(id).map(venta -> {
            venta.setMetodoPago(ventaDTO.getMetodoPago());
            venta.setFechaVenta(ventaDTO.getFechaVenta());
            venta.setTotal(ventaDTO.getTotal());
            if (ventaDTO.getOrden() != null && ventaDTO.getOrden().getId() != null) {
                venta.setIdOrden(ventaDTO.getOrden().getId());
            }
            return ventaRepository.save(venta);
        }).orElseThrow(() -> new VentaException("No se encontró la venta con id: " + id));
    }


    public byte[] generarReporteVentasPorFecha(LocalDate desde, LocalDate hasta) throws VentaException {
        List<Venta> ventas = ventaRepository.findByFechaVentaBetween(desde, hasta);

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document doc = new Document(pdf);

            doc.add(new Paragraph("Reporte de Ventas por Fecha").setBold().setFontSize(14));
            doc.add(new Paragraph("Desde: " + desde + " - Hasta: " + hasta).setFontSize(10));
            doc.add(new Paragraph("\n"));

            float[] columnas = {100F, 150F, 100F, 100F, 100F};
            Table tabla = new Table(columnas);
            tabla.addCell(new Cell().add(new Paragraph("N° Venta")));
            tabla.addCell(new Cell().add(new Paragraph("Cliente")));
            tabla.addCell(new Cell().add(new Paragraph("Fecha")));
            tabla.addCell(new Cell().add(new Paragraph("Método")));
            tabla.addCell(new Cell().add(new Paragraph("Total")));

            for (Venta v : ventas) {
                // Buscar orden por ID
                Query query = new Query(Criteria.where("_id").is(v.getIdOrden()));
                Orden orden = mongoTemplate.findOne(query, Orden.class, "ordenes");
                String nombreCliente = orden != null ? orden.getNombreCliente() : "Desconocido";

                tabla.addCell(String.valueOf(v.getNumeroVenta()));
                tabla.addCell(nombreCliente);
                tabla.addCell(v.getFechaVenta().toString());
                tabla.addCell(v.getMetodoPago().toString());
                tabla.addCell(String.format("$%.2f", v.getTotal()));
            }

            doc.add(tabla);
            doc.close();
            return baos.toByteArray();

        } catch (Exception e) {
            throw new VentaException("Error al generar el PDF: " + e.getMessage());
        }
    }


}
