package es.artyhub.banco_back.persistence.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.artyhub.banco_back.domain.enums.OrigenMovimiento;
import es.artyhub.banco_back.domain.enums.TipoMovimiento;
import es.artyhub.banco_back.domain.model.MovimientoBancario;
import es.artyhub.banco_back.domain.model.TarjetaCredito;
import es.artyhub.banco_back.persistence.dao.jpa.MovimientoBancarioJpaDao;
import es.artyhub.banco_back.persistence.dao.jpa.entity.MovimientoBancarioJpaEntity;
import es.artyhub.banco_back.persistence.dao.jpa.entity.TarjetaCreditoJpaEntity;
import es.artyhub.banco_back.persistence.repository.mapper.MovimientoBancarioMapper;

@ExtendWith(MockitoExtension.class)
public class MovimientoBancarioRepositoryImplTest {
    
    @Mock
    private MovimientoBancarioJpaDao movimientoBancarioJpaDao;

    @InjectMocks
    private MovimientoBancarioRepositoryImpl movimientoBancarioRepository;

    @Nested
    @DisplayName("findById")
    class FindByIdTest {
        
        @Test
        @DisplayName("Should return a movement")
        void shouldReturnMovement() {
            Long movimientoId = 1L;

            MovimientoBancarioJpaEntity movimientoJpaEntity = new MovimientoBancarioJpaEntity(movimientoId, TipoMovimiento.DEBE, OrigenMovimiento.TARJETABANCARIA, new TarjetaCreditoJpaEntity(1L, "1234567890123456", "12/12", "123", "Jhon Doe"), new Date(), new BigDecimal(100.00), "Concepto");

            when(movimientoBancarioJpaDao.findById(movimientoId)).thenReturn(movimientoJpaEntity);

            MovimientoBancario movimiento = MovimientoBancarioMapper.getInstance().fromMovimientoBancarioJpaEntityToMovimientoBancario(movimientoJpaEntity);

            MovimientoBancario result = movimientoBancarioRepository.findById(movimientoId);

            assertEquals(movimiento.getId(), result.getId());
            assertEquals(movimiento.getTipoMovimiento(), result.getTipoMovimiento());
            assertEquals(movimiento.getOrigenMovimiento(), result.getOrigenMovimiento());
            assertEquals(movimiento.getTarjetaCredito().getId(), result.getTarjetaCredito().getId());
            assertEquals(movimiento.getFecha(), result.getFecha());
            assertEquals(movimiento.getImporte(), result.getImporte());
            assertEquals(movimiento.getConcepto(), result.getConcepto());
        }
    }

    @Nested
    @DisplayName("findByImport")
    class FindByImportTest {
        
        @Test
        @DisplayName("Should return a movement list")
        void shouldReturnMovementList() {
            BigDecimal importe = new BigDecimal(100.00);

            MovimientoBancarioJpaEntity movimientoJpaEntity = new MovimientoBancarioJpaEntity(1L, TipoMovimiento.DEBE, OrigenMovimiento.TARJETABANCARIA, new TarjetaCreditoJpaEntity(1L, "1234567890123456", "12/12", "123", "Jhon Doe"), new Date(), importe, "Concepto");

            when(movimientoBancarioJpaDao.findByImporte(importe)).thenReturn(movimientoJpaEntity);
            
            MovimientoBancario movimiento = MovimientoBancarioMapper.getInstance().fromMovimientoBancarioJpaEntityToMovimientoBancario(movimientoJpaEntity);

            MovimientoBancario result = movimientoBancarioRepository.findByImporte(importe);

            assertEquals(movimiento.getId(), result.getId());
            assertEquals(movimiento.getTipoMovimiento(), result.getTipoMovimiento());
            assertEquals(movimiento.getOrigenMovimiento(), result.getOrigenMovimiento());
            assertEquals(movimiento.getTarjetaCredito().getId(), result.getTarjetaCredito().getId());
            assertEquals(movimiento.getFecha(), result.getFecha());
            assertEquals(movimiento.getImporte(), result.getImporte());
            assertEquals(movimiento.getConcepto(), result.getConcepto());
        }
    }

