package es.artyhub.banco_back.domain.repository;

import es.artyhub.banco_back.domain.model.Cliente;

public interface SesionRepository {
    String insertSesion(Long idUser);
    Cliente findByToken(String token);
    void logout(String token);

}
