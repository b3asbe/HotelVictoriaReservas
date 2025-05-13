package BusinessEntity;

import java.math.BigDecimal;

public class TipoHabitacionBE {
    private int tipo_id;
    private String nombre_tipo;
    private String descripcion;
    private BigDecimal precio_noche;

    public TipoHabitacionBE() {
    }

    public TipoHabitacionBE(int tipo_id, String nombre_tipo, String descripcion, BigDecimal precio_noche) {
        this.tipo_id = tipo_id;
        this.nombre_tipo = nombre_tipo;
        this.descripcion = descripcion;
        this.precio_noche = precio_noche;
    }

    // Getters y Setters
    public int getTipo_id() {
        return tipo_id;
    }

    public void setTipo_id(int tipo_id) {
        this.tipo_id = tipo_id;
    }

    public String getNombre_tipo() {
        return nombre_tipo;
    }

    public void setNombre_tipo(String nombre_tipo) {
        this.nombre_tipo = nombre_tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio_noche() {
        return precio_noche;
    }

    public void setPrecio_noche(BigDecimal precio_noche) {
        this.precio_noche = precio_noche;
    }
}