package br.com.sgv.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author Pablo Rangel <pablo.rangel@gmail.com>
 * @date 06/05/2021
 * @brief  class Venda
 */
@Entity
@Getter
@Setter
public class Venda implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @OneToMany (mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> listaItens = new ArrayList();
    private Date dataVenda = new Date();

    public String getDataVendaFormatada() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataVenda);
    }
    
    public String getDataVenda() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        return formato.format(dataVenda);
    }

    public void setDataVenda(String dataVenda) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
        Date data = null;
        try {
            data = formato.parse(dataVenda);
        } catch (ParseException ex) {
            Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dataVenda = data;
    }
    
    public void adicionarItem(Item item){
        listaItens.add(item);
    }
    
    public void removerItem (Item item){
        listaItens.remove(item);
    }
    
    public float calcularTotal(){
        float soma = 0;
        for (Item i : listaItens){
            soma += i.getProduto().getPreco() * i.getQuantidade();
        }
        return soma;
    }
    
}
