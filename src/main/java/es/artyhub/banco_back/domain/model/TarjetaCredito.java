package es.artyhub.banco_back.domain.model;

import java.util.Date;

public class TarjetaCredito {
    private Long id;
    private String numeroTarjeta;
    private Date fechaCaducidad;
    private String cvv;
    private String nombreCompleto;


    public TarjetaCredito() {
    }

    public TarjetaCredito(Long id, String numeroTarjeta, Date fechaCaducidad, String cvv, String nombreCompleto, Cuenta cuenta) {
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

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
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
