package es.artyhub.banco_back.domain.mapper;

import es.artyhub.banco_back.domain.dto.CuentaDto;
import es.artyhub.banco_back.domain.model.Cuenta;

public class CuentaMapper {
    private static CuentaMapper instance;

    public CuentaMapper() {
    }

    public static CuentaMapper getInstance() {
        if (instance == null) {
            instance = new CuentaMapper();
        }
        return instance;
    }

    public CuentaDto fromCuentaToCuentaDto(Cuenta cuenta) {
        if (cuenta == null) {
            return null;
        }
        return new CuentaDto(
            cuenta.getId(), 
            cuenta.getSaldo(), 
            cuenta.getIban());
    }

    public Cuenta fromCuentaDtoToCuenta(CuentaDto cuentaDto) {
        if (cuentaDto == null) {
            return null;
        }
        return new Cuenta(
            cuentaDto.getId(), 
            cuentaDto.getSaldo(), 
            cuentaDto.getIban());
    }
}
