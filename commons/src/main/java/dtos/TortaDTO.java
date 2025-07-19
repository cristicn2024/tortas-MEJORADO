/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import clases.NotaPersonalizacion;
import java.util.List;

/**
 *
 * @author crist
 */
public class TortaDTO extends NuevoProductoDTO {

    private List<NotaPersonalizacion> notasPersonalizadas;
    private String notas;

    public TortaDTO() {
    }

    public TortaDTO(List<NotaPersonalizacion> notasPersonalizadas, String notas) {
        this.notasPersonalizadas = notasPersonalizadas;
        this.notas = notas;
    }

    public List<NotaPersonalizacion> getNotasPersonalizadas() {
        return notasPersonalizadas;
    }

    public void setNotasPersonalizadas(List<NotaPersonalizacion> notasPersonalizadas) {
        this.notasPersonalizadas = notasPersonalizadas;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

}
