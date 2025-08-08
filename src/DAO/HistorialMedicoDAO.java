/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Model.HistorialMedico;
import java.util.List;

/**
 *
 * @author wendellgonzalez
 */
public interface HistorialMedicoDAO {
    boolean registrar(HistorialMedico historial);
    boolean actualizar(HistorialMedico historial);
    boolean eliminar(int idHistorial);
    HistorialMedico buscarPorPaciente(int idPaciente);
    List<HistorialMedico> listarPorPaciente(int idPaciente);
}
