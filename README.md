# Jogo das Moedas - Asseco PST DevOps Challenge

Esta é uma implementação da aplicação "Jogo das Moedas" para o desafio da Asseco PST. A aplicação é uma API REST desenvolvida em Java com Spring Boot.

## Descrição do Problema

A aplicação simula uma máquina de moedas com duas extremidades, onde:

- Cada pessoa começa com 3 moedas
- Quando uma pessoa insere 1 moeda de um lado (ação "P" - partilhar), a pessoa no outro lado recebe 3 moedas
- Cada pessoa pode escolher inserir uma moeda ("P") ou não fazer nada e tentar receber ("R")

## Requisitos

- Java 17 ou superior
- Maven ou Gradle

## Instalação e Execução

### Com Maven

```bash
./mvnw clean install
./mvnw spring-boot:run
```

### Com Gradle

```bash
./gradlew clean build
./gradlew bootRun
```

A aplicação estará disponível em: http://localhost:8080

## Utilização da API

### Endpoint

`POST /coins`

### Corpo da requisição

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

## Exemplo de Uso com curl

```bash
curl -X POST http://localhost:8080/coins \
  -H "Content-Type: application/json" \
  -d '{"rightPerson": ["P", "P", "R"], "leftPerson": ["P", "R", "R"]}'
```

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

## Testes

A aplicação contém testes unitários e de integração que podem ser executados com:

### Maven

```bash
./mvnw test
```

### Gradle

```bash
./gradlew test
```