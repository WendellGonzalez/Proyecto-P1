/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOImpl;

import DAO.UsuarioDAO;
import Model.Administrador;
import Model.Medico;
import Model.Paciente;
import Model.Usuario;
import Model.Usuario.TipoUsuario;
import SQLConnection.DBconnection;
import Model.UsuarioGeneral;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author wendellgonzalez
 */
public class UsuarioDAOimpl implements UsuarioDAO {

    // SQL REGISTRO DE USUARIO GENERAL
    String REGISTRO_USUARIOS = "INSERT INTO usuarios (nombre, email, password, tipoUsuario, fechaNacimiento, telefono, direccion, sexo, edad)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
    
    //AUTENTICACION CONSULTAS
    String AUTENTICACION = "SELECT * from Usuarios WHERE email = ? AND Password = ?";
    String OBTENER_MEDICO = "SELECT * FROM medicos WHERE idMedico = ?";
    
    //LISTAR USUARIOS CONSULTAS, CON VALIDACION DE QUE SOLO LOS MEDICOS ACEPTADOS APAREZCAN EN LA GESTION
    String LISTAR_USUARIOS = "SELECT u.*, m.estado_solicitud FROM usuarios u " +
        "LEFT JOIN medicos m ON u.idUsuario = m.idMedico " +
        "WHERE (u.tipoUsuario != 'MEDICO' OR m.estado_solicitud = 'ACEPTADO') " +
        "AND (u.nombre LIKE ? OR u.email LIKE ?)";

    //ACTUALIZACIONES
    String UPDATE_USUARIOS = "UPDATE usuarios SET nombre = ?, direccion = ?, email = ?, telefono = ? where idUsuario = ?";

    //ELIMINAR REGISTROS
    String DELETE_USUARIOS = "DELETE FROM usuarios WHERE idUsuario = ?";

    // CONTADORES DE USUARIOS
    String CONTAR_USUARIOS = "SELECT COUNT(*) FROM usuarios";
    String CONTAR_MEDICOS = "SELECT COUNT(*) FROM usuarios WHERE tipoUsuario = 'MEDICO';";
    String CONTAR_PACIENTES = "SELECT COUNT(*) FROM usuarios WHERE tipoUsuario = 'PACIENTE';";
    String CONTAR_PACIENTESPORMEDICO = "SELECT COUNT(DISTINCT idPaciente) FROM Citas where idMedico = ?";
    
    // EMAIL EXISTE
    String sqlEmailExiste = "SELECT COUNT(*) FROM usuarios WHERE email = ?";

    private boolean esVacio(String s) {
        return s == null || s.trim().isEmpty();
    }

