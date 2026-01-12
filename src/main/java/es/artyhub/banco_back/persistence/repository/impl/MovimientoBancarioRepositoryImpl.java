package es.artyhub.banco_back.persistence.repository.impl;

import es.artyhub.banco_back.domain.model.MovimientoBancario;
import es.artyhub.banco_back.domain.repository.MovimientoBancarioRepository;
import es.artyhub.banco_back.persistence.dao.jpa.MovimientoBancarioJpaDao;
import es.artyhub.banco_back.persistence.repository.mapper.MovimientoBancarioMapper;

import java.util.List;

public class MovimientoBancarioRepositoryImpl implements MovimientoBancarioRepository {

    private final MovimientoBancarioJpaDao movimientoBancarioJpaDao;

    public MovimientoBancarioRepositoryImpl(MovimientoBancarioJpaDao movimientoBancarioJpaDao) {
        this.movimientoBancarioJpaDao = movimientoBancarioJpaDao;
    }

    @Override
    public MovimientoBancario findById(Long id) {
        return MovimientoBancarioMapper.getInstance().fromMovimientoBancarioJpaEntityToMovimientoBancario(movimientoBancarioJpaDao.findById(id));
    }

    @Override
    public MovimientoBancario findByImporte(Long importe) {
        return MovimientoBancarioMapper.getInstance().fromMovimientoBancarioJpaEntityToMovimientoBancario(movimientoBancarioJpaDao.findByImporte(importe));
    }

    @Override
    public MovimientoBancario findByConcepto(String concepto) {
        return MovimientoBancarioMapper.getInstance().fromMovimientoBancarioJpaEntityToMovimientoBancario(movimientoBancarioJpaDao.findByConcepto(concepto));
    }

    @Override
    public List<MovimientoBancario> findByCuentaId(Long cuenta_id) {
        return MovimientoBancarioMapper.getInstance().fromMovimientoBancarioJpaEntityListToMovimientoBancarioList(movimientoBancarioJpaDao.findByCuentaId(cuenta_id));
    }

    @Override
    public List<MovimientoBancario> findAll() {
        return MovimientoBancarioMapper.getInstance().fromMovimientoBancarioJpaEntityListToMovimientoBancarioList(movimientoBancarioJpaDao.findAll());
    }

    @Override
    public MovimientoBancario save(MovimientoBancario movimientoBancario) {
        if (movimientoBancario.getId() == null) {
            return MovimientoBancarioMapper.getInstance().fromMovimientoBancarioJpaEntityToMovimientoBancario(movimientoBancarioJpaDao.insert(MovimientoBancarioMapper.getInstance().fromMovimientoBancarioToMovimientoBancarioJpaEntity(movimientoBancario)));
        }
        return MovimientoBancarioMapper.getInstance().fromMovimientoBancarioJpaEntityToMovimientoBancario(movimientoBancarioJpaDao.update(MovimientoBancarioMapper.getInstance().fromMovimientoBancarioToMovimientoBancarioJpaEntity(movimientoBancario)));
    }
}
