package br.com.sgv.controller;

import br.com.sgv.model.PessoaJuridica;
import br.com.sgv.repository.PessoaJuridicaRepository;
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
 * @brief class PessoaJuridicaController
 */
@Controller
public class PessoaJuridicaController {

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    @GetMapping("/pessoas-juridicas")
    public String listar(Model model) {
        model.addAttribute("listaPessoasJuridicas", pessoaJuridicaRepository.findAll());
        return "gerenciar_pessoas_juridicas";
    }

    @GetMapping("/pessoas-juridicas/novo")
    public String novo(Model model) {
        model.addAttribute("pessoaJuridica", new PessoaJuridica());
        return "editar_pessoa_juridica";
    }

    @GetMapping("/pessoas-juridicas/{id}")
    public String editar(@PathVariable("id") long id, Model model) {
        Optional<PessoaJuridica> pessoaJuridica = pessoaJuridicaRepository.findById(id);
        model.addAttribute("pessoaJuridica", pessoaJuridica.get());
        return "editar_pessoa_juridica";
    }

    @PostMapping("/pessoas-juridicas")
    public String salvar(@Valid PessoaJuridica pessoaJuridica, BindingResult result) {
        if (result.hasErrors()) {
            return "editar_pessoa_juridica";
        }
        pessoaJuridicaRepository.save(pessoaJuridica);
        return "redirect:/pessoas-juridicas";
    }

    @DeleteMapping("/pessoas-juridicas/{id}")
    public String excluir(@PathVariable("id") long id) {
        pessoaJuridicaRepository.deleteById(id);
        return "redirect:/pessoas-juridicas";
    }
}
