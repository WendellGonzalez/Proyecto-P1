/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DAOImpl.UsuarioDAOimpl;
import Model.*;
/**
 *
 * @author wendellgonzalez
 */
public class UsuarioController {
    
    // Este es el controlador de usuario para conectar el modelo con la vista.
    private final UsuarioDAOimpl usuarioDAO;
    
    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAOimpl();
    }
    
    public Usuario login(String email, String password) {
        return usuarioDAO.autenticar(email, password);
    }
}
