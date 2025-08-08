/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Model.Cita;
import Model.Usuario;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author wendellgonzalez
 */
public interface CitaDAO {
    
    boolean agendarCita(int idPaciente, int idMedico, LocalDate fecha, LocalTime hora);
    List<Cita> obtenerCitasPorMedicoYFecha(int idMedico, LocalDate fecha);
    boolean registrarCita(Cita cita);
    List<Usuario> getPacientesPorMedico(int idMedico);

    List<Cita> obtenerCitasPendientesPorMedico(int idMedico);
    boolean actualizarEstadoCita(int idCita, String nuevoEstado);
//    List<Cita> obtenerHistorialPorPaciente(int idPaciente);
    
}
