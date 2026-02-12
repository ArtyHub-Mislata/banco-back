package es.artyhub.banco_back.domain.dto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import es.artyhub.banco_back.domain.exception.ValidationException;
import es.artyhub.banco_back.domain.validation.DtoValidator;

public class OrigenTransferenciaDtoTest {
    @Test
    @DisplayName("Create OrigenTransferenciaDto with valid data should not throw ValidationException")
    void createOrigenTransferenciaDto_WithValidData_ShouldNotThrowValidationException() {
        OrigenTransferenciaDto origenTransferenciaDto = new OrigenTransferenciaDto("ES1234567890123456789012");

        assertDoesNotThrow(() -> DtoValidator.validate(origenTransferenciaDto));
    }

    static Stream<OrigenTransferenciaDto> invalidOrigenTransferencias() {
        return Stream.of(
            new OrigenTransferenciaDto(null)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidOrigenTransferencias")
    @DisplayName("Create OrigenTransferenciaDto with invalid data should throw ValidationException")
    void createOrigenTransferenciaDto_WithInvalidData_ShouldThrowValidationException(OrigenTransferenciaDto origenTransferenciaDto) {
        assertThrows(ValidationException.class, () -> DtoValidator.validate(origenTransferenciaDto));
    }
}
