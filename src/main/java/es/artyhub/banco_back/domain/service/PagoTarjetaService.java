package es.artyhub.banco_back.domain.service;

import es.artyhub.banco_back.domain.dto.PagoTarjetaDto;

public interface PagoTarjetaService {
    PagoTarjetaDto getPagoTarjetaDto();
    void save(PagoTarjetaDto pagoTarjetaDto);
}
