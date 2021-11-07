package br.com.fiap.xcave.global.controller;

import br.com.fiap.xcave.global.entity.Produto;
import br.com.fiap.xcave.global.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping("/produto")
    public Produto salvar(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @GetMapping("/produto")
    public List<Produto> obterTodos() {
        return produtoRepository.getAll();
    }

    @GetMapping("/produto/{id}")
    public Produto getProdutoById(@PathVariable("id") String id) {
        return produtoRepository.getProdutoById(id);
    }

    @GetMapping("/produto/{mercadoId}/{location}")
    public Produto getProdutoByMercadoIdAndLocation(String mercadoId, String location) {
        return produtoRepository.getProdutoByMercadoIdAndLocation(mercadoId, location);
    }

    @DeleteMapping("/produto/{id}")
    public String deletar(@PathVariable("id") String id) {
        return produtoRepository.delete(id);
    }

    @PutMapping("/produto/{id}")
    public String atualizar(@PathVariable("id") String id, @RequestBody Produto produto) {
        return produtoRepository.update(id, produto);
    }

    public void atualizarDonating(@PathVariable("id") String id, @RequestBody Produto produto) {
        produtoRepository.updateDonating(id, produto);
    }

}
