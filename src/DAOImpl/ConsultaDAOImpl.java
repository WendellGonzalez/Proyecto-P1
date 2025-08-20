/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOImpl;

import DAO.ConsultaDAO;
import Model.ConsultaMedica;
import Model.Receta;
import SQLConnection.DBconnection;
import java.util.List;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author wendellgonzalez
 */
public class ConsultaDAOImpl implements ConsultaDAO{
    
    /**
     * CONSULTAS
     * @param consulta
     * @param recetas
     * @return 
     */
    

    // Insertar la consulta realizada en la base de datos
    String sqlConsulta = "INSERT INTO Consulta (idCita, idPaciente, idMedico, diagnostico, recomendaciones, fechaConsulta) VALUES (?, ?, ?, ?, ?, ?)";
   
    // Insertar la receta en la base de datos
    String sqlReceta = "INSERT INTO Receta (id_consulta, medicamento, dosis, frecuencia, duracion) VALUES (?, ?, ?, ?, ?)";
    
    // obtener consulta por cita
    String OBTENER_CONSULTA_POR_CITA = "SELECT * FROM Consulta WHERE idCita = ?";
    
    //Obtener consultas por paciente
    String OBTENER_CONSULTAS_POR_PACIENTE = "SELECT * FROM Consulta WHERE idPaciente = ?";
    
    //Obtener recetas por consulta
    String OBTENER_RECETAS_POR_CONSULTA = "SELECT * FROM Receta WHERE id_consulta = ?";
    
    @Override
    public boolean registrarConsulta(ConsultaMedica consulta) {
        
        try {
            
            try (Connection conn = DBconnection.obtenerConexion();PreparedStatement psConsulta = conn.prepareStatement(sqlConsulta, Statement.RETURN_GENERATED_KEYS)) {
                psConsulta.setInt(1, consulta.getIdCita());
                psConsulta.setInt(2, consulta.getIdPaciente());
                psConsulta.setInt(3, consulta.getIdMedico());
                psConsulta.setString(4, consulta.getDiagnostico());
                psConsulta.setString(5, consulta.getRecomendaciones());
                psConsulta.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
                
                int affectedRows = psConsulta.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Falló la inserción de la consulta.");
                }
                
                int idConsulta;
                try (ResultSet generatedKeys = psConsulta.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        idConsulta = generatedKeys.getInt(1);
                        
                        
                        try (PreparedStatement psReceta = conn.prepareStatement(sqlReceta)) {
                            for (Receta receta : consulta.getRecetas()) {
                                psReceta.setInt(1, idConsulta);
                                psReceta.setString(2, receta.getMedicamento());
                                psReceta.setString(3, receta.getDosis());
                                psReceta.setString(4, receta.getFrecuencia());
                                psReceta.setString(5, receta.getDuracion());
                                psReceta.addBatch();
                            }
                            psReceta.executeBatch();
                        }
                    } else {
                        throw new SQLException("Falló la obtención del ID de la consulta.");
                    }
                }
            }
            
            return true;
        } catch (SQLException e) {
            System.err.println("Error al registrar consulta: " + e.getMessage());
            return false;
        } 
    }
    @Override
    public ConsultaMedica obtenerConsultaPorCita(int idCita) {
        ConsultaMedica consulta = null;
                
        try (Connection conn = DBconnection.obtenerConexion();PreparedStatement ps = conn.prepareStatement(OBTENER_CONSULTA_POR_CITA)) {
            ps.setInt(1, idCita);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    consulta = new ConsultaMedica();
                    consulta.setIdConsulta(rs.getInt("idConsulta"));
                    consulta.setIdCita(rs.getInt("idCita"));
                    consulta.setIdPaciente(rs.getInt("idPaciente"));
                    consulta.setIdMedico(rs.getInt("idMedico"));
                    consulta.setDiagnostico(rs.getString("diagnostico"));
                    consulta.setRecomendaciones(rs.getString("recomendaciones"));
                    consulta.setFechaConsulta(rs.getTimestamp("fechaConsulta").toLocalDateTime());
                    
                    // Obtener las recetas para esta consulta
                    List<Receta> recetas = obtenerRecetasPorConsulta(consulta.getIdConsulta());
                    consulta.setRecetas(recetas);
                    
                    
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la consulta por cita: " + e.getMessage());
        }
        return consulta;
    }

    @Override
    public List<ConsultaMedica> obtenerConsultasPorPaciente(int idPaciente) {
        List<ConsultaMedica> consultas = new ArrayList<>();
        
        try (Connection conn = DBconnection.obtenerConexion();PreparedStatement ps = conn.prepareStatement(OBTENER_CONSULTAS_POR_PACIENTE)) {
            ps.setInt(1, idPaciente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ConsultaMedica consulta = new ConsultaMedica();
                    consulta.setIdConsulta(rs.getInt("idConsulta"));
                    consulta.setIdCita(rs.getInt("idCita"));
                    consulta.setIdPaciente(rs.getInt("idPaciente"));
                    consulta.setIdMedico(rs.getInt("idMedico"));
                    consulta.setDiagnostico(rs.getString("diagnostico"));
                    consulta.setRecomendaciones(rs.getString("recomendaciones"));
                    consulta.setFechaConsulta(rs.getTimestamp("fechaConsulta").toLocalDateTime());
                    
                    // Obtener las recetas para cada consulta
                    List<Receta> recetas = obtenerRecetasPorConsulta(consulta.getIdConsulta());
                    consulta.setRecetas(recetas);
                    
                    consultas.add(consulta);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener las consultas por paciente: " + e.getMessage());
        }
        return consultas;
    }
    
    @Override
    public List<Receta> obtenerRecetasPorConsulta(int idConsulta) {
        List<Receta> recetas = new ArrayList<>();
        
        try (Connection conn = DBconnection.obtenerConexion();PreparedStatement ps = conn.prepareStatement(OBTENER_RECETAS_POR_CONSULTA)) {
            ps.setInt(1, idConsulta);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Receta receta = new Receta();
                    receta.setIdReceta(rs.getInt("id_receta"));
                    receta.setIdConsulta(rs.getInt("id_consulta"));
                    receta.setMedicamento(rs.getString("medicamento"));
                    receta.setDosis(rs.getString("dosis"));
                    receta.setFrecuencia(rs.getString("frecuencia"));
                    receta.setDuracion(rs.getString("duracion"));
                    recetas.add(receta);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener las recetas para la consulta " + idConsulta + ": " + e.getMessage());
        }
        return recetas;
    }


    
}
