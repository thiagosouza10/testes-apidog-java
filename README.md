# Projeto Dog API - Testes Automatizados (Java)

Projeto de testes automatizados para a Dog API utilizando Java, REST Assured, JUnit 5 e Allure Report.

- **Dog API**: https://dog.ceo/dog-api/documentation

## Pre-requisitos

- **Java JDK 21**: https://www.oracle.com/java/technologies/downloads/
- **Maven**: https://maven.apache.org/download.cgi
- **Git**: https://git-scm.com/install/windows
- **Allure CLI**: https://allurereport.org/docs/install/

## Arquitetura

```text
├── .github/workflows/              # GitHub Actions
│   └── tests.yml
├── src/
│   ├── main/java/
│   │   └── org/example/
│   │       └── Main.java
│   └── test/
│       ├── java/                   # Testes automatizados
│       │   ├── BreedImagesRandomTest.java
│       │   ├── BreedImagesTest.java
│       │   ├── BreedListAllTest.java
│       │   ├── base/
│       │   │   └── BaseTest.java
│       │   └── services/
│       │       └── DogService.java
│       └── resources/
│           └── schemas/            # Schemas JSON para validacao de contrato
│               ├── breeds-images-random-schema.json
│               ├── breeds-images-schema.json
│               └── breeds-list-all-schema.json
├── allure-results/                 # Resultados gerados pelo Allure
├── target/                         # Arquivos gerados pelo Maven
├── pom.xml
└── README.md
```

## Estrutura dos testes

| Arquivo | Descricao |
|---------|-----------|
| `BreedListAllTest.java` | Valida a listagem de todas as racas, sub-racas e endpoint invalido |
| `BreedImagesTest.java` | Valida imagens de uma raca especifica, contrato e cenarios de erro |
| `BreedImagesRandomTest.java` | Valida imagem aleatoria e contrato da resposta |
| `base/BaseTest.java` | Configura a URL base da API |
| `services/DogService.java` | Centraliza as chamadas para os endpoints da Dog API |
| `resources/schemas/` | Armazena os schemas usados nas validacoes de contrato |

## Passo a passo para executar os testes

```bash
# Clonar o projeto
git clone https://github.com/thiagosouza10/testes-apidog-java.git

# Entrar na pasta do projeto
cd testes-apidog-java

# Executar os testes
mvn clean test
```

## Gerando o relatorio Allure localmente

Depois de executar os testes, rode:

```bash
# Abrir o relatorio em um servidor local temporario
allure serve allure-results
```

Ou gere os arquivos estaticos do relatorio:

```bash
allure generate allure-results --clean -o allure-report
```

## Rodando os testes no GitHub Actions

### Como executar manualmente

1. Acesse a aba **Actions** no repositorio
2. Selecione o workflow **Java API Tests with Allure**
3. Clique em **Run workflow**
4. Escolha a branch, por exemplo `main`
5. Clique em **Run workflow**

### Visualizar relatorio dos testes

Apos a execucao:

- O relatorio Allure e publicado automaticamente no GitHub Pages
- Acesse o link em: https://thiagosouza10.github.io/testes-apidog-java/
