package es.artyhub.banco_back.persistence.repository.impl;

import es.artyhub.banco_back.domain.model.MovimientoBancario;
import es.artyhub.banco_back.domain.repository.MovimientoBancarioRepository;
import es.artyhub.banco_back.persistence.dao.jpa.MovimientoBancarioJpaDao;

public class MovimientoBancarioRepositoryImpl implements MovimientoBancarioRepository {
    private final MovimientoBancarioJpaDao movimientoBancarioJpaDao;

    public MovimientoBancarioRepositoryImpl(MovimientoBancarioJpaDao movimientoBancarioJpaDao) {
        this.movimientoBancarioJpaDao = movimientoBancarioJpaDao;
    }

    @Override
    public MovimientoBancario findById(Long id) {
        return null;
    }

    @Override
    public MovimientoBancario findByImporte(Long importe) {
        return null;
    }

    @Override
    public MovimientoBancario findByConcepto(String concepto) {
        return null;
    }

    @Override
    public MovimientoBancario findByCuentaId(Long cuenta_id) {
        return null;
    }
}
