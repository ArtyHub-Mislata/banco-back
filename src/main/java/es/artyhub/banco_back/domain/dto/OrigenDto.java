package es.artyhub.banco_back.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record OrigenDto(
        @NotBlank(message = "El número de cuenta no puede ser vacío")
        @Size(min = 16, max = 16, message = "El número de cuenta debe tener 16 caracteres")
        String numeroTarjeta,
        @NotNull String fechaCaducidad,
        @NotNull String cvc,
        @NotNull String nombreCompleto) {
}
