package br.com.fiap.xcave.global.controller;

import br.com.fiap.xcave.global.entity.Usuario;
import br.com.fiap.xcave.global.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/usuario")
    public Usuario salvar(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @GetMapping("/usuario")
    public List<Usuario> obterTodos() {
        return usuarioRepository.getAll();
    }

    @GetMapping("/usuario/{id}")
    public Usuario getUsuarioById(@PathVariable("id") String id) {
        return usuarioRepository.getUsuarioById(id);
    }

    @GetMapping("/usuario/login/{email}/{senha}")
    public Usuario getUsuarioByLogin(@PathVariable("email") String email, @PathVariable("senha") String senha) {
        return usuarioRepository.getUsuarioByLogin(email, senha);
    }

    @DeleteMapping("/usuario/{id}")
    public String deletar(@PathVariable("id") String id) {
        return usuarioRepository.delete(id);
    }

    @PutMapping("/usuario/{id}")
    public String atualizar(@PathVariable("id") String id, @RequestBody Usuario usuario) {
        return usuarioRepository.update(id, usuario);
    }

    @GetMapping("/")
    public String demo() {
        return "xcave";
    }
}