package br.com.sgv.repository;

import br.com.sgv.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

/**
 *
 * @author Pablo Rangel <pablo.rangel@gmail.com>
 */
public interface LoginRepository extends Repository<Usuario, Long>{
    @Query("SELECT U.login, U.senha FROM Usuario U WHERE U.login = ?1 and U.senha = ?2")
    public String efetuarLogin(String login, String senha);
}
