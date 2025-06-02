# API de Autenticação

## 📌 Sobre o Projeto

Esta é uma API pessoal desenvolvida com **Java 21** e **Spring Boot 3.4**, utilizando autenticação via **JWT**, banco de dados **PostgreSQL**, mapeamentos com **MapStruct**, e controle de acesso baseado em **roles** (customer/admin) com **Spring Security**.

O objetivo principal do projeto foi:

- Implementar autenticação JWT
- Validar os campos das requisições
- Criar endpoints protegidos com base na role do usuário

> Este projeto é **autoral** e **não** é baseado em cursos.

---

## 🛠 Tecnologias utilizadas

- Java 21
- Spring Boot 3.4
- Spring Security
- PostgreSQL
- JWT (Json Web Token)
- MapStruct
- Docker & Docker Compose

---

## 🚀 Como executar

1. Clone este repositório:
   ```bash
   git clone https://github.com/YasminVentura/project-auth.git
   cd project-auth

2. Execute a aplicação com Docker Compose:

   ```bash
   docker-compose up --build
   ```

A API estará disponível em `http://localhost:8080`.

---

## 🧪 Como testar a API

1. Importe a collection Postman incluída no repositório (`project-auth.postman_collection.json`).
2. Siga a sequência de chamadas na collection:

   * Criar usuário
   * Login
   * Copiar o token JWT retornado
   * Testar os endpoints protegidos:

     * `/admin/panel` (acessível apenas para usuários com role `admin`)
     * `/user/dashboard` (acessível por usuários com as roles `customer` e `admin`)
3. No Postman, vá até a aba **Authorization** e selecione o tipo **Bearer Token**. Cole o token JWT obtido após o login.

