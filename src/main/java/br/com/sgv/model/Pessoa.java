package br.com.sgv.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.Size;

/**
 *
 * @author Pablo Rangel <pablo.rangel@gmail.com>
 * @date 12/05/2021
 * @brief  class Pessoa
 */
@Entity
@Inheritance (strategy=InheritanceType.JOINED)
public abstract class Pessoa implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long idPessoa;
    @Size(min=1,message = "O nome precisa ser válido.")
    private String nome;

    public long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString(){
        return nome;
    }
    
}
