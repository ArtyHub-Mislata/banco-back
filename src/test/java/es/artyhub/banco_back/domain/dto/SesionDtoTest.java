package es.artyhub.banco_back.domain.dto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import es.artyhub.banco_back.domain.exception.ValidationException;
import es.artyhub.banco_back.domain.validation.DtoValidator;

public class SesionDtoTest {
    @Test
    @DisplayName("Create SesionDto with valid data should not throw ValidationException")
    void createSesionDto_WithValidData_ShouldNotThrowValidationException() {
        SesionDto sesionDto = new SesionDto("token", 1L, new Date());

        assertDoesNotThrow(() -> DtoValidator.validate(sesionDto));
    }

    static Stream<SesionDto> invalidSesiones() {
        return Stream.of(
            new SesionDto(null, 1L, new Date()),
            new SesionDto("", 1L, new Date()),
            new SesionDto(" ", 1L, new Date()),
            new SesionDto("name", null, new Date()),
            new SesionDto("name", 1L, null),
            new SesionDto(null, null, null)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidSesiones")
    @DisplayName("Create SesionDto with invalid data should throw ValidationException")
    void createSesionDto_WithInvalidData_ShouldThrowValidationException(SesionDto sesionDto) {
        assertThrows(ValidationException.class, () -> DtoValidator.validate(sesionDto));
    }
}
