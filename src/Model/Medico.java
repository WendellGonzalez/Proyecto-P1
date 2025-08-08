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
public class Medico extends Usuario{
    private String licencia;
    private List<Cita> citas;
    private Especialidad especialidad;
    private String numeroColegiatura;
    private String Universidad;
    private LocalDate fechaGraduacion;
    private String aniosExperiencia;
    private int idEspecialidad;
    private String estadoSolicitud;

    public Medico(Especialidad especialidad, String licencia, List<Cita> citas, String numeroColegiatura, String Universidad, 
            LocalDate fechaGraduacion, String aniosExperiencia, int idUsuario, String nombre, String email, String password, 
            TipoUsuario tipoUsuario, LocalDate fechaNacimiento, String telefono, String direccion, int cedula, sexo sexo, 
            int idEspecialidad, String estadoSolicitud, int edad) {
        super(idUsuario, nombre, email, password, tipoUsuario, fechaNacimiento, telefono, direccion, cedula, sexo, edad);
        this.especialidad = especialidad;
        this.licencia = licencia;
        this.citas = citas;
        this.numeroColegiatura = numeroColegiatura;
        this.Universidad = Universidad;
        this.fechaGraduacion = fechaGraduacion;
        this.aniosExperiencia = aniosExperiencia;
        this.idEspecialidad = idEspecialidad;
        this.estadoSolicitud = "ESPERA";
    }
    
    // Constructor para MedicoDAOImpl
    public Medico(int idUsuario, String nombre, String direccion, String email, String telefono, int edad) {
        super(idUsuario, nombre, direccion, email, telefono, edad);
    }
    
    public Medico() {
        
    }

    //Constructor para GestionMedicos
    public Medico(int idUsuario, String nombre, String direccion, String email, String telefono) {
        super(idUsuario, nombre, direccion, email, telefono);
    }
    // Getters y Setters

    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }
    
    public void setEstadoSolicitud(String estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }
    
    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }    

    public int getIdMedico() {
        return idUsuario;
    }
    
    public void setIdMedico(int idMedico) {
        this.idUsuario = idMedico;
    }
    
    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public String getNumeroColegiatura() {
        return numeroColegiatura;
    }

    public void setNumeroColegiatura(String numeroColegiatura) {
        this.numeroColegiatura = numeroColegiatura;
    }

    public String getUniversidad() {
        return Universidad;
    }

    public void setUniversidad(String Universidad) {
        this.Universidad = Universidad;
    }

    public LocalDate getFechaGraduacion() {
        return fechaGraduacion;
    }

    public void setFechaGraduacion(LocalDate fechaGraduacion) {
        this.fechaGraduacion = fechaGraduacion;
    }

    public String getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(String aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }
    
    
}
