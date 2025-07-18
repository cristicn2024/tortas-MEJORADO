/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.reporte;

import dtos.NuevaVentaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class VentaClient {

    private static final Logger log = LoggerFactory.getLogger(VentaClient.class);

    @Value("${servicio.ventas.url}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    public VentaClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<NuevaVentaDTO> obtenerVentasEntreFechas(LocalDate desde, LocalDate hasta, String token) {
        try {
            String url = String.format("%s/api/v1/ventas?desde=%s&hasta=%s",
                    baseUrl, desde.toString(), hasta.toString());

            log.info("Calling ventas service: {}", url);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", token); // ya incluye "Bearer "

            HttpEntity<Void> entity = new HttpEntity<>(headers);

            ResponseEntity<NuevaVentaDTO[]> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    NuevaVentaDTO[].class
            );

            return response.getBody() != null ? Arrays.asList(response.getBody()) : new ArrayList<>();

        } catch (Exception e) {
            log.error("Error calling ventas service: {}", e.getMessage(), e);
            throw new RuntimeException("Error obtaining sales data from: " + baseUrl, e);
        }
    }

}
