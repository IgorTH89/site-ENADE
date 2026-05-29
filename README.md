# 🎓 ENADE Questões - Plataforma de Simulados Inteligente

> **Status do Projeto:** 🚀 Concluído

Plataforma backend desenvolvida para gerenciamento, categorização e resolução de questões baseadas nos exames oficiais do ENADE (Exame Nacional de Desempenho dos Estudantes). O sistema permite que estudantes realizem simulados inteligentes com persistência de dados e análise de desempenho.

---

## 🌐 Links do Projeto

Para avaliar o projeto em tempo real, acesse o link de produção abaixo (não requer nenhuma configuração ou execução local):

👉 **[Acessar ENADE Questões (Link Vercel)]([https://site-enade.vercel.app/](https://site-enade.vercel.app/telaInicial.html))**

---

## 🛠️ Tecnologias e Ferramentas

### Frontend
- **HTML5 & CSS3** (Estrutura e semântica)
- **Tailwind CSS** (Estilização responsiva e moderna)
- **JavaScript (ES6+)** (Consumo de APIs assíncronas via Fetch, manipulação dinâmica do DOM e gerenciamento de estado de sessão com `sessionStorage`)
- **AOS (Animate On Scroll) & tsParticles** (Efeitos visuais e animações fluidas)
- **Hospedagem:** Vercel

### Backend
- **Java 17** & **Spring Boot 3**
- **Spring Data JPA** (Persistência e abstração de dados)
- **Hibernate** (Mapeamento Objeto-Relacional)
- **PostgreSQL Driver** (Conectividade com banco de dados)
- **Hospedagem:** Render

### Banco de Dados
- **PostgreSQL** (Armazenamento relacional e suporte a dados estruturados em JSON para alternativas)
- **Hospedagem:** Neon DB (Cloud PostgreSQL)

---

## 🧬 Arquitetura e Boas Práticas

O projeto foi estruturado seguindo o padrão arquitetural em camadas para garantir alta coesão e baixo acoplamento, aplicando os pilares fundamentais da **Programação Orientada a Objetos (POO)**:

* **Encapsulamento:** Proteção das entidades de negócio e exposição segura através de DTOs (Data Transfer Objects).
* **Camada de Controladores (Resource/Controller):** Exposição dos endpoints REST e manipulação de requisições HTTP.
* **Camada de Serviço (Service):** Concentração de todas as regras de negócio da plataforma.
* **Camada de Acesso a Dados (Repository):** Interfaces que estendem o `JpaRepository` para operações seguras de CRUD no PostgreSQL.
* **Sistema de Autenticação Completo:** Endpoints robustos de `/cadastro` e `/login` com validação de dados no backend, criptografia lógica de fluxos e controle de sessão no frontend.
* **Persistência de Dados Inteligente:** Tabelas populadas em nuvem com questões de diversas disciplinas da computação (POO, Estrutura de Dados, Engenharia de Software).
* **Listagem Dinâmica e Paginada:** O frontend consome rotas estruturadas com paginação nativa do Spring (`Pageable`), otimizando o tráfego de dados.
* **Resolução de Questões In-App:** Lógica client-side para validação de alternativas corretas/incorretas e exibição imediata do gabarito comentado vindo do banco de dados.

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
