package es.artyhub.banco_back.persistence.repository.mapper;

import es.artyhub.banco_back.domain.dto.MovimientoBancarioDto;
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

    public MovimientoBancarioJpaEntity fromMovimientoBancarioDtoToMovimientoBancarioJpaEntity(MovimientoBancarioDto movimientoBancarioDto) {
        if (movimientoBancarioDto == null) {
            return null;
        }
        return new MovimientoBancarioJpaEntity(
            movimientoBancarioDto.getId(), 
            movimientoBancarioDto.getTipoMovimiento(),
            movimientoBancarioDto.getOrigenMovimiento(),
            TarjetaCreditoMapper.getInstance().fromTarjetaCreditoDtoToTarjetaCreditoJpaEntity(movimientoBancarioDto.getTarjetaCredito()),
            movimientoBancarioDto.getFecha(), 
            movimientoBancarioDto.getImporte(),
            movimientoBancarioDto.getConcepto());
    }

    public MovimientoBancarioDto fromMovimientoBancarioToMovimientoBancarioDto(MovimientoBancarioJpaEntity movimientoBancario) {
        if (movimientoBancario == null) {
            return null;
        }
        return new MovimientoBancarioDto(
            movimientoBancario.getId(), 
            movimientoBancario.getTipoMovimiento(), 
            movimientoBancario.getOrigenMovimiento(),
            TarjetaCreditoMapper.getInstance().fromTarjetaCreditoJpaEntityToTarjetaCreditoDto(movimientoBancario.getTarjetaCredito()),
            movimientoBancario.getFecha(), 
            movimientoBancario.getImporte(), 
            movimientoBancario.getConcepto());
    }
}
