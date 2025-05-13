package BusinessEntity;

public class UsuarioBE {
    private String usuario_id; 
    private String usuario;
    private String clave_hash;
    private String rol;

    public UsuarioBE() {
    }

    public UsuarioBE(String usuario_id, String usuario, String clave_hash, String rol) {
        this.usuario_id = usuario_id;
        this.usuario = usuario;
        this.clave_hash = clave_hash;
        this.rol = rol;
    }

    // Getters y Setters
    public String getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(String usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave_hash() {
        return clave_hash;
    }

    public void setClave_hash(String clave_hash) {
        this.clave_hash = clave_hash;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}