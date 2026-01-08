package es.artyhub.banco_back.domain.dto;

public record PagoTarjetaDto(
    AutorizacionDto autorizacion,
    OrigenDto origen,
    DestinoDto destino,
    PagoDto pago) {
}
