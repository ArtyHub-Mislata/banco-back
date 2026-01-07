package es.artyhub.banco_back.domain.service.impl;

import es.artyhub.banco_back.domain.dto.PagoTarjetaDto;
import es.artyhub.banco_back.domain.repository.PagoTarjetaRepository;
import es.artyhub.banco_back.domain.service.PagoTarjetaService;

public class PagoTarjetaServiceImpl implements PagoTarjetaService {
    private final PagoTarjetaRepository pagoTarjetaRepository;

    public PagoTarjetaServiceImpl(PagoTarjetaRepository pagoTarjetaRepository) {
        this.pagoTarjetaRepository = pagoTarjetaRepository;
    }

    @Override
    public PagoTarjetaDto getPagoTarjetaDto() {
        return pagoTarjetaRepository.getPagoTarjetaDto();
    }

    @Override
    public void save(PagoTarjetaDto pagoTarjetaDto) {
        pagoTarjetaRepository.save(pagoTarjetaDto);
    }
}
