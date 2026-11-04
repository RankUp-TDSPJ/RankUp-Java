# RankUp+ — API RESTful (Java / Spring Boot)

Sprint 4 — Domain Driven Design Using Java · Turma 1TDSPJ

| Integrante | RM |
|---|---|
| Tiago Borges Dos Santos | 569926 |
| Flavio Luiz Kuratomi Junior | 571211 |
| João Victor de Jesus Bernardo | 568729 |
| Pedro Andreotti Pugliesi | 569357 |
| Henrique Osuka | 571324 |

## Links

- **Deploy (Render):** https://rankup-java.onrender.com/acoes
  (o plano gratuito do Render "dorme": a primeira requisição pode levar cerca de 1 minuto)
- **Repositório:** https://github.com/RankUp-TDSPJ/RankUp-Java
- **Front-end:** https://rank-up-front.vercel.app

## Ferramentas e versões

- Java JDK **21**
- Spring Boot 4.1.1 (Maven; o projeto traz o Maven Wrapper `mvnw`)
- Lombok (habilite o *annotation processing* na IDE)
- Banco Oracle da FIAP
- IDE usada: IntelliJ IDEA (também abre no Eclipse ou no VS Code como projeto Maven)

## Como rodar

1. **Importar:** na IDE, abra a pasta `CalculadorO2` como projeto Maven (IntelliJ: *File > Open* e escolha o `pom.xml`).
2. **Configurar o banco** com estas variáveis de ambiente (sem elas, as rotas que usam o banco respondem 500):

   | Variável | Valor |
   |---|---|
   | `SPRING_DATASOURCE_URL` | `jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl` |
   | `SPRING_DATASOURCE_USERNAME` | seu RM (ex.: `RM000000`) |
   | `SPRING_DATASOURCE_PASSWORD` | sua senha do Oracle |

3. **Executar:**
   ```bash
   ./mvnw spring-boot:run        # Windows: mvnw.cmd spring-boot:run
   ```
   A API sobe em `http://localhost:8081` (porta definida em `application.properties`).

4. **Com Docker (opcional):** defina `ORACLE_USER` e `ORACLE_PASSWORD` e rode `docker compose up --build`.

## Endpoints

| Verbo | URI | Descrição | Status |
|---|---|---|---|
| POST | `/usuarios` | Cadastra usuário | 201, 400, 409 |
| POST | `/login` | Login | 200, 400, 404 |
| GET | `/usuarios/{id}` | Busca usuário | 200, 404 |
| PUT | `/usuarios/{id}` | Atualiza usuário | 200, 400, 404, 409 |
| GET | `/usuarios` | Lista usuários (header `X-Usuario-Id` de admin) | 200, 403 |
| DELETE | `/usuarios/{id}` | Exclui usuário (header `X-Usuario-Id`) | 204, 403, 404 |
| GET | `/acoes` | Lista as ações sustentáveis | 200 |
| POST | `/usuarios/{id}/registros` | Registra ação do usuário | 201, 400, 404 |
| GET | `/usuarios/{id}/registros` | Histórico de ações do usuário | 200, 404 |
| GET | `/ranking?limite=N` | Ranking de usuários | 200, 400 |
| GET | `/usuarios/{id}/posicao` | Posição do usuário no ranking | 200, 404 |

Erros sempre no formato `{ "erro": "mensagem" }` (classe `exception/TratadordeErros`). Qualquer erro inesperado responde 500.

## Estrutura

`controller` (Resource) · `service` (BO, regras de negócio) · `dao` (DAO + `ConnectionFactory`) · `models` · `dto` · `mapper` ·
`config/CorsFilter` (libera o front-end em `localhost` e `*.vercel.app`) · `exception` · diagramas UML em `diagrama/`.
