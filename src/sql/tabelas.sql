CREATE TABLE tb_cursos(
	id int not null auto_increment primary key,
	nome varchar(2000) not null,
	data_processamento date not null,
	periodo_inicial varchar(100) not null,
	periodo_final varchar(100) not null,
	sequencial int not null,
	versao_layout varchar(100)
);

CREATE TABLE tb_fases(
	id int not null auto_increment primary key,
	nome varchar(100) not null,
	quantidade_disciplinas int not null,
	quantidade_professores int not null
);

CREATE TABLE tb_disciplinas(
	id varchar(100) primary key,
	dia_semana int not null,
	quantidade_professores int not null
);

CREATE TABLE tb_professores(
	id int not null auto_increment primary key,
	nome varchar(100) not null,
	titulo_docente int not null
);