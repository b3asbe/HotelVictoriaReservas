package BusinessEntity;

public class HabitacionBE {
    private int habitacion_id;
    private String numero;
    private int piso;
    private int tipo_id;
    private String estado;


    private TipoHabitacionBE tipoHabitacion;

    public HabitacionBE() {
    }

    public HabitacionBE(int habitacion_id, String numero, int piso, int tipo_id, String estado) {
        this.habitacion_id = habitacion_id;
        this.numero = numero;
        this.piso = piso;
        this.tipo_id = tipo_id;
        this.estado = estado;
    }

    // Getters y Setters
    public int getHabitacion_id() {
        return habitacion_id;
    }

    public void setHabitacion_id(int habitacion_id) {
        this.habitacion_id = habitacion_id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public int getTipo_id() {
        return tipo_id;
    }

    public void setTipo_id(int tipo_id) {
        this.tipo_id = tipo_id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public TipoHabitacionBE getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacionBE tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }
}