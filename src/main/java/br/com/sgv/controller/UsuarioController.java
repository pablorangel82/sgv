package br.com.sgv.controller;

import br.com.sgv.model.Usuario;
import br.com.sgv.repository.UsuarioRepository;
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
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/gerenciarUsuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("listaUsuarios", usuarioRepository.findAll());
        return "gerenciar_usuarios";
    }

    @GetMapping("/novoUsuario")
    public String novoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "editar_usuario";
    }

    @GetMapping("/editarUsuario/{id}")
    public String editarUsuario(@PathVariable("id") long idUsuario, Model model) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        model.addAttribute("usuario", usuario.get());
        return "editar_usuario";
    }

    @PostMapping("/salvarUsuario")
    public String salvarUsuario(@Valid Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            return "editar_usuario";
        }
        usuarioRepository.save(usuario);
        return "redirect:/gerenciarUsuarios";
    }

    @GetMapping("/excluirUsuario/{id}")
    public String excluirUsuario(@PathVariable("id") long idUsuario) {
        usuarioRepository.deleteById(idUsuario);
        return "redirect:/gerenciarUsuarios";
    }
}
