/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author wendellgonzalez
 */
public class EvaluacionDoctor {
    private int calificacion;
    private String comentario;
    private boolean anonima;

    public EvaluacionDoctor(int calificacion, String comentario, boolean anonima) {
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.anonima = anonima;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public boolean isAnonima() {
        return anonima;
    }

    public void setAnonima(boolean anonima) {
        this.anonima = anonima;
    }
}
