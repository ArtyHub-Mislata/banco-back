package es.artyhub.banco_back.domain.mapper;

import es.artyhub.banco_back.domain.dto.MovimientoBancarioDto;
import es.artyhub.banco_back.domain.model.MovimientoBancario;

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

    public MovimientoBancario fromMovimientoBancarioDtoToMovimientoBancario(MovimientoBancarioDto movimientoBancarioDto) {
        if (movimientoBancarioDto == null) {
            return null;
        }
        return new MovimientoBancario(
            movimientoBancarioDto.getId(), 
            movimientoBancarioDto.getTipoMovimiento(),
            movimientoBancarioDto.getOrigenMovimiento(),
            TarjetaCreditoMapper.getInstance().fromTarjetaCreditoDtoToTarjetaCredito(movimientoBancarioDto.getTarjetaCredito()),
            movimientoBancarioDto.getFecha(), 
            movimientoBancarioDto.getImporte(),
            movimientoBancarioDto.getConcepto());
    }

    public MovimientoBancarioDto fromMovimientoBancarioToMovimientoBancarioDto(MovimientoBancario movimientoBancario) {
        if (movimientoBancario == null) {
            return null;
        }
        return new MovimientoBancarioDto(
            movimientoBancario.getId(), 
            movimientoBancario.getTipoMovimiento(), 
            movimientoBancario.getOrigenMovimiento(),
            TarjetaCreditoMapper.getInstance().fromTarjetaCreditoToTarjetaCreditoDto(movimientoBancario.getTarjetaCredito()),
            movimientoBancario.getFecha(), 
            movimientoBancario.getImporte(), 
            movimientoBancario.getConcepto());
    }
}
