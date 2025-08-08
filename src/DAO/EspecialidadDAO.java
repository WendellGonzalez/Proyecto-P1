/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Model.Especialidad;
import java.util.List;

/**
 *
 * @author wendellgonzalez
 */
public interface EspecialidadDAO {
    List<Especialidad> obtenerTodas();
    void insertar(String especialidad, String descripcion);
    
    boolean actualizar(Especialidad especialidad);
    boolean eliminar(int idEspecialidad);
    int contarEspecialidades();

    public List<Especialidad> buscarPorNombre(String nombre);
}
