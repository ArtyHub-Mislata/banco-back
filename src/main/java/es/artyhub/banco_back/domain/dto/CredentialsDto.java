package es.artyhub.banco_back.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record CredentialsDto(
        @NotBlank(message = "Username cannot be void")
        String username,
        @NotBlank(message = "Password cannot be void")
        String password
) {

}