    @Override
    public boolean registrar(Usuario user) {
        try (Connection conn = DBconnection.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(REGISTRO_USUARIOS, Statement.RETURN_GENERATED_KEYS)) {

            LocalDate fechaNacimiento = user.getFechaNacimiento();

            stmt.setString(1, user.getNombre());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getTipoUsuario().name());
            if (fechaNacimiento != null) {
                stmt.setDate(5, Date.valueOf(fechaNacimiento));
            } else {
                stmt.setNull(5, java.sql.Types.DATE);
            }
            stmt.setString(6, user.getTelefono());
            stmt.setString(7, user.getDireccion());
            stmt.setString(8, user.getSexo().name());
            stmt.setInt(9, user.getEdad());

            int filas = stmt.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se insertó el usuario.");
            }

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    user.setIdUsuario(keys.getInt(1));
                    return true;
                }
            }
        } catch (SQLException e) {
            if (e.getMessage().contains("UNIQUE KEY constraint")) {
                JOptionPane.showMessageDialog(null, "El email ya está registrado.");
            } else {
                e.printStackTrace();
            }
        }

        return true;
    }

    /**
     * AUTENTICACION
     *
     * @param email
     * @param Password
     * @return
     */
    //Autenticacion basica de usuario
    @Override
    public Usuario autenticar(String email, String Password) {
        Usuario user = null;

        try (Connection conn = DBconnection.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(AUTENTICACION)) {

            stmt.setString(1, email);
            stmt.setString(2, Password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario.TipoUsuario tipo = Usuario.TipoUsuario.valueOf(rs.getString("tipoUsuario").toUpperCase());
                LocalDate fecha = rs.getDate("fechaNacimiento").toLocalDate();

                switch (tipo) {
                    case PACIENTE ->
                        user = new Paciente();
                    case MEDICO -> {
                        user = new Medico();

                        try (PreparedStatement medicoStmt = conn.prepareStatement(OBTENER_MEDICO)) {
                            medicoStmt.setInt(1, rs.getInt("idUsuario"));
                            try (ResultSet medicoRs = medicoStmt.executeQuery()) {
                                if (medicoRs.next()) {
                                    ((Medico) user).setIdEspecialidad(medicoRs.getInt("idEspecialidad"));
                                    ((Medico) user).setEstadoSolicitud(medicoRs.getString("estado_solicitud"));
                                    ((Medico) user).setNumeroColegiatura(medicoRs.getString("numero_colegiatura"));
                                    ((Medico) user).setUniversidad(medicoRs.getString("universidad"));
                                    ((Medico) user).setFechaGraduacion(medicoRs.getDate("fecha_graduacion").toLocalDate());
                                    ((Medico) user).setAniosExperiencia(medicoRs.getString("anios_experiencia"));
                                }
                            }
                        }

                    }
                    case ADMINISTRADOR ->
                        user = new Administrador();
                }

                if (user != null) {
                    user.setIdUsuario(rs.getInt("idUsuario"));
                    user.setNombre(rs.getString("nombre"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setTipoUsuario(tipo);
                    user.setFechaNacimiento(fecha);
                    user.setTelefono(rs.getString("telefono"));
                    user.setDireccion(rs.getString("direccion"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public List<Usuario> listarUsuario(String filtro) {
        List<Usuario> lista = new ArrayList<>();

        try (Connection conn = DBconnection.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(LISTAR_USUARIOS)) {

            stmt.setString(1, "%" + filtro + "%");
            stmt.setString(2, "%" + filtro + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                //Obtener el tipo de usuario desde la DB
                String tipoStr = rs.getString("tipoUsuario");
                TipoUsuario tipo = TipoUsuario.valueOf(tipoStr);
                Usuario u;

                switch (tipo) {
                    case PACIENTE -> {
                        u = new Paciente(
                                rs.getInt("idUsuario"),
                                rs.getString("nombre"),
                                rs.getString("direccion"),
                                rs.getString("email"),
                                rs.getString("telefono"),
                                rs.getInt("edad")
                        );
                    }

                    case MEDICO -> {
                        u = new Medico(
                                rs.getInt("idUsuario"),
                                rs.getString("nombre"),
                                rs.getString("direccion"),
                                rs.getString("email"),
                                rs.getString("telefono"),
                                rs.getInt("edad")
                        );
                    }

                    default -> {
                        u = new UsuarioGeneral(
                                rs.getInt("idUsuario"),
                                rs.getString("nombre"),
                                rs.getString("direccion"),
                                rs.getString("email"),
                                rs.getString("telefono"),
                                rs.getInt("edad")
                        );
                    }
                }

                u.setTipoUsuario(tipo);
                lista.add(u);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar usuarios: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public boolean actualizar(Usuario u) {
        try (Connection conn = DBconnection.obtenerConexion()) {
            conn.setAutoCommit(false); 

            try (PreparedStatement stmtUsuarios = conn.prepareStatement(UPDATE_USUARIOS)) {
                stmtUsuarios.setString(1, u.getNombre());
                stmtUsuarios.setString(2, u.getDireccion());
                stmtUsuarios.setString(3, u.getEmail());
                stmtUsuarios.setString(4, u.getTelefono());
                stmtUsuarios.setInt(5, u.getIdUsuario());
                stmtUsuarios.executeUpdate();
            }

            if (u.getTipoUsuario() == null) {
                System.out.println("Tipo de usuario es null. No se actualizará la tabla específica.");
                conn.commit();
                return true;
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int idUsuario) {
        try (Connection conn = DBconnection.obtenerConexion()) {

            conn.setAutoCommit(false);

            try (PreparedStatement stmtUsuario = conn.prepareStatement(DELETE_USUARIOS)) {
                stmtUsuario.setInt(1, idUsuario);
                stmtUsuario.executeUpdate();
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }

    @Override
    public int contarUsuarios() {
        int total = 0;
        try (Connection conn = DBconnection.obtenerConexion(); PreparedStatement stmtContador = conn.prepareStatement(CONTAR_USUARIOS); ResultSet rs = stmtContador.executeQuery()) {

            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    @Override
    public int contarMedicos() {
        int total = 0;
        try (Connection conn = DBconnection.obtenerConexion(); PreparedStatement stmtMedicos = conn.prepareStatement(CONTAR_MEDICOS); ResultSet rs = stmtMedicos.executeQuery()) {
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    @Override
    public int contarPacientes() {
        int total = 0;
        try (Connection conn = DBconnection.obtenerConexion(); PreparedStatement stmtPacientes = conn.prepareStatement(CONTAR_PACIENTES); ResultSet rs = stmtPacientes.executeQuery()) {
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    @Override
    public int contarPacientesPorMedico(int idMedico) {
        int totalPacientes = 0;
        try (Connection conn = DBconnection.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(CONTAR_PACIENTESPORMEDICO)) {

            stmt.setInt(1, idMedico);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    totalPacientes = rs.getInt(1);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalPacientes;
    }

    @Override
    public boolean emailExiste(Connection conn, String email) {
        try (PreparedStatement ps = conn.prepareStatement(sqlEmailExiste)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean actualizarDatosPersonales(Usuario usuario) {
        
        String ACTUALIZAR_DATOS_PERSONALES = "UPDATE usuarios set nombre = ?, telefono = ?, email = ?, direccion = ?, password = ? where idUsuario = ?";
        
        try (Connection conn = DBconnection.obtenerConexion();
                PreparedStatement stmt = conn.prepareStatement(ACTUALIZAR_DATOS_PERSONALES)) {
            
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getTelefono());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getDireccion());
            stmt.setString(5, usuario.getPassword());
            stmt.setInt(6, usuario.getIdUsuario());
            stmt.executeUpdate();
            
            return true;
            
        } catch (Exception e) {
            System.err.println("Error al actualizar datos personales: " + e.getMessage());
            return false;
        }
    }
    
}
