package br.com.sgv.controller;

import br.com.sgv.model.HistoricoPreco;
import br.com.sgv.model.Produto;
import br.com.sgv.repository.ProdutoRepository;
import java.util.Date;
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
 * @brief class ProdutoController
 */
@Controller
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/gerenciarProdutos")
    public String listarProdutos(Model model) {
        model.addAttribute("listaProdutos", produtoRepository.findAll());
        return "gerenciar_produtos";
    }

    @GetMapping("/novoProduto")
    public String novoProduto(Model model) {
        model.addAttribute("produto", new Produto());
        return "editar_produto";
    }

    @GetMapping("/editarProduto/{id}")
    public String editarProduto(@PathVariable("id") long idProduto, Model model) {
        Optional<Produto> produto = produtoRepository.findById(idProduto);
        model.addAttribute("produto", produto.get());
        return "editar_produto";
    }

    @PostMapping("/salvarProduto")
    public String salvarProduto(@Valid Produto produto, BindingResult result) {
        if (result.hasErrors()) {
            return "editar_produto";
        }
        HistoricoPreco hp = new HistoricoPreco();
        hp.setData(new Date());
        hp.setValor(produto.getPreco());
        hp.setProduto(produto);
        produto.getListaHistoricoPreco().add(hp);
        produtoRepository.save(produto);
        return "redirect:/gerenciarProdutos";
    }

    @GetMapping("/excluirProduto/{id}")
    public String excluirProduto(@PathVariable("id") long idProduto) {
        produtoRepository.deleteById(idProduto);
        return "redirect:/gerenciarProdutos";
    }
}
