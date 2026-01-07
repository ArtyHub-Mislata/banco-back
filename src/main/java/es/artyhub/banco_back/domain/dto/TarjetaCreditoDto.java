package es.artyhub.banco_back.domain.dto;

import java.math.BigInteger;
import java.util.Date;

public class TarjetaCreditoDto {
    private Long id;
    private BigInteger numeroTarjeta;
    private Date fechaCaducidad;
    private String cvv;
    private String nombreCompleto;

    public TarjetaCreditoDto() {
    }

    public TarjetaCreditoDto(Long id, BigInteger numeroTarjeta, Date fechaCaducidad, String cvv, String nombreCompleto) {
        this.id = id;
        this.numeroTarjeta = numeroTarjeta;
        this.fechaCaducidad = fechaCaducidad;
        this.cvv = cvv;
        this.nombreCompleto = nombreCompleto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(BigInteger numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}
