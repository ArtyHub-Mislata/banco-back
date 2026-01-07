package es.artyhub.banco_back.domain.repository;

import es.artyhub.banco_back.domain.dto.PagoTarjetaDto;

public interface PagoTarjetaRepository {
    PagoTarjetaDto getPagoTarjetaDto();
    void save(PagoTarjetaDto pagoTarjetaDto);
}
