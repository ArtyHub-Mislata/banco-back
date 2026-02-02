package es.artyhub.banco_back.domain.dto;

import jakarta.validation.constraints.NotNull;

public record OrigenTransferenciaDto(
        @NotNull
        String iban
) {
}
