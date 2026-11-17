CREATE TABLE IF NOT EXISTS usuario (
    id BIGSERIAL PRIMARY KEY,
    login VARCHAR(120) NOT NULL,
    senha VARCHAR(60) NOT NULL,
    CONSTRAINT uk_usuario_login UNIQUE (login)
);
