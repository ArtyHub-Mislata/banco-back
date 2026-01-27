package es.artyhub.banco_back.domain.service.impl;

import es.artyhub.banco_back.domain.dto.PagoTarjetaDto;
import es.artyhub.banco_back.domain.exception.ResourceNotFoundException;
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

        if (pagoTarjetaDto == null) {
            throw new ValidationException("El pago no puede ser nulo");
        }

        if (pagoTarjetaDto.destino() == null) {
            throw new ValidationException("El destino no puede ser nulo");
        }
        
        Cuenta cuenta = cuentaService.findByIban(pagoTarjetaDto.destino().iban());

        if (cuenta == null) {
            throw new ResourceNotFoundException("El número de cuenta no existe");
        }

        Cliente cliente = cuenta.getCliente();

        if (cliente == null) {
            throw new ResourceNotFoundException("La cuenta no pertenece a ningun cliente");
        }

        return pagoTarjetaDto.autorizacion().login().equals(cliente.getLogin())
                && pagoTarjetaDto.autorizacion().api_token().equals(cliente.getApi_token());
    }
}
