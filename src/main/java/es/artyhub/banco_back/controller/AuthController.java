package es.artyhub.banco_back.controller;

import es.artyhub.banco_back.domain.dto.CredentialsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody CredentialsDto credentialsDto) {
        System.out.println("LLEGA AL CONTROLLER");
        String token = loginService.login(credentialsDto);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
