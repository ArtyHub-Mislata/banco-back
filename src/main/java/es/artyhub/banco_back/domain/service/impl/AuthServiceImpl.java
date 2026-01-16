package es.artyhub.banco_back.domain.service.impl;

import es.artyhub.banco_back.domain.dto.AutorizacionDto;
import es.artyhub.banco_back.domain.dto.CredentialsDto;
import es.artyhub.banco_back.domain.exception.BusinessException;
import es.artyhub.banco_back.domain.exception.ResourceNotFoundException;
import es.artyhub.banco_back.domain.model.Cliente;
import es.artyhub.banco_back.domain.repository.ClienteRepository;
import es.artyhub.banco_back.domain.repository.SesionRepository;
import es.artyhub.banco_back.domain.service.AuthService;
import jakarta.transaction.Transactional;

public class AuthServiceImpl implements AuthService {
    private final SesionRepository sesionRepository;
    private final ClienteRepository clienteRepository;

    public AuthServiceImpl(SesionRepository sesionRepository, ClienteRepository clienteRepository) {
        this.sesionRepository = sesionRepository;
        this.clienteRepository = clienteRepository;
    }
    @Transactional
    @Override
    public String login(CredentialsDto credentialsDto) {
        Cliente cliente = clienteRepository.findByLogin(credentialsDto.username());
        if(cliente == null){
            throw new ResourceNotFoundException("No existe el User");
        }
        //Dios mio que la contraseña no esta encriptada me cago en la madre que me pario
        if(!cliente.getPassword().equals(credentialsDto.password())){
            throw new BusinessException("Contraseña incorrecta");
        }

        return sesionRepository.insertSesion(cliente.getId());
    }
    @Transactional
    @Override
    public void logout(String token) {
        if(token == null){
            throw new BusinessException("El token no es valido para hacer logout");
        }
        sesionRepository.logout(token);
    }

    @Override
    public Cliente getClienteByToken(String token) {
        if(token == null){
            throw new BusinessException("El token no es valido para hacer logout");
        }
        return sesionRepository.findByToken(token);
    }

    @Override
    public boolean autorizar(AutorizacionDto autorizacion) {
        return clienteRepository.clienAndApiTokenCorrect(autorizacion);
    }
}
