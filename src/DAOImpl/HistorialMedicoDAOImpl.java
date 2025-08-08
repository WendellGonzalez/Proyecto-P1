/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOImpl;

import DAO.HistorialMedicoDAO;
import Model.HistorialMedico;
import Model.Medicamento;
import SQLConnection.DBconnection;

//import static Model.Usuario.TipoUsuario.ADMINISTRADOR;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wendellgonzalez
 */



public class HistorialMedicoDAOImpl implements HistorialMedicoDAO{
    
    String sqlHistorial = "SELECT * FROM HistorialMedico WHERE idPaciente = ?";
    String sqlMedicamentos = "SELECT nombre, dosis, duracion FROM medicamentos WHERE idHistorial = ?";
    
    String REGISTRO_HISTORIAL = "INSERT INTO historialMedico (idPaciente, tipo_sangre, alergias, enfermedades_cronicas) VALUES (?, ?, ?, ?)";
    
    String REGISTRO_MEDICAMENTO = "INSERT INTO medicamentos (idHistorial, nombre, dosis, duracion) VALUES (?, ?, ?, ?)";
    
    String ACTUALIZAR_HISTORIAL = "UPDATE historialMedico SET tipo_sangre = ?, alergias = ?, enfermedades_cronicas = ? WHERE idHistorial = ?";
    String INSERTAR_MEDICAMENTO = "INSERT INTO medicamentos(idHistorial, nombre, dosis, duracion) VALUES (?, ?, ?, ?)";
    
    String ELIMINAR_HISTORIAL = "DELETE FROM historialMedico WHERE idHistorial = ?";
    
    @Override
    public HistorialMedico buscarPorPaciente(int idPaciente) {
        HistorialMedico historial = null;
        
        try (Connection conn = DBconnection.obtenerConexion();
             PreparedStatement stmtHistorial = conn.prepareStatement(sqlHistorial)) {
            
            stmtHistorial.setInt(1, idPaciente);
            ResultSet rsHistorial = stmtHistorial.executeQuery();
            
            if (rsHistorial.next()) {
                historial = new HistorialMedico();
                historial.setIdHistorial(rsHistorial.getInt("idHistorial"));
                historial.setIdPaciente(idPaciente);
                historial.setTipoSangre(rsHistorial.getString("tipo_sangre"));
                historial.setAlergias(rsHistorial.getString("alergias"));
                historial.setEnfermedadesCronicas(rsHistorial.getString("enfermedades_cronicas"));
                
                try (PreparedStatement stmtMedicamentos = conn.prepareStatement(sqlMedicamentos)) {
                    
                    stmtMedicamentos.setInt(1, historial.getIdHistorial());
                    ResultSet rsMedicamentos = stmtMedicamentos.executeQuery();
                    List<Medicamento> medicamentos = new ArrayList<>();
                    
                    while (rsMedicamentos.next()) {
                        
                        Medicamento m = new Medicamento();
                        m.setNombre(rsMedicamentos.getString("nombre"));
                        m.setDosis(rsMedicamentos.getString("dosis"));
                        m.setDuracion(rsMedicamentos.getString("duracion"));
                        medicamentos.add(m);
                        
                    }
                    
                    historial.setMedicamentosActuales(medicamentos);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return historial;
    }
    
    /**
     *
     * @param historial
     */
    @Override
public boolean registrar(HistorialMedico historial) {
    try(Connection conn = DBconnection.obtenerConexion();
        PreparedStatement stmt = conn.prepareStatement(REGISTRO_HISTORIAL, Statement.RETURN_GENERATED_KEYS)) {
        
        stmt.setInt(1, historial.getIdPaciente());
        stmt.setString(2, historial.getTipoSangre());
        stmt.setString(3, historial.getAlergias());
        stmt.setString(4, historial.getEnfermedadesCronicas());
        int filasAfectadas = stmt.executeUpdate();
        
        if (filasAfectadas > 0) {
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                historial.setIdHistorial(rs.getInt(1));
                
                if (historial.getMedicamentosActuales() != null && !historial.getMedicamentosActuales().isEmpty()) {
                    try (PreparedStatement stmtMedicamentos = conn.prepareStatement(REGISTRO_MEDICAMENTO)) {
                        for (Medicamento m : historial.getMedicamentosActuales()) {
                            stmtMedicamentos.setInt(1, historial.getIdHistorial());
                            stmtMedicamentos.setString(2, m.getNombre());
                            stmtMedicamentos.setString(3, m.getDosis());
                            stmtMedicamentos.setString(4, m.getDuracion());
                            stmtMedicamentos.addBatch();
                        }
                        stmtMedicamentos.executeBatch();
                    }
                }
                return true;
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        System.err.println("Error SQL al registrar historial: " + ex.getMessage());
    }
    return false;
}
    
    @Override
    public boolean actualizar(HistorialMedico historial) {
    try (Connection conn = DBconnection.obtenerConexion()) {
        
        try (PreparedStatement stmtHistorial = conn.prepareStatement(ACTUALIZAR_HISTORIAL)) {
            stmtHistorial.setString(1, historial.getTipoSangre());
            stmtHistorial.setString(2, historial.getAlergias());
            stmtHistorial.setString(3, historial.getEnfermedadesCronicas());
            stmtHistorial.setInt(4, historial.getIdHistorial());
            
            int filasActualizadas = stmtHistorial.executeUpdate();
            if (filasActualizadas == 0) {
                return false; 
            }
        }

        if (historial.getMedicamentosActuales() != null && !historial.getMedicamentosActuales().isEmpty()) {
            try (PreparedStatement stmtInsert = conn.prepareStatement(INSERTAR_MEDICAMENTO)) {
                for (Medicamento m : historial.getMedicamentosActuales()) {
                    stmtInsert.setInt(1, historial.getIdHistorial());
                    stmtInsert.setString(2, m.getNombre());
                    stmtInsert.setString(3, m.getDosis());
                    stmtInsert.setString(4, m.getDuracion());
                    stmtInsert.addBatch();
                }
                stmtInsert.executeBatch();
            }
        }
        
        return true; 
    } catch (SQLException ex) {
        ex.printStackTrace();
        System.err.println("Error SQL al actualizar el historial: " + ex.getMessage());
        return false;
    }
    }

    @Override
    public boolean eliminar(int idHistorial) {
        try (Connection conn = DBconnection.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(ELIMINAR_HISTORIAL)) {
            
            stmt.setInt(1, idHistorial);
            int filasAfectadas = stmt.executeUpdate();
            
            return filasAfectadas > 0;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<HistorialMedico> listarPorPaciente(int idPaciente) {
        List<HistorialMedico> historiales = new ArrayList<>();
        
        HistorialMedico historial = buscarPorPaciente(idPaciente);
        if (historial != null) {
            historiales.add(historial);
        }
        return historiales;
    }
    
}
