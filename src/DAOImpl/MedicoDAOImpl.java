/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOImpl;

import DAO.HorarioDAO;
import DAO.MedicoDAO;
import Model.Especialidad;
import Model.Medico;
import Model.Usuario;
import SQLConnection.DBconnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wendellgonzalez
 */
public class MedicoDAOImpl implements MedicoDAO {

    private HorarioDAO horarioDAO = new HorarioDAOImpl();

    String REGISTRO_MEDICO = "INSERT INTO medicos (idMedico, idEspecialidad, numero_colegiatura, universidad, fecha_graduacion, anios_experiencia, estado_solicitud) VALUES (?, ?, ?, ?, ?, ?, 'ESPERA')";

    String ACTUALIZAR_MEDICO = "UPDATE medicos SET especialidad = ?, numero_colegiatura = ?, universidad = ?, fecha_graduacion = ?, anios_experiencia = ? WHERE idMedico = ?";

    String ELIMINAR_MEDICO = "DELETE FROM medicos WHERE idMedico = ?";

    String LISTAR_MEDICOS = "SELECT m.*, e.nombre, e.descripcion "
            + " FROM medicos m "
            + "JOIN especialidades e ON m.idEspecialidad = e.idEspecialidad";

    String OBTENER_MEDICOS_POR_ESPECIALIDAD = "SELECT m.idMedico, u.nombre, u.telefono, m.numero_colegiatura, m.universidad, m.anios_experiencia "
            + "FROM medicos m "
            + "JOIN usuarios u on m.idMedico = u.idUsuario "
            + "WHERE m.idEspecialidad = ? and m.estado_solicitud = 'ACEPTADO'";

    String OBTENER_MEDICOS_EN_ESPERA = "SELECT u.idUsuario, u.nombre, u.edad, u.email, u.password, u.tipoUsuario, "
            + "u.fechaNacimiento, u.telefono, u.direccion, u.sexo, "
            + "m.idMedico, m.idEspecialidad, m.numero_colegiatura, m.universidad, "
            + "m.fecha_graduacion, m.anios_experiencia, m.estado_solicitud, e.nombre  "
            + "FROM Medicos m "
            + "INNER JOIN Usuarios u ON m.idMedico = u.idUsuario "
            + "INNER JOIN Especialidades e ON m.idEspecialidad = e.idEspecialidad "
            + "WHERE m.estado_solicitud = 'ESPERA'";
    
    String ACTUALIZAR_ESTADO_SOLICITUD = "UPDATE Medicos SET estado_solicitud = ? WHERE idMedico = ?";
    
    String OBTENER_MEDICOS_RECHAZADOS = "SELECT u.idUsuario, u.nombre, u.edad, u.email, u.password, u.tipoUsuario, "
            + "u.fechaNacimiento, u.telefono, u.direccion, u.sexo, "
            + "m.idMedico, m.idEspecialidad, m.numero_colegiatura, m.universidad, "
            + "m.fecha_graduacion, m.anios_experiencia, m.estado_solicitud, e.nombre  "
            + "FROM Medicos m "
            + "INNER JOIN Usuarios u ON m.idMedico = u.idUsuario "
            + "INNER JOIN Especialidades e ON m.idEspecialidad = e.idEspecialidad "
            + "WHERE m.estado_solicitud = 'RECHAZADO'";

