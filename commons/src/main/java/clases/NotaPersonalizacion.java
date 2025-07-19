/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author crist
 */
public class NotaPersonalizacion {
    private String etiqueta;
    private double incremento; // puede ser positivo o negativo

    public NotaPersonalizacion() {}

    public NotaPersonalizacion(String etiqueta, double incremento) {
        this.etiqueta = etiqueta;
        this.incremento = incremento;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public double getIncremento() {
        return incremento;
    }

    public void setIncremento(double incremento) {
        this.incremento = incremento;
    }
}


