package br.com.sgv.model;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

/**
 *
 * @author Pablo Rangel <pablo.rangel@gmail.com>
 * @date 12/05/2021
 * @brief  class PessoaJuridica
 */
@Entity
public class PessoaJuridica extends Pessoa{
    @Size(min=1,message = "O CNPJ precisa ser válido.")
    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
}