    @Nested
    @DisplayName("findByConcept")
    class FindByConceptTest {
        
        @Test
        @DisplayName("Should return a movement")
        void shouldReturnMovement() {
            String concepto = "Concepto";

            MovimientoBancarioJpaEntity movimientoJpaEntity = new MovimientoBancarioJpaEntity(1L, TipoMovimiento.DEBE, OrigenMovimiento.TARJETABANCARIA, new TarjetaCreditoJpaEntity(1L, "1234567890123456", "12/12", "123", "Jhon Doe"), new Date(), new BigDecimal(100.00), concepto);

            when(movimientoBancarioJpaDao.findByConcepto(concepto)).thenReturn(movimientoJpaEntity);

            MovimientoBancario movimiento = MovimientoBancarioMapper.getInstance().fromMovimientoBancarioJpaEntityToMovimientoBancario(movimientoJpaEntity);

            MovimientoBancario result = movimientoBancarioRepository.findByConcepto(concepto);

            assertEquals(movimiento.getId(), result.getId());
            assertEquals(movimiento.getTipoMovimiento(), result.getTipoMovimiento());
            assertEquals(movimiento.getOrigenMovimiento(), result.getOrigenMovimiento());
            assertEquals(movimiento.getTarjetaCredito().getId(), result.getTarjetaCredito().getId());
            assertEquals(movimiento.getFecha(), result.getFecha());
            assertEquals(movimiento.getImporte(), result.getImporte());
            assertEquals(movimiento.getConcepto(), result.getConcepto());
        }
    }

    @Nested
    @DisplayName("findByAccountId")
    class FindByAccountIdTest {
        
        @Test
        @DisplayName("Should return a movement list")
        void shouldReturnMovementList() {
            Long accountId = 1L;

            MovimientoBancarioJpaEntity movimientoJpaEntity = new MovimientoBancarioJpaEntity(1L, TipoMovimiento.DEBE, OrigenMovimiento.TARJETABANCARIA, new TarjetaCreditoJpaEntity(1L, "1234567890123456", "12/12", "123", "Jhon Doe"), new Date(), new BigDecimal(100.00), "Concepto");
            
            List<MovimientoBancarioJpaEntity> movimientoJpaEntityList = List.of(movimientoJpaEntity);
            List<MovimientoBancario> movimientoList = movimientoJpaEntityList.stream().map(MovimientoBancarioMapper.getInstance()::fromMovimientoBancarioJpaEntityToMovimientoBancario).toList();

            when(movimientoBancarioJpaDao.findByCuentaId(accountId)).thenReturn(movimientoJpaEntityList);

            List<MovimientoBancario> result = movimientoBancarioRepository.findByCuentaId(accountId);

            assertEquals(movimientoList.get(0).getId(), result.get(0).getId());
        }
    }

    @Nested
    @DisplayName("findAll")
    class FindAllTest {
        
        @Test
        @DisplayName("Should return a list of movements")
        void shouldReturnMovementList() {
            MovimientoBancarioJpaEntity movimientoJpaEntity = new MovimientoBancarioJpaEntity(1L, TipoMovimiento.DEBE, OrigenMovimiento.TARJETABANCARIA, new TarjetaCreditoJpaEntity(1L, "1234567890123456", "12/12", "123", "Jhon Doe"), new Date(), new BigDecimal(100.00), "Concepto");
            
            List<MovimientoBancarioJpaEntity> movimientoJpaEntityList = List.of(movimientoJpaEntity);
            List<MovimientoBancario> movimientoList = movimientoJpaEntityList.stream().map(MovimientoBancarioMapper.getInstance()::fromMovimientoBancarioJpaEntityToMovimientoBancario).toList();

            when(movimientoBancarioJpaDao.findAll()).thenReturn(movimientoJpaEntityList);

            List<MovimientoBancario> result = movimientoBancarioRepository.findAll();

            assertEquals(movimientoList.get(0).getId(), result.get(0).getId());
        }
    }

    @Nested
    @DisplayName("findAllMovesOfCard")
    class FindAllMovesOfCardTest {
        
