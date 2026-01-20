package es.artyhub.banco_back.controller;

import es.artyhub.banco_back.domain.model.Cuenta;
import es.artyhub.banco_back.domain.model.TarjetaCredito;
import es.artyhub.banco_back.domain.service.CuentaService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CuentaController {
    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping("/customer/accounts")
    public ResponseEntity<List<Cuenta>> getAllCuentas(HttpServletRequest request) {
        String token = request.getHeader("authorization").substring(7);
        List<Cuenta> cuentas = cuentaService.findByToken(token);
        return new ResponseEntity<>(cuentas, HttpStatus.OK);
    }
    @GetMapping("/customer/accounts/{id}")
    public ResponseEntity<Cuenta> getCuentaById(@PathVariable Long id){
        Cuenta cuenta = cuentaService.findById(id);
        if(cuenta == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cuenta,HttpStatus.OK);
    }



}
