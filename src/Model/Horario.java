/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalTime;

/**
 *
 * @author wendellgonzalez
 */
public class Horario {
    private int idHorario;
    private int idMedico;
    private String dia_semana;
    private LocalTime hora_inicio;
    private LocalTime hora_fin;

    public Horario() {
        
    }
    
    public Horario(int idHorario, int idMedico, String dia_semana, LocalTime hora_inicio, LocalTime hora_fin) {
        this.idHorario = idHorario;
        this.idMedico = idMedico;
        this.dia_semana = dia_semana;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
    }
    
    public Horario(int idMedico, String dia_semana, LocalTime hora_inicio, LocalTime hora_fin) {
        this.idMedico = idMedico;
        this.dia_semana = dia_semana;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
    }
    
    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getDia_semana() {
        return dia_semana;
    }

    public void setDia_semana(String dia_semana) {
        this.dia_semana = dia_semana;
    }

    public LocalTime getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(LocalTime hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public LocalTime getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(LocalTime hora_fin) {
        this.hora_fin = hora_fin;
    }
    
    
}