    @Override
    public boolean registrar(Medico medico) {

        Connection conn = null;
        try {
            conn = DBconnection.obtenerConexion();

            try (PreparedStatement stmtMedico = conn.prepareStatement(REGISTRO_MEDICO)) {
                int idMedico = medico.getIdUsuario();
                if (idMedico <= 0) {
                    System.err.println("ID de usuario no valido al registrar medico.");
                }

                stmtMedico.setInt(1, idMedico);
                stmtMedico.setInt(2, medico.getIdEspecialidad());
                stmtMedico.setString(3, medico.getNumeroColegiatura());
                stmtMedico.setString(4, medico.getUniversidad());
                stmtMedico.setDate(5, java.sql.Date.valueOf(medico.getFechaGraduacion()));
                stmtMedico.setString(6, medico.getAniosExperiencia());

                int filasMedico = stmtMedico.executeUpdate();
                if (filasMedico > 0) {
                    if (horarioDAO.insertarHorarioDefecto(idMedico)) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al registrar medico" + e.getMessage());
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException rollbackEx) {
                System.err.println("Error al hacer rollback: " + rollbackEx.getMessage());
                rollbackEx.printStackTrace();
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException closeEx) {
                    System.err.println("Error al cerrar la conexión: " + closeEx.getMessage());
                }
            }
        }
        return false;
    }

    
    @Override
    public boolean actualizar(Medico medico) {

        try (Connection conn = DBconnection.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(ACTUALIZAR_MEDICO)) {
            stmt.setInt(1, medico.getEspecialidad().getIdEspecialidad());
            stmt.setString(2, medico.getNumeroColegiatura());
            stmt.setString(3, medico.getUniversidad());
            stmt.setDate(4, Date.valueOf(medico.getFechaGraduacion()));
            stmt.setString(5, medico.getAniosExperiencia());
            stmt.setInt(6, medico.getIdUsuario());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean eliminar(int idMedico) {
        try (Connection conn = DBconnection.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(ELIMINAR_MEDICO)) {

            stmt.setInt(1, idMedico);

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Medico> listar() {
        List<Medico> lista = new ArrayList<>();
        try (Connection conn = DBconnection.obtenerConexion(); PreparedStatement stmtListarMedico = conn.prepareStatement(LISTAR_MEDICOS); ResultSet rs = stmtListarMedico.executeQuery()) {
            while (rs.next()) {
                Medico m = new Medico();
                m.setIdUsuario(rs.getInt("idMedico"));

                Especialidad esp = new Especialidad(
                        rs.getInt("idEspecialidad"),
                        rs.getString("nombre"),
                        rs.getString("descripcion")
                );
                m.setEspecialidad(esp);

                m.setNumeroColegiatura(rs.getString("numero_colegiatura"));
                m.setUniversidad(rs.getString("universidad"));
                m.setFechaGraduacion(rs.getDate("fecha_graduacion").toLocalDate());
                m.setAniosExperiencia(rs.getString("anios_experiencia"));
                lista.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<Medico> obtenerMedicosPorEspecialidad(int idEspecialidad) {
        List<Medico> lista = new ArrayList<>();

        try (Connection conn = DBconnection.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(OBTENER_MEDICOS_POR_ESPECIALIDAD)) {

            stmt.setInt(1, idEspecialidad);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Medico m = new Medico();
                m.setIdMedico(rs.getInt("idMedico"));
                m.setNombre(rs.getString("nombre"));
                m.setTelefono(rs.getString("telefono"));
                m.setNumeroColegiatura(rs.getString("numero_colegiatura"));
                m.setUniversidad(rs.getString("universidad"));
                m.setAniosExperiencia(rs.getString("anios_experiencia"));
                lista.add(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public List<Medico> obtenerMedicosEnEspera() {
        List<Medico> medicosEnEspera = new ArrayList<>();
        try (Connection conn = DBconnection.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(OBTENER_MEDICOS_EN_ESPERA); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Medico medico = new Medico();
                medico.setIdUsuario(rs.getInt("idUsuario"));
                medico.setNombre(rs.getString("nombre"));
                medico.setEdad(rs.getInt("edad"));
                medico.setEmail(rs.getString("email"));
                medico.setPassword(rs.getString("password"));
                String tipoUsuariostr = rs.getString("tipoUsuario");

                if (tipoUsuariostr != null) {
                    medico.setTipoUsuario(Usuario.TipoUsuario.valueOf(tipoUsuariostr.toUpperCase()));
                }

                medico.setFechaNacimiento(rs.getDate("fechaNacimiento") != null ? rs.getDate("fechaNacimiento").toLocalDate() : null);
                medico.setTelefono(rs.getString("telefono"));
                medico.setDireccion(rs.getString("direccion"));
                String sexoStr = rs.getString("sexo");
                if (sexoStr != null) {
                    medico.setSexo(Usuario.sexo.valueOf(sexoStr.toUpperCase()));
                }

                medico.setIdMedico(rs.getInt("idMedico"));
                medico.setNumeroColegiatura(rs.getString("numero_colegiatura"));
                medico.setUniversidad(rs.getString("universidad"));
                medico.setFechaGraduacion(rs.getDate("fecha_graduacion") != null ? rs.getDate("fecha_graduacion").toLocalDate() : null);
                medico.setAniosExperiencia(rs.getString("anios_experiencia"));
                medico.setEstadoSolicitud(rs.getString("estado_solicitud"));

                Especialidad especialidad = new Especialidad();
                especialidad.setIdEspecialidad(rs.getInt("idEspecialidad"));
                especialidad.setNombre(rs.getString("nombre"));
                medico.setEspecialidad(especialidad);

                medicosEnEspera.add(medico);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicosEnEspera;
    }

    @Override
    public boolean actualizarEstadoSolicitud(int idMedico, String nuevoEstado) {
        try (Connection conn = DBconnection.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(ACTUALIZAR_ESTADO_SOLICITUD)) {

            stmt.setString(1, nuevoEstado);
            stmt.setInt(2, idMedico);

            int filasAfectadas = stmt.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public List<Medico> obtenerMedicosRechazados() {
        List<Medico> medicosrechazados = new ArrayList<>();
        try (Connection conn = DBconnection.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(OBTENER_MEDICOS_RECHAZADOS); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Medico medico = new Medico();
                medico.setIdUsuario(rs.getInt("idUsuario"));
                medico.setNombre(rs.getString("nombre"));
                medico.setEdad(rs.getInt("edad"));
                medico.setEmail(rs.getString("email"));
                medico.setPassword(rs.getString("password"));
                String tipoUsuariostr = rs.getString("tipoUsuario");

                if (tipoUsuariostr != null) {
                    medico.setTipoUsuario(Usuario.TipoUsuario.valueOf(tipoUsuariostr.toUpperCase()));
                }

                medico.setFechaNacimiento(rs.getDate("fechaNacimiento") != null ? rs.getDate("fechaNacimiento").toLocalDate() : null);
                medico.setTelefono(rs.getString("telefono"));
                medico.setDireccion(rs.getString("direccion"));
                String sexoStr = rs.getString("sexo");
                if (sexoStr != null) {
                    medico.setSexo(Usuario.sexo.valueOf(sexoStr.toUpperCase()));
                }

                medico.setIdMedico(rs.getInt("idMedico"));
                medico.setNumeroColegiatura(rs.getString("numero_colegiatura"));
                medico.setUniversidad(rs.getString("universidad"));
                medico.setFechaGraduacion(rs.getDate("fecha_graduacion") != null ? rs.getDate("fecha_graduacion").toLocalDate() : null);
                medico.setAniosExperiencia(rs.getString("anios_experiencia"));
                medico.setEstadoSolicitud(rs.getString("estado_solicitud"));

                Especialidad especialidad = new Especialidad();
                especialidad.setIdEspecialidad(rs.getInt("idEspecialidad"));
                especialidad.setNombre(rs.getString("nombre"));
                medico.setEspecialidad(especialidad);

                medicosrechazados.add(medico);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicosrechazados;
    }

}
