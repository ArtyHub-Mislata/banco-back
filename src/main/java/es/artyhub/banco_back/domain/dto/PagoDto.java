package es.artyhub.banco_back.domain.dto;

import java.math.BigDecimal;

public record PagoDto(
    BigDecimal importe,
    String concepto) {
}
