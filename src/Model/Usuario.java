package Model;
import java.time.LocalDate;
/**
 *
 * @author wendellgonzalez
 */
public abstract class Usuario {
    
    // Atributos de usuarios en general
    
    protected int idUsuario;
    protected String nombre;
    protected String email;
    protected String password;
    protected TipoUsuario tipoUsuario;
    protected LocalDate fechaNacimiento;
    protected String telefono;
    protected String direccion;
    protected int cedula;
    protected sexo sexo;
    protected int edad;
    
    
    //Constructor vacio
    
    public Usuario() {
        
    }

    // Constructor para Usuarios
    
    public Usuario(int idUsuario, String nombre, String email, String password, 
            TipoUsuario tipoUsuario, LocalDate fechaNacimiento, String telefono, String direccion, int cedula, sexo sexo, int edad) {
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
        this.edad = edad;
    }
    
    //Constructor para gestion de usuarios
    public Usuario(int idUsuario, String nombre, String direccion, String email, String telefono, int edad) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.edad = edad;
    }
    
    public Usuario(int idUsuario, String nombre, String direccion, String email, String telefono) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
    }
    
    // Metodo para editar datos personales
    public void editarDatosPersonales(String nombre, String apellido, String telefono, String direccion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        System.out.println("Datos personales actualizados correctamente.");
    }
    
    public enum TipoUsuario { 
        ELEGIR, PACIENTE, MEDICO, ADMINISTRADOR;

        public static TipoUsuario fromStringSafe(String tipoTexto) {
              for(TipoUsuario tipo : TipoUsuario.values()) {
                  if(tipo.name().equalsIgnoreCase(tipoTexto.trim())) {
                      return tipo;
                  }
              }
              return null;
        }
    }
    
    public enum sexo {
        ELEGIR, MASCULINO, FEMENINO
    }

    // Getters y setters correspondientes

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public int getCedula() {
        return cedula;
    }
    
    public void setCedula(int cedula) {
        this.cedula = cedula;
    }
    
    public sexo getSexo() {
        return sexo;
    }
    
    public void setSexo(sexo sexo) {
        this.sexo = sexo;
    }
    
    public int getEdad() {
        return edad;
    }
    
    public void setEdad(int edad) {
        this.edad = edad;
    }
}

