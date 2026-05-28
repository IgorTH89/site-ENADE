# 🎓 ENADE Questões - Plataforma de Simulados Inteligente

> **Status do Projeto:** 🚀 Concluído & Em Produção

Plataforma backend desenvolvida para gerenciamento, categorização e resolução de questões baseadas nos exames oficiais do ENADE (Exame Nacional de Desempenho dos Estudantes). O sistema permite que estudantes realizem simulados inteligentes com persistência de dados e análise de desempenho.

---

## 🌐 Links do Projeto

* **API em Produção (Render):** [https://enade-questoes.onrender.com](https://enade-questoes.onrender.com)
* **Banco de Dados (Neon PostgreSQL):** Nuvem AWS (`us-east-1`)

---

## 🛠️ Tecnologias e Ferramentas

O ecossistema do projeto foi construído utilizando as melhores práticas de desenvolvimento backend do mercado:

* **Linguagem Principal:** Java 17
* **Framework:** Spring Boot 3.x
  * Spring Data JPA (Persistência e ORM)
  * Spring Web (Criação de Endpoints RESTful)
  * Spring Boot Actuator (Monitoramento de métricas)
* **Banco de Dados:** PostgreSQL (Hospedado via Neon Serverless Postgres)
* **Gerenciador de Dependências:** Maven
* **Containers & DevOps:** Docker & Dockerfile
* **Hospedagem da API:** Render Cloud

---

## 🧬 Arquitetura e Boas Práticas

O projeto foi estruturado seguindo o padrão arquitetural em camadas para garantir alta coesão e baixo acoplamento, aplicando os pilares fundamentais da **Programação Orientada a Objetos (POO)**:

* **Encapsulamento:** Proteção das entidades de negócio e exposição segura através de DTOs (Data Transfer Objects).
* **Camada de Controladores (Resource/Controller):** Exposição dos endpoints REST e manipulação de requisições HTTP.
* **Camada de Serviço (Service):** Concentração de todas as regras de negócio da plataforma.
* **Camada de Acesso a Dados (Repository):** Interfaces que estendem o `JpaRepository` para operações seguras de CRUD no PostgreSQL.

---

## 🐳 Configuração de Ambiente com Docker

O projeto está totalmente conteinerizado, permitindo que qualquer desenvolvedor execute a aplicação localmente sem a necessidade de instalar o Java ou o Maven na máquina física.

### Pré-requisitos
* Git
* Docker instalado e rodando

### Como executar localmente:

1. **Clone o repositório:**
   ```bash
   git clone [https://github.com/IgorTH89/site-ENADE.git](https://github.com/IgorTH89/site-ENADE.git)
2. **Navegue até a pasta do projeto**
   ```bash
   cd site-ENADE/questoes
3. **Cosntrua a imagem Docker**
    ```bash
    docker build -t enade-backend .
4. **Execute o container**
   ```bash
   docker run -p 8080:8080 enade-backend
