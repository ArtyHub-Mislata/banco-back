package es.artyhub.banco_back.domain.dto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import es.artyhub.banco_back.domain.exception.ValidationException;
import es.artyhub.banco_back.domain.validation.DtoValidator;

public class PagoTarjetaDtoTest {
    
    @Test
    @DisplayName("Create PagoTarjetaDto with valid data should not throw ValidationException")
    void createPagoTarjetaDto_WithValidData_ShouldNotThrowValidationException() {
        AutorizacionDto autorizacionDto = new AutorizacionDto("login", "api_token");
        OrigenDto origenDto = new OrigenDto("1234567890123456", "12/25", "123", "nombre");
        DestinoDto destinoDto = new DestinoDto("123456789012345678901234");
        PagoDto pagoDto = new PagoDto(new BigDecimal(1), "concepto");
        
        PagoTarjetaDto pagoTarjetaDto = new PagoTarjetaDto(
            autorizacionDto,
            origenDto,
            destinoDto,
            pagoDto
        );

        assertDoesNotThrow(() -> DtoValidator.validate(pagoTarjetaDto));
    }

    static Stream<PagoTarjetaDto> invalidPagoTarjetaDtos() {
        AutorizacionDto autorizacionDto = new AutorizacionDto("login", "api_token");
        OrigenDto origenDto = new OrigenDto("1234567890123456", "12/25", "123", "nombre");
        DestinoDto destinoDto = new DestinoDto("123456789012345678901234");
        PagoDto pagoDto = new PagoDto(new BigDecimal(1), "concepto");
        return Stream.of(
            new PagoTarjetaDto(null, origenDto, destinoDto, pagoDto),
            new PagoTarjetaDto(autorizacionDto, null, destinoDto, pagoDto),
            new PagoTarjetaDto(autorizacionDto, origenDto, null, pagoDto),
            new PagoTarjetaDto(autorizacionDto, origenDto, destinoDto, null)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidPagoTarjetaDtos")
    @DisplayName("Create PagoTarjetaDto with invalid data should throw ValidationException")
    void createPagoTarjetaDto_WithInvalidData_ShouldThrowValidationException(PagoTarjetaDto pagoTarjetaDto) {
        assertThrows(ValidationException.class, () -> DtoValidator.validate(pagoTarjetaDto));
    }
}
