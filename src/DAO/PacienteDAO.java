/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Model.Paciente;
import java.util.List;

/**
 *
 * @author wendellgonzalez
 */
public interface PacienteDAO {
    boolean registrar(Paciente paciente);
   
    boolean actualizar(Paciente paciente);
    
    boolean eliminar(int idPaciente);
    
    List<Paciente> listar();
    
    List<Paciente> listarPorMedico(int idMedico);
    
    public int obtenderIdPacientePorIdUsuario(int idUsuario);
    
}
