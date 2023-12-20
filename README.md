# Gerenciamento de Votações em Pautas API

## Pré-requisitos
- **Maven**
  ```bash
  mvn -v

  Apache Maven 3.5.2

- **java**
  ```bash
  openjdk version "17" 2021-09-14

   OpenJDK Runtime Environment (build 17+35-2724)

   OpenJDK 64-Bit Server VM (build 17+35-2724, mixed mode)



## Banco De Dados

  Nesta aplicação, é importante destacar a presença de dois ambientes de banco de dados distintos: o ambiente de desenvolvimento (dev), no qual foi implementado o uso do H2 em memória; e o ambiente de produção (prod), no qual os dados são persistentes, atendendo aos requisitos específicos do desafio.

## Testes
Para executar os testes do projeto

mvn test


## Swagger

[Swagger](http://localhost:8080/swagger-ui/index.html#/voting-controller/abrirSessao)
 

## Postman

[Postman](https://api.postman.com/collections/24448222-960f98b0-fbe4-4926-a87c-9fa506d2fb58?access_key=PMAT-01HJ4DZ0TVKJ0K557YQNJHJ46A)

## Versionamento:

    Atente-se ao parâmetro no header da requisição :

    Api-Version = 1

## Operações Disponíveis

### Cadastrar Pauta
```bash
POST http://localhost:8080/votacao/cadastrarSchedule

{
  "descrição": "Descrição da pauta"
}
```

### Abrir Sessão
```bash
POST http://localhost:8080/votacao/abrirSessao/scheduleId

{
    "scheduleId": 1,
    "durationInMinutes": 4
}
```
OU
```bash
{
    "scheduleId": 1
}
```

### Votar em uma Pauta
```bash
POST http://localhost:8080/receberVoto/scheduleId 

{
    "associateId": 1,
    "vote": "SIM"
}
```

### Cadastrar Associado
```bash
POST http://localhost:8080/api/associados/criarAssociado
```
{
   "nome":"Willian"
}

### Consultar Resultado da Pauta
```bash
GET http://localhost:8080/votacao/resultadoVotacao/{scheduleId}
```

## Tarefa Bonus 1

  ## Integração com Sistema Externo

  A aplicação possui uma funcionalidade de integração com um sistema externo para verificar a elegibilidade de um associado para votar, com base no CPF. No entanto, no momento da implementação, a API externa ([link da API externa](https://user-info.herokuapp.com/users/{cpf})) encontra-se fora do ar.

  ## Como Funcionaria

  O serviço `ExternalSystemService` foi implementado para realizar chamadas para o sistema externo e verificar o status do associado com base no CPF. A lógica para verificar a elegibilidade foi adicionada na controladora `VotingController`, que antes de permitir o registro do voto, verifica se o associado está autorizado a votar.

  ## Não Implementação Completa

  Devido à inatividade da API externa, a funcionalidade de integração não foi implementada. Posso deixar como seria o código em um arquivo separado para analise do mesmo.


## Tarefa Bonus 4 

Utilizei a Versão na URL (URI), pois facilitaria a distinção entre versões diferentes. E para testes locais eram mais simples
