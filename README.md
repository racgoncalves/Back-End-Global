# Back-End-Global

## Grupo xcave:

1. Pedro Henrico Romania Pires RM: 85439;
2. Rodrigo André Chiarelli Gonçalves RM: 85716;
3. Victor Luiz Dias Parisi RM: 84190;

## Lista de APIs
### USUARIO:
### @Post – Cadastrar usuário
#### ec2-54-233-173-244.sa-east-1.compute.amazonaws.com:8083/usuario
Exemplo de body:
```json
{
  "name":"teste",
  "email":"teste@teste.com",
  "password":"123",
  "address":"Rua teste",
  "number":"1A",
  "city":"São Paulo",
  "state":"SP",
  "zipcode":"18603-555"
}
```
Exemplo de Retorno:
```json
{
  "id": "20d0b346-a824-4e13-b760-03490742a558",
  "name": "teste",
  "email": "teste@teste.com",
  "password": "123",
  "role": "user",
  "address": "Rua teste",
  "number": "1A",
  "city": "São Paulo",
  "state": "SP",
  "zipcode": "18603-555",
  "lastUpdated": "2021-11-07T20:35:32.673+00:00"
}
```
Exemplo de Retorno se email já existir no banco:
```json
{
  "id": null,
  "name": "teste",
  "email": "teste@teste.com",
  "password": "123",
  "role": "user",
  "address": "Rua teste",
  "number": "1A",
  "city": "São Paulo",
  "state": "SP",
  "zipcode": "18603-555",
  "lastUpdated": null
}
```
### @Get – Retorna todos os usuários
#### ec2-54-233-173-244.sa-east-1.compute.amazonaws.com:8083/usuario

### @Get – Retorna o usuário em caso do login do usuário estar correto ou null caso esteja incorreto
#### ec2-54-233-173-244.sa-east-1.compute.amazonaws.com:8083/usuario/login/{{email}}/{{senha}}

### @Get - Ler usuário pelo ID
#### ec2-54-233-173-244.sa-east-1.compute.amazonaws.com:8083/usuario/{id}

### @Delete – Deletar o usuário pelo ID (retorna a string "Usuario Deletado!" em caso de sucesso ou "Usuario não encontrado!" em caso de não encontrar)
#### ec2-54-233-173-244.sa-east-1.compute.amazonaws.com:8083/usuario/{id}

### @Put – Atualizar o usuário pelo ID (retorna o ID do usuário caso tenha sucesso, "Email já existente" caso já exista o email cadastrado com outro usuário ou "Usuario não encontrado!" caso não encontre o ID)
#### ec2-54-233-173-244.sa-east-1.compute.amazonaws.com:8083/usuario/{id}
Exemplo de body:
```json
{
  "id": "20d0b346-a824-4e13-b760-03490742a558",
  "name": "teste2",
  "email": "teste2@teste.com",
  "password": "1233",
  "address": "Rua teste",
  "number": "1A",
  "city": "São Paulo",
  "state": "SP",
  "zipcode": "18603-555"
}
```

### MERCADO:
### @Post – Cadastrar mercado
#### ec2-54-233-173-244.sa-east-1.compute.amazonaws.com:8083/mercado/
Exemplo de body:
```json
{
  "name":"Mercado teste",
  "email":"teste-mercado@teste.com",
  "password":"123",
  "address":"Rua teste",
  "number":"1A",
  "city":"São Paulo",
  "state":"SP",
  "zipcode":"18603-555",
  "donating":true,
  "picture":"https://content.paodeacucar.com/wp-content/uploads/2019/06/8-dicas-%C3%BAteis-2.jpg"
}
```
Exemplo de Retorno:
```json
{
  "id": "21435105-300b-4803-8ae2-21295127a04f",
  "name": "Mercado teste",
  "email": "teste-mercado@teste.com",
  "password": "123",
  "role": "admin",
  "address": "Rua teste",
  "number": "1A",
  "city": "São Paulo",
  "state": "SP",
  "zipcode": "18603-555",
  "donating": true,
  "picture": "https://content.paodeacucar.com/wp-content/uploads/2019/06/8-dicas-%C3%BAteis-2.jpg",
  "lastUpdated": "2021-11-07T20:53:26.649+00:00"
}
```
Exemplo de Retorno se mercado já existir no banco:
```json
{
  "id": null,
  "name": "Mercado teste",
  "email": "teste-mercado@teste.com",
  "password": "123",
  "role": "admin",
  "address": "Rua teste",
  "number": "1A",
  "city": "São Paulo",
  "state": "SP",
  "zipcode": "18603-555",
  "donating": true,
  "picture": "https://content.paodeacucar.com/wp-content/uploads/2019/06/8-dicas-%C3%BAteis-2.jpg",
  "lastUpdated": null
}
```
### @Get – Retorna todas as mercados
#### ec2-54-233-173-244.sa-east-1.compute.amazonaws.com:8083/mercado

