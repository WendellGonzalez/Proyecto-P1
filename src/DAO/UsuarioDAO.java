/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;
import Model.Usuario;
import java.util.List;
import java.sql.*;
/**
 *
 * @author wendellgonzalez
 */
public interface UsuarioDAO{
    boolean registrar(Usuario usuario);
    
    public Usuario autenticar(String email, String password);
    
    List<Usuario> listarUsuario(String filtro);
    
    boolean actualizar(Usuario u);
    
    boolean eliminar(int id);
    
    public int contarUsuarios();
    
    public int contarMedicos();
    
    public int contarPacientes();
    
    public int contarPacientesPorMedico(int idMedico);
    
    public boolean emailExiste(Connection conn, String email) throws SQLException;
    
    boolean actualizarDatosPersonales(Usuario usuario);
    
}
