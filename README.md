# Projeto Demo DAO com JDBC

Este projeto é uma aplicação Java simples para demonstrar os conceitos fundamentais de 
acesso a banco de dados com JDBC (Java Database Connectivity). 
Ele implementa as operações CRUD (Create, Read, Update, Delete) e o 
controle de transações, utilizando o padrão de projeto DAO (Data Access Object).

---

## O que o projeto faz e demonstra:

O projeto implementa um mini-sistema de gerenciamento de vendedores e departamentos, demonstrando as seguintes funcionalidades e conceitos:

* **Padrão de Projeto DAO (Data Access Object):**
    * Separação clara entre a lógica de negócio e a lógica de acesso a dados (persistência).
    * Uso de interfaces (`SellerDao`, `DepartmentDao`) para definir os contratos das operações.
    * Implementações concretas (`SellerDaoJDBC`) que contêm o código SQL e a lógica JDBC.


## 🚀 Tecnologias

* **Java 17+**: Linguagem de programação.
* **JDBC**: API padrão do Java para conectividade com bancos de dados.
* **MySQL**: Sistema de Gerenciamento de Banco de Dados relacional.
* **Git & GitHub**: Para controle de versão.
* **Maven**: Gerenciamento de dependências (especificamente o conector MySQL).

---

* **Operações CRUD Completas:**
    * **Create (Inserir):** Demonstra como inserir novos registros nas tabelas `seller` e `department`, incluindo a recuperação do ID gerado.
    * **Read (Recuperar):** Mostra como buscar um registro específico por seu ID ou listar todos os registros de uma tabela.
    * **Update (Atualizar):** Exemplifica como modificar os dados de um registro existente com base em uma condição.
    * **Delete (Excluir):** Demonstra como remover um registro do banco de dados e a importância de tratar as restrições de integridade referencial.


    * Carregamento de credenciais do banco de dados a partir de um arquivo externo (`db.properties`).