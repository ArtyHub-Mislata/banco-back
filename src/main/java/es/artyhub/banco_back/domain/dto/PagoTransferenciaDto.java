package es.artyhub.banco_back.domain.dto;

import jakarta.validation.constraints.NotNull;

public record PagoTransferenciaDto(
        @NotNull
        AutorizacionDto autorizacion,
        @NotNull
        OrigenTransferenciaDto origen,
        @NotNull
        DestinoDto destino,
        @NotNull
        PagoDto pago
) {
}
