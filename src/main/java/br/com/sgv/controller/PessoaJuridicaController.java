package br.com.sgv.controller;

import br.com.sgv.model.PessoaJuridica;
import br.com.sgv.repository.PessoaJuridicaRepository;
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
 * @brief class PessoaJuridicaController
 */
@Controller
public class PessoaJuridicaController {

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    @GetMapping("/gerenciarPessoasJuridicas")
    public String listarPessoasJuridicas(Model model) {
        model.addAttribute("listaPessoasJuridicas", pessoaJuridicaRepository.findAll());
        return "gerenciar_pessoas_juridicas";
    }

    @GetMapping("/novaPessoaJuridica")
    public String novaPessoaJuridica(Model model) {
        model.addAttribute("pessoaJuridica", new PessoaJuridica());
        return "editar_pessoa_juridica";
    }

    @GetMapping("/editarPessoaJuridica/{id}")
    public String editarPessoaJuridica(@PathVariable("id") long idPessoa, Model model) {
        Optional<PessoaJuridica> pessoaJuridica = pessoaJuridicaRepository.findById(idPessoa);
        model.addAttribute("pessoaJuridica", pessoaJuridica.get());
        return "editar_pessoa_juridica";
    }

    @PostMapping("/salvarPessoaJuridica")
    public String salvarPessoaJuridica(@Valid PessoaJuridica pessoaJuridica, BindingResult result) {
        if (result.hasErrors()) {
            return "editar_pessoa_juridica";
        }
        pessoaJuridicaRepository.save(pessoaJuridica);
        return "redirect:/gerenciarPessoasJuridicas";
    }

    @GetMapping("/excluirPessoaJuridica/{id}")
    public String excluirPessoaJuridica(@PathVariable("id") long idPessoa) {
        pessoaJuridicaRepository.deleteById(idPessoa);
        return "redirect:/gerenciarPessoasJuridicas";
    }
}
