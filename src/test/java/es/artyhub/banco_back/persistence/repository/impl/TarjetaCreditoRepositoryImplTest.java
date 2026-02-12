package es.artyhub.banco_back.persistence.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.artyhub.banco_back.domain.model.TarjetaCredito;
import es.artyhub.banco_back.persistence.dao.jpa.TarjetaCreditoJpaDao;
import es.artyhub.banco_back.persistence.dao.jpa.entity.TarjetaCreditoJpaEntity;
import es.artyhub.banco_back.persistence.repository.mapper.TarjetaCreditoMapper;

@ExtendWith(MockitoExtension.class)
public class TarjetaCreditoRepositoryImplTest {
    
    @Mock
    private TarjetaCreditoJpaDao tarjetaCreditoJpaDao;

    @InjectMocks
    private TarjetaCreditoRepositoryImpl tarjetaCreditoRepository;

    @Nested
    @DisplayName("findById")
    class FindByIdTest {
        
        @Test
        @DisplayName("Should return a card")
        void shouldReturnCard() {
            Long tarjetaId = 1L;

            TarjetaCreditoJpaEntity tarjetaJpaEntity = new TarjetaCreditoJpaEntity(tarjetaId, "1234567890123456", "12/12", "123", "Jhon Doe");

            when(tarjetaCreditoJpaDao.findById(tarjetaId)).thenReturn(tarjetaJpaEntity);

            TarjetaCredito tarjeta = TarjetaCreditoMapper.getInstance().fromTarjetaCreditoJpaEntityToTarjetaCredito(tarjetaJpaEntity);

            TarjetaCredito result = tarjetaCreditoRepository.findById(tarjetaId);

            assertEquals(tarjeta.getId(), result.getId());
            assertEquals(tarjeta.getNumeroTarjeta(), result.getNumeroTarjeta());
            assertEquals(tarjeta.getFechaCaducidad(), result.getFechaCaducidad());
            assertEquals(tarjeta.getCvv(), result.getCvv());
            assertEquals(tarjeta.getNombreCompleto(), result.getNombreCompleto());
        }
    }

    @Nested
    @DisplayName("findByNumberCard")
    class FindByNumberCardTest {
        
        @Test
        @DisplayName("Should return a card")
        void shouldReturnCard() {
            String numeroTarjeta = "1234567890123456";

            TarjetaCreditoJpaEntity tarjetaJpaEntity = new TarjetaCreditoJpaEntity(1L, numeroTarjeta, "12/12", "123", "Jhon Doe");

            when(tarjetaCreditoJpaDao.findByNumeroTarjeta(numeroTarjeta)).thenReturn(tarjetaJpaEntity);
            
            TarjetaCredito tarjeta = TarjetaCreditoMapper.getInstance().fromTarjetaCreditoJpaEntityToTarjetaCredito(tarjetaJpaEntity);

            TarjetaCredito result = tarjetaCreditoRepository.findByNumeroTarjeta(numeroTarjeta);

            assertEquals(tarjeta.getId(), result.getId());
            assertEquals(tarjeta.getNumeroTarjeta(), result.getNumeroTarjeta());
            assertEquals(tarjeta.getFechaCaducidad(), result.getFechaCaducidad());
            assertEquals(tarjeta.getCvv(), result.getCvv());
            assertEquals(tarjeta.getNombreCompleto(), result.getNombreCompleto());
        }
    }

    @Nested
    @DisplayName("findByAccountId")
    class FindByAccountIdTest {
        
