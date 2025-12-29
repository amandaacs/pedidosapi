## Pedidos API – Backend

API REST desenvolvida em Spring Boot, Spring Data JPA e SQLite, responsável por gerenciar produtos, clientes, pedidos e pagamentos.

## Como executar o projeto
Pré-requisitos:
* Java 17 ou superior
* Maven
* (Opcional) SQLite CLI para visualizar o banco de dados

## Passo a passo:

* Instalar as dependências
* mvn clean install
* Apagar o banco antigo (opcional, mas recomendado): rm database.sqlite
* Executar o projeto: mvn spring-boot:run
* Acessar a API: http://localhost:8080

## Estrutura principal do projeto

src/main/java/com/project/pedidosapi
* controller/ → Endpoints REST
* service/ → Regras de negócio
* repository/ → Acesso ao banco de dados
* entity/ → Entidades JPA
* dto/ → DTOs de entrada e saída
* config/ → Configurações (CORS, conversores, etc.)

src/main/resources 
* application.properties
* schema.sql → Criação das tabelas
* data.sql → Dados iniciais

## Endpoints principais
Produtos:
* GET /products

Pedidos:
* GET /orders
* POST /orders
* GET /orders/{id}

Pagamentos:
* POST /payments

## Observações importantes
O banco SQLite é criado automaticamente na raiz do projeto.
Os arquivos schema.sql e data.sql são executados sempre que a aplicação inicia.
Datas do tipo LocalDateTime são convertidas utilizando o LocalDateTimeConverter.

## Tecnologias utilizadas
* Java 17
* Spring Boot
* Spring Data JPA
* SQLite
* Maven