package br.com.fiap.xcave.global.repository;

import br.com.fiap.xcave.global.entity.Mercado;
import br.com.fiap.xcave.global.entity.Produto;
import br.com.fiap.xcave.global.entity.Usuario;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MercadoRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Mercado save(Mercado mercado) {

        if (!checaMercadoByEmail(mercado.getEmail())) {
            dynamoDBMapper.save(mercado);
        }
        return mercado;
    }

    public Mercado getMercadoById(String id) {
        return dynamoDBMapper.load(Mercado.class, id);
    }

    public boolean checaMercadoByEmail(String email) {

        List<Mercado> mercados = getAll();

        for (Mercado mercado : mercados) {
            if (mercado.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public boolean checaMercadoByEmailAndId(String email, String id) {

        List<Mercado> mercados = getAll();

        for (Mercado mercado : mercados) {
            if (mercado.getEmail().equals(email) && mercado.getId().equals((id))) {
                return true;
            }
        }
        return false;
    }

    public Mercado getMercadoByLogin(String email, String senha) {

        List<Mercado> mercados = getAll();

        for (Mercado mercado : mercados) {
            if (mercado.getEmail().equals(email) && mercado.getPassword().equals(senha)) {
                return mercado;
            }
        }
        return null;
    }

    public List<Mercado> getAll() {
        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression();
        return dynamoDBMapper.scan(Mercado.class, dynamoDBScanExpression);
    }

    public List<Produto> getProdutosByMercadoId(String id) {

        List<Produto> produtos = produtoRepository.getAll();

        List<Produto> produtosMercado = new ArrayList<>();

        for (Produto produto : produtos) {
            if (produto.getMercadoId().equals(id)) {
                produtosMercado.add(produto);
            }
        }

        return produtosMercado;
    }

    public String delete(String id) {
        Mercado mercado = dynamoDBMapper.load(Mercado.class, id);
        try {
            dynamoDBMapper.delete(mercado);
            return "Mercado Deletado!";
        } catch (Exception e) {
            return "Mercado não encontrado!";
        }
    }

    public String update(String id, Mercado mercado) {
        try {
            if (!checaMercadoByEmail(mercado.getEmail()) || checaMercadoByEmailAndId(mercado.getEmail(), id)) {
                dynamoDBMapper.save(mercado,
                        new DynamoDBSaveExpression()
                                .withExpectedEntry("id",
                                        new ExpectedAttributeValue(
                                                new AttributeValue().withS(id)
                                        )));
                return id;
            }

        } catch (Exception e) {
            return "Mercado não encontrado!";
        }
        return "Email já existente";

    }
}