package es.artyhub.banco_back.persistence;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import es.artyhub.banco_back.domain.repository.ClienteRepository;
import es.artyhub.banco_back.domain.repository.CuentaRepository;
import es.artyhub.banco_back.domain.repository.MovimientoBancarioRepository;
import es.artyhub.banco_back.domain.repository.SesionRepository;
import es.artyhub.banco_back.domain.repository.TarjetaCreditoRepository;
import es.artyhub.banco_back.domain.service.AuthService;
import es.artyhub.banco_back.domain.service.ClienteService;
import es.artyhub.banco_back.domain.service.CuentaService;
import es.artyhub.banco_back.domain.service.MovimientoBancarioService;
import es.artyhub.banco_back.domain.service.PagoTarjetaService;
import es.artyhub.banco_back.domain.service.PagoTransferenciaService;
import es.artyhub.banco_back.domain.service.TarjetaCreditoService;
import es.artyhub.banco_back.domain.service.impl.AuthServiceImpl;
import es.artyhub.banco_back.domain.service.impl.ClienteServiceImpl;
import es.artyhub.banco_back.domain.service.impl.CuentaServiceImpl;
import es.artyhub.banco_back.domain.service.impl.MovimientoBancarioServiceImpl;
import es.artyhub.banco_back.domain.service.impl.PagoTarjetaServiceImpl;
import es.artyhub.banco_back.domain.service.impl.PagoTransferenciaServiceImpl;
import es.artyhub.banco_back.domain.service.impl.TarjetaCreditoServiceImpl;
import es.artyhub.banco_back.persistence.dao.jpa.ClienteJpaDao;
import es.artyhub.banco_back.persistence.dao.jpa.CuentaJpaDao;
import es.artyhub.banco_back.persistence.dao.jpa.MovimientoBancarioJpaDao;
import es.artyhub.banco_back.persistence.dao.jpa.SesionJpaDao;
import es.artyhub.banco_back.persistence.dao.jpa.TarjetaCreditoJpaDao;
import es.artyhub.banco_back.persistence.dao.jpa.impl.ClienteJpaDaoImpl;
import es.artyhub.banco_back.persistence.dao.jpa.impl.CuentaJpaDaoImpl;
import es.artyhub.banco_back.persistence.dao.jpa.impl.MovimientoBancarioJpaDaoImpl;
import es.artyhub.banco_back.persistence.dao.jpa.impl.SesionJpaDaoImpl;
import es.artyhub.banco_back.persistence.dao.jpa.impl.TarjetaCreditoJpaDaoImpl;
import es.artyhub.banco_back.persistence.repository.impl.ClienteRepositoryImpl;
import es.artyhub.banco_back.persistence.repository.impl.CuentaRepositoryImpl;
import es.artyhub.banco_back.persistence.repository.impl.MovimientoBancarioRepositoryImpl;
import es.artyhub.banco_back.persistence.repository.impl.SesionRepositoryImpl;
import es.artyhub.banco_back.persistence.repository.impl.TarjetaCreditoRepositoryImpl;

@Configuration
@EnableJpaRepositories(basePackages = "es.artyhub.banco_back.persistence.dao.jpa")
@EntityScan(basePackages = "es.artyhub.banco_back.persistence.dao.jpa.entity")
public class TestConfig {
    
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
    //BEANS DE PAGO TRANSFERENCIA
    @Bean
    public PagoTransferenciaService pagoTransferenciaService(AuthService autorizacionService, CuentaService cuentaService,
                                                 TarjetaCreditoService tarjetaCreditoService, MovimientoBancarioService movimientoBancarioService){
        return new PagoTransferenciaServiceImpl(autorizacionService, cuentaService, movimientoBancarioService, tarjetaCreditoService);
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
