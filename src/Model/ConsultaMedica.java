/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author wendellgonzalez
 */
public class ConsultaMedica {
    
    //Atributos
    private int idConsulta;
    private int idCita;
    private int idPaciente; 
    private int idMedico;   
    private String diagnostico;
    private String recomendaciones;
    private List<Receta> recetas; 
    private LocalDateTime fechaConsulta;
    
    public ConsultaMedica() {
    }

    public ConsultaMedica(int idConsulta, int idCita, int idPaciente, int idMedico, String diagnostico, String recomendaciones, List<Receta> recetas, LocalDateTime fechaConsulta) {
        this.idConsulta = idConsulta;
        this.idCita = idCita;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.diagnostico = diagnostico;
        this.recomendaciones = recomendaciones;
        this.recetas = recetas;
        this.fechaConsulta = fechaConsulta;
    }

    // Getters y setters
    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
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

    public List<Receta> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<Receta> recetas) {
        this.recetas = recetas;
    }

    public LocalDateTime getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(LocalDateTime fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }
    
}