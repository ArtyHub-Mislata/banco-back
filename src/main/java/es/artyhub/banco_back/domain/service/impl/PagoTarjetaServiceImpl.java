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
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.Date;

public class PagoTarjetaServiceImpl implements PagoTarjetaService {

    private final AuthService authService;
    private final CuentaService cuentaService;
    private final MovimientoBancarioService movimientoBancarioService;
    private final TarjetaCreditoService tarjetaCreditoService;

    public PagoTarjetaServiceImpl(AuthService authService, CuentaService cuentaService,
                                  MovimientoBancarioService movimientoBancarioService, TarjetaCreditoService tarjetaCreditoService) {
        this.authService = authService;
        this.cuentaService = cuentaService;
        this.movimientoBancarioService = movimientoBancarioService;
        this.tarjetaCreditoService = tarjetaCreditoService;
    }
    @Transactional
    @Override
    public void save(PagoTarjetaDto pagoTarjetaDto) {

        DtoValidator.validate(pagoTarjetaDto);

        //Comprobación de los datos de la tarjeta
        TarjetaCredito tarjetaCredito = tarjetaCreditoService.findByNumeroTarjeta(pagoTarjetaDto.origen().numeroTarjeta());

        if (!tarjetaCreditoService.tarjetaIsValid(pagoTarjetaDto.origen(), tarjetaCredito)) {
            throw new BusinessException("La tarjeta de origen no es valida");
        }

        Cuenta cuentaOrigen = cuentaService.findByNumeroTarjeta(tarjetaCredito.getNumeroTarjeta());
        Cuenta cuentaDestino = cuentaService.findByIban(pagoTarjetaDto.destino().iban());

        if(!cuentaDestino.getCliente().getLogin().equals(pagoTarjetaDto.autorizacion().login())){
            throw new BusinessException("La cuenta destino no coincide con el usuario login");
        }

        //Comprobar saldo suficiente
        if(cuentaOrigen.getSaldo().compareTo(pagoTarjetaDto.pago().importe()) < 0){
            throw new BusinessException("La cuenta no tiene suficiente saldo");
        }


        MovimientoBancario movimientoBancarioDebe = new MovimientoBancario();
        movimientoBancarioDebe.setConcepto(pagoTarjetaDto.pago().concepto());
        movimientoBancarioDebe.setFecha(new Date());
        movimientoBancarioDebe.setOrigenMovimiento(OrigenMovimiento.TARJETABANCARIA);
        movimientoBancarioDebe.setImporte(pagoTarjetaDto.pago().importe());
        movimientoBancarioDebe.setTarjetaCredito(tarjetaCredito);
        movimientoBancarioDebe.setTipoMovimiento(TipoMovimiento.DEBE);



        MovimientoBancario movimientoBancarioHaber = new MovimientoBancario();
        movimientoBancarioHaber.setConcepto(pagoTarjetaDto.pago().concepto());
        movimientoBancarioHaber.setFecha(new Date());
        movimientoBancarioHaber.setOrigenMovimiento(OrigenMovimiento.TRANSFERENCIA);
        movimientoBancarioHaber.setImporte(pagoTarjetaDto.pago().importe());
        movimientoBancarioHaber.setTarjetaCredito(null);
        movimientoBancarioHaber.setTipoMovimiento(TipoMovimiento.HABER);



        cuentaService.updateSaldo(cuentaOrigen, pagoTarjetaDto.pago().importe(), TipoMovimiento.DEBE);
        movimientoBancarioService.saveMovimientoBancario(movimientoBancarioDebe, cuentaOrigen.getId());

        cuentaService.updateSaldo(cuentaDestino, pagoTarjetaDto.pago().importe(), TipoMovimiento.HABER);

        movimientoBancarioService.saveMovimientoBancario(movimientoBancarioHaber, cuentaDestino.getId());

    }
}
