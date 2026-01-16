package es.artyhub.banco_back.spring;

import es.artyhub.banco_back.domain.model.Cliente;
import es.artyhub.banco_back.domain.model.MovimientoBancario;
import es.artyhub.banco_back.domain.repository.*;
import es.artyhub.banco_back.domain.service.*;
import es.artyhub.banco_back.domain.service.impl.*;
import es.artyhub.banco_back.persistence.dao.jpa.*;
import es.artyhub.banco_back.persistence.dao.jpa.impl.*;
import es.artyhub.banco_back.persistence.repository.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    //BEANS DE CLIENTE
    @Bean
    public ClienteJpaDao clienteJpaDao(){
        return new ClienteJpaDaoImpl();
    }
    @Bean
    public ClienteRepository clienteRepository(ClienteJpaDao clienteJpaDao){
        return new ClienteRepositoryImpl(clienteJpaDao);
    }
    @Bean
    public ClienteService clienteService(ClienteRepository clienteRepository){
        return new ClienteServiceImpl(clienteRepository);
    }

    //BEANS DE CUENTA
    @Bean
    public CuentaJpaDao cuentaJpaDao(){
        return new CuentaJpaDaoImpl();
    }
    @Bean
    public CuentaRepository cuentaRepository(CuentaJpaDao cuentaJpaDao){
        return new CuentaRepositoryImpl(cuentaJpaDao);
    }
    @Bean
    public CuentaService cuentaService(CuentaRepository cuentaRepository){
        return new CuentaServiceImpl(cuentaRepository);
    }
    //BEANS DE TARJETA
    @Bean
    public TarjetaCreditoJpaDao tarjetaCreditoJpaDao(){
        return new TarjetaCreditoJpaDaoImpl();
    }
    @Bean
    public TarjetaCreditoRepository tarjetaCreditoRepository(TarjetaCreditoJpaDao tarjetaCreditoJpaDao){
        return new TarjetaCreditoRepositoryImpl(tarjetaCreditoJpaDao);
    }
    @Bean
    public TarjetaCreditoService tarjetaCreditoService(TarjetaCreditoRepository tarjetaCreditoRepository){
        return new TarjetaCreditoServiceImpl(tarjetaCreditoRepository);
    }
    //BEANS DE MOVIMIENTO BANCARIO
    @Bean
    public MovimientoBancarioJpaDao movimientoBancarioJpaDao(){
        return new MovimientoBancarioJpaDaoImpl();
    }
    @Bean
    public MovimientoBancarioRepository movimientoBancarioRepository(MovimientoBancarioJpaDao movimientoBancarioJpaDao){
        return new MovimientoBancarioRepositoryImpl(movimientoBancarioJpaDao);
    }
    @Bean
    public MovimientoBancarioService movimientoBancarioService(MovimientoBancarioRepository movimientoBancarioRepository){
        return new MovimientoBancarioServiceImpl(movimientoBancarioRepository);
    }

    //BEANS DE PAGO_TARJETA
    @Bean
    public PagoTarjetaService pagoTarjetaService(AuthService autorizacionService, CuentaService cuentaService,
                                                 TarjetaCreditoService tarjetaCreditoService, MovimientoBancarioService movimientoBancarioService){
        return new PagoTarjetaServiceImpl(autorizacionService, cuentaService, movimientoBancarioService, tarjetaCreditoService);
    }

    @Bean
    public AuthService authService(SesionRepository sesionRepository, ClienteRepository clienteRepository){
        return new AuthServiceImpl(sesionRepository, clienteRepository);
    }
    @Bean
    public SesionRepository sesionRepository(SesionJpaDao sesionJpaDao){
        return new SesionRepositoryImpl(sesionJpaDao);
    }
    @Bean
    public SesionJpaDao sesionJpaDao(){
        return new SesionJpaDaoImpl();
    }


}
