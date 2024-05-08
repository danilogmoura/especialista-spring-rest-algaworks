create table cozinha
(
    id               bigint      not null auto_increment,
    nome             varchar(60) not null,
    data_cadastro    datetime    not null,
    data_atualizacao datetime    not null,

    primary key (id)
) engine = InnoDB
  default charset = utf8;
