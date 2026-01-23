package es.artyhub.banco_back.persistence.dao.jpa.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "sesions")
public class SesionJpaEntity implements Serializable{
    @Id
    private String token;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private ClienteJpaEntity cliente;

    @Column(name = "date_create")
    private Date dateCreate;

    public SesionJpaEntity() {
    }

    public SesionJpaEntity(String token, ClienteJpaEntity cliente, Date dateCreate) {
        this.token = token;
        this.cliente = cliente;
        this.dateCreate = dateCreate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public ClienteJpaEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteJpaEntity cliente) {
        this.cliente = cliente;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }


}
