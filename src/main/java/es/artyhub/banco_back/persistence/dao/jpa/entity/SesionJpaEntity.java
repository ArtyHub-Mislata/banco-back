package es.artyhub.banco_back.persistence.dao.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "sesions")
public class SesionJpaEntity implements Serializable{
    @Id
    private String token;
    private Long userId;
    private Date dateCreate;

    public SesionJpaEntity() {
    }

    public SesionJpaEntity(String token, Long userId, Date dateCreate) {
        this.token = token;
        this.userId = userId;
        this.dateCreate = dateCreate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SesionJpaEntity that = (SesionJpaEntity) o;
        return Objects.equals(token, that.token) && Objects.equals(userId, that.userId) && Objects.equals(dateCreate, that.dateCreate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, userId, dateCreate);
    }
}

