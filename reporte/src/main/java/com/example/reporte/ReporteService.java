/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.reporte;

import java.io.IOException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import java.io.ByteArrayOutputStream;
import java.awt.Color;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import dtos.NuevaVentaDTO;
import com.example.reporte.VentaClient;


/**
 * Servicio principal para la gestión de reportes de defectos.
 *
 * Esta clase se encarga de todas las operaciones relacionadas con reportes,
 * incluyendo la creación de nuevos reportes, la consulta de reportes
 * existentes, el filtrado por diferentes criterios, y la exportación en
 * diversos formatos. También maneja la conversión de moneda para los reportes.
 *
 * @author Ramos
 */
@Service
public class ReporteService {

    @Autowired
    private VentaRepository ventaRepository;
    
    @Autowired
    private VentaClient ventaClient;

    public byte[] generarReportePDF(LocalDate desde, LocalDate hasta) throws IOException {
    List<NuevaVentaDTO> ventas = ventaClient.obtenerVentasEntreFechas(desde, hasta);

    int total = ventas.size();
    double totalGanado = ventas.stream().mapToDouble(NuevaVentaDTO::getTotal).sum();

    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PDDocument document = new PDDocument();
    PDPage page = new PDPage();
    document.addPage(page);

    PDPageContentStream stream = new PDPageContentStream(document, page);
    stream.beginText();
    stream.setFont(PDType1Font.HELVETICA_BOLD, 14);
    stream.newLineAtOffset(100, 700);
    stream.showText("REPORTE DE VENTAS");
    stream.newLineAtOffset(0, -20);
    stream.showText("Desde: " + desde.toString());
    stream.newLineAtOffset(0, -20);
    stream.showText("Hasta: " + hasta.toString());
    stream.newLineAtOffset(0, -20);
    stream.showText("Total ventas: " + total);
    stream.newLineAtOffset(0, -20);
    stream.showText("Total ganado: $" + totalGanado);
    stream.endText();
    stream.close();

    document.save(output);
    document.close();

    return output.toByteArray();
}

}
