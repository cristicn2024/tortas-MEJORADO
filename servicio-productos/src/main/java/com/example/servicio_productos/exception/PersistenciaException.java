/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.servicio_productos.exception;

/**
 * Excepción para errores relacionados con la persistencia de datos (guardar, actualizar, eliminar).
 */
public class PersistenciaException extends RuntimeException {
    public PersistenciaException(String mensaje) {
        super(mensaje);
    }

    public PersistenciaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}

