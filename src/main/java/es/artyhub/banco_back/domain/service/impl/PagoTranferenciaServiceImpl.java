package es.artyhub.banco_back.domain.service.impl;

import es.artyhub.banco_back.domain.dto.PagoTransferenciaDto;
import es.artyhub.banco_back.domain.repository.PagoTransferenciaRepository;
import es.artyhub.banco_back.domain.service.PagoTransferenciaService;

public class PagoTranferenciaServiceImpl implements PagoTransferenciaService {
    private final PagoTransferenciaRepository pagoTransferenciaRepository;

    public PagoTranferenciaServiceImpl(PagoTransferenciaRepository pagoTransferenciaRepository) {
        this.pagoTransferenciaRepository = pagoTransferenciaRepository;
    }

    @Override
    public PagoTransferenciaDto getPagoTransferenciaDto() {
        return pagoTransferenciaRepository.getPagoTransferenciaDto();
    }

    @Override
    public void save(PagoTransferenciaDto pagoTransferenciaDto) {
        pagoTransferenciaRepository.save(pagoTransferenciaDto);
    }
}
