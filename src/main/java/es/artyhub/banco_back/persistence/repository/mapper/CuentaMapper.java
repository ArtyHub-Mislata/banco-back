package es.artyhub.banco_back.persistence.repository.mapper;

import java.util.ArrayList;
import java.util.List;

import es.artyhub.banco_back.domain.model.Cuenta;
import es.artyhub.banco_back.domain.model.MovimientoBancario;
import es.artyhub.banco_back.persistence.dao.jpa.entity.CuentaJpaEntity;
import es.artyhub.banco_back.persistence.dao.jpa.entity.MovimientoBancarioJpaEntity;
import es.artyhub.banco_back.persistence.dao.jpa.entity.TarjetaCreditoJpaEntity;

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
        if (cuentaJpaEntity.getId() == null) {
            return null;
        }

        return new Cuenta(
            cuentaJpaEntity.getId(), 
            cuentaJpaEntity.getSaldo(), 
            cuentaJpaEntity.getIban(),
            ClienteMapper.getInstance().fromClienteJpaEntityToCliente(cuentaJpaEntity.getCliente()),
            cuentaJpaEntity.getTarjetas().stream().map(TarjetaCreditoMapper.getInstance()::fromTarjetaCreditoJpaEntityToTarjetaCredito).toList(),
            cuentaJpaEntity.getMovimientos().stream().map(MovimientoBancarioMapper.getInstance():: fromMovimientoBancarioJpaEntityToMovimientoBancario).toList()
        );
    }

    public CuentaJpaEntity fromCuentaToCuentaJpaEntity(Cuenta cuenta) {
        if (cuenta == null) {
            return null;
        }
        List<MovimientoBancarioJpaEntity> movimientoBancarios =  cuenta.getMovimientos().stream()
                .map(MovimientoBancarioMapper.getInstance():: fromMovimientoBancarioToMovimientoBancarioJpaEntity)
                .toList();
        List<TarjetaCreditoJpaEntity>  tarjetaCreditoJpaEntities = cuenta.getTarjetas().stream()
                .map(TarjetaCreditoMapper.getInstance()::fromTarjetaCreditoToTarjetaCreditoJpaEntity)
                .toList();
        CuentaJpaEntity cuentaJpaEntity = new CuentaJpaEntity(
            cuenta.getId(),
            cuenta.getSaldo(),
            cuenta.getIban(),
            ClienteMapper.getInstance().fromClienteToClienteJpaEntity(cuenta.getCliente()),
                new ArrayList<>(), // mutable
                new ArrayList<>()
        );
        cuentaJpaEntity.setMovimientos(setCuentaAMovimientos(cuentaJpaEntity,movimientoBancarios));
        cuentaJpaEntity.setTarjetas(setCuentaATarjetas(cuentaJpaEntity, tarjetaCreditoJpaEntities));

        return cuentaJpaEntity;
    }


    private List<MovimientoBancarioJpaEntity> setCuentaAMovimientos(CuentaJpaEntity cuenta, List<MovimientoBancarioJpaEntity> movimientos){
        if(movimientos == null){
            return new ArrayList<>();
        }
        for (MovimientoBancarioJpaEntity movimiento : movimientos) {
            movimiento.setCuenta(cuenta);
        }
        return movimientos;
    }


    private List<TarjetaCreditoJpaEntity> setCuentaATarjetas(CuentaJpaEntity cuenta, List<TarjetaCreditoJpaEntity> tarjetas){
        if(tarjetas == null){
            return new ArrayList<>();
        }
        for (TarjetaCreditoJpaEntity tarjeta: tarjetas) {
            tarjeta.setCuenta(cuenta);
        }
        return tarjetas;
    }

}
