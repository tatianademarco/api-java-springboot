# API Rest para gerenciamento de tarefas utilizando Java com Spring Boot 

Backend de uma API o qual o usuário poderá cadastrar, visualizar, editar e excluir tarefas. As tarefas serão armazenadas no banco de dados relacional H2.

## Tecnologias Utilizadas

- Java 22
- Spring Boot 3.3.0
- Spring Web
- Spring Data JPA
- H2 Database
- Swagger

## Executar a Aplicação

Compile e execute a aplicação com o seguinte comando:

```
mvn spring-boot:run
````
## Acessar a Aplicação

Durante a execução da aplicação, você poderá acessa-lá pelos seguintes caminhos URL:

- A API estará disponível em: http://localhost:8080/tasks
- A documentação Swagger estará disponível em: http://localhost:8080/swagger-ui.html
- O console do H2 Database estará disponível em: http://localhost:8080/h2-console

## Executar os testes Unitários

Use o seguinte comando para rodar os testes:
```
mvn test
```
