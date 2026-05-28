# 🎓 ENADE Questões - Plataforma de Simulados Inteligente

[![Java Version](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?style=for-the-badge&logo=springboot)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-JSONB-blue?style=for-the-badge&logo=postgresql)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-Container-blue?style=for-the-badge&logo=docker)](https://www.docker.com/)

Uma aplicação Full Stack robusta e responsiva desenvolvida para auxiliar estudantes na preparação para o exame do ENADE. O sistema permite a geração de simulados dinâmicos com cronômetro regressivo, seleção por disciplinas e entrega um painel de desempenho com métricas visuais.

---

## 🚀 Diferenciais Técnicos (O que há de especial sob o capô?)

Este projeto foi construído aplicando boas práticas de engenharia de software e padrões de mercado:

* **Persistência Híbrida com PostgreSQL `JSONB`**: Em vez de engessar o banco de dados com tabelas relacionais complexas para armazenar alternativas de escolha múltipla (A, B, C, D, E), utilizei o recurso de alta performance **JSONB** do Postgres. Isso permite armazenar e consultar estruturas dinâmicas de dados diretamente em uma única coluna estruturada, otimizando as queries e simplificando consideravelmente o mapeamento de entidades no Java.
* **Segurança da Informação e Arquitetura Cloud Ready**: O gerenciamento de credenciais críticas (como a string de conexão de produção) foi totalmente desacoplado do código-fonte utilizando **Variáveis de Ambiente** (`${SPRING_DATASOURCE_URL}`). Isso impede o vazamento de credenciais sensíveis no histórico público do Git.
* **Performance com JPA Open-In-View Desativado**: Configurado explicitamente como `false` para evitar o anti-padrão mundial de manter conexões com o pool do banco de dados presas durante todo o ciclo de renderização das respostas HTTP, garantindo alta escalabilidade para a API.
* **Containerização com Docker**: Criação de um ambiente de compilação multi-estágio (*Multi-stage build*) otimizado via `Dockerfile`. A aplicação está pronta para ser buildada e rodar em qualquer provedor de nuvem de forma idêntica ao ecossistema local.

---

## 🛠️ Tecnologias Utilizadas

### Backend (API REST)
* **Java 17** (LTS)
* **Spring Boot 3.x**
* **Spring Data JPA** (Persistência e Repositórios)
* **PostgreSQL Driver** (Com suporte nativo a operações JSONB)
* **Maven** (Gerenciador de dependências)

### Frontend (Interface Responsiva)
* **HTML5 & JavaScript Vanilla** (Manipulação dinâmica de DOM e tratamento inteligente de objetos JSON estruturados vindos do banco de dados)
* **Tailwind CSS** (Design moderno via utilitários com efeitos de glassmorphism)
* **FontAwesome & Google Fonts** (Tipografia e ícones vetoriais)
* **AOS (Animate On Scroll)** (Animações fluidas de interface)

---

## 📁 Estrutura do Projeto

```text
├── src/
│   ├── main/
│   │   ├── java/         # Camadas de Controller, Service, Model e Repository (Spring Boot)
│   │   └── resources/
│   │       ├── application.properties  # Configurações dinâmicas de Ambiente
│   │       └── ...
├── .gitignore            # Proteção contra upload de pastas de compilação locais
├── Dockerfile            # Instruções de containerização para deploy automatizado
└── pom.xml               # Manifesto de dependências do ecossistema Maven
