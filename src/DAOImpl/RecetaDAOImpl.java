/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOImpl;

import DAO.RecetaDAO;
import Model.Receta;
import SQLConnection.DBconnection;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author wendellgonzalez
 */
public class RecetaDAOImpl implements RecetaDAO{

    /**
     * @Consultas
     */
    
    // Query para obtener las recetas por paciente, y tambien datos del medico que le puso la receta
        String sqlObtenerRecetasPorPaciente = "SELECT r.id_receta, r.medicamento, r.dosis, r.frecuencia, r.duracion, " +
                     "u.nombre AS nombre_medico, e.nombre, c.fechaConsulta AS fecha " +
                     "FROM Receta r " +
                     "INNER JOIN Consulta c ON r.id_consulta = c.idConsulta " + 
                     "INNER JOIN Medicos m ON c.idMedico = m.idMedico " +
                     "INNER JOIN especialidades e on m.idEspecialidad = e.idEspecialidad " +
                     "INNER JOIN Usuarios u ON m.idMedico = u.idUsuario " +
                     "WHERE c.idPaciente = ?";
        
        // Queryu para obtener el historial por paciente, tambien trayendo datos del medico 
        String sqlObtenerHistorialPorPaciente = "SELECT r.id_receta, r.medicamento, r.dosis, r.frecuencia, r.duracion, " +
                     "u.nombre AS nombre_medico, c.fechaConsulta AS fecha, c.diagnostico, c.recomendaciones " +
                     "FROM Receta r " +
                     "INNER JOIN Consulta c ON r.id_consulta = c.idConsulta " + 
                     "INNER JOIN Medicos m ON c.idMedico = m.idMedico " +
                     "INNER JOIN Usuarios u ON m.idMedico = u.idUsuario " +
                     "WHERE c.idPaciente = ?";
    
    @Override
    public List<Receta> obtenerRecetasPorPaciente(int idPaciente) {
        List<Receta> recetas = new ArrayList<>();
        
        try (Connection conn = DBconnection.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sqlObtenerRecetasPorPaciente)) {
            
            stmt.setInt(1, idPaciente);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Receta receta = new Receta();
                    receta.setIdReceta(rs.getInt("id_receta"));
                    receta.setMedicamento(rs.getString("medicamento"));
                    receta.setEspecialidad(rs.getString("nombre"));
                    receta.setDosis(rs.getString("dosis"));
                    receta.setFrecuencia(rs.getString("frecuencia"));
                    receta.setDuracion(rs.getString("duracion"));
                    receta.setNombreMedico(rs.getString("nombre_medico"));
                    receta.setFechaConsulta(rs.getDate("fecha").toLocalDate());
                    
                    recetas.add(receta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recetas;
    }
    
    @Override
    public List<Receta> obtenerHistorialPorPaciente(int idPaciente) {
        List<Receta> historial = new ArrayList<>();
        try (Connection conn = DBconnection.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sqlObtenerHistorialPorPaciente)) {
            
            stmt.setInt(1, idPaciente);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Receta receta = new Receta();
                    receta.setIdReceta(rs.getInt("id_receta"));
                    receta.setMedicamento(rs.getString("medicamento"));
                    receta.setDosis(rs.getString("dosis"));
                    receta.setFrecuencia(rs.getString("frecuencia"));
                    receta.setDuracion(rs.getString("duracion"));
                    receta.setNombreMedico(rs.getString("nombre_medico"));
                    receta.setFechaConsulta(rs.getDate("fecha").toLocalDate());
                    receta.setDiagnostico(rs.getString("diagnostico"));
                    receta.setRecomendaciones(rs.getString("recomendaciones"));
                    
                    historial.add(receta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historial;
    }
    
}
