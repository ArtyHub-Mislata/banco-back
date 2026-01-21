package es.artyhub.banco_back.domain.service;

import java.util.List;

import es.artyhub.banco_back.domain.model.MovimientoBancario;

public interface MovimientoBancarioService {
    MovimientoBancario findById(Long id);
    MovimientoBancario findByImporte(Long importe);
    MovimientoBancario findByConcepto(String concepto);
    List<MovimientoBancario> findByCuentaId(Long cuenta_id);
    List<MovimientoBancario> findAll();
    MovimientoBancario saveMovimientoBancario(MovimientoBancario movimientoBancario, Long cuentaId);
    List<MovimientoBancario> findByTarjetaId(Long idTarjeta);
}
