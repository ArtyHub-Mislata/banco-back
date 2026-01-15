package es.artyhub.banco_back.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record OrigenDto(
        @NotBlank(message = "El número de tarjeta no puede ser vacío")
        @Size(min = 16, max = 16, message = "El número de tarjeta debe tener 16 caracteres")
        String numeroTarjeta,

        @NotBlank 
        String fechaCaducidad,

        @NotBlank 
        String cvc,

        @NotBlank 
        String nombreCompleto) {
    
}
