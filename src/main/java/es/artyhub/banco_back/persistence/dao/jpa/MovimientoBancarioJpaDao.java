package es.artyhub.banco_back.persistence.dao.jpa;

import java.util.List;

import es.artyhub.banco_back.persistence.dao.jpa.entity.MovimientoBancarioJpaEntity;

public interface MovimientoBancarioJpaDao extends GenericJpaDao<MovimientoBancarioJpaEntity>{
    MovimientoBancarioJpaEntity findByImporte(Long importe);
    MovimientoBancarioJpaEntity findByConcepto(String concepto);
    List<MovimientoBancarioJpaEntity> findByCuentaId(Long cuenta_id);
    MovimientoBancarioJpaEntity insert(MovimientoBancarioJpaEntity movimientoBancarioJpaEntity);
    MovimientoBancarioJpaEntity update(MovimientoBancarioJpaEntity movimientoBancarioJpaEntity);
}
