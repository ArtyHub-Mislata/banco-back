package es.artyhub.banco_back.persistence.repository.mapper;

import es.artyhub.banco_back.domain.dto.CuentaDto;
import es.artyhub.banco_back.persistence.dao.jpa.entity.CuentaJpaEntity;

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

    public CuentaDto fromCuentaJpaEntityToCuentaDto(CuentaJpaEntity cuenta) {
        if (cuenta == null) {
            return null;
        }
        return new CuentaDto(
            cuenta.getId(), 
            cuenta.getSaldo(), 
            cuenta.getIban());
    }

    public CuentaJpaEntity fromCuentaDtoToCuentaJpaEntity(CuentaDto cuentaDto) {
        if (cuentaDto == null) {
            return null;
        }
        return new CuentaJpaEntity(
            cuentaDto.getId(), 
            cuentaDto.getSaldo(), 
            cuentaDto.getIban());
    }
}
