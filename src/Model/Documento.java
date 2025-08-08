/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author wendellgonzalez
 */
public class Documento {
    
    /**
     * Atributos
     * @param tipo
     * @param date
     * @param archivo 
     */
    
    private String tipo;
    private LocalDate Date;
    private String archivo;
    
    /**
     * Constructor
     * @param tipo
     * @param date
     * @param archivo 
     */
    
    public Documento(String tipo, LocalDate date, String archivo) {
        this.tipo = tipo;
        this.Date = date;
        this.archivo = archivo;
    }
    
    /**
     * Getters y setters
     * @return 
     */
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public LocalDate getDate() {
        return Date;
    }
    
    public void setDate(LocalDate date) {
        this.Date = date;
    }
    
    public String getArchivo() {
        return archivo;
    }
    
    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }
}
