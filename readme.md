# Delivery Microservices System

Este repositório contém um projeto de estudo para entregas desenvolvido em **Java Spring Boot**, estruturado em uma arquitetura de **microserviços**. O projeto utiliza tecnologias modernas como **Kafka**, **Docker**, **Eureka Service Discovery**, **Gateway API**, **Resilience4j**, além de boas práticas de resiliência e comunicação assíncrona.

---

## 🏗️ Arquitetura dos Serviços
O sistema é composto pelos seguintes componentes:

### 🔹 **delivery-tracking**
Microserviço responsável pelo gerenciamento das encomendas.

### 🔹 **courier-management**
Microserviço responsável pelo gerenciamento dos entregadores.

### 🔹 **gateway**
Responsável pelo roteamento centralizado das requisições.

### 🔹 **eureka-server**
Service Discovery para registro e descoberta dos microserviços com LoadBalancer.

---

## 🚀 Funcionalidades

### 📦 **Delivery Tracking**
- Listar todas as encomendas
- Criar rascunho de uma nova encomenda
- Buscar encomenda por ID
- Editar encomenda existente
- Submeter encomenda para processamento
- Registrar retirada da encomenda pelo entregador
- Registrar conclusão da entrega

### 🚴 **Courier Management**
- Listar todos os entregadores
- Criar novo entregador
- Buscar entregador por ID
- Editar informações de um entregador

---

## 🧩 Tecnologias Utilizadas
- **Java 21**
- **Spring Boot**
- **Kafka**
- **Docker e Docker Compose**
- **PostgreSQL**
- **Eureka Service Discovery**
- **API Gateway**
- **Resilience4j** (Timeout, Retry, Circuit Breaker)
- **Spring Cloud**

---

## 🔗 Documentação da API
Acesse o Swagger pelo Gateway:

👉 **http://localhost:9999/webjars/swagger-ui/index.html**

---

## 🐳 Como Rodar o Projeto com Docker
Para rodar o projeto usando **Docker**, basta utilizar o arquivo `docker-compose.yml` presente no repositório.

### ✔️ Tecnologias necessárias
- **Docker**
- **Docker Compose**

### ▶️ Subindo todos os serviços
Apenas execute:

```
cd Delivery
docker compose up -d
```

Isso irá subir automaticamente:
- Kafka + UI
- PostgreSQL
- Eureka
- Gateway (com load balancer)
- delivery-tracking
- courier-management

Após tudo subir, você poderá acessar:
- **Swagger (via Gateway):** http://localhost:9999/webjars/swagger-ui/index.html
- **Kafka UI:** http://localhost:8084/
- **Eureka Server:** http://localhost:8761/



---


## 🐧 Alternativa Como Rodar o Projeto no Linux
Se você estiver utilizando Linux, há um script que facilita ainda mais a inicialização do ambiente.

### ✔️ Tecnologias necessárias
- **Docker**
- **Docker Compose**

### ▶️ Executando o projeto no Linux
Basta rodar o comando abaixo: 

```
bash start.sh
```

### O script inicializará automaticamente todos os serviços definidos no Docker Compose. Além de fazer a verificação de portas

Isso irá subir automaticamente:
- Kafka + UI
- PostgreSQL
- Eureka
- Gateway (com load balancer)
- delivery-tracking
- courier-management

Após tudo subir, você poderá acessar:
- **Swagger (via Gateway):** http://localhost:9999/webjars/swagger-ui/index.html
- **Kafka UI:** http://localhost:8084/
- **Eureka Server:** http://localhost:8761/



---


## 🗂️ Estrutura do Repositório
```
/Delivery
 ├── courier-management
 ├── delivery-tracking
 ├── gateway
 └── eureka-server
```

---

## 🛠️ Padrões de Resiliência
O sistema utiliza **Resilience4j** implementando:
- **Timeout**: Limita o tempo de espera de chamadas remotas.
- **Retry**: Tenta novamente chamadas que falham.
- **Circuit Breaker**: Abre o circuito quando há falhas contínuas, evitando sobrecarga.

---

## 📦 Banco de Dados
Cada microserviço utiliza **PostgreSQL** para persistência dos dados.

---

## 📫 Contato
Criado por **Carlos Serafim**.

