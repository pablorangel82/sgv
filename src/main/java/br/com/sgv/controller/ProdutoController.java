package br.com.sgv.controller;

import br.com.sgv.model.Produto;
import br.com.sgv.repository.ProdutoRepository;
import jakarta.validation.Valid;
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
 * @brief class ProdutoController
 */
@Controller
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/produtos")
    public String listar(Model model) {
        model.addAttribute("listaProdutos", produtoRepository.findAll());
        return "gerenciar_produtos";
    }

    @GetMapping("/produtos/novo")
    public String novo(Model model) {
        model.addAttribute("produto", new Produto());
        return "editar_produto";
    }

    @GetMapping("/produtos/{id}")
    public String editar(@PathVariable("id") long id, Model model) {
        Optional<Produto> produto = produtoRepository.findById(id);
        model.addAttribute("produto", produto.get());
        return "editar_produto";
    }

    @PostMapping("/produtos")
    public String salvar(@Valid Produto produto, BindingResult result) {
        if (result.hasErrors()) {
            return "editar_produto";
        }
        produtoRepository.save(produto);
        return "redirect:/produtos";
    }

    @DeleteMapping("/produtos/{id}")
    public String excluir(@PathVariable("id") long id) {
        produtoRepository.deleteById(id);
        return "redirect:/produtos";
    }
}
