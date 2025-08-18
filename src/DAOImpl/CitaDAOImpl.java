/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOImpl;

import DAO.CitaDAO;
import Model.Cita;
import Model.Medico;
import Model.Paciente;
import Model.Usuario;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.*;
import SQLConnection.DBconnection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wendellgonzalez
 */
public class CitaDAOImpl implements CitaDAO {

    /**
     * CONSULTAS
     *
     * @param idMedico
     * @param fecha
     * @return
     */
    // Consulta para obtener citas por medico y fecha
    String OBTENER_CITAS_POR_MEDICO_Y_FECHA = "SELECT * FROM citas WHERE idMedico = ? AND fecha = ?";

    // Registrar Citas
    String REGISTRAR_CITA = "INSERT INTO citas(idPaciente, idMedico, fecha, hora, motivo, estado)"
            + "VALUES (?,?,?,?,?,?)";

    // Obtener pacientes por medico
    String OBTENER_PACIENTES_POR_MEDICO = "SELECT DISTINCT U.idUsuario, U.nombre, U.tipoUsuario, U.direccion, U.email, U.telefono "
            + "FROM Citas C "
            + "JOIN Usuarios U ON C.idPaciente = U.idUsuario "
            + "WHERE C.idMedico = ?";

    String OBTENER_CITAS_PENDIENTES_POR_MEDICO = "SELECT c.idCita, c.fecha, c.hora, c.motivo, c.estado, "
            + "u.idUsuario, u.nombre, u.edad, u.email, u.telefono, p.seguro_medico, p.numero_seguro "
            + "FROM Citas c JOIN Usuarios u ON c.idPaciente = u.idUsuario "
            + "Join pacientes p on c.idPaciente = p.idPaciente "
            + "WHERE c.idMedico = ? AND c.estado in ('PENDIENTE', 'ACEPTADA') order by fecha asc, hora asc";

    // Actualizar estado de la cita
    String ACTUALIZAR_ESTADO_CITA = "UPDATE citas Set estado = ? where idCita = ?";

    //Obtener citas por paciente
    String OBTENER_CITAS_POR_PACIENTE
            = "SELECT c.idCita, m.idMedico, u.idUsuario, u.nombre, u.telefono, "
            + "c.fecha, c.hora, c.motivo, c.estado "
            + "FROM citas c "
            + "JOIN medicos m on c.idMedico = m.idMedico "
            + "JOIN usuarios u on m.idMedico = u.idUsuario "
            + "WHERE c.idPaciente = ?";

    @Override
    public List<Cita> obtenerCitasPorMedicoYFecha(int idMedico, LocalDate fecha) {
        List<Cita> citas = new ArrayList<>();
        try (Connection conn = DBconnection.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(OBTENER_CITAS_POR_MEDICO_Y_FECHA)) {

            stmt.setInt(1, idMedico);
            stmt.setDate(2, Date.valueOf(fecha));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Cita cita = new Cita();
                    cita.setIdCita(rs.getInt("idCita"));
                    cita.setIdMedico(rs.getInt("idMedico"));
                    cita.setIdPaciente(rs.getInt("idPaciente"));
                    cita.setFecha(rs.getDate("fecha").toLocalDate());

                    Time horaSQL = rs.getTime("hora");
                    if (horaSQL != null) {
                        cita.setHora(horaSQL.toLocalTime());
                    } else {
                        cita.setHora(null);
                    }

                    citas.add(cita);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener citas por médico y fecha: " + e.getMessage());
            e.printStackTrace();
        }
        return citas;
    }

    @Override
    public boolean agendarCita(int idPaciente, int idMedico, LocalDate fecha, LocalTime hora) {

        Cita cita = new Cita();
        cita.setIdPaciente(idPaciente);
        cita.setIdMedico(idMedico);
        cita.setFecha(fecha);
        cita.setHora(hora);
        cita.setEstado(Cita.Estado.PENDIENTE);
        return registrarCita(cita);
    }

