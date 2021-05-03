package br.com.sgv.repository;

import org.springframework.data.repository.CrudRepository;
import br.com.sgv.model.Produto;

/**
 *
 * @author Pablo Rangel <pablo.rangel@gmail.com>
 */
public interface ProdutoRepository extends CrudRepository<Produto,Long>{
    
}
