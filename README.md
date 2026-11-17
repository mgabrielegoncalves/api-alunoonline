# 🎓 API Aluno Online

API REST desenvolvida em **Java 21** e **Spring Boot 4**, voltada ao gerenciamento de informações acadêmicas. Possui operações CRUD para alunos, professores e disciplinas, gerenciamento de matrículas, controle de acesso com autenticação **JWT (JSON Web Token)**, criptografia de senhas com **BCrypt**, migrações com **Flyway** e documentação interativa com **Swagger OpenAPI**.

---

## 🚀 Acesso Rápido à Documentação (Swagger UI)

Com a aplicação em execução, acesse a documentação interativa para visualizar e testar os endpoints diretamente pelo navegador:

👉 **[Swagger UI](http://localhost:8080/swagger-ui/index.html)**

**Especificação OpenAPI (JSON):** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## 🔐 Como se autenticar no Swagger

A API utiliza autenticação Stateless via JWT para proteger as rotas de gerenciamento acadêmico.

Para testar os endpoints protegidos:

1. Acesse o endpoint **`POST /cadastros`** no Swagger para cadastrar um usuário.
2. Informe um login e uma senha de sua preferência:

   ```json
   {
     "login": "seu_usuario",
     "senha": "sua_senha"
   }
   ```

3. Após o cadastro, acesse **`POST /login`** e informe as mesmas credenciais.
4. Execute a requisição e copie o token JWT retornado no corpo da resposta.
5. No topo da documentação Swagger, clique no botão **Authorize 🔓**.
6. No campo **Value**, cole o token e confirme em **Authorize**.
7. Pronto! Agora você poderá acessar as rotas protegidas utilizando o token gerado.

> **Importante:** o token deve ser obtido por meio do endpoint de login. Não é necessário informar o prefixo `Bearer` no campo de autorização quando o Swagger já utiliza o esquema Bearer configurado na API.

---

## 🛠️ Tecnologias Utilizadas

- **Java 21** – Linguagem de programação.
- **Spring Boot 4.0.4** – Estrutura principal da aplicação.
- **Spring Security** – Configuração de segurança e controle de acesso.
- **JWT (Auth0)** – Geração e validação de tokens de autenticação.
- **BCrypt** – Criptografia de senhas.
- **Spring Data JPA e Hibernate** – Persistência e mapeamento objeto-relacional.
- **PostgreSQL 16** – Banco de dados relacional.
- **Flyway** – Versionamento e gerenciamento de migrações do banco de dados.
- **SpringDoc OpenAPI e Swagger UI** – Documentação e testes dos endpoints.
- **Maven** – Gerenciamento de dependências e build do projeto.

---

## 📌 Principais pontos de extremidade

### 🔑 Autenticação e Usuários

Rotas públicas utilizadas para cadastro e autenticação.

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/cadastros` | Cadastra um novo usuário com senha protegida por BCrypt. |
| `POST` | `/login` | Autentica o usuário e gera um token JWT. |

### 👨‍🏫 Professores (Requer Token JWT)

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/professores` | Lista os professores cadastrados. |
| `GET` | `/professores/{id}` | Consulta um professor específico. |
| `POST` | `/professores` | Cadastra um novo professor. |
| `PUT` | `/professores/{id}` | Atualiza os dados de um professor. |
| `DELETE` | `/professores/{id}` | Remove um professor. |

### 🎓 Alunos (Requer Token JWT)

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/alunos` | Lista os alunos cadastrados. |
| `GET` | `/alunos/{id}` | Consulta um aluno específico. |
| `POST` | `/alunos` | Cadastra um novo aluno. |
| `PUT` | `/alunos/{id}` | Atualiza os dados de um aluno. |
| `DELETE` | `/alunos/{id}` | Remove um aluno. |

### 📚 Disciplinas (Requer Token JWT)

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/disciplinas` | Lista as disciplinas cadastradas. |
| `GET` | `/disciplinas/{id}` | Consulta uma disciplina específica. |
| `POST` | `/disciplinas` | Cadastra uma nova disciplina. |
| `PUT` | `/disciplinas/{id}` | Atualiza os dados de uma disciplina. |
| `DELETE` | `/disciplinas/{id}` | Remove uma disciplina. |

### 📝 Matrículas (Requer Token JWT)

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/matriculas` | Realiza uma matrícula. |
| `PATCH` | `/matriculas/trancar/{id}` | Realiza o trancamento de uma matrícula. |
| `PATCH` | `/matriculas/atualizar-notas/{id}` | Atualiza as notas de uma matrícula. |

---

## ⚙️ Configuração e Execução

### 1. Pré-requisitos

- JDK 21 instalado e configurado.
- PostgreSQL 16 instalado e em execução.
- Maven (opcional, pois o projeto possui Maven Wrapper).

Crie um banco de dados chamado:

```sql
CREATE DATABASE aluno_online;
```

### 2. Configuração das variáveis de ambiente

Antes de iniciar a aplicação, configure as variáveis de ambiente necessárias para a conexão com o banco de dados e para a assinatura dos tokens JWT.

No PowerShell:

```powershell
$env:DB_URL = "jdbc:postgresql://localhost:5432/aluno_online"
$env:DB_USERNAME = "postgres"
$env:DB_PASSWORD = "sua_senha"
$env:JWT_SECRET = "sua-chave-secreta-com-pelo-menos-32-caracteres"
```

Substitua os valores pelos dados do seu ambiente local. Não compartilhe suas credenciais nem envie chaves secretas ao repositório.

### 3. Executar a aplicação

Na pasta raiz do projeto, execute:

```powershell
.\mvnw.cmd spring-boot:run
```

A aplicação será iniciada na porta `8080`.

### 4. Build do projeto

Para compilar o projeto e executar as verificações:

```powershell
.\mvnw.cmd clean package
```

O arquivo `.jar` será gerado no diretório `target/`.
