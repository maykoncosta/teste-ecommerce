-- Inserir marcas
INSERT INTO Marca (id, codigo, descricao, ativo, data_criacao, data_ultima_atualizacao, usuario_criacao, usuario_atualizacao) VALUES (1, 'SAMSUNG', 'Samsung Electronics', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin');
INSERT INTO Marca (id, codigo, descricao, ativo, data_criacao, data_ultima_atualizacao, usuario_criacao, usuario_atualizacao) VALUES (2, 'APPLE', 'Apple Inc.', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin');
INSERT INTO Marca (id, codigo, descricao, ativo, data_criacao, data_ultima_atualizacao, usuario_criacao, usuario_atualizacao) VALUES (3, 'NIKE', 'Nike, Inc.', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin');
INSERT INTO Marca (id, codigo, descricao, ativo, data_criacao, data_ultima_atualizacao, usuario_criacao, usuario_atualizacao) VALUES (4, 'LENOVO', 'Lenovo Group Ltd.', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin');
INSERT INTO Marca (id, codigo, descricao, ativo, data_criacao, data_ultima_atualizacao, usuario_criacao, usuario_atualizacao) VALUES (5, 'ADIDAS', 'Adidas AG', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin');

-- Inserir categorias
INSERT INTO Categoria (id, codigo, descricao, ativo, data_criacao, data_ultima_atualizacao, usuario_criacao, usuario_atualizacao) VALUES (1, 'ELETRONICOS', 'Eletrônicos', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin');
INSERT INTO Categoria (id, codigo, descricao, ativo, data_criacao, data_ultima_atualizacao, usuario_criacao, usuario_atualizacao) VALUES (2, 'VESTUARIO', 'Vestuário', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin');
INSERT INTO Categoria (id, codigo, descricao, ativo, data_criacao, data_ultima_atualizacao, usuario_criacao, usuario_atualizacao) VALUES (3, 'CALCADOS', 'Calçados', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin');

-- Inserir produtos
INSERT INTO Produto (id, codigo, descricao, preco, marca_id, ativo, data_criacao, data_ultima_atualizacao, usuario_criacao, usuario_atualizacao) VALUES (1, 'GALAXY-S20', 'Samsung Galaxy S20', 2999.99, 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin');
INSERT INTO Produto (id, codigo, descricao, preco, marca_id, ativo, data_criacao, data_ultima_atualizacao, usuario_criacao, usuario_atualizacao) VALUES (2, 'IPHONE-12', 'Apple iPhone 12', 4999.99, 2, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin');
INSERT INTO Produto (id, codigo, descricao, preco, marca_id, ativo, data_criacao, data_ultima_atualizacao, usuario_criacao, usuario_atualizacao) VALUES (3, 'CORTA-VENTO', 'Nike Corta vento', 299.99, 3, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin');
INSERT INTO Produto (id, codigo, descricao, preco, marca_id, ativo, data_criacao, data_ultima_atualizacao, usuario_criacao, usuario_atualizacao) VALUES (4, 'THINKPAD-X1', 'Lenovo ThinkPad X1 Carbon', 1999.99, 4, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin');
INSERT INTO Produto (id, codigo, descricao, preco, marca_id, ativo, data_criacao, data_ultima_atualizacao, usuario_criacao, usuario_atualizacao) VALUES (5, 'ULTRABOOST', 'Adidas Ultraboost', 159.99, 5, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin');

INSERT INTO Produto_Categoria (produto_id, categoria_id) VALUES (1, 1);
INSERT INTO Produto_Categoria (produto_id, categoria_id) VALUES (2, 1);
INSERT INTO Produto_Categoria (produto_id, categoria_id) VALUES (3, 2);
INSERT INTO Produto_Categoria (produto_id, categoria_id) VALUES (4, 1);
INSERT INTO Produto_Categoria (produto_id, categoria_id) VALUES (5, 3);