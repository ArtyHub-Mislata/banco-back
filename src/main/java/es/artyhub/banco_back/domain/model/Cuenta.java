package es.artyhub.banco_back.domain.model;

import java.math.BigDecimal;

public class Cuenta {
    private Long id;
    private BigDecimal saldo;
    private String iban;

    public Cuenta() {
    }

    public Cuenta(Long id, BigDecimal saldo, String iban) {
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
