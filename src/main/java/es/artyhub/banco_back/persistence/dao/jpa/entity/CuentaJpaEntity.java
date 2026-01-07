package es.artyhub.banco_back.persistence.dao.jpa.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name= "cuentas")
public class CuentaJpaEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal saldo;
    private String iban;
    
    public CuentaJpaEntity() {
    }

    public CuentaJpaEntity(Long id, BigDecimal saldo, String iban) {
        this.id = id;
        this.saldo = saldo;
        this.iban = iban;
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
}
