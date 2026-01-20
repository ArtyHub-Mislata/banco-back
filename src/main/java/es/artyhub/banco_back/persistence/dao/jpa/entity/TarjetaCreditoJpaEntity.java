package es.artyhub.banco_back.persistence.dao.jpa.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name= "tarjetas_credito")
public class TarjetaCreditoJpaEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "numero_tarjeta")
    private String numeroTarjeta;
    @Column(name = "fecha_caducidad")
    private String fechaCaducidad;
    private String cvv;
    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @OneToMany(mappedBy = "tarjetaCredito", fetch = FetchType.LAZY)
    private List<MovimientoBancarioJpaEntity> movements = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "cuenta_id") // opcional pero recomendado
    private CuentaJpaEntity cuenta;

    public TarjetaCreditoJpaEntity() {
    }

    public TarjetaCreditoJpaEntity(Long id, String numeroTarjeta, String fechaCaducidad, String cvv,
            String nombreCompleto) {
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

    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
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

    public List<MovimientoBancarioJpaEntity> getMovements() {
        return movements;
    }

    public void setMovements(List<MovimientoBancarioJpaEntity> movements) {
        this.movements = movements;
    }
}
