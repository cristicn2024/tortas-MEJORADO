/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 * Excepción para errores durante búsquedas o consultas de datos.
 */
public class FindException extends RuntimeException {
    public FindException(String mensaje) {
        super(mensaje);
    }

    public FindException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}

