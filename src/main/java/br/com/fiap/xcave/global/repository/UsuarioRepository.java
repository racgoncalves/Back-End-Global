package br.com.fiap.xcave.global.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import br.com.fiap.xcave.global.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Usuario save(Usuario usuario) {

        if (!checaUsuarioByEmail(usuario.getEmail())) {
            dynamoDBMapper.save(usuario);
        }

        return usuario;
    }

    public Usuario getUsuarioById(String usuarioId) {
        return dynamoDBMapper.load(Usuario.class, usuarioId);
    }

    public boolean checaUsuarioByEmail(String email) {

        List<Usuario> usuarios = getAll();

        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public boolean checaUsuarioByEmailAndId(String email, String id) {

        List<Usuario> usuarios = getAll();

        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getId().equals((id))) {
                return true;
            }
        }
        return false;
    }

    public Usuario getUsuarioByLogin(String email, String senha) {

        List<Usuario> usuarios = getAll();

        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getPassword().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }

    public List<Usuario> getAll() {
        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression();
        return dynamoDBMapper.scan(Usuario.class, dynamoDBScanExpression);
    }

    public String delete(String usuarioId) {
        Usuario usuario = dynamoDBMapper.load(Usuario.class, usuarioId);
        try {
            dynamoDBMapper.delete(usuario);
            return "Usuario Deletado!";
        } catch (Exception e) {
            return "Usuario não encontrado!";
        }
    }

    public String update(String id, Usuario usuario) {
        try {
            if (!checaUsuarioByEmail(usuario.getEmail()) || checaUsuarioByEmailAndId(usuario.getEmail(), id)) {
                dynamoDBMapper.save(usuario,
                        new DynamoDBSaveExpression()
                                .withExpectedEntry("id",
                                        new ExpectedAttributeValue(
                                                new AttributeValue().withS(id)
                                        )));
                return id;
            }

        } catch (Exception e) {
            return "Usuario não encontrado!";
        }
        return "Email já existente";

    }
}