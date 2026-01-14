package es.artyhub.banco_back.domain.service;

import es.artyhub.banco_back.domain.dto.CredentialsDto;
import es.artyhub.banco_back.domain.model.Cliente;

public interface AuthService {
    String login(CredentialsDto credentialsDto);
    void logout(String token);
    Cliente getClienteByToken(String token);
}
