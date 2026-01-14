package es.artyhub.banco_back.persistence.dao.jpa.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import es.artyhub.banco_back.domain.enums.OrigenMovimiento;
import es.artyhub.banco_back.domain.enums.TipoMovimiento;
import jakarta.persistence.*;

@Entity
@Table(name= "movimientos_bancarios")
public class MovimientoBancarioJpaEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimiento")
    private TipoMovimiento tipoMovimiento;
    @Column(name = "origen_movimiento")
    private OrigenMovimiento origenMovimiento;
    @Column(name = "n_tarjeta")
    private String nTarjetaCredito;
    private Date fecha;
    private BigDecimal importe;
    private String concepto;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_id", nullable = false)
    private CuentaJpaEntity cuenta;

    public MovimientoBancarioJpaEntity() {
    }

    public MovimientoBancarioJpaEntity(Long id, TipoMovimiento tipoMovimiento, OrigenMovimiento origenMovimiento, String tarjetaCredito, Date fecha, BigDecimal importe, String concepto, CuentaJpaEntity cuenta) {
        this.id = id;
        this.tipoMovimiento = tipoMovimiento;
        this.origenMovimiento = origenMovimiento;
        this.nTarjetaCredito = tarjetaCredito;
        this.fecha = fecha;
        this.importe = importe;
        this.concepto = concepto;
        this.cuenta = cuenta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public OrigenMovimiento getOrigenMovimiento() {
        return origenMovimiento;
    }

    public void setOrigenMovimiento(OrigenMovimiento origenMovimiento) {
        this.origenMovimiento = origenMovimiento;
    }


    public CuentaJpaEntity getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaJpaEntity cuenta) {
        this.cuenta = cuenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getnTarjetaCredito() {
        return nTarjetaCredito;
    }

    public void setnTarjetaCredito(String nTarjetaCredito) {
        this.nTarjetaCredito = nTarjetaCredito;
    }
}
