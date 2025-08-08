/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author wendellgonzalez
 */
public class Cita {
    private int idCita;
    private int idPaciente;
    private int idMedico;
    private LocalDate fecha;
    private LocalTime hora;
    private String motivo;
    private Estado estado;
    private Usuario paciente;
    private Usuario medico;
    private int edadPaciente;

    public Cita(int idCita, int idPaciente, int idMedico, LocalDate fecha, LocalTime hora, String motivo, Estado estado, Paciente edadPaciente) {
        this.idCita = idCita;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.estado = estado;
        this.edadPaciente = edadPaciente.getEdad();
    }

    public int getEdadpaciente() {
        return edadPaciente;
    }
    
    public void setEdadPaciente(Paciente edadPaciente) {
        this.edadPaciente = edadPaciente.getEdad();
    }
    
    public Cita() {
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    public Usuario getPaciente() {
        return paciente;
    }

    public void setPaciente(Usuario paciente) {
        this.paciente = paciente;
    }

    public Usuario getMedico() {
        return medico;
    }

    public void setMedico(Usuario medico) {
        this.medico = medico;
    }
    
    public enum Estado {
        PENDIENTE, ACEPTADA, CANCELADA, REALIZADA
    }
}
