create database sicombd;

use sicombd;

create table material(

	idMaterial int primary key auto_increment not null,
    descricao varchar(100) not null,
    tipoUnid varchar(30) not null,
    saldo int not null,
    categoria varchar(30) not null,
    localizacao varchar(30)

);

create table solicitante(

	idSolicitante int primary key auto_increment not null,
    siape int not null,
    nome varchar(80) not null,
    setor varchar(30) not null,
    ocupacao varchar(50) not null

);

create table usuario(

	idUsuario int primary key auto_increment not null,
    siape int not null,
    nome varchar(80) not null,
    senha varchar(20) not null,
    tipo varchar(10) not null

);

create table entrada(

	idEntrada int primary key auto_increment not null,
    idMaterial int not null,
    qtd int not null,
    empenho varchar(50) not null,
    fornecedor varchar(50) not null,
    dataEntrada date not null,
    
    FOREIGN KEY (idMaterial) REFERENCES material(idMaterial)

);

create table solicitacao(

	idSolicitacao int primary key auto_increment not null,
    idMaterial int not null,
    idSolicitante int not null,
    idUsuario int not null,
    qtdFornecida int not null,
    dataSolicitacao date not null,
    
    FOREIGN KEY (idMaterial) REFERENCES material(idMaterial),
    FOREIGN KEY (idSolicitante) REFERENCES solicitante(idSolicitante),
    FOREIGN KEY (idUsuario) REFERENCES usuario(idUsuario)

);

