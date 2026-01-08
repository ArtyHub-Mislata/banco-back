package es.artyhub.banco_back.spring;

import es.artyhub.banco_back.domain.repository.ClienteRepository;
import es.artyhub.banco_back.domain.repository.CuentaRepository;
import es.artyhub.banco_back.domain.repository.MovimientoBancarioRepository;
import es.artyhub.banco_back.domain.repository.TarjetaCreditoRepository;
import es.artyhub.banco_back.domain.service.ClienteService;
import es.artyhub.banco_back.domain.service.CuentaService;
import es.artyhub.banco_back.domain.service.MovimientoBancarioService;
import es.artyhub.banco_back.domain.service.TarjetaCreditoService;
import es.artyhub.banco_back.domain.service.impl.ClienteServiceImpl;
import es.artyhub.banco_back.domain.service.impl.CuentaServiceImpl;
import es.artyhub.banco_back.domain.service.impl.MovimientoBancarioServiceImpl;
import es.artyhub.banco_back.domain.service.impl.TarjetaCreditoServiceImpl;
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
        return new ClienteServiceImpl();
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
    public CuentaService cuentaService(){
        return new CuentaServiceImpl();
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
    public TarjetaCreditoService tarjetaCreditoService(){
        return new TarjetaCreditoServiceImpl();
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
    public MovimientoBancarioService movimientoBancarioService(){
        return new MovimientoBancarioServiceImpl();
    }














}
