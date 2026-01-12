package es.artyhub.banco_back.domain.dto;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PagoDto(

    @Positive BigDecimal importe,
    String concepto) {
}
