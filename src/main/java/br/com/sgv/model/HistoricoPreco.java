package br.com.sgv.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Pablo Rangel <pablo.rangel@gmail.com>
 * @date 11/05/2021
 * @brief  class HistoricoPreco
 */
@Entity
public class HistoricoPreco implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long idHistoricoPreco;
    private float valor;
    private Date data;
    @OneToOne
    private Produto produto;

    public long getIdHistoricoPreco() {
        return idHistoricoPreco;
    }

    public void setIdHistoricoPreco(long idHistoricoPreco) {
        this.idHistoricoPreco = idHistoricoPreco;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
}
