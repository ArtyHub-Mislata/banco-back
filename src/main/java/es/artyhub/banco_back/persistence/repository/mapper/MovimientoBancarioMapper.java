package es.artyhub.banco_back.persistence.repository.mapper;



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
            TarjetaCreditoMapper.getInstance().fromTarjetaCreditoToTarjetaCreditoJpaEntity(movimientoBancario.getTarjetaCredito()),
            movimientoBancario.getFecha(), 
            movimientoBancario.getImporte(), 
            movimientoBancario.getConcepto()
        );
    }

    public MovimientoBancario fromMovimientoBancarioJpaEntityToMovimientoBancario(MovimientoBancarioJpaEntity movimientoBancarioJpaEntity) {
        if (movimientoBancarioJpaEntity == null) {
            return null;
        }
        return new MovimientoBancario(
                movimientoBancarioJpaEntity.getId(),
                movimientoBancarioJpaEntity.getTipoMovimiento(),
                movimientoBancarioJpaEntity.getOrigenMovimiento(),
                TarjetaCreditoMapper.getInstance().fromTarjetaCreditoJpaEntityToTarjetaCredito(movimientoBancarioJpaEntity.getTarjetaCredito()),
                movimientoBancarioJpaEntity.getFecha(),
                movimientoBancarioJpaEntity.getImporte(),
                movimientoBancarioJpaEntity.getConcepto()
        );

    }
}
