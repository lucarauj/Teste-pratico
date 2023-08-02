[![NPM](https://img.shields.io/npm/l/react)](https://github.com/lucarauj/Teste-pratico/blob/main/LICENSE)

# Teste prático - Desenvolvimento de sistema

## Problema

- No código de uma página HTML há diversas tags para apresentar o conteúdo da melhor forma. 
- Para uma análise mais cautelosa, há o interesse de contabilizar a quantidade de cada tag HTML em uma determinada página.
- É necessário criar um programa para identificar as tags HTML existentes nas páginas que forem carregadas por meio da URL informada. 
- Deve ser contado quantas vezes cada tag aparece em cada página.
- É necessário mostrar as informações coletadas para possibilitar verificar os dados da URL informada. 
- Assim, as URL, tags e as respectivas contagens devem ser armazenadas em um banco de dados.

<br>

## Tecnologias utilizadas

- Java 17
- Maven
- Spring Boot
- PostgreSQL
- Git
- GitHub
- Flyway
- Lombok
- Jsoup
- Thymeleaf
- Postman
- Swagger

<br>

## Diagrama de Classe

```mermaid
classDiagram
    class HtmlTag {
        + Long id
        + String url
        + String tag
        + int contador
    }
```

<br>

## Swagger

- http://localhost:8080/swagger-ui/index.html#/
- http://localhost:8080/v3/api-docs

<br>

## Validações

- Não permite cadastrar uma URL mais de uma vez;
- Verifica se o endereço é uma URL válida;
- Endereços salvos no banco para teste:

```
- https://www.example.com
- https://novidades100.com.br/gadget/web/php/gerar_pagina
- https://www.iana.org/help/example-domains
```

<br>


## 👨‍🎓 Autor

#### Lucas Araujo

<a href="https://www.linkedin.com/in/lucarauj"><img alt="lucarauj | LinkdeIN" width="40px" src="https://user-images.githubusercontent.com/43545812/144035037-0f415fc7-9f96-4517-a370-ccc6e78a714b.png" /></a>

