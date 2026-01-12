package es.artyhub.banco_back.persistence.dao.jpa;

import java.util.List;

import es.artyhub.banco_back.persistence.dao.jpa.entity.TarjetaCreditoJpaEntity;

public interface TarjetaCreditoJpaDao extends GenericJpaDao<TarjetaCreditoJpaEntity> {
    TarjetaCreditoJpaEntity findByNumeroTarjeta(String numero);
    List<TarjetaCreditoJpaEntity> findByCuentaId(Long cuenta_id);
    TarjetaCreditoJpaEntity save(TarjetaCreditoJpaEntity tarjetaCreditoJpaEntity);
}
