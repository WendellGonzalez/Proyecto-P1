/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

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

}
