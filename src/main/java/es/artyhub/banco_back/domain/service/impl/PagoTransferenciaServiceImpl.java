package es.artyhub.banco_back.domain.service.impl;

import es.artyhub.banco_back.domain.dto.PagoTransferenciaDto;
import es.artyhub.banco_back.domain.enums.OrigenMovimiento;
import es.artyhub.banco_back.domain.enums.TipoMovimiento;
import es.artyhub.banco_back.domain.exception.BusinessException;
import es.artyhub.banco_back.domain.model.Cuenta;
import es.artyhub.banco_back.domain.model.MovimientoBancario;
import es.artyhub.banco_back.domain.service.*;
import es.artyhub.banco_back.domain.validation.DtoValidator;

import java.util.Date;

public class PagoTransferenciaServiceImpl implements PagoTransferenciaService {
    private final AuthService authService;
    private final CuentaService cuentaService;
    private final MovimientoBancarioService movimientoBancarioService;
    private final TarjetaCreditoService tarjetaCreditoService;

    public PagoTransferenciaServiceImpl(AuthService authService, CuentaService cuentaService,
                                  MovimientoBancarioService movimientoBancarioService, TarjetaCreditoService tarjetaCreditoService) {
        this.authService = authService;
        this.cuentaService = cuentaService;
        this.movimientoBancarioService = movimientoBancarioService;
        this.tarjetaCreditoService = tarjetaCreditoService;
    }
    @Override
    public void save(PagoTransferenciaDto pagoTransferenciaDto) {
        DtoValidator.validate(pagoTransferenciaDto);
        DtoValidator.validate(pagoTransferenciaDto.pago());
        DtoValidator.validate(pagoTransferenciaDto.autorizacion());
        DtoValidator.validate(pagoTransferenciaDto.destino());
        DtoValidator.validate(pagoTransferenciaDto.origen());

        Cuenta cuentaOrigen = cuentaService.findByIban(pagoTransferenciaDto.origen().iban());
        Cuenta cuentaDestino = cuentaService.findByIban(pagoTransferenciaDto.destino().iban());

        //La cuenta origen debe ser del usuario login
        if(!cuentaOrigen.getCliente().getLogin().equals(pagoTransferenciaDto.autorizacion().login())){
            throw new BusinessException("La cuenta origen no coincide con el usuario login");
        }

        if(!authService.autorizar(pagoTransferenciaDto.autorizacion())){
            throw new BusinessException("El usuario no tiene token correcto para hacer la operación");
        }

        //Comprobar saldo suficiente
        if(cuentaOrigen.getSaldo().compareTo(pagoTransferenciaDto.pago().importe()) < 0){
            throw new BusinessException("La cuenta no tiene suficiente saldo");
        }

        MovimientoBancario movimientoBancarioDebe = new MovimientoBancario();
        movimientoBancarioDebe.setConcepto(pagoTransferenciaDto.pago().concepto());
        movimientoBancarioDebe.setFecha(new Date());
        movimientoBancarioDebe.setOrigenMovimiento(OrigenMovimiento.TRANSFERENCIA);
        movimientoBancarioDebe.setImporte(pagoTransferenciaDto.pago().importe());
        movimientoBancarioDebe.setTarjetaCredito(null);
        movimientoBancarioDebe.setTipoMovimiento(TipoMovimiento.DEBE);



        MovimientoBancario movimientoBancarioHaber = new MovimientoBancario();
        movimientoBancarioHaber.setConcepto(pagoTransferenciaDto.pago().concepto());
        movimientoBancarioHaber.setFecha(new Date());
        movimientoBancarioHaber.setOrigenMovimiento(OrigenMovimiento.TRANSFERENCIA);
        movimientoBancarioHaber.setImporte(pagoTransferenciaDto.pago().importe());
        movimientoBancarioHaber.setTarjetaCredito(null);
        movimientoBancarioHaber.setTipoMovimiento(TipoMovimiento.HABER);

        cuentaService.updateSaldo(cuentaOrigen, pagoTransferenciaDto.pago().importe(), TipoMovimiento.DEBE);
        movimientoBancarioService.saveMovimientoBancario(movimientoBancarioDebe, cuentaOrigen.getId());

        cuentaService.updateSaldo(cuentaDestino, pagoTransferenciaDto.pago().importe(), TipoMovimiento.HABER);
        movimientoBancarioService.saveMovimientoBancario(movimientoBancarioHaber, cuentaDestino.getId());


    }
}
