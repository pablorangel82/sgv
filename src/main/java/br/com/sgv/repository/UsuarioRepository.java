package br.com.sgv.repository;


import br.com.sgv.model.Usuario;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Pablo Rangel <pablo.rangel@gmail.com>
 */
public interface UsuarioRepository extends CrudRepository<Usuario,Long>{
    
}
