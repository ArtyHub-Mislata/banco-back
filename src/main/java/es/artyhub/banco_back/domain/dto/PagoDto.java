package es.artyhub.banco_back.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PagoDto(

    @Positive 
    @NotNull
    BigDecimal importe,
    String concepto) {
}
