package es.artyhub.banco_back.domain.service.impl;

import es.artyhub.banco_back.domain.dto.PagoTarjetaDto;
import es.artyhub.banco_back.domain.enums.OrigenMovimiento;
import es.artyhub.banco_back.domain.enums.TipoMovimiento;
import es.artyhub.banco_back.domain.exception.BusinessException;
import es.artyhub.banco_back.domain.model.Cuenta;
import es.artyhub.banco_back.domain.model.MovimientoBancario;
import es.artyhub.banco_back.domain.model.TarjetaCredito;
import es.artyhub.banco_back.domain.service.*;
import es.artyhub.banco_back.domain.validation.DtoValidator;

import java.util.Date;

public class PagoTarjetaServiceImpl implements PagoTarjetaService {

    private final AutorizacionService autorizacionService;
    private final CuentaService cuentaService;
    private final MovimientoBancarioService movimientoBancarioService;
    private final TarjetaCreditoService tarjetaCreditoService;

    public PagoTarjetaServiceImpl(AutorizacionService autorizacionService, CuentaService cuentaService,
                                  MovimientoBancarioService movimientoBancarioService, TarjetaCreditoService tarjetaCreditoService) {
        this.autorizacionService = autorizacionService;
        this.cuentaService = cuentaService;
        this.movimientoBancarioService = movimientoBancarioService;
        this.tarjetaCreditoService = tarjetaCreditoService;
    }

    @Override
    public void save(PagoTarjetaDto pagoTarjetaDto) {
        DtoValidator.validate(pagoTarjetaDto);

        TarjetaCredito tarjetaCredito = tarjetaCreditoService.findByNumeroTarjeta(pagoTarjetaDto.origen().numeroTarjeta());
        if (!tarjetaCreditoService.tarjetaIsValid(pagoTarjetaDto.origen().numeroTarjeta())) {
            throw new BusinessException("La tarjeta de origen no es valida");
        }

        Cuenta cuentaOrigen = cuentaService.findByNumeroTarjeta(tarjetaCredito.getNumeroTarjeta());
        Cuenta cuentaDestino = cuentaService.findByIban(pagoTarjetaDto.destino().numeroCuenta());

        if (!cuentaService.saldoIsEnough(pagoTarjetaDto.pago().importe(), cuentaOrigen.getIban())) {
            throw new BusinessException("El saldo de la cuenta no es suficiente");
        }
        if (!autorizacionService.autorizar(pagoTarjetaDto)) {
            throw new BusinessException("La autorizacion no es valida");
        }

        MovimientoBancario movimientoBancarioDebe = new MovimientoBancario();
        movimientoBancarioDebe.setConcepto(pagoTarjetaDto.pago().concepto());
        movimientoBancarioDebe.setCuenta(cuentaOrigen);
        movimientoBancarioDebe.setFecha(new Date());
        movimientoBancarioDebe.setOrigenMovimiento(OrigenMovimiento.TARJETABANCARIA);
        movimientoBancarioDebe.setImporte(pagoTarjetaDto.pago().importe());
        movimientoBancarioDebe.setTarjetaCredito(tarjetaCredito);

        MovimientoBancario movimientoBancarioHaber = new MovimientoBancario();
        movimientoBancarioHaber.setConcepto(pagoTarjetaDto.pago().concepto());
        movimientoBancarioHaber.setCuenta(cuentaDestino);
        movimientoBancarioHaber.setFecha(new Date());
        movimientoBancarioHaber.setOrigenMovimiento(OrigenMovimiento.TRANSFERENCIA);
        movimientoBancarioHaber.setImporte(pagoTarjetaDto.pago().importe());
        movimientoBancarioHaber.setTarjetaCredito(null);

        cuentaService.updateSaldo(cuentaOrigen, pagoTarjetaDto.pago().importe(), TipoMovimiento.DEBE);
        cuentaService.updateSaldo(cuentaDestino, pagoTarjetaDto.pago().importe(), TipoMovimiento.HABER);

        movimientoBancarioService.saveMovimientoBancario(movimientoBancarioHaber);
        movimientoBancarioService.saveMovimientoBancario(movimientoBancarioDebe);
    }
}
