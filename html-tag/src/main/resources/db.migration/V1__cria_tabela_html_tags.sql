CREATE TABLE html_tags (
    id SERIAL PRIMARY KEY,
    url VARCHAR(255) NOT NULL,
    tag VARCHAR(50) NOT NULL,
    contador INT NOT NULL
);