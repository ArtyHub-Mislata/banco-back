package es.artyhub.banco_back.controller;

import es.artyhub.banco_back.domain.model.Cliente;
import es.artyhub.banco_back.domain.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/customer")
@RestController
public class ClienteController {
    private final AuthService authService;

    public ClienteController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<Cliente> getCliente(HttpServletRequest request){
        String token = request.getHeader("authorization").substring(7);
        Cliente cliente = authService.getClienteByToken(token);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }
}