    @Override
    public boolean registrarCita(Cita cita) {
        boolean exito = false;

        try (Connection conn = DBconnection.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(REGISTRAR_CITA)) {

            stmt.setInt(1, cita.getIdPaciente());
            stmt.setInt(2, cita.getIdMedico());
            stmt.setDate(3, Date.valueOf(cita.getFecha()));
            stmt.setTime(4, Time.valueOf(cita.getHora()));
            stmt.setString(5, cita.getMotivo());
            stmt.setString(6, cita.getEstado().name());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Cita registraa correctamente");
                exito = true;
            }

        } catch (SQLException ex) {
            System.out.println("Error registrando cita: " + ex.getMessage());
            ex.printStackTrace();
        }
        return exito;
    }

    @Override
    public List<Usuario> getPacientesPorMedico(int idMedico) {
        List<Usuario> pacientes = new ArrayList<>();
        try (Connection conn = DBconnection.obtenerConexion(); PreparedStatement ps = conn.prepareStatement(OBTENER_PACIENTES_POR_MEDICO)) {

            ps.setInt(1, idMedico);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Usuario paciente = new Paciente();

                    paciente.setIdUsuario(rs.getInt("idUsuario"));
                    paciente.setNombre(rs.getString("nombre"));
                    paciente.setTipoUsuario(Usuario.TipoUsuario.valueOf(rs.getString("tipoUsuario")));
                    paciente.setDireccion(rs.getString("direccion"));
                    paciente.setEmail(rs.getString("email"));
                    paciente.setTelefono(rs.getString("telefono"));

                    pacientes.add(paciente);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener pacientes por médico: " + e.getMessage());
            e.printStackTrace();
        }
        return pacientes;
    }

    @Override
    public List<Cita> obtenerCitasPendientesPorMedico(int idMedico) {
        List<Cita> citas = new ArrayList<>();
        try (Connection conn = DBconnection.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(OBTENER_CITAS_PENDIENTES_POR_MEDICO)) {
            stmt.setInt(1, idMedico);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Cita cita = new Cita();
                    cita.setIdCita(rs.getInt("idCita"));
                    cita.setIdMedico(idMedico);

                    cita.setFecha(rs.getDate("fecha").toLocalDate());
                    cita.setHora(LocalTime.parse(rs.getString("hora")));

                    cita.setMotivo(rs.getString("motivo"));
                    cita.setEstado(Cita.Estado.valueOf(rs.getString("estado")));

                    Paciente paciente = new Paciente();
                    paciente.setIdUsuario(rs.getInt("idUsuario"));
                    paciente.setNombre(rs.getString("nombre"));
                    paciente.setEdad(rs.getInt("edad"));
                    paciente.setEmail(rs.getString("email"));
                    paciente.setTelefono(rs.getString("telefono"));
                    paciente.setSeguroMedico(rs.getString("seguro_medico"));
                    paciente.setNumeroSeguro(rs.getString("numero_seguro"));
                    cita.setPaciente(paciente);

                    citas.add(cita);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener citas pendientes: " + e.getMessage());
        }
        return citas;
    }

    @Override
    public boolean actualizarEstadoCita(int idCita, String nuevoEstado) {
        try (Connection conn = DBconnection.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(ACTUALIZAR_ESTADO_CITA)) {

            stmt.setString(1, nuevoEstado);
            stmt.setInt(2, idCita);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar el estado de la cita: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Cita> obtenerCitasPorPaciente(int idPaciente) {

        List<Cita> citas = new ArrayList<>();

        try (Connection conn = DBconnection.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(OBTENER_CITAS_POR_PACIENTE)) {

            stmt.setInt(1, idPaciente);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Cita cita = new Cita();
                    cita.setIdCita(rs.getInt("idCita"));
                    cita.setIdMedico(rs.getInt("idMedico"));
                    cita.setFecha(rs.getDate("fecha").toLocalDate());
                    cita.setHora(rs.getTime("hora").toLocalTime());
                    cita.setMotivo(rs.getString("motivo"));
                    cita.setEstado(Cita.Estado.valueOf(rs.getString("estado")));

                    Medico medico = new Medico();
                    medico.setIdUsuario(rs.getInt("idUsuario"));
                    medico.setNombre(rs.getString("nombre"));
                    medico.setTelefono(rs.getString("telefono"));

                    cita.setMedico(medico);

                    citas.add(cita);
                }
            }

        } catch (SQLException ex) {
            System.err.println("Error al obtener citas por paciente");
            ex.printStackTrace();
        }

        return citas;
    }

}
