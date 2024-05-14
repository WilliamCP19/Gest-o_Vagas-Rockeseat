## Aplicação de Gerenciamento de Vagas de RH
### Projeto desenvolvido seguindo a Trilha de Java da Rocketseat

## Requisitos
- Java 21
- Maven
- Banco de dados
  - Desenvolvido usando o MySQL (Há também a dependência do Postgres)
- Docker
- Docker-Compose

## Configuração

1. Clone o repositório:
```
git clone https://github.com/WilliamCP19/Gestao_Vagas-Rocketseat.git
```
2. Configure o banco de dados editando o arquivo application.properties e fornecendo as configurações necessárias:

```java
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.${HIBERNATE_DIALECT}
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}
```

3. Construa a aplicação:

```
mvn clean install
```

4. Inicie a aplicação

```
mvn spring-boot:run
```

A aplicação agora deve estar em execução em http://localhost:8080.

## Documentação
Acesse a documentação disponibilizada pelo Swagger: http://localhost:8080/swagger-ui/index.html (acessível somente com a aplicação em execução).
