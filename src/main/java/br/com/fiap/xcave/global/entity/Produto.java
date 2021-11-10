package br.com.fiap.xcave.global.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "produto")
@Data
public class Produto {

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String id;

    @NotBlank(message = "O mercado é obrigatório. Digite um mercado.")
    @DynamoDBAttribute
    private String mercadoId;

    @NotBlank(message = "O nome é obrigatório. Digite um nome.")
    @DynamoDBAttribute
    private String name;

    @NotBlank(message = "A marca é obrigatória. Digite uma marca.")
    @DynamoDBAttribute
    private String brand;

    @NotBlank(message = "A localização é obrigatória. Digite uma localização.")
    @DynamoDBAttribute
    private String location;

    @DynamoDBAttribute
    private boolean donating = true;

    @DynamoDBAutoGeneratedTimestamp
    @DynamoDBAttribute
    private Date lastUpdated;

}