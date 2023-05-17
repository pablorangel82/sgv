package br.com.sgv.controller;

import br.com.sgv.model.Item;
import br.com.sgv.model.Venda;
import br.com.sgv.repository.ProdutoRepository;
import br.com.sgv.repository.VendaRepository;
import jakarta.validation.Valid;
import java.util.Iterator;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Pablo Rangel <pablo.rangel@gmail.com>
 * @date 22/04/2021
 * @brief class VendaController
 */
@Controller
public class VendaController {

    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    private Venda venda;

    @GetMapping("/vendas")
    public String listarVendas(Model model) {
        model.addAttribute("listaVendas", vendaRepository.findAll());
        return "gerenciar_vendas";
    }

    @GetMapping("/vendas/novo")
    public String novo(Model model) {
        venda = new Venda();
        vendaRepository.save(venda);
        model.addAttribute("listaProdutos", produtoRepository.findAll());
        model.addAttribute("venda", venda);
        model.addAttribute("item", new Item());
        return "editar_venda";
    }

    @GetMapping("/vendas/{id}")
    public String editar(@PathVariable("id") long idVenda, Model model) {
        Optional<Venda> busca = vendaRepository.findById(idVenda);
        venda = busca.get();
        model.addAttribute("venda", venda);
        model.addAttribute("item", new Item());
        model.addAttribute("listaProdutos", produtoRepository.findAll());
        return "editar_venda";
    }

    @PostMapping("/vendas")
    public String salvar(@Valid Venda venda, BindingResult result) {
        if (result.hasErrors()) {
            return "editar_venda";
        }
        this.venda.setDataVenda(venda.getDataVenda());
        vendaRepository.save(this.venda);
        return "redirect:/vendas";
    }
    
    @PostMapping("/vendas/itens")
    public String adicionarItem(@Valid Item item, Model model, BindingResult result) {
        if (result.hasErrors()) {
            return "editar_venda";
        }
        if (item.getProduto() != null){
            venda.adicionarItem(item);
            item.setVenda(venda);
            vendaRepository.save(venda);
        }
        String url = "redirect:/vendas/"+venda.getId();
        return url;
    }

    @GetMapping("/vendas/itens/{id}")
    public String removerItem(@PathVariable("id") long id) {
        Item aux = null;
        Iterator<Item> iterator = venda.getListaItens().iterator();
        while (iterator.hasNext()){
            Item i = iterator.next();
            if (i.getId() == id){
                aux = i;
                break;
            }
        }
        if (aux != null){
            venda.removerItem(aux);
            aux.setVenda(null);
            vendaRepository.save(venda);
        }
        String url = "redirect:/vendas/"+venda.getId();
        return url;
    }
    
    @DeleteMapping("/vendas/{id}")
    public String excluir(@PathVariable("id") long id) {
        vendaRepository.deleteById(id);
        return "redirect:/vendas";
    }
}
