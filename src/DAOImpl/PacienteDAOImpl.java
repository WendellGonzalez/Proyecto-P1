/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOImpl;

import DAO.PacienteDAO;
import Model.HistorialMedico;
import Model.Paciente;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import SQLConnection.DBconnection;

/**
 *
 * @author wendellgonzalez
 */
public class PacienteDAOImpl implements PacienteDAO {

    String REGISTRO_PACIENTE = "INSERT INTO pacientes (idPaciente, seguro_medico, numero_seguro, contacto_emergencia, relacion_contacto, telefono_contacto) VALUES (?, ?, ?, ?, ?, ?)";
    String REGISTRO_HISTORIAL_PACIENTE = "INSERT INTO historialMedico (idPaciente, tipo_sangre, alergias, enfermedades_cronicas) VALUES (?,?,?,?)";

    String HISTORIAL_POR_PACIENTE = "SELECT * FROM historialMedico WHERE idPaciente = ?";

    String UPDATE_PACIENTE = "UPDATE usuarios SET nombre = ?, direccion = ?, email = ?, telefono = ? WHERE tipoUsuario = 'PACIENTE';";

    String ELIMINAR_PACIENTE = "DELETE FROM pacientes WHERE idPaciente = ?";

    String LISTAR_PACIENTES = "SELECT * FROM pacientes";
    
    String PACIENTES_POR_MEDICO = "SELECT u.idUsuario, u.nombre, u.telefono, u.edad, "
            + "p.seguro_medico, p.numero_seguro "
            + "FROM usuarios u "
            + "JOIN pacientes p ON u.idUsuario = p.idPaciente "
            + "JOIN citas c ON c.idPaciente = p.idPaciente "
            + "WHERE c.idMedico = ?";

    private boolean esVacio(String s) {
        return s == null || s.trim().isEmpty();
    }

    @Override
    public boolean registrar(Paciente paciente) {

        try (Connection conn = DBconnection.obtenerConexion(); PreparedStatement stmtPaciente = conn.prepareStatement(REGISTRO_PACIENTE, Statement.RETURN_GENERATED_KEYS); PreparedStatement stmtHistorial = conn.prepareStatement(REGISTRO_HISTORIAL_PACIENTE, Statement.RETURN_GENERATED_KEYS)) {

            conn.setAutoCommit(false);

            int idUsuario = paciente.getIdUsuario();

            stmtPaciente.setInt(1, idUsuario);
            stmtPaciente.setString(2, esVacio(paciente.getSeguroMedico()) ? null : paciente.getSeguroMedico());
            stmtPaciente.setString(3, esVacio(paciente.getNumeroSeguro()) ? null : paciente.getNumeroSeguro());
            stmtPaciente.setString(4, esVacio(paciente.getContactoEmergencia()) ? null : paciente.getContactoEmergencia());
            stmtPaciente.setString(5, esVacio(paciente.getRelacionContacto()) ? null : paciente.getRelacionContacto());
            stmtPaciente.setString(6, esVacio(paciente.getTelefonoContacto()) ? null : paciente.getTelefonoContacto());

            int filasPaciente = stmtPaciente.executeUpdate();
            if (filasPaciente == 0) {
                throw new SQLException("No se insertó paciente");
            }

            try {
                HistorialMedico h = paciente.getHistorial();

                if (h == null) {
                    h = new HistorialMedico();

                }
                stmtHistorial.setInt(1, idUsuario);
                stmtHistorial.setString(2, esVacio(h.getTipoSangre()) ? null : h.getTipoSangre());
                stmtHistorial.setString(3, esVacio(h.getAlergias()) ? null : h.getAlergias());
                stmtHistorial.setString(4, esVacio(h.getEnfermedadesCronicas()) ? null : h.getEnfermedadesCronicas());

                int filasHistorial = stmtHistorial.executeUpdate();

                if (filasHistorial == 0) {
                    throw new SQLException("No se inserto historial medico.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    
@Override
public List<Paciente> listarPorMedico(int idMedico) {
    List<Paciente> lista = new ArrayList<>();

    try (Connection conn = DBconnection.obtenerConexion();
         PreparedStatement stmtConsulta = conn.prepareStatement(PACIENTES_POR_MEDICO)) {

        stmtConsulta.setInt(1, idMedico);
        ResultSet rsPacientes = stmtConsulta.executeQuery();

        while (rsPacientes.next()) {
            Paciente p = new Paciente();

            p.setIdUsuario(rsPacientes.getInt("idUsuario"));
            p.setNombre(rsPacientes.getString("nombre"));
            p.setTelefono(rsPacientes.getString("telefono"));
            p.setEdad(rsPacientes.getInt("edad"));

            p.setSeguroMedico(rsPacientes.getString("seguro_medico") != null 
                               ? rsPacientes.getString("seguro_medico") 
                               : "Sin seguro");
            p.setNumeroSeguro(rsPacientes.getString("numero_seguro") != null 
                              ? rsPacientes.getString("numero_seguro") 
                              : "N/A");

            HistorialMedico h = null;
            try (PreparedStatement stmtHistorial = conn.prepareStatement(HISTORIAL_POR_PACIENTE)) {
                stmtHistorial.setInt(1, p.getIdUsuario());
                ResultSet rsHistorial = stmtHistorial.executeQuery();

                if (rsHistorial.next()) {
                    h = new HistorialMedico();
                    h.setTipoSangre(rsHistorial.getString("tipo_sangre"));
                    h.setAlergias(rsHistorial.getString("alergias"));
                    h.setEnfermedadesCronicas(rsHistorial.getString("enfermedades_cronicas"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            p.setHistorial(h);
            lista.add(p);
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return lista;
}


    @Override
    public boolean actualizar(Paciente p) {
        try (Connection conn = DBconnection.obtenerConexion()) {

            try (PreparedStatement stmt = conn.prepareStatement(UPDATE_PACIENTE)) {

                stmt.setString(1, p.getNombre());
                stmt.setString(2, p.getDireccion());
                stmt.setString(3, p.getEmail());
                stmt.setString(4, p.getTelefono());
                stmt.setInt(5, p.getIdUsuario());
                stmt.executeUpdate();

            } catch (SQLException ex) {
                throw new SQLException("Error");
            }

        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public boolean eliminar(int idPaciente) {
        try (Connection conn = DBconnection.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(ELIMINAR_PACIENTE)) {
            stmt.setInt(1, idPaciente);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Paciente> listar() {
        List<Paciente> lista = new ArrayList<>();

        try (Connection conn = DBconnection.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(LISTAR_PACIENTES); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Paciente p = new Paciente();
                p.setIdUsuario(rs.getInt("idPaciente"));
                p.setidMedico(rs.getInt("idMedico"));
                p.setSeguroMedico(rs.getString("seguro_medico"));
                p.setNumeroSeguro(rs.getString("numero_seguro"));
                p.setContactoEmergencia(rs.getString("contacto_emergencia"));
                p.setRelacionContacto(rs.getString("relacion_contacto"));
                p.setTelefonoContacto(rs.getString("telefono_contacto"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public int obtenderIdPacientePorIdUsuario(int idUsuario) {
        String sql = "SELECT idPaciente FROM pacientes WHERE idPaciente = ?";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idPaciente = -1;

        try {
            con = DBconnection.obtenerConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();
            if (rs.next()) {
                idPaciente = rs.getInt("idPaciente");
            }
        } catch (SQLException ex) {

            System.err.println("Error al obtener idPaciente por idUsuario: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
        return idPaciente;
    }
}
