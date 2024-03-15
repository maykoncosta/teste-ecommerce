CREATE TABLE IF NOT EXISTS marca (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    codigo VARCHAR(20) NOT NULL,
    descricao VARCHAR(50),
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_ultima_atualizacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario_criacao VARCHAR(100) NOT NULL,
    usuario_atualizacao VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS categoria (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    codigo VARCHAR(20) NOT NULL,
    descricao VARCHAR(50),
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_ultima_atualizacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario_criacao VARCHAR(100) NOT NULL,
    usuario_atualizacao VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS produto (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    codigo VARCHAR(20) NOT NULL,
    descricao VARCHAR(100),
    preco DECIMAL(10, 2),
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_ultima_atualizacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario_criacao VARCHAR(100) NOT NULL,
    usuario_atualizacao VARCHAR(100) NOT NULL,
    marca_id BIGINT,
    categoria_id BIGINT,
    FOREIGN KEY (marca_id) REFERENCES marca(id),
    FOREIGN KEY (categoria_id) REFERENCES categoria(id)
    );

CREATE TABLE IF NOT EXISTS Produto_Categoria (
    produto_id BIGINT NOT NULL,
    categoria_id BIGINT NOT NULL,
    PRIMARY KEY (produto_id, categoria_id)
);
