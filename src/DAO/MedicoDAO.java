/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Model.Medico;
import java.util.List;

/**
 *
 * @author wendellgonzalez
 */
public interface MedicoDAO {
    boolean registrar(Medico medico);
    
    boolean actualizar(Medico medico);
    
    boolean eliminar(int idMedico);
    
    List<Medico> listar();
    
    List<Medico> obtenerMedicosPorEspecialidad(int idEspecialidad);
    
    List<Medico> obtenerMedicosEnEspera();
    
    boolean actualizarEstadoSolicitud(int idMedico, String nuevoEstado);
    
    List<Medico> obtenerMedicosRechazados();
    
}
