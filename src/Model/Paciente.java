/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.time.LocalDate;
import java.util.List;
/**
 *
 * @author wendellgonzalez
 */
public class Paciente extends Usuario{
    
   
    private HistorialMedico historial;
    private List<Documento> documentos;
    private List<EvaluacionDoctor> evaluaciones;
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
    

    // Constructor con parametros

    public Paciente(HistorialMedico historial, List<Documento> documentos, List<EvaluacionDoctor> evaluaciones, String seguroMedico, String numeroSeguro, 
            String contactoEmergencia, String relacionContacto, String telefonoContacto, int idUsuario, String nombre, String email, String password, 
            TipoUsuario tipoUsuario, LocalDate fechaNacimiento, String telefono, String direccion, int cedula, sexo sexo, int idMedico, int idPaciente, int edad) {
        
        super(idUsuario, nombre, email, password, tipoUsuario, fechaNacimiento, telefono, direccion, cedula, sexo, edad);
        
        this.historial = historial;
        this.documentos = documentos;
        this.evaluaciones = evaluaciones;
        this.seguroMedico = seguroMedico;
        this.numeroSeguro = numeroSeguro;
        this.contactoEmergencia = contactoEmergencia;
        this.relacionContacto = relacionContacto;
        this.telefonoContacto = telefonoContacto;
        this.idMedico = idMedico;
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
    
//    
//    public void solicitarCita(Cita cita) {
//        // Logica para solicitar cita
//        System.out.println("Cita solicitada para: " + cita.getfechaHora() + ", Motivo: " + cita.getMotivo());
//    }
//    
//    public void enviarMensaje(String mensaje) {
//        // Logica para enviar mensaje
//    }
    
    // Getters y setters
     
    public HistorialMedico getHistorial() {
        return historial;
    }

    public void setHistorial(HistorialMedico historial) {
        this.historial = historial;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public List<EvaluacionDoctor> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(List<EvaluacionDoctor> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    @Override
    public int getIdUsuario() {
        return idUsuario;
    }
    @Override
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    @Override
    public String getNombre() {
        return nombre;
    }
    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String getEmail() {
        return email;
    }
    @Override
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario() {
        this.tipoUsuario = tipoUsuario.PACIENTE;
    }
    @Override
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    @Override
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    @Override
    public String getTelefono() {
        return telefono;
    }
    @Override
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    @Override
    public String getDireccion() {
        return direccion;
    }
    @Override
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
}
