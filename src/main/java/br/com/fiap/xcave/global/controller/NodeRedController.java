package br.com.fiap.xcave.global.controller;

import br.com.fiap.xcave.global.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class NodeRedController {

    @Autowired
    ProdutoController produtoController;

    HashMap<String, String> produtos = new HashMap<String, String>();

    @PostMapping("/status/{mercadoId}/{location}")
    public String atualizaStatus(@PathVariable("mercadoId") String mercadoId, @PathVariable("location") String location, @RequestBody String aviso) {

        String chave = mercadoId.concat(location);

        if (!produtos.containsKey(chave)) {
            produtos.put(chave, "");
        }

        if (!aviso.equals(produtos.get(chave))) {

            produtos.put(chave, aviso);
            Produto produto = produtoController.getProdutoByMercadoIdAndLocation(mercadoId, location);

            if (produto.getId() != null) {

                if (aviso.equals("Disponível")) {
                    produto.setDonating(true);
                    produtoController.atualizarDonating(produto.getId(), produto);
                    return produto.getId();

                } else if (aviso.equals("Indisponível")) {
                    produto.setDonating(false);
                    produtoController.atualizarDonating(produto.getId(), produto);
                    return produto.getId();

                } else {
                    return "Condição inválida";
                }

            } else {
                return "Produto não encontrado";
            }
        }
        return "Produto já está nessa condição";
    }
}