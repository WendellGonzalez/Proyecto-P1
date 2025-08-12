/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Arrays;

/**
 *
 * @author wendellgonzalez
 */
public class UsuarioGeneral extends Usuario {

    // Constructor para UsuarioDAOImpl
    public UsuarioGeneral(int idUsuario, String nombre, String direccion, String email, String telefono, int edad) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.edad = edad;
    }

    //Constructor vacio
    public UsuarioGeneral() {

    }

    //Constructor para gestionUsuarios.
    public UsuarioGeneral(int idUsuario, String nombre, String direccion, String email, String telefono) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
    }

    public UsuarioGeneral(String Nombre, String telefono, String email, String direccion, String password, int idUsuario) {
        this.idUsuario = idUsuario;
        this.nombre = Nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.password = password;
    }
}
