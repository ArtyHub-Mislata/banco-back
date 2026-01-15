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

public class PagoDtoTest {
    
    @Test
    @DisplayName("Create PagoDto with valid data should not throw ValidationException")
    void createPagoDto_WithValidData_ShouldNotThrowValidationException() {
        PagoDto pagoDto = new PagoDto(new BigDecimal(1), "concepto");

        assertDoesNotThrow(() -> DtoValidator.validate(pagoDto));
    }

    static Stream<PagoDto> invalidPagos() {
        return Stream.of(
            new PagoDto(null, "concepto"),
            new PagoDto(new BigDecimal(0), "concepto"),
            new PagoDto(new BigDecimal(-1), "concepto"),
            new PagoDto(new BigDecimal(1), null)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidPagos")
    @DisplayName("Create PagoDto with invalid data should throw ValidationException")
    void createPagoDto_WithInvalidData_ShouldThrowValidationException(PagoDto pagoDto) {
        assertThrows(ValidationException.class, () -> DtoValidator.validate(pagoDto));
    }
}
