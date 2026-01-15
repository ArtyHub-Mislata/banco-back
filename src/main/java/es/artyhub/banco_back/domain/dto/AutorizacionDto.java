package es.artyhub.banco_back.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record AutorizacionDto(
    @NotBlank
    String login,
    
    @NotBlank
    String api_token) {
}
