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

public class OrigenDtoTest {
    
    @Test
    @DisplayName("Create OrigenDto with valid data should not throw ValidationException")
    void createOrigenDto_WithValidData_ShouldNotThrowValidationException() {
        OrigenDto origenDto = new OrigenDto("1234567890123456", "12/24", "123", "name");

        assertDoesNotThrow(() -> DtoValidator.validate(origenDto));
    }

    static Stream<OrigenDto> invalidOrigenes() {
        return Stream.of(
            new OrigenDto(null, "12/24", "123", "name"),
            new OrigenDto("", "12/24", "123", "name"),
            new OrigenDto(" ", "12/24", "123", "name"),
            new OrigenDto("name", null, "123", "name"),
            new OrigenDto("name", "", "123", "name"),
            new OrigenDto("name", " ", "123", "name"),
            new OrigenDto("name", "12/24", null, "name"),
            new OrigenDto("name", "12/24", "", "name"),
            new OrigenDto("name", "12/24", " ", "name"),
            new OrigenDto("name", "12/24", "123", null),
            new OrigenDto("name", "12/24", "123", ""),
            new OrigenDto("name", "12/24", "123", " ")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidOrigenes")
    @DisplayName("Create OrigenDto with invalid data should throw ValidationException")
    void createOrigenDto_WithInvalidData_ShouldThrowValidationException(OrigenDto origenDto) {
        assertThrows(ValidationException.class, () -> DtoValidator.validate(origenDto));
    }
}
