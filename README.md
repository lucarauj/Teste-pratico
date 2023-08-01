[![NPM](https://img.shields.io/npm/l/react)](https://github.com/lucarauj/Teste-pratico/blob/main/LICENSE)

# Teste prático - Desenvolvimento de sistema

## Problema

- No código de uma página HTML há diversas tags para apresentar o conteúdo da melhor forma. 
- Para uma análise mais cautelosa, há o interesse de contabilizar a quantidade de cada tag HTML em uma determinada página.
- É necessário criar um programa para identificar as tags HTML existentes nas páginas que forem carregadas por meio de uma lista de URL informada. 
- Deve ser contado quantas vezes cada tag aparece em cada página.
- É necessário mostrar as informações coletadas para possibilitar verificar os dados da URL informada. 
- Assim, as URL, tags e as respectivas contagens devem ser armazenadas em um banco de dados.

<br>

## Tecnologias utilizadas

- Java 17
- Maven
- Spring Boot
- PostgreSQL
- Flyway
- Lombok
- Jsoup
- Thymeleaf

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

## 👨‍🎓 Autor

#### Lucas Araujo

<a href="https://www.linkedin.com/in/lucarauj"><img alt="lucarauj | LinkdeIN" width="40px" src="https://user-images.githubusercontent.com/43545812/144035037-0f415fc7-9f96-4517-a370-ccc6e78a714b.png" /></a>

