/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOImpl;

import DAO.CitaDAO;
import DAO.HorarioDAO;
import DAOImpl.CitaDAOImpl;
import Model.Cita;
import Model.Horario;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import SQLConnection.DBconnection;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author wendellgonzalez
 */
public class HorarioDAOImpl implements HorarioDAO {

    String INSERTAR_HORARIO = "INSERT INTO horarios_medico (idMedico, dia_semana, hora_inicio, hora_fin) VALUES (?,?,?,?)";

    String OBTENER_HORARIO_POR_DIA = "SELECT * FROM horarios_medico WHERE idMedico = ? AND dia_semana = ?";

    private CitaDAO citaDAO = new CitaDAOImpl();

    @Override
    public Horario obtenerHorarioPorDia(int idMedico, String diaSemana) {
        try (Connection conn = DBconnection.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(OBTENER_HORARIO_POR_DIA)) {

            stmt.setInt(1, idMedico);
            stmt.setString(2, diaSemana);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int idHorario = rs.getInt("idHorario");

                    LocalTime horaInicio = null;
                    if (rs.getTime("hora_inicio") != null) {
                        horaInicio = rs.getTime("hora_inicio").toLocalTime();
                    }

                    LocalTime horaFin = null;
                    if (rs.getTime("hora_fin") != null) {
                        horaFin = rs.getTime("hora_fin").toLocalTime();
                    }

                    if (horaInicio != null && horaFin != null) {
                        return new Horario(idHorario, idMedico, diaSemana, horaInicio, horaFin);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener horario por día: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<String> obtenerHorasDisponibles(int idMedico, LocalDate fecha) {
        List<String> horasDisponibles = new ArrayList<>();
        DayOfWeek diaSemana = fecha.getDayOfWeek();

        Horario horarioTrabajo = obtenerHorarioPorDia(idMedico, diaSemana.toString());

        if (horarioTrabajo == null) {
            return horasDisponibles;
        }

        LocalTime horaInicio = horarioTrabajo.getHora_inicio();
        LocalTime horaFin = horarioTrabajo.getHora_fin();

        List<Cita> citasReservadas = citaDAO.obtenerCitasPorMedicoYFecha(idMedico, fecha);

        LocalTime horaActual = horaInicio;
        while (horaActual.isBefore(horaFin)) {

            final LocalTime horaIteracion = horaActual;

            // Verificar si la hora actual no coincide con ninguna cita reservada
            boolean estaReservada = citasReservadas.stream()
                    .anyMatch(cita -> cita.getHora().equals(horaIteracion));

            if (!estaReservada) {
                horasDisponibles.add(horaIteracion.format(DateTimeFormatter.ofPattern("HH:mm")));
            }

            horaActual = horaActual.plusMinutes(60);
        }

        return horasDisponibles;
    }

    @Override
    public boolean insertarHorario(Horario horario) {
        if (horario.getHora_inicio() == null || horario.getHora_fin() == null) {
            System.err.println("Error: no se pueden insertar horarios con valores de hora nulos.");
            return false;
        }

        try (Connection conn = DBconnection.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(INSERTAR_HORARIO)) {

            stmt.setInt(1, horario.getIdMedico());
            stmt.setString(2, horario.getDia_semana());
            stmt.setTime(3, Time.valueOf(horario.getHora_inicio()));
            stmt.setTime(4, Time.valueOf(horario.getHora_fin()));

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException ex) {
            System.err.println("Error al insertar horario: " + ex.getMessage());
            return false;
        }

    }

    @Override
    public boolean insertarHorarioDefecto(int idMedico) {
        LocalTime horaInicio = LocalTime.of(8, 0);
        LocalTime horaFin = LocalTime.of(20, 0);

        for (DayOfWeek day : DayOfWeek.values()) {
            Horario horario = new Horario(idMedico, day.toString(), horaInicio, horaFin);
            if (!insertarHorario(horario)) {
                return false;
            }
        }

        return true;
    }
}
