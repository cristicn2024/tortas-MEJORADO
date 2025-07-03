/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.reporte;

import dtos.NuevaVentaDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class VentaClient {

    @Value("${servicio.ventas.url}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    public VentaClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<NuevaVentaDTO> obtenerVentasEntreFechas(LocalDate desde, LocalDate hasta) {
        String url = String.format("%s/api/v1/ventas?desde=%s&hasta=%s",
                baseUrl, desde, hasta);

        NuevaVentaDTO[] ventas = restTemplate.getForObject(url, NuevaVentaDTO[].class);
        return Arrays.asList(ventas);
    }
}