        @Test
        @DisplayName("Should return a movement list")
        void shouldReturnMovementList() {
            Long cardId = 1L;

            MovimientoBancarioJpaEntity movimientoJpaEntity = new MovimientoBancarioJpaEntity(1L, TipoMovimiento.DEBE, OrigenMovimiento.TARJETABANCARIA, new TarjetaCreditoJpaEntity(cardId, "1234567890123456", "12/12", "123", "Jhon Doe"), new Date(), new BigDecimal(100.00), "Concepto");
            
            List<MovimientoBancarioJpaEntity> movimientoJpaEntityList = List.of(movimientoJpaEntity);
            List<MovimientoBancario> movimientoList = movimientoJpaEntityList.stream().map(MovimientoBancarioMapper.getInstance()::fromMovimientoBancarioJpaEntityToMovimientoBancario).toList();
            
            when(movimientoBancarioJpaDao.findByTarjetaId(cardId)).thenReturn(movimientoJpaEntityList);

            List<MovimientoBancario> result = movimientoBancarioRepository.findAllOfTarjeta(cardId);

            assertEquals(movimientoList.get(0).getId(), result.get(0).getId());
        }
    }

    @Nested
    @DisplayName("save")
    class SaveTest {
        
        @Test
        @DisplayName("Should insert a movement if id is null")
        void shouldInsertMovement() {
            MovimientoBancario movimiento = new MovimientoBancario(null, TipoMovimiento.DEBE, OrigenMovimiento.TARJETABANCARIA, new TarjetaCredito(1L, "1234567890123456", "12/12", "123", "Jhon Doe"), new Date(), new BigDecimal(100.00), "Concepto");
            Long accountId = 1L;

            MovimientoBancarioJpaEntity movimientoJpaEntity = MovimientoBancarioMapper.getInstance().fromMovimientoBancarioToMovimientoBancarioJpaEntity(movimiento);

            when(movimientoBancarioJpaDao.insert(any(MovimientoBancarioJpaEntity.class), any(Long.class))).thenReturn(movimientoJpaEntity);

            MovimientoBancario result = movimientoBancarioRepository.save(movimiento, accountId);

            assertEquals(movimiento.getId(), result.getId());
            assertEquals(movimiento.getTipoMovimiento(), result.getTipoMovimiento());
            assertEquals(movimiento.getOrigenMovimiento(), result.getOrigenMovimiento());
            assertEquals(movimiento.getTarjetaCredito().getId(), result.getTarjetaCredito().getId());
            assertEquals(movimiento.getFecha(), result.getFecha());
            assertEquals(movimiento.getImporte(), result.getImporte());
            assertEquals(movimiento.getConcepto(), result.getConcepto());
        }

        @Test
        @DisplayName("Should update a movement if id is not null")
        void shouldUpdateMovement() {
            MovimientoBancario movimiento = new MovimientoBancario(1L, TipoMovimiento.DEBE, OrigenMovimiento.TARJETABANCARIA, new TarjetaCredito(1L, "1234567890123456", "12/12", "123", "Jhon Doe"), new Date(), new BigDecimal(100.00), "Concepto");
            Long accountId = 1L;

            MovimientoBancarioJpaEntity movimientoJpaEntity = MovimientoBancarioMapper.getInstance().fromMovimientoBancarioToMovimientoBancarioJpaEntity(movimiento);

            when(movimientoBancarioJpaDao.update(any(MovimientoBancarioJpaEntity.class))).thenReturn(movimientoJpaEntity);

            MovimientoBancario result = movimientoBancarioRepository.save(movimiento, accountId);

            assertEquals(movimiento.getId(), result.getId());
            assertEquals(movimiento.getTipoMovimiento(), result.getTipoMovimiento());
            assertEquals(movimiento.getOrigenMovimiento(), result.getOrigenMovimiento());
            assertEquals(movimiento.getTarjetaCredito().getId(), result.getTarjetaCredito().getId());
            assertEquals(movimiento.getFecha(), result.getFecha());
            assertEquals(movimiento.getImporte(), result.getImporte());
            assertEquals(movimiento.getConcepto(), result.getConcepto());
        }
    }
}
