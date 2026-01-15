package es.artyhub.banco_back.persistence.dao.jpa;

import java.util.List;

import es.artyhub.banco_back.persistence.dao.jpa.entity.CuentaJpaEntity;

public interface CuentaJpaDao extends GenericJpaDao<CuentaJpaEntity>{
    CuentaJpaEntity findByIban(String iban);
    List<CuentaJpaEntity> findByClienteId(Long cliente_id);
    List<CuentaJpaEntity> findByToken(String token);
    CuentaJpaEntity save(CuentaJpaEntity cuentaJpaEntity);
}
