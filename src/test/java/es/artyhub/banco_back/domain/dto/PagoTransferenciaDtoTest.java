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

public class PagoTransferenciaDtoTest {
    @Test
    @DisplayName("Create PagoTransferenciaDto with valid data should not throw ValidationException")
    void createPagoTransferenciaDto_WithValidData_ShouldNotThrowValidationException() {
        PagoTransferenciaDto pagoTransferenciaDto = new PagoTransferenciaDto(
            new AutorizacionDto("login", "api_token"),
            new OrigenTransferenciaDto("ES1234567890123456789013"),
            new DestinoDto("ES1234567890123456789012"),
            new PagoDto(new BigDecimal(1), "concepto")
        );

        assertDoesNotThrow(() -> DtoValidator.validate(pagoTransferenciaDto));
    }

    static Stream<PagoTransferenciaDto> invalidPagoTransferencias() {
        return Stream.of(
            new PagoTransferenciaDto(null, new OrigenTransferenciaDto("ES1234567890123456789013"), new DestinoDto("ES1234567890123456789012"), new PagoDto(new BigDecimal(1), "concepto")),
            new PagoTransferenciaDto(new AutorizacionDto("login", "api_token"), null, new DestinoDto("ES1234567890123456789012"), new PagoDto(new BigDecimal(1), "concepto")),
            new PagoTransferenciaDto(new AutorizacionDto("login", "api_token"), new OrigenTransferenciaDto("ES1234567890123456789013"), null, new PagoDto(new BigDecimal(1), "concepto")),
            new PagoTransferenciaDto(new AutorizacionDto("login", "api_token"), new OrigenTransferenciaDto("ES1234567890123456789013"), new DestinoDto("ES1234567890123456789012"), null),
            new PagoTransferenciaDto(null, null, null, null)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidPagoTransferencias")
    @DisplayName("Create PagoTransferenciaDto with invalid data should throw ValidationException")
    void createPagoTransferenciaDto_WithInvalidData_ShouldThrowValidationException(PagoTransferenciaDto pagoTransferenciaDto) {
        assertThrows(ValidationException.class, () -> DtoValidator.validate(pagoTransferenciaDto));
    }
}
