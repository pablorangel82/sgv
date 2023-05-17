package br.com.sgv.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Pablo Rangel <pablo.rangel@gmail.com>
 * @date 06/05/2021
 * @brief class Item
 */
@Entity
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    private Produto produto;
    @OneToOne
    private Venda venda;
    private int quantidade;

    public Item(){
        produto = new Produto();
        quantidade = 1;
    }
    
    @Override
    public String toString(){
        return produto.getNome() + " " + quantidade;
    }

}
