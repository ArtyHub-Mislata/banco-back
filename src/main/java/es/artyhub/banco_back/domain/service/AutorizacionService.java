package es.artyhub.banco_back.domain.service;

import es.artyhub.banco_back.domain.dto.PagoTarjetaDto;

public interface AutorizacionService {

    boolean autorizar(PagoTarjetaDto pagoTarjetaDto);
    
}
