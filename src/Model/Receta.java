/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;

/**
 *
 * @author wendellgonzalez
 */
public class Receta {

    private int idReceta;
    private int idPaciente;
    private int idMedico;
    private String nombreMedico;
    private LocalDate fechaConsulta;
    private String especialidadDoctor;
    private String recomendaciones;
    private String diagnostico;
    private int idConsulta;
    private String medicamento;
    private String dosis;
    private String frecuencia;
    private String duracion;

    // Constructor vacío
    public Receta() {
    }

    // Constructor para insertar en la base de datos (sin ID de receta)
    public Receta(String medicamento, String dosis, String frecuencia, String duracion) {
        this.medicamento = medicamento;
        this.dosis = dosis;
        this.frecuencia = frecuencia;
        this.duracion = duracion;
    }

    public Receta(String medicamento, String dosis, String frecuencia, String duracion, String recomendaciones, String diagnostico) {
        this.recomendaciones = recomendaciones;
        this.diagnostico = diagnostico;
        this.medicamento = medicamento;
        this.dosis = dosis;
        this.frecuencia = frecuencia;
        this.duracion = duracion;
    }

    // Constructor completo
    public Receta(int idReceta, int idPaciente, int idMedico, String nombreMedico, LocalDate fechaConsulta, int idConsulta, String medicamento, String dosis, String frecuencia, String duracion, String especialidad) {
        this.idReceta = idReceta;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.nombreMedico = nombreMedico;
        this.fechaConsulta = fechaConsulta;
        this.idConsulta = idConsulta;
        this.medicamento = medicamento;
        this.dosis = dosis;
        this.frecuencia = frecuencia;
        this.duracion = duracion;
        this.especialidadDoctor = especialidad;
    }

    // Getters y Setters
    
    public String getEspecialidad() {
        return especialidadDoctor;
    }
    
    public void setEspecialidad(String especialidad) {
        this.especialidadDoctor = especialidad;
    } 
    
    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
    
    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }
    
    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public LocalDate getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(LocalDate fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

}
