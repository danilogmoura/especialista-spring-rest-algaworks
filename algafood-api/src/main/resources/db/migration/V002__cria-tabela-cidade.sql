create table cidade
(
    id               bigint      not null auto_increment,
    nome_cidade      varchar(80) not null,
    nome_estado      varchar(80) not null,
    data_cadastro    datetime    not null,
    data_atualizacao datetime    not null,

    primary key (id)
) engine = InnoDB
  default charset = utf8;
