package es.artyhub.banco_back.domain.dto;

import java.math.BigDecimal;
import java.util.Date;

import es.artyhub.banco_back.domain.enums.OrigenMovimiento;
import es.artyhub.banco_back.domain.enums.TipoMovimiento;

public class MovimientoBancarioDto {
    private Long id;
    private TipoMovimiento tipoMovimiento;
    private OrigenMovimiento origenMovimiento;
    private TarjetaCreditoDto tarjetaCredito;
    private Date fecha;
    private BigDecimal importe;
    private String concepto;

    public MovimientoBancarioDto() {
    }

    public MovimientoBancarioDto(Long id, TipoMovimiento tipoMovimiento, OrigenMovimiento origenMovimiento,
            TarjetaCreditoDto tarjetaCredito, Date fecha, BigDecimal importe, String concepto) {
        this.id = id;
        this.tipoMovimiento = tipoMovimiento;
        this.origenMovimiento = origenMovimiento;
        this.tarjetaCredito = tarjetaCredito;
        this.fecha = fecha;
        this.importe = importe;
        this.concepto = concepto;
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

    public TarjetaCreditoDto getTarjetaCredito() {
        return tarjetaCredito;
    }

    public void setTarjetaCredito(TarjetaCreditoDto tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
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
}
