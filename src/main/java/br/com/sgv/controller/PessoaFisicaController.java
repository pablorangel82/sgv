package br.com.sgv.controller;

import br.com.sgv.model.PessoaFisica;
import br.com.sgv.repository.PessoaFisicaRepository;
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
 * @brief class PessoaFisicaController
 */
@Controller
public class PessoaFisicaController {

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @GetMapping("/gerenciarPessoasFisicas")
    public String listarPessoasFisicas(Model model) {
        model.addAttribute("listaPessoasFisicas", pessoaFisicaRepository.findAll());
        return "gerenciar_pessoas_fisicas";
    }

    @GetMapping("/novaPessoaFisica")
    public String novaPessoaFisica(Model model) {
        model.addAttribute("pessoaFisica", new PessoaFisica());
        return "editar_pessoa_fisica";
    }

    @GetMapping("/editarPessoaFisica/{id}")
    public String editarPessoaFisica(@PathVariable("id") long idPessoa, Model model) {
        Optional<PessoaFisica> pessoaFisica = pessoaFisicaRepository.findById(idPessoa);
        model.addAttribute("pessoaFisica", pessoaFisica.get());
        return "editar_pessoa_fisica";
    }

    @PostMapping("/salvarPessoaFisica")
    public String salvarPessoaFisica(@Valid PessoaFisica pessoaFisica, BindingResult result) {
        if (result.hasErrors()) {
            return "editar_pessoa_fisica";
        }
        pessoaFisicaRepository.save(pessoaFisica);
        return "redirect:/gerenciarPessoasFisicas";
    }

    @GetMapping("/excluirPessoaFisica/{id}")
    public String excluirPessoaFisica(@PathVariable("id") long idPessoa) {
        pessoaFisicaRepository.deleteById(idPessoa);
        return "redirect:/gerenciarPessoasFisicas";
    }
}
