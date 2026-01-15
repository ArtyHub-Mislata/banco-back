package es.artyhub.banco_back.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PagoDto(

    @Positive 
    @NotBlank
    BigDecimal importe,

    @NotNull
    String concepto) {
}
