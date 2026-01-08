package es.artyhub.banco_back.domain.dto;

public record OrigenDto(
    String numeroTarjeta,
    String fechaCaducidad,
    String cvc,
    String nombreCompleto) {
    
}
