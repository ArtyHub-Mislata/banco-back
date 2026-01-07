package es.artyhub.banco_back.domain.repository;

import es.artyhub.banco_back.domain.dto.PagoTransferenciaDto;

public interface PagoTransferenciaRepository {
    PagoTransferenciaDto getPagoTransferenciaDto();
    void save(PagoTransferenciaDto pagoTransferenciaDto);
}
