/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.servicio_productos;

import com.example.servicio_productos.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<clases.Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public clases.Producto guardar(clases.Producto producto) {
        return productoRepository.save(producto);
    }

    public Optional<clases.Producto> obtenerPorId(String id) {
        return productoRepository.findById(id);
    }

    public void eliminar(String id) {
        productoRepository.deleteById(id);
    }
    
    public clases.Producto actualizarProducto(String id, clases.Producto productoActualizado) {
        return productoRepository.findById(id)
            .map(productoExistente -> {
                productoExistente.setNombre(productoActualizado.getNombre());
                productoExistente.setPrecio(productoActualizado.getPrecio());
                productoExistente.setCantidad(productoActualizado.getCantidad());
                productoExistente.setCategoria(productoActualizado.getCategoria());
                productoExistente.setNotas(productoActualizado.getNotas());
                return productoRepository.save(productoExistente);
            })
            .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }

    public byte[] generarReporteProductosPDF() {
        List<clases.Producto> productos = productoRepository.findAll();
        productos.sort(Comparator.comparing(clases.Producto::getNombre));

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph("Reporte de Productos").setBold().setFontSize(16));
            document.add(new Paragraph("Fecha de generación: " + LocalDate.now()).setFontSize(10));
            document.add(new Paragraph("\n"));

            float[] columnWidths = {100F, 100F, 100F, 100F, 100F};
            Table tabla = new Table(columnWidths);

            tabla.addCell(new Cell().add(new Paragraph("Nombre")));
            tabla.addCell(new Cell().add(new Paragraph("Precio")));
            tabla.addCell(new Cell().add(new Paragraph("Cantidad")));
            tabla.addCell(new Cell().add(new Paragraph("Categoría")));
            tabla.addCell(new Cell().add(new Paragraph("Descripcion")));

            for (clases.Producto producto : productos) {
                tabla.addCell(new Cell().add(new Paragraph(producto.getNombre() != null ? producto.getNombre() : "N/A")));
                tabla.addCell(new Cell().add(new Paragraph(String.format("$%.2f", producto.getPrecio()))));
                tabla.addCell(new Cell().add(new Paragraph(String.valueOf(producto.getCantidad()))));
                tabla.addCell(new Cell().add(new Paragraph(producto.getCategoria() != null ? producto.getCategoria() : "N/A")));
             }


            document.add(tabla);
            document.close();
            return baos.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error al generar el PDF", e);
        }
    }
}
