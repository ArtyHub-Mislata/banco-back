package es.artyhub.banco_back.controller;

import es.artyhub.banco_back.domain.dto.PagoTarjetaDto;
import es.artyhub.banco_back.domain.service.PagoTarjetaService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/pagos")
@RestController
public class PagoController {
    private final PagoTarjetaService pagoTarjetaService;

    public PagoController(PagoTarjetaService pagoTarjetaService) {
        this.pagoTarjetaService = pagoTarjetaService;
    }

    @RequestMapping("/pago_tarjeta")
    @PostMapping
    public void pagarConTarjeta(@RequestBody PagoTarjetaDto pagoTarjetaDto){
        pagoTarjetaService.save(pagoTarjetaDto);
    }
}