        @Test
        @DisplayName("Should return a card list")
        void shouldReturnCardList() {
            Long accountId = 1L;

            TarjetaCreditoJpaEntity tarjetaJpaEntity = new TarjetaCreditoJpaEntity(1L, "1234567890123456", "12/12", "123", "Jhon Doe");
            
            List<TarjetaCreditoJpaEntity> tarjetaJpaEntityList = List.of(tarjetaJpaEntity);
            List<TarjetaCredito> tarjetaList = tarjetaJpaEntityList.stream().map(TarjetaCreditoMapper.getInstance()::fromTarjetaCreditoJpaEntityToTarjetaCredito).toList();

            when(tarjetaCreditoJpaDao.findByCuentaId(accountId)).thenReturn(tarjetaJpaEntityList);

            List<TarjetaCredito> result = tarjetaCreditoRepository.findByCuentaId(accountId);

            assertEquals(tarjetaList.get(0).getId(), result.get(0).getId());
            assertEquals(tarjetaList.get(0).getNumeroTarjeta(), result.get(0).getNumeroTarjeta());
            assertEquals(tarjetaList.get(0).getFechaCaducidad(), result.get(0).getFechaCaducidad());
            assertEquals(tarjetaList.get(0).getCvv(), result.get(0).getCvv());
            assertEquals(tarjetaList.get(0).getNombreCompleto(), result.get(0).getNombreCompleto());
        }
    }

    @Nested
    @DisplayName("findAll")
    class FindAllTest {
        
        @Test
        @DisplayName("Should return a list of cards")
        void shouldReturnCardList() {
            TarjetaCreditoJpaEntity tarjetaJpaEntity = new TarjetaCreditoJpaEntity(1L, "1234567890123456", "12/12", "123", "Jhon Doe");
            
            List<TarjetaCreditoJpaEntity> tarjetaJpaEntityList = List.of(tarjetaJpaEntity);
            List<TarjetaCredito> tarjetaList = tarjetaJpaEntityList.stream().map(TarjetaCreditoMapper.getInstance()::fromTarjetaCreditoJpaEntityToTarjetaCredito).toList();

            when(tarjetaCreditoJpaDao.findAll()).thenReturn(tarjetaJpaEntityList);

            List<TarjetaCredito> result = tarjetaCreditoRepository.findAll();

            assertEquals(tarjetaList.get(0).getId(), result.get(0).getId());
            assertEquals(tarjetaList.get(0).getNumeroTarjeta(), result.get(0).getNumeroTarjeta());
            assertEquals(tarjetaList.get(0).getFechaCaducidad(), result.get(0).getFechaCaducidad());
            assertEquals(tarjetaList.get(0).getCvv(), result.get(0).getCvv());
            assertEquals(tarjetaList.get(0).getNombreCompleto(), result.get(0).getNombreCompleto());
        }
    }

    @Nested
    @DisplayName("findAllCardsOfUser")
    class FindAllCardsOfUserTest {
        
        @Test
        @DisplayName("Should return a card list")
        void shouldReturnCardList() {
            String token = "token";

            TarjetaCreditoJpaEntity tarjetaJpaEntity = new TarjetaCreditoJpaEntity(1L, "1234567890123456", "12/12", "123", "Jhon Doe");
            
            List<TarjetaCreditoJpaEntity> tarjetaJpaEntityList = List.of(tarjetaJpaEntity);
            List<TarjetaCredito> tarjetaList = tarjetaJpaEntityList.stream().map(TarjetaCreditoMapper.getInstance()::fromTarjetaCreditoJpaEntityToTarjetaCredito).toList();
            
            when(tarjetaCreditoJpaDao.findAllOfUser(token)).thenReturn(tarjetaJpaEntityList);

            List<TarjetaCredito> result = tarjetaCreditoRepository.findAllOfUser(token);

            assertEquals(tarjetaList.get(0).getId(), result.get(0).getId());
            assertEquals(tarjetaList.get(0).getNumeroTarjeta(), result.get(0).getNumeroTarjeta());
            assertEquals(tarjetaList.get(0).getFechaCaducidad(), result.get(0).getFechaCaducidad());
            assertEquals(tarjetaList.get(0).getCvv(), result.get(0).getCvv());
            assertEquals(tarjetaList.get(0).getNombreCompleto(), result.get(0).getNombreCompleto());
        }
    }

    @Nested
    @DisplayName("card belongs to user")
    class CardBelongsToUserTest {
        
        @Test
        @DisplayName("Should return true if card belongs to user")
        void shouldReturnTrue() {
            Long cardId = 1L;
            String token = "token";

            when(tarjetaCreditoJpaDao.tarjetaPerteneceAUsuario(cardId, token)).thenReturn(true);

            boolean result = tarjetaCreditoRepository.tarjetaPerteneceAUsuario(cardId, token);

            assertEquals(true, result);
        }
    }
}
