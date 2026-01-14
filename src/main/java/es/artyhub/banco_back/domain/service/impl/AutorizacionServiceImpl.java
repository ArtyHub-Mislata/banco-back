package es.artyhub.banco_back.domain.service.impl;

import es.artyhub.banco_back.domain.dto.PagoTarjetaDto;
import es.artyhub.banco_back.domain.exception.ValidationException;
import es.artyhub.banco_back.domain.model.Cliente;
import es.artyhub.banco_back.domain.model.Cuenta;
import es.artyhub.banco_back.domain.service.AutorizacionService;
import es.artyhub.banco_back.domain.service.CuentaService;

public class AutorizacionServiceImpl implements AutorizacionService {

    private final CuentaService cuentaService;

    public AutorizacionServiceImpl(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }
    
    @Override
    public boolean autorizar(PagoTarjetaDto pagoTarjetaDto) {
        Cuenta cuenta = cuentaService.findByIban(pagoTarjetaDto.destino().numeroCuenta());

        Cliente cliente = cuenta.getCliente();

        return pagoTarjetaDto.autorizacion().login().equals(cliente.getLogin())
                && pagoTarjetaDto.autorizacion().api_token().equals(cliente.getApi_token());

    }
    
}
