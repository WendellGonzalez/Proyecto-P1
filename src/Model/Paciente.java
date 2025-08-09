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
public class Paciente extends Usuario{
    
   
    private HistorialMedico historial;
    private String seguroMedico;
    private String numeroSeguro;
    private String contactoEmergencia;
    private String relacionContacto;
    private String telefonoContacto;
    private int idMedico;

    // Constructor vacio
    public Paciente() {
    }
    
    //Constructor para gestion de pacientes
    public Paciente(int idUsuario, String nombre, String direccion, String email, String telefono, int edad) {
        super(idUsuario, nombre, direccion, email, telefono, edad);
    }
    

    // Constructor completo
    public Paciente(HistorialMedico historial, String seguroMedico, String numeroSeguro, 
            String contactoEmergencia, String relacionContacto, String telefonoContacto, int idUsuario, String nombre, String email, String password, 
            TipoUsuario tipoUsuario, LocalDate fechaNacimiento, String telefono, String direccion, int cedula, sexo sexo, int idMedico, int idPaciente, int edad) {
        
        super(idUsuario, nombre, email, password, tipoUsuario, fechaNacimiento, telefono, direccion, cedula, sexo, edad);
        
        this.historial = historial;
        this.seguroMedico = seguroMedico;
        this.numeroSeguro = numeroSeguro;
        this.contactoEmergencia = contactoEmergencia;
        this.relacionContacto = relacionContacto;
        this.telefonoContacto = telefonoContacto;
        this.idMedico = idMedico;
        this.idUsuario = idPaciente;
    }

    //Constructor para GestionPacientes
    public Paciente(int idUsuario, String nombre, String direccion, String email, String telefono) {
        super(idUsuario, nombre, direccion, email, telefono);
    }

    public int getidPaciente() {
        return idUsuario;
    }
    
    public void setidPaciente(int idPaciente) {
        this.idUsuario = idPaciente;
    }
    public int getidMedico() {
        return idMedico;
    }
    
    public void setidMedico(int idMedico) {
        this.idMedico = idMedico;
    }
    
    public String getSeguroMedico() {
        return seguroMedico;
    }

    public void setSeguroMedico(String seguroMedico) {
        this.seguroMedico = seguroMedico;
    }

    public String getNumeroSeguro() {
        return numeroSeguro;
    }

    public void setNumeroSeguro(String numeroSeguro) {
        this.numeroSeguro = numeroSeguro;
    }

    public String getContactoEmergencia() {
        return contactoEmergencia;
    }

    public void setContactoEmergencia(String contactoEmergencia) {
        this.contactoEmergencia = contactoEmergencia;
    }

    public String getRelacionContacto() {
        return relacionContacto;
    }

    public void setRelacionContacto(String relacionContacto) {
        this.relacionContacto = relacionContacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }
    
    public HistorialMedico getHistorial() {
        return historial;
    }

    public void setHistorial(HistorialMedico historial) {
        this.historial = historial;
    }

    public void setTipoUsuario() {
        this.tipoUsuario = tipoUsuario.PACIENTE;
    } 
}
