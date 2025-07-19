package com.example.ventas;

import dtos.NuevaVentaDTO;
import exception.VentaException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;

    @Autowired
    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public List<clases.Venta> obtenerTodasLasVentas() {
        return ventaRepository.findAll();
    }

    public clases.Venta registrarVenta(NuevaVentaDTO nuevaVentaDTO) throws VentaException {
        try {
            clases.Venta venta = new clases.Venta();

            venta.setNumeroVenta((int) ventaRepository.count() + 1);

            LocalDate fecha = nuevaVentaDTO.getFechaVenta() != null
                    ? nuevaVentaDTO.getFechaVenta()
                    : LocalDate.now();
            venta.setFechaVenta(fecha);

            venta.setIdOrden(String.valueOf(nuevaVentaDTO.getOrden().getId()));

            venta.setMetodoPago(nuevaVentaDTO.getMetodoPago());
            venta.setTotal(nuevaVentaDTO.getTotal());

            return ventaRepository.save(venta);

        } catch (Exception e) {
            throw new VentaException("Error al registrar la venta: " + e.getMessage());
        }
    }

    public List<NuevaVentaDTO> obtenerVentasPorRango(LocalDate desde, LocalDate hasta) {
        List<clases.Venta> ventas = ventaRepository.findByFechaVentaBetween(desde, hasta);

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
}
