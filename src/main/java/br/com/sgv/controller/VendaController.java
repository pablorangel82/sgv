package br.com.sgv.controller;

import br.com.sgv.model.Item;
import br.com.sgv.model.Venda;
import br.com.sgv.repository.ProdutoRepository;
import br.com.sgv.repository.VendaRepository;
import java.util.Iterator;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @GetMapping("/gerenciarVendas")
    public String listarVendas(Model model) {
        model.addAttribute("listaVendas", vendaRepository.findAll());
        return "gerenciar_vendas";
    }

    @GetMapping("/novaVenda")
    public String novaVenda(Model model) {
        venda = new Venda();
        model.addAttribute("listaProdutos", produtoRepository.findAll());
        model.addAttribute("venda", venda);
        model.addAttribute("item", new Item());
        return "editar_venda";
    }

    @GetMapping("/editarVenda/{id}")
    public String editarVenda(@PathVariable("id") long idVenda, Model model) {
        Optional<Venda> busca = vendaRepository.findById(idVenda);
        venda = busca.get();
        model.addAttribute("venda", venda);
        model.addAttribute("item", new Item());
        model.addAttribute("listaProdutos", produtoRepository.findAll());
        return "editar_venda";
    }

    @PostMapping("/salvarVenda")
    public String salvarVenda(@Valid Venda venda, BindingResult result) {
        if (result.hasErrors()) {
            return "editar_venda";
        }
        this.venda.setDataVenda(venda.getDataVenda());
        vendaRepository.save(this.venda);
        return "redirect:/gerenciarVendas";
    }
    
    @PostMapping("/adicionarItem")
    public String adicionarItem(@Valid Item item, Model model, BindingResult result) {
        if (result.hasErrors()) {
            return "editar_venda";
        }
        if (item.getProduto() != null){
            venda.adicionarItem(item);
            item.setVenda(venda);
            vendaRepository.save(venda);
        }
        String url = "redirect:/editarVenda/"+venda.getIdVenda();
        return url;
    }

    @GetMapping("/removerItem/{id}")
    public String removerItem(@PathVariable("id") long idItem) {
        Item aux = null;
        Iterator<Item> iterator = venda.getListaItens().iterator();
        while (iterator.hasNext()){
            Item i = iterator.next();
            if (i.getIdItem() == idItem){
                aux = i;
                break;
            }
        }
        if (aux != null){
            venda.removerItem(aux);
            aux.setVenda(null);
            vendaRepository.save(venda);
        }
        String url = "redirect:/editarVenda/"+venda.getIdVenda();
        return url;
    }
    
    @GetMapping("/excluirVenda/{id}")
    public String excluirVenda(@PathVariable("id") long idVenda) {
        vendaRepository.deleteById(idVenda);
        return "redirect:/gerenciarVendas";
    }
}
