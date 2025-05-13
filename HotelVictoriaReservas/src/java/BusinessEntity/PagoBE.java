package BusinessEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PagoBE {
    private int pago_id;
    private int reserva_id; 
    private LocalDateTime fecha_pago;
    private BigDecimal monto;
    private String metodo_pago;
    private String estado_pago;

    private ReservaBE reserva;

    public PagoBE() {
    }

    public PagoBE(int pago_id, int reserva_id, LocalDateTime fecha_pago, BigDecimal monto, String metodo_pago, String estado_pago) {
        this.pago_id = pago_id;
        this.reserva_id = reserva_id;
        this.fecha_pago = fecha_pago;
        this.monto = monto;
        this.metodo_pago = metodo_pago;
        this.estado_pago = estado_pago;
    }

    // Getters y Setters
    public int getPago_id() {
        return pago_id;
    }

    public void setPago_id(int pago_id) {
        this.pago_id = pago_id;
    }

    public int getReserva_id() {
        return reserva_id;
    }

    public void setReserva_id(int reserva_id) {
        this.reserva_id = reserva_id;
    }

    public LocalDateTime getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(LocalDateTime fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public String getEstado_pago() {
        return estado_pago;
    }

    public void setEstado_pago(String estado_pago) {
        this.estado_pago = estado_pago;
    }

    public ReservaBE getReserva() {
        return reserva;
    }

    public void setReserva(ReservaBE reserva) {
        this.reserva = reserva;
    }
}
