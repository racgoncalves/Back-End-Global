package br.com.fiap.xcave.global.controller;

import br.com.fiap.xcave.global.entity.Mercado;
import br.com.fiap.xcave.global.entity.Produto;
import br.com.fiap.xcave.global.entity.Usuario;
import br.com.fiap.xcave.global.repository.MercadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MercadoController {

    @Autowired
    private MercadoRepository mercadoRepository;

    @PostMapping("/mercado")
    public Mercado salvar(@RequestBody Mercado lixo) {
        return mercadoRepository.save(lixo);
    }

    @GetMapping("/mercado")
    public List<Mercado> obterTodos() {
        return mercadoRepository.getAll();
    }

    @GetMapping("/mercado/{id}")
    public Mercado getMercadoById(@PathVariable("id") String id) {
        return mercadoRepository.getMercadoById(id);
    }

    @GetMapping("/mercado/{id}/produtos")
    public List<Produto> getProdutosByMercadoId (@PathVariable("id") String id) {
        return mercadoRepository.getProdutosByMercadoId(id);
    }

    @GetMapping("/mercado/login/{email}/{senha}")
    public Mercado getMercadoByLogin(@PathVariable("email") String email, @PathVariable("senha") String senha) {
        return mercadoRepository.getMercadoByLogin(email, senha);
    }

    @DeleteMapping("/mercado/{id}")
    public String deletar(@PathVariable("id") String id) {
        return mercadoRepository.delete(id);
    }

    @PutMapping("/mercado/{id}")
    public String atualizar(@PathVariable("id") String id, @RequestBody Mercado mercado) {
        return mercadoRepository.update(id, mercado);
    }

}