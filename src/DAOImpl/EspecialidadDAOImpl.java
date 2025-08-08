/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOImpl;

import DAO.EspecialidadDAO;
import Model.Especialidad;
import SQLConnection.DBconnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wendellgonzalez
 */
public class EspecialidadDAOImpl implements EspecialidadDAO {

    // Obtener Todas las especialidades
    String OBTENER_ESPECIALIDADES = "SELECT idEspecialidad, nombre, descripcion From especialidades";
    
    // Registrar Especialidad
    String REGISTRAR_ESPECIALIDAD = "INSERT INTO especialidades(nombre, descripcion) VALUES (?,?)";
    
    //Actualizar Especialidad
    String ACTUALIZAR_ESPECIALIDAD = "UPDATE especialidades SET nombre = ?, descripcion = ? WHERE idEspecialidad = ?";
    
    //Eliminar Especialidad
    String ELIMINAR_ESPECIALIDAD = "DELETE FROM especialidades WHERE idEspecialidad = ?";
    
    //Buscar especialidad por nombre
    String BUSCAR_POR_NOMBRE = "SELECT * from especialidades Where nombre Like ?";
    
    //Contar especialidades
    String CONTAR_ESPECILIDADES = "SELECT COUNT(*) FROM especialidades";
    
    @Override
    public List<Especialidad> obtenerTodas() {
        List<Especialidad> lista = new ArrayList<>();

        try (Connection conn = DBconnection.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(OBTENER_ESPECIALIDADES); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("idEspecialidad");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                Especialidad especialidad = new Especialidad(id, nombre, descripcion);
                lista.add(especialidad);
            }
        } catch (SQLException e) {
            System.out.println("Error obteniendo especialidades: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public void insertar(String especialidad, String descripcion) {
        try (Connection conn = DBconnection.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(REGISTRAR_ESPECIALIDAD)) {

            stmt.setString(1, especialidad);
            stmt.setString(2, descripcion);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error insertando especialidad: " + e.getMessage());
        }
    }

    @Override
    public boolean actualizar(Especialidad especialidad) {
        try (Connection conn = DBconnection.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(ACTUALIZAR_ESPECIALIDAD)) {

            stmt.setString(1, especialidad.getNombre());
            stmt.setString(2, especialidad.getDescripcion());
            stmt.setInt(3, especialidad.getIdEspecialidad());

            int filasActualizadas = stmt.executeUpdate();
            return filasActualizadas > 0;

        } catch (SQLException e) {
            System.out.println("Error actualizando especialidad: " + e.getMessage());
            return false;
        }

    }

    @Override
    public boolean eliminar(int idEspecialidad) {
        try (Connection conn = DBconnection.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(ELIMINAR_ESPECIALIDAD)) {

            stmt.setInt(1, idEspecialidad);
            int filasEliminadas = stmt.executeUpdate();
            return filasEliminadas > 0; // true si eliminó

        } catch (SQLException e) {
            System.out.println("Error eliminando especialidad: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Especialidad> buscarPorNombre(String nombre) {
        List<Especialidad> lista = new ArrayList<>();
        
        try (Connection conn = DBconnection.obtenerConexion();
                PreparedStatement stmt = conn.prepareStatement(BUSCAR_POR_NOMBRE)) {
            
            stmt.setString(1, "%" + nombre + "%");
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                lista.add(new Especialidad(rs.getInt("idEspecialidad"), rs.getString("nombre"), rs.getString("descripcion")));
            }
        } catch (SQLException ex) {
            System.out.println("Error buscando especialidad: " + ex.getMessage());
        }
        return lista;
    }
    
    @Override
    public int contarEspecialidades() {
        int total = 0;
        try (Connection conn = DBconnection.obtenerConexion();
                PreparedStatement stmtContador = conn.prepareStatement(CONTAR_ESPECILIDADES);
                ResultSet rs = stmtContador.executeQuery()) {
            
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

}
