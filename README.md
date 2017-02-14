# cepservice


## _Arquitetura_ ##

Para o design do projeto, foram usados as seguintes tecnologias:

- Spring Framework
- Java 8
- Gradle
- Spark Framework
- JUnit

A aplicação foi desenvolvida baseada no conceito de CLEAN ARCHITECTURE. Garantindo que as camadas que se interagem conheçam apenas a camada interior. Para mais informações acesse: [https://8thlight.com/](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html)

## _Para Rodar a Aplicação_ ##

Via terminal, digite o seguinte comando na pasta raíz do projeto:

```./gradlew run```

A aplicação rodará no endereço: ```http://localhost:8081/```

**Observação:** Não é necessário rodar no tomcat. Ambas as aplicações possuem um embedded server permitindo subir a aplicação e responder em uma determinada porta. 


## _Requisições_ ##

Para se comunicar com a aplicação, basta fazer um POST para ```http://localhost:8081/cep``` com o seguinte payload:
```{"cep":"12345600"}```

#### _Cenário com sucesso_ ####

######_Request_######

```curl -H "Content-Type: application/json" -X POST -d '{"cep":"12345600"}' http://localhost:8081/cep```

**Observação:**  Os últimos dois dígitos pode ser substituídos por: 01, 02, 03, 04, 05.

######_Response_######

``` {"mensagem":"CEP Encontrado","endereco":{"cep":"12345600","rua":"Rua 00","bairro":"Bairro 00","cidade":"Cidade 00","estado":"Estado 00"}} ```

#### _Cenário com falha_ ####

Basta usar um cep não citado anteriormente.

######_Request_######

```curl -H "Content-Type: application/json" -X POST -d '{"cep":"99345600"}' http://localhost:8081/cep```

######_Response_######

``` {"mensagem":"CEP Inválido"} ```

## _Testes_ ##

Para rodar os testes basta rodar o seguinte comando no terminal:

``` ./gradlew test ```
