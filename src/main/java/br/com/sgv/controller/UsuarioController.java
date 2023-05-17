package br.com.sgv.controller;

import br.com.sgv.model.Usuario;
import br.com.sgv.repository.UsuarioRepository;
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
 * @brief class UsuarioController
 */
@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios")
    public String listar(Model model) {
        model.addAttribute("listaUsuarios", usuarioRepository.findAll());
        return "gerenciar_usuarios";
    }

    @GetMapping("/usuarios/novo")
    public String novo(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "editar_usuario";
    }

    @GetMapping("/usuarios/{id}")
    public String editar(@PathVariable("id") long id, Model model) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        model.addAttribute("usuario", usuario.get());
        return "editar_usuario";
    }

    @PostMapping("/usuarios")
    public String salvar(@Valid Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            return "editar_usuario";
        }
        usuarioRepository.save(usuario);
        return "redirect:/usuarios";
    }

    @DeleteMapping("/usuarios/{id}")
    public String excluir(@PathVariable("id") long id) {
        usuarioRepository.deleteById(id);
        return "redirect:/usuarios";
    }
}
