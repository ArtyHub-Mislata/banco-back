package es.artyhub.banco_back.persistence.repository.impl;

import es.artyhub.banco_back.domain.model.MovimientoBancario;
import es.artyhub.banco_back.domain.repository.MovimientoBancarioRepository;
import es.artyhub.banco_back.persistence.dao.jpa.MovimientoBancarioJpaDao;
import es.artyhub.banco_back.persistence.repository.mapper.MovimientoBancarioMapper;

import java.math.BigDecimal;
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
    public MovimientoBancario findByImporte(BigDecimal importe) {
        return MovimientoBancarioMapper.getInstance().fromMovimientoBancarioJpaEntityToMovimientoBancario(movimientoBancarioJpaDao.findByImporte(importe));
    }

    @Override
    public MovimientoBancario findByConcepto(String concepto) {
        return MovimientoBancarioMapper.getInstance().fromMovimientoBancarioJpaEntityToMovimientoBancario(movimientoBancarioJpaDao.findByConcepto(concepto));
    }

    @Override
    public List<MovimientoBancario> findByCuentaId(Long cuenta_id) {
        return movimientoBancarioJpaDao.findByCuentaId(cuenta_id)
                .stream()
                .map(MovimientoBancarioMapper.getInstance():: fromMovimientoBancarioJpaEntityToMovimientoBancario)
                .toList();
    }

    @Override
    public List<MovimientoBancario> findAll() {
        return movimientoBancarioJpaDao.findAll()
                .stream()
                .map(MovimientoBancarioMapper.getInstance():: fromMovimientoBancarioJpaEntityToMovimientoBancario)
                .toList();
    }

    @Override
    public MovimientoBancario save(MovimientoBancario movimientoBancario, Long cuentaId) {
        if (movimientoBancario.getId() == null) {
            return MovimientoBancarioMapper
                    .getInstance()
                    .fromMovimientoBancarioJpaEntityToMovimientoBancario(
                            movimientoBancarioJpaDao.insert(MovimientoBancarioMapper.getInstance().fromMovimientoBancarioToMovimientoBancarioJpaEntity(movimientoBancario), cuentaId));
        }
        return MovimientoBancarioMapper.getInstance().fromMovimientoBancarioJpaEntityToMovimientoBancario(movimientoBancarioJpaDao.update(MovimientoBancarioMapper.getInstance().fromMovimientoBancarioToMovimientoBancarioJpaEntity(movimientoBancario)));
    }

    @Override
    public List<MovimientoBancario> findAllOfTarjeta(Long tarjetaId) {
        return movimientoBancarioJpaDao.findByTarjetaId(tarjetaId)
                .stream()
                .map(MovimientoBancarioMapper.getInstance():: fromMovimientoBancarioJpaEntityToMovimientoBancario)
                .toList();
    }
}
