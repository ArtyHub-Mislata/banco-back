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

public class DestinoDtoTest {
    
    @Test
    @DisplayName("Create DestinoDto with valid data should not throw ValidationException")
    void createDestinoDto_WithValidData_ShouldNotThrowValidationException() {
        DestinoDto destinoDto = new DestinoDto("1234567890123456");

        assertDoesNotThrow(() -> DtoValidator.validate(destinoDto));
    }

    static Stream<DestinoDto> invalidDestinos() {
        return Stream.of(
            new DestinoDto(null),
            new DestinoDto(""),
            new DestinoDto(" ")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidDestinos")
    @DisplayName("Create DestinoDto with invalid data should throw ValidationException")
    void createDestinoDto_WithInvalidData_ShouldThrowValidationException(DestinoDto destinoDto) {
        assertThrows(ValidationException.class, () -> DtoValidator.validate(destinoDto));
    }
}
