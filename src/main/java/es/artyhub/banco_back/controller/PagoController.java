package es.artyhub.banco_back.controller;

import es.artyhub.banco_back.domain.dto.PagoTarjetaDto;
import es.artyhub.banco_back.domain.dto.PagoTransferenciaDto;
import es.artyhub.banco_back.domain.service.PagoTarjetaService;

import es.artyhub.banco_back.domain.service.PagoTransferenciaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/pagos")
@RestController
public class PagoController {
    private final PagoTarjetaService pagoTarjetaService;
    private final PagoTransferenciaService pagoTransferenciaService;

    public PagoController(PagoTarjetaService pagoTarjetaService, PagoTransferenciaService pagoTransferenciaService) {
        this.pagoTarjetaService = pagoTarjetaService;
        this.pagoTransferenciaService = pagoTransferenciaService;
    }

    @RequestMapping("/pago_tarjeta")
    @PostMapping
    public void pagarConTarjeta(@RequestBody PagoTarjetaDto pagoTarjetaDto){
        pagoTarjetaService.save(pagoTarjetaDto);
    }
    @RequestMapping("/transferencia")
    @PostMapping
    public void hacerTransferencia(@RequestBody PagoTransferenciaDto pagoTransferenciaDto){
        pagoTransferenciaService.save(pagoTransferenciaDto);
    }

}
