package es.artyhub.banco_back.persistence.dao.jpa.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name= "tarjetas_credito")
public class TarjetaCreditoJpaEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "numero_tarjeta")
    private BigInteger numeroTarjeta;
    @Column(name = "fecha_caducidad")
    private Date fechaCaducidad;
    private String cvv;
    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_id", nullable = false)
    private CuentaJpaEntity cuenta;
    public TarjetaCreditoJpaEntity() {
    }

    public TarjetaCreditoJpaEntity(Long id, BigInteger numeroTarjeta, Date fechaCaducidad, String cvv,
            String nombreCompleto, CuentaJpaEntity cuenta) {
        this.id = id;
        this.numeroTarjeta = numeroTarjeta;
        this.fechaCaducidad = fechaCaducidad;
        this.cvv = cvv;
        this.nombreCompleto = nombreCompleto;
        this.cuenta = cuenta;
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

    public CuentaJpaEntity getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaJpaEntity cuenta) {
        this.cuenta = cuenta;
    }

}
