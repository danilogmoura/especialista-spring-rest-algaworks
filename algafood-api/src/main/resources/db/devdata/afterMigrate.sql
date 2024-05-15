set foreign_key_checks = 0;

delete from cidade;
delete from cozinha;
delete from estado;
delete from forma_pagamento;
delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from produto;
delete from restaurante;
delete from restaurante_forma_pagamento;
delete from usuario;
delete from usuario_grupo;
delete from restaurante_usuario_responsavel;
delete from pedido;
delete from item_pedido;

set foreign_key_checks = 1;

alter table cidade auto_increment = 1;
alter table cozinha auto_increment = 1;
alter table estado auto_increment = 1;
alter table forma_pagamento auto_increment = 1;
alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table produto auto_increment = 1;
alter table restaurante auto_increment = 1;
alter table usuario auto_increment = 1;
alter table pedido auto_increment = 1;
alter table item_pedido auto_increment = 1;

insert into cozinha (id, nome, data_cadastro, data_atualizacao) values (1, 'Tailandesa', current_timestamp, current_timestamp);
insert into cozinha (id, nome, data_cadastro, data_atualizacao) values (2, 'Indiana', current_timestamp, current_timestamp);
insert into cozinha (id, nome, data_cadastro, data_atualizacao) values (3, 'Argentina', current_timestamp, current_timestamp);
insert into cozinha (id, nome, data_cadastro, data_atualizacao) values (4, 'Brasileira', current_timestamp, current_timestamp);

insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');

insert into cidade (id, nome, estado_id, data_cadastro, data_atualizacao) values (1, 'Uberlândia', 1, current_timestamp, current_timestamp);
insert into cidade (id, nome, estado_id, data_cadastro, data_atualizacao) values (2, 'Belo Horizonte', 1, current_timestamp, current_timestamp);
insert into cidade (id, nome, estado_id, data_cadastro, data_atualizacao) values (3, 'São Paulo', 2, current_timestamp, current_timestamp);
insert into cidade (id, nome, estado_id, data_cadastro, data_atualizacao) values (4, 'Campinas', 2, current_timestamp, current_timestamp);
insert into cidade (id, nome, estado_id, data_cadastro, data_atualizacao) values (5, 'Fortaleza', 3, current_timestamp, current_timestamp);

insert into restaurante (id, nome, ativo, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, aberto, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Thai Gourmet', true, 10, 1, current_timestamp, current_timestamp, true, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurante (id, nome, ativo, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, aberto) values (2, 'Thai Delivery', true, 9.50, 1, current_timestamp, current_timestamp, true);
insert into restaurante (id, nome, ativo, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, aberto) values (3, 'Tuk Tuk Comida Indiana', true, 15, 2, current_timestamp, current_timestamp, true);
insert into restaurante (id, nome, ativo, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, aberto) values (4, 'Java Steakhouse', true, 12, 3, current_timestamp, current_timestamp, true);
insert into restaurante (id, nome, ativo, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, aberto) values (5, 'Lanchonete do Tio Sam', true, 11, 4, current_timestamp, current_timestamp, true);
insert into restaurante (id, nome, ativo, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, aberto) values (6, 'Bar da Maria', true, 6, 4, current_timestamp, current_timestamp, true);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into permissao (id, nome, descricao, data_cadastro, data_atualizacao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas', current_timestamp, current_timestamp);
insert into permissao (id, nome, descricao, data_cadastro, data_atualizacao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas', current_timestamp, current_timestamp);

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

insert into produto (nome, descricao, preco, ativo, restaurante_id, data_cadastro, data_atualizacao) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1, current_timestamp, current_timestamp);
insert into produto (nome, descricao, preco, ativo, restaurante_id, data_cadastro, data_atualizacao) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1, current_timestamp, current_timestamp);
insert into produto (nome, descricao, preco, ativo, restaurante_id, data_cadastro, data_atualizacao) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2, current_timestamp, current_timestamp);
insert into produto (nome, descricao, preco, ativo, restaurante_id, data_cadastro, data_atualizacao) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3, current_timestamp, current_timestamp);
insert into produto (nome, descricao, preco, ativo, restaurante_id, data_cadastro, data_atualizacao) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3, current_timestamp, current_timestamp);
insert into produto (nome, descricao, preco, ativo, restaurante_id, data_cadastro, data_atualizacao) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4, current_timestamp, current_timestamp);
insert into produto (nome, descricao, preco, ativo, restaurante_id, data_cadastro, data_atualizacao) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4, current_timestamp, current_timestamp);
insert into produto (nome, descricao, preco, ativo, restaurante_id, data_cadastro, data_atualizacao) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5, current_timestamp, current_timestamp);
insert into produto (nome, descricao, preco, ativo, restaurante_id, data_cadastro, data_atualizacao) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6, current_timestamp, current_timestamp);

insert into grupo (nome, data_cadastro, data_atualizacao) values ('Gerente', current_timestamp, current_timestamp);
insert into grupo (nome, data_cadastro, data_atualizacao) values ('Vendedor', current_timestamp, current_timestamp);
insert into grupo (nome, data_cadastro, data_atualizacao) values ('Secretária', current_timestamp, current_timestamp);
insert into grupo (nome, data_cadastro, data_atualizacao) values ('Cadastrador', current_timestamp, current_timestamp);

insert into grupo_permissao (grupo_id, permissao_id) values (1, 1), (1, 2), (2, 1), (2, 2), (3, 1);

insert into usuario (id, nome, email, senha, data_cadastro, data_atualizacao) values (1, 'João da Silva', 'joao.ger@algafood.com', '123', utc_timestamp, utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro, data_atualizacao) values(2, 'Maria Joaquina', 'maria.vnd@algafood.com', '123', utc_timestamp, utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro, data_atualizacao) values(3, 'José Souza', 'jose.aux@algafood.com', '123', utc_timestamp, utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro, data_atualizacao) values(4, 'Sebastião Martins', 'sebastiao.cad@algafood.com', '123', utc_timestamp, utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro, data_atualizacao) values (5, 'Manoel Lima', 'manoel.loja@gmail.com', '123', utc_timestamp, utc_timestamp);

insert into usuario_grupo (usuario_id, grupo_id) values (1, 1), (1, 2), (2, 2);

insert into restaurante_usuario_responsavel (restaurante_id, usuario_id) values (1, 5), (3, 5);

insert into pedido (id, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, status, data_criacao, data_atualizacao, subtotal, taxa_frete, valor_total)
    values (1, 1, 1, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil', 'CRIADO', utc_timestamp, utc_timestamp, 298.90, 10, 308.90);
insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao, data_criacao, data_atualizacao)
    values (1, 1, 1, 1, 78.9, 78.9, null, utc_timestamp, utc_timestamp);
insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao, data_criacao, data_atualizacao)
    values (2, 1, 2, 2, 110, 220, 'Menos picante, por favor', utc_timestamp, utc_timestamp);

insert into pedido (id, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, status, data_criacao, data_atualizacao, subtotal, taxa_frete, valor_total)
    values (2, 4, 1, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro', 'CRIADO', utc_timestamp, utc_timestamp, 79, 0, 79);
insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao, data_criacao, data_atualizacao)
    values (3, 2, 6, 1, 79, 79, 'Ao ponto', utc_timestamp, utc_timestamp);
