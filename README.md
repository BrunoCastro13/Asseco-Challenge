# Jogo das Moedas - Asseco PST DevOps Challenge

Esta é uma implementação da aplicação "Jogo das Moedas" para o desafio da Asseco PST. A aplicação é uma API REST desenvolvida em Java com Spring Boot.

## Descrição do Problema

A aplicação simula uma máquina de moedas com duas extremidades, onde:

- Cada pessoa começa com 3 moedas
- Quando uma pessoa insere 1 moeda de um lado (ação "P" - partilhar), a pessoa no outro lado recebe 3 moedas
- Cada pessoa pode escolher inserir uma moeda ("P") ou não fazer nada e tentar receber ("R")

## Requisitos

- Java 17 ou superior
- Maven

## Instalação e Execução

### Com Maven

```bash
./mvnw clean install
./mvnw spring-boot:run
```


A aplicação estará disponível em: http://localhost:8080

## Utilização da API

### Endpoint

`POST /coins`

### Corpo da requisição (Exemplo)

```json
{
  "rightPerson": ["P", "P", "R"],
  "leftPerson": ["P", "R", "R"]
}
```

Onde:
- `rightPerson`: Array de ações da pessoa à direita
- `leftPerson`: Array de ações da pessoa à esquerda
- Cada elemento pode ser "P" (partilhar/inserir moeda) ou "R" (receber/não fazer nada)

### Resposta

```json
{
  "rightPerson": 4,
  "leftPerson": 8
}
```

Onde:
- `rightPerson`: Número final de moedas da pessoa à direita
- `leftPerson`: Número final de moedas da pessoa à esquerda

## Estrutura do Projeto

- `com.asseco.challenge`
    - `CoinGameApplication.java`: Classe principal da aplicação
    - `controller`
        - `CoinGameController.java`: Controlador REST
    - `model`
        - `GameRequest.java`: Modelo para a requisição
        - `GameResult.java`: Modelo para a resposta
    - `service`
        - `CoinGameService.java`: Serviço com a lógica do jogo
    - `exception`
        - `GlobalExceptionHandler.java`: Classe responsável por tratar exceções globais da aplicação

## Testes

A aplicação contém testes que podem ser executados com:

### Maven

```bash
./mvnw test
```

## Pipleline CI/CD

A aplicação utiliza uma pipeline de CI/CD configurada no GitHub Actions. A pipeline realiza os seguintes passos:

- Compilação: O código é compilado usando Maven e as dependências são resolvidas.

- Testes: São executados testes automatizados para garantir a qualidade do código.

- Análise de Qualidade de Código: A qualidade do código é verificada usando o SonarCloud, que analisa o código e gera relatórios detalhados sobre cobertura de testes, complexidade e outros aspectos (https://sonarcloud.io/project/overview?id=BrunoCastro13_Asseco-Challenge).

- Docker Build: A imagem Docker é construída e publicada no Docker Hub, permitindo a fácil execução do projeto em qualquer ambiente ("https://hub.docker.com/r/brunocast13/asseco-challenge").

- Deploy (Simulação): O deploy da aplicação é simulado na pipeline.

