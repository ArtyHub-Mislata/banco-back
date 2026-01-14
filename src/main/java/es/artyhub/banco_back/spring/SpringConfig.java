package es.artyhub.banco_back.spring;

import es.artyhub.banco_back.domain.model.MovimientoBancario;
import es.artyhub.banco_back.domain.repository.ClienteRepository;
import es.artyhub.banco_back.domain.repository.CuentaRepository;
import es.artyhub.banco_back.domain.repository.MovimientoBancarioRepository;
import es.artyhub.banco_back.domain.repository.TarjetaCreditoRepository;
import es.artyhub.banco_back.domain.service.*;
import es.artyhub.banco_back.domain.service.impl.*;
import es.artyhub.banco_back.persistence.dao.jpa.ClienteJpaDao;
import es.artyhub.banco_back.persistence.dao.jpa.CuentaJpaDao;
import es.artyhub.banco_back.persistence.dao.jpa.MovimientoBancarioJpaDao;
import es.artyhub.banco_back.persistence.dao.jpa.TarjetaCreditoJpaDao;
import es.artyhub.banco_back.persistence.dao.jpa.impl.ClienteJpaDaoImpl;
import es.artyhub.banco_back.persistence.dao.jpa.impl.CuentaJpaDaoImpl;
import es.artyhub.banco_back.persistence.dao.jpa.impl.MovimientoBancarioJpaDaoImpl;
import es.artyhub.banco_back.persistence.dao.jpa.impl.TarjetaCreditoJpaDaoImpl;
import es.artyhub.banco_back.persistence.repository.impl.ClienteRepositoryImpl;
import es.artyhub.banco_back.persistence.repository.impl.CuentaRepositoryImpl;
import es.artyhub.banco_back.persistence.repository.impl.MovimientoBancarioRepositoryImpl;
import es.artyhub.banco_back.persistence.repository.impl.TarjetaCreditoRepositoryImpl;
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
    //BEANS DE AUTHORITATION
    @Bean
    public AutorizacionService autorizacionService(CuentaService cuentaService){
        return new AutorizacionServiceImpl(cuentaService);
    }
    //BEANS DE PAGO_TARJETA
    @Bean
    public PagoTarjetaService pagoTarjetaService(AutorizacionService autorizacionService, CuentaService cuentaService,
                                                 TarjetaCreditoService tarjetaCreditoService, MovimientoBancarioService movimientoBancarioService){
        return new PagoTarjetaServiceImpl(autorizacionService, cuentaService, movimientoBancarioService, tarjetaCreditoService);
    }














}
