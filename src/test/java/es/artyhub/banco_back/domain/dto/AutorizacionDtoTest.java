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

public class AutorizacionDtoTest {
    
    @Test
    @DisplayName("Create AutorizacionDto with valid data should not throw ValidationException")
    void createAutorizacionDto_WithValidData_ShouldNotThrowValidationException() {
        AutorizacionDto autorizacionDto = new AutorizacionDto("login", "api_token");

        assertDoesNotThrow(() -> DtoValidator.validate(autorizacionDto));
    }

    static Stream<AutorizacionDto> invalidAutorizaciones() {
        return Stream.of(
            new AutorizacionDto(null, "api_token"),
            new AutorizacionDto("", "api_token"),
            new AutorizacionDto(" ", "api_token"),
            new AutorizacionDto("name", null),
            new AutorizacionDto("name", ""),
            new AutorizacionDto("name", " ")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidAutorizaciones")
    @DisplayName("Create AutorizacionDto with invalid data should throw ValidationException")
    void createAutorizacionDto_WithInvalidData_ShouldThrowValidationException(AutorizacionDto autorizacionDto) {
        assertThrows(ValidationException.class, () -> DtoValidator.validate(autorizacionDto));
    }
}
