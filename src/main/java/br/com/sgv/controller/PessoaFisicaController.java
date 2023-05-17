package br.com.sgv.controller;

import br.com.sgv.model.PessoaFisica;
import br.com.sgv.repository.PessoaFisicaRepository;
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
 * @brief class PessoaFisicaController
 */
@Controller
public class PessoaFisicaController {

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @GetMapping("/pessoas-fisicas")
    public String listar(Model model) {
        model.addAttribute("listaPessoasFisicas", pessoaFisicaRepository.findAll());
        return "gerenciar_pessoas_fisicas";
    }

    @GetMapping("/pessoas-fisicas/novo")
    public String novo(Model model) {
        model.addAttribute("pessoaFisica", new PessoaFisica());
        return "editar_pessoa_fisica";
    }

    @GetMapping("/pessoas-fisicas/{id}")
    public String editar(@PathVariable("id") long id, Model model) {
        Optional<PessoaFisica> pessoaFisica = pessoaFisicaRepository.findById(id);
        model.addAttribute("pessoaFisica", pessoaFisica.get());
        return "editar_pessoa_fisica";
    }

    @PostMapping("/pessoas-fisicas")
    public String salvar(@Valid PessoaFisica pessoaFisica, BindingResult result) {
        if (result.hasErrors()) {
            return "editar_pessoa_fisica";
        }
        pessoaFisicaRepository.save(pessoaFisica);
        return "redirect:/pessoas-fisicas";
    }

    @DeleteMapping("/pessoas-fisicas/{id}")
    public String excluir(@PathVariable("id") long id) {
        pessoaFisicaRepository.deleteById(id);
        return "redirect:/pessoas-fisicas";
    }
}
