# API Aluno Online

API REST desenvolvida em **Java 21** e **Spring Boot 4**, voltada ao gerenciamento de informações acadêmicas.

A aplicação oferece operações CRUD para alunos, professores e disciplinas, gerenciamento de matrículas e controle de acesso por meio de autenticação **JWT (JSON Web Token)**. Também conta com criptografia de senhas utilizando **BCrypt**, gerenciamento de migrações com **Flyway** e documentação interativa com **Swagger OpenAPI**.

## Acesso à documentação (Swagger UI)

Com a aplicação em execução, acesse a documentação interativa para visualizar e testar os endpoints diretamente pelo navegador:

- **Swagger UI:** http://localhost:8080/swagger-ui/index.html
- **Especificação OpenAPI (JSON):** http://localhost:8080/v3/api-docs

## Como se autenticar no Swagger

A API utiliza autenticação *Stateless* com JWT para proteger as rotas de gerenciamento.

Para testar os endpoints protegidos, siga os passos abaixo:

**1. Cadastre um usuário**

Acesse o endpoint `POST /cadastros` no Swagger e informe um login e uma senha de sua preferência:

```json
{
  "login": "seu_usuario",
  "senha": "sua_senha"
}
```

**2. Realize o login**

Acesse o endpoint `POST /login` e informe as mesmas credenciais utilizadas no cadastro.

**3. Obtenha o token JWT**

Execute a requisição e copie o token retornado no corpo da resposta.

**4. Autorize o acesso no Swagger**

No topo da documentação, clique no botão **Authorize** e cole o token no campo de autorização.

Confirme em **Authorize** e feche a janela.

Pronto! Agora você poderá acessar os endpoints protegidos utilizando o token gerado.

> **Importante:** o token deve ser obtido por meio do endpoint de login. Quando o esquema Bearer está configurado no Swagger, basta informar o token no campo de autorização, sem adicionar manualmente o prefixo `Bearer`.

## Tecnologias utilizadas

- **Java 21:** linguagem de programação.
- **Spring Boot 4.0.4:** estrutura principal da aplicação.
- **Spring Security:** configuração de segurança e controle de acesso.
- **JWT (Auth0):** geração e validação de tokens de autenticação.
- **BCrypt:** criptografia de senhas.
- **Spring Data JPA e Hibernate:** persistência e mapeamento objeto-relacional.
- **PostgreSQL 16:** banco de dados relacional.
- **Flyway:** versionamento e gerenciamento de migrações do banco de dados.
- **SpringDoc OpenAPI e Swagger UI:** documentação e testes dos endpoints.
- **Maven:** gerenciamento de dependências e build do projeto.

## Principais endpoints

As rotas de cadastro e autenticação são públicas. Os demais endpoints de gerenciamento exigem autenticação por meio de um token JWT.

### Autenticação e usuários

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/cadastros` | Cadastra um novo usuário com senha protegida por BCrypt. |
| POST | `/login` | Autentica o usuário e gera um token JWT. |

### Professores

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/professores` | Lista os professores cadastrados. |
| GET | `/professores/{id}` | Consulta um professor específico. |
| POST | `/professores` | Cadastra um novo professor. |
| PUT | `/professores/{id}` | Atualiza os dados de um professor. |
| DELETE | `/professores/{id}` | Remove um professor. |

### Alunos

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/alunos` | Lista os alunos cadastrados. |
| GET | `/alunos/{id}` | Consulta um aluno específico. |
| POST | `/alunos` | Cadastra um novo aluno. |
| PUT | `/alunos/{id}` | Atualiza os dados de um aluno. |
| DELETE | `/alunos/{id}` | Remove um aluno. |

### Disciplinas

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/disciplinas` | Lista as disciplinas cadastradas. |
| GET | `/disciplinas/{id}` | Consulta uma disciplina específica. |
| POST | `/disciplinas` | Cadastra uma nova disciplina. |
| PUT | `/disciplinas/{id}` | Atualiza os dados de uma disciplina. |
| DELETE | `/disciplinas/{id}` | Remove uma disciplina. |

### Matrículas

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/matriculas` | Realiza uma matrícula. |
| PATCH | `/matriculas/trancar/{id}` | Realiza o trancamento de uma matrícula. |
| PATCH | `/matriculas/atualizar-notas/{id}` | Atualiza as notas de uma matrícula. |

## Configuração e execução

### 1. Pré-requisitos

Antes de executar a aplicação, certifique-se de ter instalado:

- JDK 21 ou superior.
- PostgreSQL 16.
- Maven (opcional, pois o projeto possui Maven Wrapper).

Crie um banco de dados chamado:

```sql
CREATE DATABASE aluno_online;
```

### 2. Configuração das variáveis de ambiente

Antes de iniciar a aplicação, configure as variáveis de ambiente necessárias para a conexão com o banco de dados e para a assinatura dos tokens JWT.

No PowerShell, execute:

```powershell
$env:DB_URL = "jdbc:postgresql://localhost:5432/aluno_online"
$env:DB_USERNAME = "postgres"
$env:DB_PASSWORD = "sua_senha"
$env:JWT_SECRET = "sua-chave-secreta-com-pelo-menos-32-caracteres"
```

Substitua os valores de exemplo pelas informações do seu ambiente local.

**Atenção:** não compartilhe suas credenciais nem envie senhas ou chaves secretas para o repositório.

### 3. Executando a aplicação

Na pasta raiz do projeto, execute:

```powershell
.\mvnw.cmd spring-boot:run
```

A aplicação será iniciada na porta `8080`.

### 4. Construindo o projeto

Para limpar, compilar e empacotar a aplicação, execute:

```powershell
.\mvnw.cmd clean package
```

O arquivo `.jar` será gerado no diretório `target/`.
