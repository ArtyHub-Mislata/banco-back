package es.artyhub.banco_back.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record AutorizacionDto(
    @NotBlank(message = "El login no puede ser nulo o vacío")
    String login,

    @NotBlank(message = "El api_token no puede ser nulo o vacío")
    String api_token) {
}
