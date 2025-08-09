/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.ConsultaMedica;
import Model.Receta;
import java.util.List;

/**
 *
 * @author wendellgonzalez
 */
public interface ConsultaDAO {
    boolean registrarConsulta(ConsultaMedica consulta);
    ConsultaMedica obtenerConsultaPorCita(int idCita);
    List<ConsultaMedica> obtenerConsultasPorPaciente(int idPaciente);
    List<Receta> obtenerRecetasPorConsulta(int idConsulta);
}
