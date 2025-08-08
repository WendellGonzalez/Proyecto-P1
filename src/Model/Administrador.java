/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;

/**
 *
 * @author wendellgonzalez
 */
public class Administrador extends Usuario{
    
    
    // Constructor vacio
    
    public Administrador() {
        
    }
    
    // Constructor con parametros y herencias
    public Administrador(int idUsuario, String nombre, String email, String password, TipoUsuario tipoUsuario, 
            LocalDate fechaNacimiento, String telefono, String direccion, int cedula, sexo sexo, int edad) {
        super(idUsuario, nombre, email, password, tipoUsuario, fechaNacimiento, telefono, direccion, cedula, sexo, edad);
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.direccion = direccion;
        this.cedula = cedula;
        this.sexo = sexo;
    }    
}
