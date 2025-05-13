package BusinessEntity;

import java.time.LocalDateTime; 

public class ClienteBE {
    private String cliente_id; 
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private LocalDateTime fecha_registro;

    public ClienteBE() {
    }

    public ClienteBE(String cliente_id, String nombre, String apellido, String email, String telefono, LocalDateTime fecha_registro) {
        this.cliente_id = cliente_id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.fecha_registro = fecha_registro;
    }

    // Getters y Setters
    public String getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(String cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDateTime getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(LocalDateTime fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
}