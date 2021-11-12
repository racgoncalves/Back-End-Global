package br.com.fiap.xcave.global.repository;

import br.com.fiap.xcave.global.entity.Produto;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProdutoRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private MercadoRepository mercadoRepository;

    public Produto save(Produto produto) {

        if (checaProdutoNovoByLocation(produto)) {
            dynamoDBMapper.save(produto);
        }

        return produto;
    }

    public Produto getProdutoById(String id) {
        return dynamoDBMapper.load(Produto.class, id);
    }

    public List<Produto> getAll() {
        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression();
        return dynamoDBMapper.scan(Produto.class, dynamoDBScanExpression);
    }

    private boolean checaProdutoUpdateByLocation(Produto produtoNovo) {

        List<Produto> produtos = mercadoRepository.getProdutosByMercadoId(produtoNovo.getMercadoId());

        Produto produtoAntigo = getProdutoById(produtoNovo.getId());

        if (produtoAntigo.getLocation().equals(produtoNovo.getLocation())) {
            return true;
        } else {
            for (Produto produto : produtos) {
                if (produto.getLocation().equals(produtoNovo.getLocation())) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checaProdutoNovoByLocation(Produto produtoNovo) {

        List<Produto> produtos = mercadoRepository.getProdutosByMercadoId(produtoNovo.getMercadoId());

        for (Produto produto : produtos) {
            if (produto.getLocation().equals(produtoNovo.getLocation())) {
                return false;
            }
        }
        return true;
    }

    public Produto getProdutoByMercadoIdAndLocation(String mercadoId, String location) {

        List<Produto> produtos = mercadoRepository.getProdutosByMercadoId(mercadoId);

        Produto produtoRetorno = new Produto();

        for (Produto produto : produtos) {
            if (produto.getLocation().equals(location)) {
                produtoRetorno = produto;
            }
        }
        return produtoRetorno;
    }

    public String delete(String id) {
        Produto produto = dynamoDBMapper.load(Produto.class, id);
        try {
            dynamoDBMapper.delete(produto);
            return "Produto Deletado!";
        } catch (Exception e) {
            return "Produto não encontrado!";
        }
    }

    public String update(String id, Produto produto) {

        if (checaProdutoUpdateByLocation(produto)) {
            try {
                dynamoDBMapper.save(produto,
                        new DynamoDBSaveExpression()
                                .withExpectedEntry("id",
                                        new ExpectedAttributeValue(
                                                new AttributeValue().withS(id)
                                        )));
                return id;
            } catch (Exception e) {
                return "Produto não encontrado!";
            }
        } else {
            return "Localização já ocupada";
        }
    }

    public void updateDonating(String id, Produto produto) {

        dynamoDBMapper.save(produto,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("id",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(id)
                                )));
    }

}