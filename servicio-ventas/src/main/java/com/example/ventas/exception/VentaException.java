/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.ventas.exception;

/**
 * Excepción personalizada para errores en el proceso de ventas.
 */
public class VentaException extends RuntimeException {
    public VentaException(String mensaje) {
        super(mensaje);
    }

    public VentaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}

