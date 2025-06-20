/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.ventas;

/**
 *
 * @author crist
 */
public class Ingrediente {
    private String nombreIngrediente;
    private int cantidad;

    public Ingrediente() {
    }

    public Ingrediente(String nombreIngrediente, int cantidad) {
        this.nombreIngrediente = nombreIngrediente;
        this.cantidad = cantidad;
    }

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

