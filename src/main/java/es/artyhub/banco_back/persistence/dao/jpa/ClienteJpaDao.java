package es.artyhub.banco_back.persistence.dao.jpa;

import es.artyhub.banco_back.domain.dto.AutorizacionDto;
import es.artyhub.banco_back.persistence.dao.jpa.entity.ClienteJpaEntity;

public interface ClienteJpaDao extends GenericJpaDao<ClienteJpaEntity>{
    ClienteJpaEntity findByLogin(String login);
    Boolean userAndApiTokenCorrect(AutorizacionDto autorizacionDto);
}
