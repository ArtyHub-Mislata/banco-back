package es.artyhub.banco_back.persistence.dao.jpa.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name= "cuentas")
public class CuentaJpaEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal saldo;
    private String iban;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteJpaEntity cliente;
    @OneToMany(mappedBy = "cuenta")
    private List<TarjetaCreditoJpaEntity> tarjetas;
    @OneToMany(mappedBy = "cuenta")
    private List<MovimientoBancarioJpaEntity> movimientos;
    
    public CuentaJpaEntity() {
    }

    public CuentaJpaEntity(Long id, BigDecimal saldo, String iban, ClienteJpaEntity cliente, List<TarjetaCreditoJpaEntity> tarjetas, List<MovimientoBancarioJpaEntity> movimientos) {
        this.id = id;
        this.saldo = saldo;
        this.iban = iban;
        this.cliente = cliente;
        this.tarjetas = tarjetas;
        this.movimientos = movimientos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public ClienteJpaEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteJpaEntity cliente) {
        this.cliente = cliente;
    }

    public List<TarjetaCreditoJpaEntity> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<TarjetaCreditoJpaEntity> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public List<MovimientoBancarioJpaEntity> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoBancarioJpaEntity> movimientos) {
        this.movimientos = movimientos;
    }
}