### @Get – Retorna todos os produtos do mercado
#### ec2-54-233-173-244.sa-east-1.compute.amazonaws.com:8083/mercado/{id}/produtos

### @Get – Retorna o mercado em caso do login do mercado estar correto ou null caso esteja incorreto
#### ec2-54-233-173-244.sa-east-1.compute.amazonaws.com:8083/mercado/login/{{email}}/{{senha}}

### @Get - Ler mercado pelo ID
#### ec2-54-233-173-244.sa-east-1.compute.amazonaws.com:8083/mercado/{id}

### @Delete – Deletar a mercado pelo ID (retorna a string "Mercado Deletado!" em caso de sucesso ou "Mercado não encontrado!" em caso de não encontrar)
#### ec2-54-233-173-244.sa-east-1.compute.amazonaws.com:8083/mercado/{id}

### @Put – Atualizar a mercado pelo ID (retorna o ID do mercado caso tenha sucesso, "Mercado já existente" caso já exista a mercado cadastrado com a mesma posição ou "Mercado não encontrado!" caso não encontre o ID)
#### ec2-54-233-173-244.sa-east-1.compute.amazonaws.com:8083/mercado/{id}
Exemplo de body:
```json
{
  "id": "21435105-300b-4803-8ae2-21295127a04f",
  "name": "Mercado teste2",
  "email": "teste-mercado2@teste.com",
  "password": "123",
  "address": "Rua teste2",
  "number": "1A",
  "city": "São Paulo",
  "state": "SP",
  "zipcode": "18603-555",
  "donating": true,
  "picture": "https://content.paodeacucar.com/wp-content/uploads/2019/06/8-dicas-%C3%BAteis-2.jpg"
}
```

### Produto:
### @Post – Cadastrar produto
#### ec2-54-233-173-244.sa-east-1.compute.amazonaws.com:8083/produto/
Exemplo de body:
```json
{
  "mercadoId":"21435105-300b-4803-8ae2-21295127a04f",
  "name":"Iogurte Grego",
  "brand":"Vigor",
  "location":"BOX-1A",
  "donating":true
}
```
Exemplo de Retorno:
```json
{
  "id": "a5defaae-72bf-482c-81d5-9eb385dfc2b0",
  "mercadoId": "21435105-300b-4803-8ae2-21295127a04f",
  "name": "Iogurte Grego",
  "brand": "Vigor",
  "location": "BOX-1A",
  "donating": true,
  "lastUpdated": "2021-11-07T20:58:11.602+00:00"
}
```
Exemplo de Retorno se produto já existir no banco dentro do mesmo mercadoId:
```json
{
  "id": null,
  "mercadoId": "21435105-300b-4803-8ae2-21295127a04f",
  "name": "Iogurte Grego",
  "brand": "Vigor",
  "location": "BOX-1A",
  "donating": true,
  "lastUpdated": null
}
```
### @Get – Retorna todas as produtos
#### ec2-54-233-173-244.sa-east-1.compute.amazonaws.com:8083/produto

### @Get - Ler produto pelo ID
#### ec2-54-233-173-244.sa-east-1.compute.amazonaws.com:8083/produto/{id}

### @Get - Ler produto pelo mercadoId e sua localização
#### ec2-54-233-173-244.sa-east-1.compute.amazonaws.com:8083/produto/{mercadoId}/{location}

### @Delete – Deletar a produto pelo ID (retorna a string "Produto Deletado!" em caso de sucesso ou "Produto não encontrado!" em caso de não encontrar)
#### ec2-54-233-173-244.sa-east-1.compute.amazonaws.com:8083/produto/{id}

### @Put – Atualizar a produto pelo ID (retorna o ID do produto caso tenha sucesso, "Localização já ocupada" caso já exista o produto cadastrado na mesma localização ou "Produto não encontrado!" caso não encontre o ID)
#### ec2-54-233-173-244.sa-east-1.compute.amazonaws.com:8083/produto/{id}
Exemplo de body:
```json
{
  "id": "a5defaae-72bf-482c-81d5-9eb385dfc2b0",
  "mercadoId": "21435105-300b-4803-8ae2-21295127a04f",
  "name": "Iogurte Grego",
  "brand": "Batavo",
  "location": "BOX-1A",
  "donating": true
}
```

### @POST – Atualizar o produto se está ocupada ou desocupada pelo mercado Id e localização (retorna o ID do produto caso tenha sucesso, "Produto já está nessa condição" caso já esteja na condição enviada, "Produto não encontrado!" caso não encontre o ID, ou "Condição inválida" caso a condição não seja "Disponível" nem "Indisponível")
#### ec2-54-233-173-244.sa-east-1.compute.amazonaws.com:8083/status/{mercadoId}/{location}
Exemplo de body:
```text
    Disponível
```