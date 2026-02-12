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

public class CredentialsDtoTest {
    @Test
    @DisplayName("Create CredentialsDto with valid data should not throw ValidationException")
    void createCredentialsDto_WithValidData_ShouldNotThrowValidationException() {
        CredentialsDto credentialsDto = new CredentialsDto("login", "api_token");

        assertDoesNotThrow(() -> DtoValidator.validate(credentialsDto));
    }

    static Stream<CredentialsDto> invalidCredentials() {
        return Stream.of(
            new CredentialsDto(null, "api_token"),
            new CredentialsDto("", "api_token"),
            new CredentialsDto(" ", "api_token"),
            new CredentialsDto("name", null),
            new CredentialsDto("name", ""),
            new CredentialsDto("name", " "),
            new CredentialsDto(null, null)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidCredentials")
    @DisplayName("Create CredentialsDto with invalid data should throw ValidationException")
    void createCredentialsDto_WithInvalidData_ShouldThrowValidationException(CredentialsDto credentialsDto) {
        assertThrows(ValidationException.class, () -> DtoValidator.validate(credentialsDto));
    }
}
