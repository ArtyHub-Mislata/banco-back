package es.artyhub.banco_back.persistence.repository.mapper;


import java.util.List;

import es.artyhub.banco_back.domain.model.MovimientoBancario;
import es.artyhub.banco_back.persistence.dao.jpa.entity.MovimientoBancarioJpaEntity;

public class MovimientoBancarioMapper {
    private static MovimientoBancarioMapper instance;

    public MovimientoBancarioMapper() {
    }

    public static MovimientoBancarioMapper getInstance() {
        if (instance == null) {
            instance = new MovimientoBancarioMapper();
        }
        return instance;
    }

    public MovimientoBancarioJpaEntity fromMovimientoBancarioToMovimientoBancarioJpaEntity(MovimientoBancario movimientoBancario) {
        if (movimientoBancario == null) {
            return null;
        }
        return new MovimientoBancarioJpaEntity(
            movimientoBancario.getId(), 
            movimientoBancario.getTipoMovimiento(),
            movimientoBancario.getOrigenMovimiento(),
            movimientoBancario.getTarjetaCredito().getNumeroTarjeta(),
            movimientoBancario.getFecha(), 
            movimientoBancario.getImporte(), 
            movimientoBancario.getConcepto(),
            CuentaMapper.getInstance().fromCuentaToCuentaJpaEntity(movimientoBancario.getCuenta()));
    }

    public MovimientoBancario fromMovimientoBancarioJpaEntityToMovimientoBancario(MovimientoBancarioJpaEntity movimientoBancarioJpaEntity) {
        if (movimientoBancarioJpaEntity == null) {
            return null;
        }
        return new MovimientoBancario(
            movimientoBancarioJpaEntity.getId(), 
            movimientoBancarioJpaEntity.getTipoMovimiento(),
            movimientoBancarioJpaEntity.getOrigenMovimiento(),
            movimientoBancarioJpaEntity.getFecha(), 
            movimientoBancarioJpaEntity.getImporte(), 
            movimientoBancarioJpaEntity.getConcepto(),
            CuentaMapper.getInstance().fromCuentaJpaEntityToCuenta(movimientoBancarioJpaEntity.getCuenta()));
    }
    
    public List<MovimientoBancarioJpaEntity> fromMovimientoBancarioListToMovimientoBancarioJpaEntityList(List<MovimientoBancario> movimientosBancarios) {
        if (movimientosBancarios == null) {
            return null;
        }
        return movimientosBancarios.stream()
            .map(this::fromMovimientoBancarioToMovimientoBancarioJpaEntity)
            .toList();
    }

    public List<MovimientoBancario> fromMovimientoBancarioJpaEntityListToMovimientoBancarioList(List<MovimientoBancarioJpaEntity> movimientosBancariosJpaEntity) {
        if (movimientosBancariosJpaEntity == null) {
            return null;
        }
        return movimientosBancariosJpaEntity.stream()
            .map(this::fromMovimientoBancarioJpaEntityToMovimientoBancario)
            .toList();
    }
}
