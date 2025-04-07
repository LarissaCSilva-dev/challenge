# 🧪 Challenge - Migração de Microsserviço Java

Este projeto é resultado de um code challenge com o objetivo de migrar um microsserviço legado utilizando **Java 8** e **Spring Boot 2.6.x** para uma stack mais atual, com **Java 17** e **Spring Boot 3.2.5**.

## ✅ Objetivos atingidos

- ✅ Migração do projeto para **Java 17**
- ✅ Atualização do Spring Boot para **3.2.5**
- ✅ Substituição de `RestTemplate` por `WebClient`
- ✅ Parametrização da URL da API por ambiente (`application.yml`)
- ✅ Criação de endpoint `/health` para verificação de status da aplicação
- ✅ Atualização dos testes para **JUnit 5**
- ✅ Todos os testes passam com sucesso 🎉

---

## 📦 Tecnologias utilizadas

- Java 17
- Spring Boot 3.2.5
- Spring WebFlux (WebClient)
- JUnit 5
- Maven

---

## 🚀 Como rodar o projeto

### Pré-requisitos

- Java 17 instalado
- Maven instalado

### Executando a aplicação

```bash
./mvnw spring-boot:run
