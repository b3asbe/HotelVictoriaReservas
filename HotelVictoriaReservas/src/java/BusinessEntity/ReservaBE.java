package BusinessEntity;

import java.time.LocalDate;      
import java.time.LocalDateTime; 

public class ReservaBE {
    private int reserva_id;
    private String cliente_id;    
    private int habitacion_id;  
    private String usuario_id;     
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private LocalDateTime fecha_reserva;
    private String estado_reserva;
    private int numero_huespedes;


    private ClienteBE cliente;
    private HabitacionBE habitacion;
    private UsuarioBE usuario;


    public ReservaBE() {
    }

    public ReservaBE(int reserva_id, String cliente_id, int habitacion_id, String usuario_id,
                     LocalDate fecha_inicio, LocalDate fecha_fin, LocalDateTime fecha_reserva,
                     String estado_reserva, int numero_huespedes) {
        this.reserva_id = reserva_id;
        this.cliente_id = cliente_id;
        this.habitacion_id = habitacion_id;
        this.usuario_id = usuario_id;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.fecha_reserva = fecha_reserva;
        this.estado_reserva = estado_reserva;
        this.numero_huespedes = numero_huespedes;
    }

    // Getters y Setters
    public int getReserva_id() {
        return reserva_id;
    }

    public void setReserva_id(int reserva_id) {
        this.reserva_id = reserva_id;
    }

    public String getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(String cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getHabitacion_id() {
        return habitacion_id;
    }

    public void setHabitacion_id(int habitacion_id) {
        this.habitacion_id = habitacion_id;
    }

    public String getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(String usuario_id) {
        this.usuario_id = usuario_id;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public LocalDateTime getFecha_reserva() {
        return fecha_reserva;
    }

    public void setFecha_reserva(LocalDateTime fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }

    public String getEstado_reserva() {
        return estado_reserva;
    }

    public void setEstado_reserva(String estado_reserva) {
        this.estado_reserva = estado_reserva;
    }

    public int getNumero_huespedes() {
        return numero_huespedes;
    }

    public void setNumero_huespedes(int numero_huespedes) {
        this.numero_huespedes = numero_huespedes;
    }

    public ClienteBE getCliente() {
        return cliente;
    }

    public void setCliente(ClienteBE cliente) {
        this.cliente = cliente;
    }

    public HabitacionBE getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(HabitacionBE habitacion) {
        this.habitacion = habitacion;
    }

    public UsuarioBE getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioBE usuario) {
        this.usuario = usuario;
    }
}