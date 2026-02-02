package es.artyhub.banco_back.controller;

import es.artyhub.banco_back.domain.model.MovimientoBancario;
import es.artyhub.banco_back.domain.model.TarjetaCredito;
import es.artyhub.banco_back.domain.service.MovimientoBancarioService;
import es.artyhub.banco_back.domain.service.TarjetaCreditoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/cards")
@RestController
public class TarjetaController {
    private final TarjetaCreditoService tarjetaCreditoService;
    private final MovimientoBancarioService movimientoBancarioService;

    public TarjetaController(TarjetaCreditoService tarjetaCreditoService, MovimientoBancarioService movimientoBancarioService) {
        this.tarjetaCreditoService = tarjetaCreditoService;
        this.movimientoBancarioService = movimientoBancarioService;
    }

    @GetMapping
    public ResponseEntity<List<TarjetaCredito>> getAllTarjetasOfUser(HttpServletRequest request){
        String token = request.getHeader("authorization").substring(7);
        List<TarjetaCredito> tarjetasCredito = tarjetaCreditoService.findAllOfUser(token);
        return new ResponseEntity<>(tarjetasCredito, HttpStatus.OK);
    }
    //Hay que comprobar que la tarjeta sea del usuario
    @GetMapping("/{id}")
    public ResponseEntity<TarjetaCredito> getTarjetaByID(@PathVariable Long id, HttpServletRequest request){
        TarjetaCredito tarjetaCredito = tarjetaCreditoService.findById(id);
        if(tarjetaCredito == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String token = request.getHeader("authorization").substring(7);
        if(!tarjetaCreditoService.tarjetaPerteneceAUsuario(id, token)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(tarjetaCredito, HttpStatus.OK);
    }
    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<MovimientoBancario>> getMovimientosBancariosOfTarjeta(@PathVariable Long id){
        List<MovimientoBancario> movimientos = movimientoBancarioService.findByTarjetaId(id);
        return new ResponseEntity<>(movimientos, HttpStatus.OK);
    }
}
