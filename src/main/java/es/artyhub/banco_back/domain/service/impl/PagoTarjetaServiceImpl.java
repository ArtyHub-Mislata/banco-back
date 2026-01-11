package es.artyhub.banco_back.domain.service.impl;

import es.artyhub.banco_back.domain.dto.PagoTarjetaDto;
import es.artyhub.banco_back.domain.exception.BusinessException;
import es.artyhub.banco_back.domain.model.Cuenta;
import es.artyhub.banco_back.domain.model.TarjetaCredito;
import es.artyhub.banco_back.domain.service.PagoTarjetaService;

public class PagoTarjetaServiceImpl implements PagoTarjetaService {

    private final AutorizacionServiceImpl autorizacionService;
    private final CuentaServiceImpl cuentaService;
    private final MovimientoBancarioServiceImpl movimientoBancarioService;
    private final TarjetaCreditoServiceImpl tarjetaCreditoService;

    public PagoTarjetaServiceImpl(AutorizacionServiceImpl autorizacionService, CuentaServiceImpl cuentaService, MovimientoBancarioServiceImpl movimientoBancarioService, TarjetaCreditoServiceImpl tarjetaCreditoService) {
        this.autorizacionService = autorizacionService;
        this.cuentaService = cuentaService;
        this.movimientoBancarioService = movimientoBancarioService;
        this.tarjetaCreditoService = tarjetaCreditoService;
    }

    @Override
    public void save(PagoTarjetaDto pagoTarjetaDto) {

        TarjetaCredito tarjetaCredito = tarjetaCreditoService.findByNumeroTarjeta(pagoTarjetaDto.origen().numeroTarjeta());
        Cuenta cuenta = tarjetaCredito.getCuenta();

        if (!tarjetaCreditoService.tarjetaIsValid(pagoTarjetaDto.origen().numeroTarjeta())) {
            throw new BusinessException("La tarjeta de origen no es valida");
        }

        if (!cuentaService.saldoIsEnough(pagoTarjetaDto.pago().importe(), cuenta.getIban())) {
            throw new BusinessException("El saldo de la cuenta no es suficiente");
        }

        if (!autorizacionService.autorizar(pagoTarjetaDto)) {
            throw new BusinessException("La autorizacion no es valida");
        }

        movimientoBancarioService.saveMovimientoBancario(pagoTarjetaDto);

        cuentaService.updateSaldo(cuenta, pagoTarjetaDto.pago().importe());

        //No se si hay que guardar el pago de la tarjeta
    }
}
