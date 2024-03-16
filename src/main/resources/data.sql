-- Inserir marcas
INSERT INTO Marca (id, codigo, descricao, ativo, data_criacao, data_ultima_atualizacao, usuario_criacao, usuario_atualizacao) VALUES (100, 'SAMSUNG', 'Samsung Electronics', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin');
INSERT INTO Marca (id, codigo, descricao, ativo, data_criacao, data_ultima_atualizacao, usuario_criacao, usuario_atualizacao) VALUES (200, 'APPLE', 'Apple Inc.', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin');
INSERT INTO Marca (id, codigo, descricao, ativo, data_criacao, data_ultima_atualizacao, usuario_criacao, usuario_atualizacao) VALUES (300, 'NIKE', 'Nike, Inc.', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin');
INSERT INTO Marca (id, codigo, descricao, ativo, data_criacao, data_ultima_atualizacao, usuario_criacao, usuario_atualizacao) VALUES (400, 'LENOVO', 'Lenovo Group Ltd.', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin');
INSERT INTO Marca (id, codigo, descricao, ativo, data_criacao, data_ultima_atualizacao, usuario_criacao, usuario_atualizacao) VALUES (500, 'ADIDAS', 'Adidas AG', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin');

-- Inserir categorias
INSERT INTO Categoria (id, codigo, descricao, ativo, data_criacao, data_ultima_atualizacao, usuario_criacao, usuario_atualizacao) VALUES (100, 'ELETRONICOS', 'Eletrônicos', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin');
INSERT INTO Categoria (id, codigo, descricao, ativo, data_criacao, data_ultima_atualizacao, usuario_criacao, usuario_atualizacao) VALUES (200, 'VESTUARIO', 'Vestuário', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin');
INSERT INTO Categoria (id, codigo, descricao, ativo, data_criacao, data_ultima_atualizacao, usuario_criacao, usuario_atualizacao) VALUES (300, 'CALCADOS', 'Calçados', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin');

-- Inserir produtos
INSERT INTO Produto (id, codigo, descricao, preco, marca_id, ativo, data_criacao, data_ultima_atualizacao, usuario_criacao, usuario_atualizacao) VALUES (100, 'GALAXY-S20', 'Samsung Galaxy S20', 2999.99, 100, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin');
INSERT INTO Produto (id, codigo, descricao, preco, marca_id, ativo, data_criacao, data_ultima_atualizacao, usuario_criacao, usuario_atualizacao) VALUES (200, 'IPHONE-12', 'Apple iPhone 12', 4999.99, 200, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin');
INSERT INTO Produto (id, codigo, descricao, preco, marca_id, ativo, data_criacao, data_ultima_atualizacao, usuario_criacao, usuario_atualizacao) VALUES (300, 'CORTA-VENTO', 'Nike Corta vento', 299.99, 300, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin');
INSERT INTO Produto (id, codigo, descricao, preco, marca_id, ativo, data_criacao, data_ultima_atualizacao, usuario_criacao, usuario_atualizacao) VALUES (400, 'THINKPAD-X1', 'Lenovo ThinkPad X1 Carbon', 1999.99, 400, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin');
INSERT INTO Produto (id, codigo, descricao, preco, marca_id, ativo, data_criacao, data_ultima_atualizacao, usuario_criacao, usuario_atualizacao) VALUES (500, 'ULTRABOOST', 'Adidas Ultraboost', 159.99, 500, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin');

INSERT INTO Produto_Categoria (produto_id, categoria_id) VALUES (100, 100);
INSERT INTO Produto_Categoria (produto_id, categoria_id) VALUES (200, 100);
INSERT INTO Produto_Categoria (produto_id, categoria_id) VALUES (300, 200);
INSERT INTO Produto_Categoria (produto_id, categoria_id) VALUES (400, 100);
INSERT INTO Produto_Categoria (produto_id, categoria_id) VALUES (500, 300);