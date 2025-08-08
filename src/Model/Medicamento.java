/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author wendellgonzalez
 */
public class Medicamento {
    /**
     * Atributos
     * @param nombre
     * @param dosis
     * @param duracion 
     */
    private String nombre;
    private String dosis;
    private String duracion;
    
    /**
     * Constructor
     */
    
    public Medicamento() {
        
    }
    
    public Medicamento(String nombre, String dosis, String duracion) {
        this.nombre = nombre;
        this.dosis = dosis;
        this.duracion = duracion;
    }
    
    /**
     * Getters y setters
     * @return 
     */
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
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
}
