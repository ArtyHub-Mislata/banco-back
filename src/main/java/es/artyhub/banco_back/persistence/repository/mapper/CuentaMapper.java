package es.artyhub.banco_back.persistence.repository.mapper;

import java.util.List;

import es.artyhub.banco_back.domain.model.Cuenta;
import es.artyhub.banco_back.persistence.dao.jpa.entity.CuentaJpaEntity;

public class CuentaMapper {
    private static CuentaMapper instance;

    public CuentaMapper() {
    }

    public static CuentaMapper getInstance() {
        if (instance == null) {
            instance = new CuentaMapper();
        }
        return instance;
    }

    public Cuenta fromCuentaJpaEntityToCuenta(CuentaJpaEntity cuentaJpaEntity) {
        if (cuentaJpaEntity == null) {
            return null;
        }
        return new Cuenta(
            cuentaJpaEntity.getId(), 
            cuentaJpaEntity.getSaldo(), 
            cuentaJpaEntity.getIban(),
            ClienteMapper.getInstance().fromClienteJpaEntityToCliente(cuentaJpaEntity.getCliente()),
            TarjetaCreditoMapper.getInstance().fromTarjetaCreditoJpaEntityListToTarjetaCreditoList(cuentaJpaEntity.getTarjetas()),
            MovimientoBancarioMapper.getInstance().fromMovimientoBancarioJpaEntityListToMovimientoBancarioList(cuentaJpaEntity.getMovimientos())
        );
    }

    public CuentaJpaEntity fromCuentaToCuentaJpaEntity(Cuenta cuenta) {
        if (cuenta == null) {
            return null;
        }
        return new CuentaJpaEntity(
            cuenta.getId(),
            cuenta.getSaldo(),
            cuenta.getIban(),
            ClienteMapper.getInstance().fromClienteToClienteJpaEntity(cuenta.getCliente()),
            TarjetaCreditoMapper.getInstance().fromTarjetaCreditoListToTarjetaCreditoJpaEntityList(cuenta.getTarjetas()),
            MovimientoBancarioMapper.getInstance().fromMovimientoBancarioListToMovimientoBancarioJpaEntityList(cuenta.getMovimientos())
        );
    }

    public List<Cuenta> fromCuentaJpaEntityListToCuentaList(List<CuentaJpaEntity> cuentaJpaEntityList) {
        if (cuentaJpaEntityList == null) {
            return null;
        }
        return cuentaJpaEntityList.stream()
                .map(this::fromCuentaJpaEntityToCuenta)
                .toList();
    }

    public List<CuentaJpaEntity> fromCuentaListToCuentaJpaEntityList(List<Cuenta> cuentaList) {
        if (cuentaList == null) {
            return null;
        }
        return cuentaList.stream()
                .map(this::fromCuentaToCuentaJpaEntity)
                .toList();
    }
}
