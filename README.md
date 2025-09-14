![Programação-Arquitetura Java](https://github.com/jacqueline-oliveira/3698-java-clean-architecture/assets/66698429/0191ea20-432f-4583-a391-f01558004fb9)
![](https://img.shields.io/github/license/alura-cursos/android-com-kotlin-personalizando-ui)

# CodeChella

App de simulação de um site para venda de ingressos de eventos diversos

## 🔨 Objetivos do projeto

- Conhecer os diferentes tipos de arquitetura de software;
- Aprender os princípios da Clean Architecture;
- Implementar um projeto com separação de responsabilidades e isolamento do domínio;
- Entender sobre entidades, objetos de valor, casos de uso, repositórios e controladores;
- Analisar os prós e contras de arquiteturas que utilizam muitas camadas de abstração.


# alura-clean-architecture

```mermaid
graph TD
    subgraph "Infra - Controller"
        A[POST /usuarios com UsuarioDto] --> B(UsuarioController);
    end

    subgraph "Application - Use Cases"
        B -- Chama --> C(CriarUsuario);
    end

    subgraph "Domain - Entities"
        C -- Cria --> D["new Usuario(...)"];
    end

    subgraph "Application - Gateways"
        C -- Chama o método cadastrarUsuario --> E(RepositorioDeUsuario);
    end

    subgraph "Infra - Gateways/Persistence"
        E -- Implementado por --> F(RepositorioDeUsuarioJpa);
        F -- Converte Usuario para UsuarioEntity --> G(UsuarioEntityMapper);
        F -- Usa --> H(UsuarioRepository);
        H -- Salva no Banco de Dados --> I[(Banco de Dados)];
    end

    subgraph "Retorno do Fluxo"
        I -- Retorna UsuarioEntity salvo --> H;
        H -- Retorna para --> F;
        F -- Converte UsuarioEntity para Usuario --> G;
        G -- Retorna Usuario para --> F;
        F -- Retorna Usuario salvo para --> C;
        C -- Retorna Usuario salvo para --> B;
        B -- Converte Usuario para UsuarioDto e retorna --> J{200 OK com UsuarioDto};
    end

    style B fill:#f9f,stroke:#333,stroke-width:2px
    style C fill:#ccf,stroke:#333,stroke-width:2px
    style E fill:#ccf,stroke:#333,stroke-width:2px

```
