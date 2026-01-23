package es.artyhub.banco_back.controller;

import es.artyhub.banco_back.domain.dto.CredentialsDto;
import es.artyhub.banco_back.domain.model.Cliente;
import es.artyhub.banco_back.domain.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api")
@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody CredentialsDto credentialsDto) {
        String token = authService.login(credentialsDto);
        return ResponseEntity.ok(Map.of("token", token));
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        String token = request.getHeader("authorization").substring(7);
        authService.logout(token);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/islogged")
    public ResponseEntity<Boolean> isLogged(HttpServletRequest request){
        Boolean isLogged = Boolean.FALSE;
        String header = request.getHeader("authorization");

        if(header != null){
            String token = header.substring(7);
            Cliente cliente = authService.getClienteByToken(token);
            if(cliente != null) {
                isLogged = Boolean.TRUE;
            }
        }
        return new ResponseEntity<>(isLogged, HttpStatus.OK);
    }
}
