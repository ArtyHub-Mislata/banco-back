package es.artyhub.banco_back.persistence.dao.jpa;

import java.util.List;

import es.artyhub.banco_back.persistence.dao.jpa.entity.CuentaJpaEntity;

public interface CuentaJpaDao extends GenericJpaDao<CuentaJpaEntity>{
    CuentaJpaEntity findByIban(String iban);
    List<CuentaJpaEntity> findByClienteId(Long cliente_id);
    CuentaJpaEntity findByNumeroTarjeta(String numeroTarjeta);
    CuentaJpaEntity save(CuentaJpaEntity cuentaJpaEntity);
}
