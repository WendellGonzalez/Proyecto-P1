/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Model.Receta;
import java.util.List;

/**
 *
 * @author wendellgonzalez
 */
public interface RecetaDAO {
    List<Receta> obtenerRecetasPorPaciente(int idPaciente);
    List<Receta> obtenerHistorialPorPaciente(int idPaciente);
}
