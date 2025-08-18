package Model;

import java.util.List;

public class HistorialMedico {
    private int idHistorial;
    private int idPaciente; 
    private String tipoSangre;
    private String alergias;
    private String enfermedadesCronicas;
    
    private String seguroMedico;
    private String numeroSeguro;
    private String contactoEmergencia;
    private String relacionContacto;
    private String telefonoContacto;
     private String duracion;
     private String dosis;
    
    // Constructores
    public HistorialMedico() {
    }

    public HistorialMedico(int idHistorial, int idPaciente, String tipoSangre, String alergias, String enfermedadesCronicas,
                           String seguroMedico, String numeroSeguro,
                           String contactoEmergencia, String relacionContacto, String telefonoContacto, String dosis, String duracion) {
        this.idHistorial = idHistorial;
        this.idPaciente = idPaciente;
        this.tipoSangre = tipoSangre;
        this.alergias = alergias;
        this.enfermedadesCronicas = enfermedadesCronicas;
        this.seguroMedico = seguroMedico;
        this.numeroSeguro = numeroSeguro;
        this.contactoEmergencia = contactoEmergencia;
        this.relacionContacto = relacionContacto;
        this.telefonoContacto = telefonoContacto;
        this.dosis = dosis;
        this.duracion = duracion;
    }
    
    

    // Getters y Setters
    
    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }
    
    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
    
    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getEnfermedadesCronicas() {
        return enfermedadesCronicas;
    }

    public void setEnfermedadesCronicas(String enfermedadesCronicas) {
        this.enfermedadesCronicas = enfermedadesCronicas;
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
}