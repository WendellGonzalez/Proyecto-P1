/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Model.Horario;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

/**
 *
 * @author wendellgonzalez
 */
public interface HorarioDAO {
    
    boolean insertarHorario(Horario horario);
    List<String> obtenerHorasDisponibles(int idMedico, LocalDate fecha);
    Horario obtenerHorarioPorDia(int idMedico, String diaSemana);
    boolean insertarHorarioDefecto(int idMedico);
    
}
