package es.artyhub.banco_back.domain.service;

import es.artyhub.banco_back.domain.dto.PagoTransferenciaDto;

public interface PagoTransferenciaService {
    PagoTransferenciaDto getPagoTransferenciaDto();
    void save(PagoTransferenciaDto pagoTransferenciaDto);
}
