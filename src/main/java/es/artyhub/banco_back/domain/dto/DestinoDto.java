package es.artyhub.banco_back.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DestinoDto(
    @NotBlank(message = "El número de cuenta no puede ser vacío")
    @Size(min = 24, max = 24, message = "El número de cuenta debe tener 24 caracteres")
    String numeroCuenta
) {
}
