/*____________________________________   LOCALIDADES   ____________________________________*/

insert into localidades (nome) values ('Aveiro');
insert into localidades (nome) values ('Beja');
insert into localidades (nome) values ('Braga');
insert into localidades (nome) values ('Bragança');
insert into localidades (nome) values ('Castelo Branco');
insert into localidades (nome) values ('Coimbra');
insert into localidades (nome) values ('Évora');
insert into localidades (nome) values ('Faro');
insert into localidades (nome) values ('Guarda');
insert into localidades (nome) values ('Leiria');
insert into localidades (nome) values ('Lisboa');
insert into localidades (nome) values ('Portalegre');
insert into localidades (nome) values ('Porto');
insert into localidades (nome) values ('Santarém');
insert into localidades (nome) values ('Setúbal');
insert into localidades (nome) values ('Viana do Castelo');
insert into localidades (nome) values ('Viseu');
insert into localidades (nome) values ('Vila Real');


/*____________________________________   UTILIZADORES   ____________________________________*/

insert into utilizadores (nomeUtilizador,nome,email,password,morada,telemovel,localidade,codigoPostal,dataNascimento,contaPaypal,contaMBNet,dataRegisto,avaliacaoComprador,imagem,descricao,nrAvaliacoes,nrVendas,nrCompras,totalGanho,totalGasto,totalAVender,avaliacoesPositivas,avaliacoesNegativas,avaliacaoVendedor,nrAvaliacoesVendaPos,nrAvaliacoesVendaNeg,dadosCompletos) values
('insatisfeito', 'Daniel Carvalho', 'dapcarvalho@gmail.com', 'password85', 'Rua António Gomes Robeiro Ent 300 4º Dto, Fr', '919985936', 'Porto', '4620-133', timestamp'2007-07-12 09:00:00.123456789', 'dapcarvalho@gmail.com', 'dapcarvalho@gmail.com', CURRENT_TIMESTAMP, 85, hextoraw('FFD8FFE00010'),
'Olá sou o 85!', 1000, 1000, 0, 1000, 0, 1000, 1000, 0, 85, 1000, 0, 1 );
insert into utilizadores (nomeUtilizador,nome,email,password,morada,telemovel,localidade,codigoPostal,dataNascimento,contaPaypal,contaMBNet,dataRegisto,avaliacaoComprador,imagem,descricao,nrAvaliacoes,nrVendas,nrCompras,totalGanho,totalGasto,totalAVender,avaliacoesPositivas,avaliacoesNegativas,avaliacaoVendedor,nrAvaliacoesVendaPos,nrAvaliacoesVendaNeg,dadosCompletos) values
('gerente', 'André Santos', 'andre@gmail.com', 'password62', 'Rua do Doomz', '91111111', 'Braga', '4620-133', timestamp'2007-12-12 09:00:00.123456789', 'andre@gmail.com', 'andreo@gmail.com', CURRENT_TIMESTAMP, 62, hextoraw('FFD8FFE00010'),
'Olá sou o 62!', 1000, 1000, 0, 1000, 0, 1000, 1000, 0, 62, 1000, 0, 1 );
insert into utilizadores (nomeUtilizador,nome,email,password,morada,telemovel,localidade,codigoPostal,dataNascimento,contaPaypal,contaMBNet,dataRegisto,avaliacaoComprador,imagem,descricao,nrAvaliacoes,nrVendas,nrCompras,totalGanho,totalGasto,totalAVender,avaliacoesPositivas,avaliacoesNegativas,avaliacaoVendedor,nrAvaliacoesVendaPos,nrAvaliacoesVendaNeg,dadosCompletos) values
('treze', 'treze Araujo', 'treze@gmail.com', 'password13', 'Rua do treze', '912222222', 'Braga', '4620-133', timestamp'2007-07-10 09:00:00.123456789', 'treze@gmail.com', 'treze@gmail.com', CURRENT_TIMESTAMP, 13, hextoraw('FFD8FFE00010'),
'Olá sou o 13!', 1000, 1000, 0, 1000, 0, 1000, 1000, 0, 13, 1000, 0, 1 );
insert into utilizadores (nomeUtilizador,nome,email,password,morada,telemovel,localidade,codigoPostal,dataNascimento,contaPaypal,contaMBNet,dataRegisto,avaliacaoComprador,imagem,descricao,nrAvaliacoes,nrVendas,nrCompras,totalGanho,totalGasto,totalAVender,avaliacoesPositivas,avaliacoesNegativas,avaliacaoVendedor,nrAvaliacoesVendaPos,nrAvaliacoesVendaNeg,dadosCompletos) values
('kamikaze', 'Helena', '41@gmail.com', 'password41', 'Rua  300', '913333333', 'Aveiro', '4620-133', timestamp'2007-07-12 09:00:00.123456789', '41@gmail.com', '41@gmail.com', CURRENT_TIMESTAMP, 41, hextoraw('FFD8FFE00010'),
'Olá sou a 41!', 1000, 1000, 0, 1000, 0, 1000, 1000, 0, 41, 1000, 0, 1 );
insert into utilizadores (nomeUtilizador,nome,email,password,morada,telemovel,localidade,codigoPostal,dataNascimento,contaPaypal,contaMBNet,dataRegisto,avaliacaoComprador,imagem,descricao,nrAvaliacoes,nrVendas,nrCompras,totalGanho,totalGasto,totalAVender,avaliacoesPositivas,avaliacoesNegativas,avaliacaoVendedor,nrAvaliacoesVendaPos,nrAvaliacoesVendaNeg,dadosCompletos) values
('jose', 'jose jose', 'reze@gmail.com', 'password', 'Rua do treze', '912288222', 'Braga', '4620-133', timestamp'2007-07-10 09:00:00.123456789', 'treze@gmail.com', 'treze@gmail.com', CURRENT_TIMESTAMP, 13, hextoraw('FFD8FFE00010'),
'Olá sou o vegeta', 1000, 1000, 0, 1000, 0, 1000, 1000, 0, 13, 1000, 0, 1 );
insert into utilizadores (nomeUtilizador,nome,email,password,morada,telemovel,localidade,codigoPostal,dataNascimento,contaPaypal,contaMBNet,dataRegisto,avaliacaoComprador,imagem,descricao,nrAvaliacoes,nrVendas,nrCompras,totalGanho,totalGasto,totalAVender,avaliacoesPositivas,avaliacoesNegativas,avaliacaoVendedor,nrAvaliacoesVendaPos,nrAvaliacoesVendaNeg,dadosCompletos) values
('ana', 'Ana Helena', '43@gmail.com', 'password45', 'Rua  300', '913300333', 'Aveiro', '4620-133', timestamp'2007-07-12 09:00:00.123456789', '41@gmail.com', '41@gmail.com', CURRENT_TIMESTAMP, 41, hextoraw('FFD8FFE00010'),
'Olá sou a ana', 1000, 1000, 0, 1000, 0, 1000, 1000, 0, 41, 1000, 0, 1 );
insert into utilizadores (nomeUtilizador,nome,email,password,morada,telemovel,localidade,codigoPostal,dataNascimento,contaPaypal,contaMBNet,dataRegisto,avaliacaoComprador,imagem,descricao,nrAvaliacoes,nrVendas,nrCompras,totalGanho,totalGasto,totalAVender,avaliacoesPositivas,avaliacoesNegativas,avaliacaoVendedor,nrAvaliacoesVendaPos,nrAvaliacoesVendaNeg,dadosCompletos) values
('martins', 'joao martins', 'jm@gmail.com', 'passwordjm', 'Rua do treze', '912989222', 'Braga', '4620-133', timestamp'2007-07-10 09:00:00.123456789', 'treze@gmail.com', 'treze@gmail.com', CURRENT_TIMESTAMP, 13, hextoraw('FFD8FFE00010'),
'Olá sou o joao!', 1000, 1000, 0, 1000, 0, 1000, 1000, 0, 13, 1000, 0, 1 );
insert into utilizadores (nomeUtilizador,nome,email,password,morada,telemovel,localidade,codigoPostal,dataNascimento,contaPaypal,contaMBNet,dataRegisto,avaliacaoComprador,imagem,descricao,nrAvaliacoes,nrVendas,nrCompras,totalGanho,totalGasto,totalAVender,avaliacoesPositivas,avaliacoesNegativas,avaliacaoVendedor,nrAvaliacoesVendaPos,nrAvaliacoesVendaNeg,dadosCompletos) values
('santos', 'joana santos', 'lena@gmail.com', 'password41', 'Rua  300', '913121333', 'Aveiro', '4620-133', timestamp'2007-07-12 09:00:00.123456789', '41@gmail.com', '41@gmail.com', CURRENT_TIMESTAMP, 41, hextoraw('FFD8FFE00010'),
'Olá sou a joana', 1000, 1000, 0, 1000, 0, 1000, 1000, 0, 41, 1000, 0, 1 );


/*____________________________________   ADMINISTRADORES   ____________________________________*/

insert into administradores (login,password) values ('insatisfeito',md5('password85'));
insert into administradores (login,password) values ('gerente',md5('password62'));
insert into administradores (login,password) values ('treze',md5('password13'));

/*____________________________________   MENSAGENS   ____________________________________*/

insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'treze','insatisfeito', 'Mensagem Administrador', 'Mensagem de teste 1', 'msg1', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'treze','insatisfeito', 'Mensagem Administrador', 'Mensagem de teste 2', 'msg2', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'treze','insatisfeito', 'Mensagem Administrador', 'Mensagem de teste 3', 'msg3', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'kamikaze','insatisfeito', 'Mensagem Administrador', 'Mensagem de teste 4', 'msg4', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'insatisfeito','kamikaze', 'Mensagem Administrador', 'Mensagem de teste 5', 'msg5', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'insatisfeito','kamikaze', 'Mensagem Administrador', 'Mensagem de teste 6', 'msg6', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'insatisfeito','kamikaze', 'Mensagem Administrador', 'Mensagem de teste 7', 'msg7', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'insatisfeito','kamikaze', 'Mensagem Administrador', 'Mensagem de teste 8', 'msg8', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'treze','insatisfeito', 'Mensagem Administrador', 'Mensagem de teste 1', 'msg1', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'treze','insatisfeito', 'Mensagem Administrador', 'Mensagem de teste 2', 'msg2', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'kamikaze','insatisfeito', 'Mensagem Administrador', 'Mensagem de teste 3', 'msg3', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'treze','insatisfeito', 'Mensagem Administrador', 'Mensagem de teste 4', 'msg4', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'insatisfeito','treze', 'Mensagem Administrador', 'Mensagem de teste 5', 'msg5', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'insatisfeito','treze', 'Mensagem Administrador', 'Mensagem de teste 6', 'msg6', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'insatisfeito','treze', 'Mensagem Administrador', 'Mensagem de teste 7', 'msg7', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'insatisfeito','treze', 'Mensagem Administrador', 'Mensagem de teste 8', 'msg8', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'treze','insatisfeito', 'Mensagem Administrador', 'Mensagem de teste 1', 'msg1', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'treze','insatisfeito', 'Mensagem Administrador', 'Mensagem de teste 2', 'msg2', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'treze','insatisfeito', 'Mensagem Administrador', 'Mensagem de teste 3', 'msg3', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'treze','insatisfeito', 'Mensagem Administrador', 'Mensagem de teste 4', 'msg4', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'insatisfeito','treze', 'Mensagem Administrador', 'Mensagem de teste 5', 'msg5', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'insatisfeito','treze', 'Mensagem Administrador', 'Mensagem de teste 6', 'msg6', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'insatisfeito','kamikaze', 'Mensagem Administrador', 'Mensagem de teste 7', 'msg7', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'insatisfeito','treze', 'Mensagem Administrador', 'Mensagem de teste 8', 'msg8', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'treze','insatisfeito', 'Mensagem Administrador', 'Mensagem de teste 1', 'msg1', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'treze','insatisfeito', 'Mensagem Administrador', 'Mensagem de teste 2', 'msg2', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'kamikaze','insatisfeito', 'Mensagem Administrador', 'Mensagem de teste 3', 'msg3', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'kamikaze','gerente', 'Mensagem Administrador', 'Mensagem de teste 4', 'msg4', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'gerente','kamikaze', 'Mensagem Administrador', 'Mensagem de teste 5', 'msg5', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'gerente','kamikaze', 'Mensagem Administrador', 'Mensagem de teste 6', 'msg6', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'gerente','treze', 'Mensagem Administrador', 'Mensagem de teste 7', 'msg7', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'insatisfeito','treze', 'Mensagem Administrador', 'Mensagem de teste 8', 'msg8', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'treze','insatisfeito', 'Mensagem Administrador', 'Mensagem de teste 1', 'msg1', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'treze','insatisfeito', 'Mensagem Administrador', 'Mensagem de teste 2', 'msg2', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'kamikaze','gerente', 'Mensagem Administrador', 'Mensagem de teste 3', 'msg3', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'kamikaze','gerente', 'Mensagem Administrador', 'Mensagem de teste 4', 'msg4', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'gerente','kamikaze', 'Mensagem Administrador', 'Mensagem de teste 5', 'msg5', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'gerente','kamikaze', 'Mensagem Administrador', 'Mensagem de teste 6', 'msg6', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'insatisfeito','treze', 'Mensagem Administrador', 'Mensagem de teste 7', 'msg7', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'insatisfeito','treze', 'Mensagem Administrador', 'Mensagem de teste 8', 'msg8', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'treze','insatisfeito', 'Mensagem Administrador', 'Mensagem de teste 1', 'msg1', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'treze','gerente', 'Mensagem Administrador', 'Mensagem de teste 2', 'msg2', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'kamikaze','gerente', 'Mensagem Administrador', 'Mensagem de teste 3', 'msg3', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'kamikaze','gerente', 'Mensagem Administrador', 'Mensagem de teste 4', 'msg4', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'insatisfeito','kamikaze', 'Mensagem Administrador', 'Mensagem de teste 5', 'msg5', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'gerente','treze', 'Mensagem Administrador', 'Mensagem de teste 6', 'msg6', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'gerente','treze', 'Mensagem Administrador', 'Mensagem de teste 7', 'msg7', CURRENT_TIMESTAMP, 0, 0, 0);
insert into mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) values (mensagens_id.nextval, 'gerente','treze', 'Mensagem Administrador', 'Mensagem de teste 8', 'msg8', CURRENT_TIMESTAMP, 0, 0, 0);

/*____________________________________   CATEGORIAS   ____________________________________*/


insert into categorias (nome) values ('Antiguidades');
insert into categorias (nome) values ('Arte');
insert into categorias (nome) values ('Livros');
insert into categorias (nome) values ('Indústria');
insert into categorias (nome) values ('Câmaras e Fotografia');
insert into categorias (nome) values ('Telecomunicações e Acessórios');
insert into categorias (nome) values ('Roupa, Calçado e Acessórios');
insert into categorias (nome) values ('Colecionáveis');
insert into categorias (nome) values ('Computação e Redes');
insert into categorias (nome) values ('Electrónica');
insert into categorias (nome) values ('Saúde e Beleza');
insert into categorias (nome) values ('Música');
insert into categorias (nome) values ('Joalharia e Relojoaria');
insert into categorias (nome) values ('Jogos');
insert into categorias (nome) values ('Desporto');
insert into categorias (nome) values ('Outros');

/*____________________________________   PROPOSTAS   ____________________________________*/

INSERT INTO PROPOSTAS (id, idProduto, idUtilizador, valor, data) VALUES (propostas_id.nextval, 1, 'treze', 30, timestamp'2007-12-12 09:00:00.123456789');
INSERT INTO PROPOSTAS (id, idProduto, idUtilizador, valor, data) VALUES (propostas_id.nextval, 1, 'kamikaze', 40, timestamp'2008-12-12 09:00:00.123456789');
INSERT INTO PROPOSTAS (id, idProduto, idUtilizador, valor, data) VALUES (propostas_id.nextval, 1, 'insatisfeito', 50, timestamp'2009-12-12 09:00:00.123456789');
INSERT INTO PROPOSTAS (id, idProduto, idUtilizador, valor, data) VALUES (propostas_id.nextval, 1, 'gerente', 55, timestamp'2010-12-12 09:10:00.123456789');
INSERT INTO PROPOSTAS (id, idProduto, idUtilizador, valor, data) VALUES (propostas_id.nextval, 1, 'kamikaze', 70, timestamp'2011-12-12 09:20:00.123456789');
INSERT INTO PROPOSTAS (id, idProduto, idUtilizador, valor, data) VALUES (propostas_id.nextval, 3, 'gerente', 200, timestamp'2012-12-12 09:30:00.123456789');
INSERT INTO PROPOSTAS (id, idProduto, idUtilizador, valor, data) VALUES (propostas_id.nextval, 3, 'martins', 210, timestamp'2012-12-12 09:40:00.123456789');
INSERT INTO PROPOSTAS (id, idProduto, idUtilizador, valor, data) VALUES (propostas_id.nextval, 3, 'ana', 230, timestamp'2012-12-12 09:50:00.123456789');
INSERT INTO PROPOSTAS (id, idProduto, idUtilizador, valor, data) VALUES (propostas_id.nextval, 3, 'jose', 240, timestamp'2012-12-12 10:00:00.123456789');
INSERT INTO PROPOSTAS (id, idProduto, idUtilizador, valor, data) VALUES (propostas_id.nextval, 3, 'insatisfeito', 250, timestamp'2012-12-12 11:00:00.123456789');
INSERT INTO PROPOSTAS (id, idProduto, idUtilizador, valor, data) VALUES (propostas_id.nextval, 3, 'martins', 300, timestamp'2012-12-12 11:50:30.123456789');


/*____________________________________   SUBCATEGORIAS   ____________________________________*/


insert into subcategorias (nome, categoria) values ('Pinturas','Antiguidades');
insert into subcategorias (nome, categoria) values ('Mobília','Antiguidades');
insert into subcategorias (nome, categoria) values ('Manuscritos','Antiguidades');
insert into subcategorias (nome, categoria) values ('Pinturas','Arte');
insert into subcategorias (nome, categoria) values ('Esculturas','Arte');
insert into subcategorias (nome, categoria) values ('Artesanato','Arte');
insert into subcategorias (nome, categoria) values ('Fotografia','Arte');
insert into subcategorias (nome, categoria) values ('Romance','Livros');
insert into subcategorias (nome, categoria) values ('Drama','Livros');
insert into subcategorias (nome, categoria) values ('Revistas','Livros');
insert into subcategorias (nome, categoria) values ('Manuais Escolares','Livros');
insert into subcategorias (nome, categoria) values ('Infantis','Livros');
insert into subcategorias (nome, categoria) values ('Digitais','Câmaras e Fotografia');
insert into subcategorias (nome, categoria) values ('Analógicas','Câmaras e Fotografia');
insert into subcategorias (nome, categoria) values ('Papel de fotografia','Câmaras e Fotografia');
insert into subcategorias (nome, categoria) values ('Molduras','Câmaras e Fotografia');
insert into subcategorias (nome, categoria) values ('Smartphones','Telecomunicações e Acessórios');
insert into subcategorias (nome, categoria) values ('Acessórios','Telecomunicações e Acessórios');
insert into subcategorias (nome, categoria) values ('PDAs','Telecomunicações e Acessórios');
insert into subcategorias (nome, categoria) values ('Casacos','Roupa, Calçado e Acessórios');
insert into subcategorias (nome, categoria) values ('Tshirts','Roupa, Calçado e Acessórios');
insert into subcategorias (nome, categoria) values ('Sapatos','Roupa, Calçado e Acessórios');
insert into subcategorias (nome, categoria) values ('Moedas','Colecionáveis');
insert into subcategorias (nome, categoria) values ('Selos','Colecionáveis');
insert into subcategorias (nome, categoria) values ('Cadernetas e cromos','Colecionáveis');
insert into subcategorias (nome, categoria) values ('Portatéis','Computação e Redes');
insert into subcategorias (nome, categoria) values ('Discos Externos','Computação e Redes');
insert into subcategorias (nome, categoria) values ('Periféricos','Computação e Redes');
insert into subcategorias (nome, categoria) values ('Equipamentos de Rede','Computação e Redes');
insert into subcategorias (nome, categoria) values ('Cabos','Computação e Redes');
insert into subcategorias (nome, categoria) values ('Memórias','Computação e Redes');
insert into subcategorias (nome, categoria) values ('Computadores','Computação e Redes');
insert into subcategorias (nome, categoria) values ('TVs','Electrónica');
insert into subcategorias (nome, categoria) values ('Sistemas de Som','Electrónica');
insert into subcategorias (nome, categoria) values ('Electrodomésticos','Electrónica');
insert into subcategorias (nome, categoria) values ('Sistemas de Vigilância','Electrónica');
insert into subcategorias (nome, categoria) values ('Áudio Portátil','Electrónica');
insert into subcategorias (nome, categoria) values ('Produtos de Higiene','Saúde e Beleza');
insert into subcategorias (nome, categoria) values ('Dieta','Saúde e Beleza');
insert into subcategorias (nome, categoria) values ('Fragrâncias','Saúde e Beleza');
insert into subcategorias (nome, categoria) values ('CDs','Música');
insert into subcategorias (nome, categoria) values ('DVDs','Música');
insert into subcategorias (nome, categoria) values ('Instrumentos','Música');
insert into subcategorias (nome, categoria) values ('Cassetes','Música');
insert into subcategorias (nome, categoria) values ('Relógios','Joalharia e Relojoaria');
insert into subcategorias (nome, categoria) values ('Jóias','Joalharia e Relojoaria');
insert into subcategorias (nome, categoria) values ('PS','Jogos');
insert into subcategorias (nome, categoria) values ('PC','Jogos');
insert into subcategorias (nome, categoria) values ('XBOX','Jogos');
insert into subcategorias (nome, categoria) values ('Equipamentos','Desporto');
insert into subcategorias (nome, categoria) values ('Bolas','Desporto');
insert into subcategorias (nome, categoria) values ('Calçado desportivo','Desporto');
insert into subcategorias (nome, categoria) values ('Desportos radicais','Desporto');


/*____________________________________   PRODUTOS   ____________________________________*/

INSERT INTO Produtos (id,nome,preco,descricao,estado,quantidade,nrImagens,metodoVenda,localidade,categoria,subCategoria,nomeUtilizador) VALUES 
	(produtos_id.nextval,'Asus N61q',999.60,'Intel i7, 6GB, 500GB 5400rpm, ATI 1GB, Windows 8','Novo',1,0,'Leilão','Bragança','Computação e Redes','Computadores','insatisfeito');
INSERT INTO Produtos (id,nome,preco,descricao,estado,quantidade,nrImagens,metodoVenda,localidade,categoria,subCategoria,nomeUtilizador) VALUES 
	(produtos_id.nextval,'Toshiba',900.00,'Intel i5, 6GB, 1T 5400rpm, ATI 1GB, Windows 8','Novo',3,0,'Venda Directa','Évora','Computação e Redes','Computadores','treze');
INSERT INTO Produtos (id,nome,preco,descricao,estado,quantidade,nrImagens,metodoVenda,localidade,categoria,subCategoria,nomeUtilizador) VALUES 
	(produtos_id.nextval,'Insys',500.20,'Intel i3, 24B, 500GB 5400rpm, ATI 500MB, Windows 7','Usado',1,0,'Leilão','Viseu','Computação e Redes','Computadores','insatisfeito');
insert into produtos (id, nome, preco, descricao, estado, quantidade, nrimagens, datacolocacao, metodovenda, localidade, categoria,subcategoria, nomeutilizador, datavenda, dataavaliacao) values 
  (produtos_id.nextval, 'MacBook Pro', 2349, 'Descrição do produto teste ', 'Novo', 1, 0, CURRENT_TIMESTAMP, 'Venda Directa', 'Braga', 'Computação e Redes', 'Portatéis', 'insatisfeito', null, null);
insert into produtos (id, nome, preco, descricao, estado, quantidade, nrimagens, datacolocacao, metodovenda, localidade, categoria,subcategoria, nomeutilizador, datavenda, dataavaliacao) values 
  (produtos_id.nextval, 'Swatch Prestige', 90, 'Relogio do sbem tranqz', 'Novo', 1, 0, CURRENT_TIMESTAMP, 'Venda Directa', 'Porto', 'Joalharia e Relojoaria', 'Relógios', 'gerente', null, null);
insert into produtos (id, nome, preco, descricao, estado, quantidade, nrimagens, datacolocacao, metodovenda, localidade, categoria,subcategoria, nomeutilizador, datavenda, dataavaliacao) values 
  (produtos_id.nextval, 'Disco SSD 256GB', 500, '256GB', 'Usado', 1, 0, CURRENT_TIMESTAMP, 'Venda Directa', 'Braga', 'Computação e Redes', 'Discos Externos', 'gerente', null, null);
insert into produtos (id, nome, preco, descricao, estado, quantidade, nrimagens, datacolocacao, metodovenda, localidade, categoria,subcategoria, nomeutilizador, datavenda, dataavaliacao) values 
  (produtos_id.nextval, 'Cillit Bang', 5, 'Descrição do produto teste ', 'Novo', 1, 0, CURRENT_TIMESTAMP, 'Venda Directa', 'Porto', 'Saúde e Beleza', 'Produtos de Higiene', 'treze', null, null);
insert into produtos (id, nome, preco, descricao, estado, quantidade, nrimagens, datacolocacao, metodovenda, localidade, categoria,subcategoria, nomeutilizador, datavenda, dataavaliacao) values 
  (produtos_id.nextval, 'Samsung Omnia i8000', 200, 'Descrição do produto teste ', 'Novo', 1, 0, CURRENT_TIMESTAMP, 'Venda Directa', 'Porto', 'Telecomunicações e Acessórios', 'Smartphones', 'treze', null, null);
insert into produtos (id, nome, preco, descricao, estado, quantidade, nrimagens, datacolocacao, metodovenda, localidade, categoria,subcategoria, nomeutilizador, datavenda, dataavaliacao) values 
  (produtos_id.nextval, 'Sons Immortal', 10, 'Descrição do produto teste ', 'Novo', 1, 0, CURRENT_TIMESTAMP, 'Venda Directa', 'Faro', 'Música', 'CDs', 'treze', null, null);
insert into produtos (id, nome, preco, descricao, estado, quantidade, nrimagens, datacolocacao, metodovenda, localidade, categoria,subcategoria, nomeutilizador, datavenda, dataavaliacao) values 
  (produtos_id.nextval, 'Escultura José Mourinho', 5000, 'Descrição do produto teste ', 'Novo', 1, 0, CURRENT_TIMESTAMP, 'Venda Directa', 'Faro', 'Arte', 'Esculturas', 'kamikaze', null, null);
insert into produtos (id, nome, preco, descricao, estado, quantidade, nrimagens, datacolocacao, metodovenda, localidade, categoria,subcategoria, nomeutilizador, datavenda, dataavaliacao) values 
  (produtos_id.nextval, 'Camisola oficial Porto', 60, 'Descrição do produto teste ', 'Novo', 1, 0, CURRENT_TIMESTAMP, 'Venda Directa', 'Lisboa', 'Desporto', 'Equipamentos', 'kamikaze', null, null);
insert into produtos (id, nome, preco, descricao, estado, quantidade, nrimagens, datacolocacao, metodovenda, localidade, categoria,subcategoria, nomeutilizador, datavenda, dataavaliacao) values 
  (produtos_id.nextval, 'Nike Air', 90, 'Descrição do produto teste ', 'Novo', 1, 0, CURRENT_TIMESTAMP, 'Venda Directa', 'Lisboa', 'Desporto', 'Equipamentos', 'kamikaze', null, null);
insert into produtos (id, nome, preco, descricao, estado, quantidade, nrimagens, datacolocacao, metodovenda, localidade, categoria,subcategoria, nomeutilizador, datavenda, dataavaliacao) values 
  (produtos_id.nextval, 'Cannon', 500, 'Descrição do produto teste ', 'Novo', 1, 0, CURRENT_TIMESTAMP, 'Venda Directa', 'Vila Real', 'Câmaras e Fotografia', 'Analógicas', 'kamikaze', null, null);
insert into produtos (id, nome, preco, descricao, estado, quantidade, nrimagens, datacolocacao, metodovenda, localidade, categoria,subcategoria, nomeutilizador, datavenda, dataavaliacao) values 
  (produtos_id.nextval, 'Need For Speed', 45, 'Descrição do produto teste ', 'Novo', 1, 0, CURRENT_TIMESTAMP, 'Venda Directa', 'Vila Real', 'Jogos', 'PC', 'gerente', null, null);
insert into produtos (id, nome, preco, descricao, estado, quantidade, nrimagens, datacolocacao, metodovenda, localidade, categoria,subcategoria, nomeutilizador, datavenda, dataavaliacao) values 
  (produtos_id.nextval, 'Guitarra Ibanez', 390, 'Descrição do produto teste ', 'Novo', 1, 0, CURRENT_TIMESTAMP, 'Venda Directa', 'Braga', 'Música', 'Instrumentos', 'insatisfeito', null, null);
insert into produtos (id, nome, preco, descricao, estado, quantidade, nrimagens, datacolocacao, metodovenda, localidade, categoria,subcategoria, nomeutilizador, datavenda, dataavaliacao) values 
  (produtos_id.nextval, 'Game of Thrones', 19, 'Descrição do produto teste ', 'Novo', 1, 0, CURRENT_TIMESTAMP, 'Venda Directa', 'Braga', 'Livros', 'Outros', 'insatisfeito', null, null);
insert into produtos (id, nome, preco, descricao, estado, quantidade, nrimagens, datacolocacao, metodovenda, localidade, categoria,subcategoria, nomeutilizador, datavenda, dataavaliacao) values 
  (produtos_id.nextval, 'TV Sony', 200, 'Descrição do produto teste ', 'Novo', 1, 0, CURRENT_TIMESTAMP, 'Venda Directa', 'Braga', 'Electrónica', 'TVs', 'insatisfeito', null, null);
insert into produtos (id, nome, preco, descricao, estado, quantidade, nrimagens, datacolocacao, metodovenda, localidade, categoria,subcategoria, nomeutilizador, datavenda, dataavaliacao) values 
  (produtos_id.nextval, 'Arroz', 45, 'Descrição do produto teste ', 'Novo', 1, 0, CURRENT_TIMESTAMP, 'Venda Directa', 'Vila Real', 'Outros', 'Outros', 'martins', null, null);
insert into produtos (id, nome, preco, descricao, estado, quantidade, nrimagens, datacolocacao, metodovenda, localidade, categoria,subcategoria, nomeutilizador, datavenda, dataavaliacao) values 
  (produtos_id.nextval, 'Guitarra Fender', 390, 'Descrição do produto teste ', 'Novo', 1, 0, CURRENT_TIMESTAMP, 'Venda Directa', 'Braga', 'Música', 'Instrumentos', 'martins', null, null);
insert into produtos (id, nome, preco, descricao, estado, quantidade, nrimagens, datacolocacao, metodovenda, localidade, categoria,subcategoria, nomeutilizador, datavenda, dataavaliacao) values 
  (produtos_id.nextval, 'Idiocracy', 19, 'Descrição do produto teste ', 'Novo', 1, 0, CURRENT_TIMESTAMP, 'Venda Directa', 'Braga', 'Livros', 'Outros', 'ana', null, null);
insert into produtos (id, nome, preco, descricao, estado, quantidade, nrimagens, datacolocacao, metodovenda, localidade, categoria,subcategoria, nomeutilizador, datavenda, dataavaliacao) values 
  (produtos_id.nextval, 'TV LG 3D', 200, 'Descrição do produto teste ', 'Novo', 1, 0, CURRENT_TIMESTAMP, 'Venda Directa', 'Braga', 'Electrónica', 'TVs', 'ana', null, null);


/*____________________________________   leiloes   ____________________________________*/

insert into leiloes values (1,CURRENT_TIMESTAMP,70);
insert into leiloes values (3,CURRENT_TIMESTAMP,300);


/*____________________________________   fotos values   ____________________________________*/

insert into fotos values (fotos_id.nextval,1,null,'path imagem teste1');
insert into fotos values (fotos_id.nextval,2,null,'path imagem teste2');	
insert into fotos values (fotos_id.nextval,3,null,'path imagem teste3');
insert into fotos values (fotos_id.nextval,4,null,'path imagem teste4');
insert into fotos values (fotos_id.nextval,5,null,'path imagem teste5');
insert into fotos values (fotos_id.nextval,6,null,'path imagem teste6');
insert into fotos values (fotos_id.nextval,7,null,'path imagem teste7');
insert into fotos values (fotos_id.nextval,8,null,'path imagem teste8');
insert into fotos values (fotos_id.nextval,9,null,'path imagem teste9');
insert into fotos values (fotos_id.nextval,10,null,'path imagem teste10');
insert into fotos values (fotos_id.nextval,11,null,'path imagem teste11');
insert into fotos values (fotos_id.nextval,12,null,'path imagem teste12');
insert into fotos values (fotos_id.nextval,13,null,'path imagem teste13');
insert into fotos values (fotos_id.nextval,14,null,'path imagem teste14');
insert into fotos values (fotos_id.nextval,15,null,'path imagem teste15');
insert into fotos values (fotos_id.nextval,16,null,'path imagem teste16');
insert into fotos values (fotos_id.nextval,17,null,'path imagem teste17');
insert into fotos values (fotos_id.nextval,18,null,'path imagem teste18');
insert into fotos values (fotos_id.nextval,19,null,'path imagem teste19');
insert into fotos values (fotos_id.nextval,20,null,'path imagem  teste20');
insert into fotos values (fotos_id.nextval,21,null,'path imagem  teste21');

/*____________________________________   PRODUTOS SEGUIDOS   ____________________________________*/

INSERT INTO ProdutosSeguidos (nomeUtilizador,idProduto) VALUES ('treze','0');
INSERT INTO ProdutosSeguidos (nomeUtilizador,idProduto) VALUES ('treze','7');
INSERT INTO ProdutosSeguidos (nomeUtilizador,idProduto) VALUES ('treze','6');
INSERT INTO ProdutosSeguidos (nomeUtilizador,idProduto) VALUES ('treze','2');
INSERT INTO ProdutosSeguidos (nomeUtilizador,idProduto) VALUES ('insatisfeito','4');
INSERT INTO ProdutosSeguidos (nomeUtilizador,idProduto) VALUES ('insatisfeito','3');
INSERT INTO ProdutosSeguidos (nomeUtilizador,idProduto) VALUES ('insatisfeito','2');
INSERT INTO ProdutosSeguidos (nomeUtilizador,idProduto) VALUES ('insatisfeito','1');
INSERT INTO ProdutosSeguidos (nomeUtilizador,idProduto) VALUES ('kamikaze','21');
INSERT INTO ProdutosSeguidos (nomeUtilizador,idProduto) VALUES ('kamikaze','2');
INSERT INTO ProdutosSeguidos (nomeUtilizador,idProduto) VALUES ('kamikaze','13');
INSERT INTO ProdutosSeguidos (nomeUtilizador,idProduto) VALUES ('kamikaze','14');
INSERT INTO ProdutosSeguidos (nomeUtilizador,idProduto) VALUES ('gerente','1');
INSERT INTO ProdutosSeguidos (nomeUtilizador,idProduto) VALUES ('gerente','11');
INSERT INTO ProdutosSeguidos (nomeUtilizador,idProduto) VALUES ('gerente','17');
INSERT INTO ProdutosSeguidos (nomeUtilizador,idProduto) VALUES ('gerente','15');

/*____________________________________   PRODUTOS DESEJADOS   ____________________________________*/

INSERT INTO ProdutosDesejados (nomeUtilizador,idProduto) VALUES ('treze','2');
INSERT INTO ProdutosDesejados (nomeUtilizador,idProduto) VALUES ('treze','7');
INSERT INTO ProdutosDesejados (nomeUtilizador,idProduto) VALUES ('kamikaze','3');
INSERT INTO ProdutosDesejados (nomeUtilizador,idProduto) VALUES ('kamikaze','6');
INSERT INTO ProdutosDesejados (nomeUtilizador,idProduto) VALUES ('kamikaze','9');
INSERT INTO ProdutosDesejados (nomeUtilizador,idProduto) VALUES ('insatisfeito','10');
INSERT INTO ProdutosDesejados (nomeUtilizador,idProduto) VALUES ('insatisfeito','1');
INSERT INTO ProdutosDesejados (nomeUtilizador,idProduto) VALUES ('gerente','7');
INSERT INTO ProdutosDesejados (nomeUtilizador,idProduto) VALUES ('gerente','3');
INSERT INTO ProdutosDesejados (nomeUtilizador,idProduto) VALUES ('gerente','6');
INSERT INTO ProdutosDesejados (nomeUtilizador,idProduto) VALUES ('gerente','9');
/*____________________________________   CARRINHO DE COMPRAS   ____________________________________*/

INSERT INTO CarrinhoCompras (nomeUtilizador,idProduto) VALUES ('insatisfeito','1');
INSERT INTO CarrinhoCompras (nomeUtilizador,idProduto) VALUES ('treze','3');
INSERT INTO CarrinhoCompras (nomeUtilizador,idProduto) VALUES ('treze','5');
INSERT INTO CarrinhoCompras (nomeUtilizador,idProduto) VALUES ('treze','7');
INSERT INTO CarrinhoCompras (nomeUtilizador,idProduto) VALUES ('insatisfeito','17');
INSERT INTO CarrinhoCompras (nomeUtilizador,idProduto) VALUES ('insatisfeito','21');
INSERT INTO CarrinhoCompras (nomeUtilizador,idProduto) VALUES ('gerente','14');
INSERT INTO CarrinhoCompras (nomeUtilizador,idProduto) VALUES ('gerente','3');
INSERT INTO CarrinhoCompras (nomeUtilizador,idProduto) VALUES ('kamikaze','12');

/*____________________________________   Metodos Pagamento   ____________________________________*/

INSERT INTO MetodosPagamento (idProduto, nomeMetodo) VALUES (1, 'Paypal');
INSERT INTO MetodosPagamento (idProduto, nomeMetodo) VALUES (2, 'Paypal');
INSERT INTO MetodosPagamento (idProduto, nomeMetodo) VALUES (3, 'Paypal');
INSERT INTO MetodosPagamento (idProduto, nomeMetodo) VALUES (4, 'Paypal');
INSERT INTO MetodosPagamento (idProduto, nomeMetodo) VALUES (5, 'Paypal');
INSERT INTO MetodosPagamento (idProduto, nomeMetodo) VALUES (6, 'Paypal');
INSERT INTO MetodosPagamento (idProduto, nomeMetodo) VALUES (7, 'Paypal');
INSERT INTO MetodosPagamento (idProduto, nomeMetodo) VALUES (8, 'Paypal');
INSERT INTO MetodosPagamento (idProduto, nomeMetodo) VALUES (9, 'Paypal');
INSERT INTO MetodosPagamento (idProduto, nomeMetodo) VALUES (1, 'MBNet');
INSERT INTO MetodosPagamento (idProduto, nomeMetodo) VALUES (2, 'MBNet');
INSERT INTO MetodosPagamento (idProduto, nomeMetodo) VALUES (3, 'MBNet');
INSERT INTO MetodosPagamento (idProduto, nomeMetodo) VALUES (6, 'MBNet');
INSERT INTO MetodosPagamento (idProduto, nomeMetodo) VALUES (10, 'MBNet');
INSERT INTO MetodosPagamento (idProduto, nomeMetodo) VALUES (11, 'MBNet');
INSERT INTO MetodosPagamento (idProduto, nomeMetodo) VALUES (12, 'MBNet');
INSERT INTO MetodosPagamento (idProduto, nomeMetodo) VALUES (13, 'MBNet');
INSERT INTO MetodosPagamento (idProduto, nomeMetodo) VALUES (14, 'MBNet');
INSERT INTO MetodosPagamento (idProduto, nomeMetodo) VALUES (3, 'MBNet');
INSERT INTO MetodosPagamento (idProduto, nomeMetodo) VALUES (6, 'MBNet');
INSERT INTO MetodosPagamento (idProduto, nomeMetodo) VALUES (20, 'MBNet');
INSERT INTO MetodosPagamento (idProduto, nomeMetodo) VALUES (15, 'MBNet');
INSERT INTO MetodosPagamento (idProduto, nomeMetodo) VALUES (17, 'MBNet');
INSERT INTO MetodosPagamento (idProduto, nomeMetodo) VALUES (16, 'MBNet');
INSERT INTO MetodosPagamento (idProduto, nomeMetodo) VALUES (15, 'MBNet');
INSERT INTO MetodosPagamento (idProduto, nomeMetodo) VALUES (18, 'MBNet');
INSERT INTO MetodosPagamento (idProduto, nomeMetodo) VALUES (19, 'MBNet');
INSERT INTO MetodosPagamento (idProduto, nomeMetodo) VALUES (21, 'MBNet');
/*____________________________________ HISTORICO _______________________________________*/

INSERT INTO Historico (idProduto, comprador, descricao, data, dataavaliado, dataavaliadocomp) VALUES  (2, 'gerente', 'Venda', CURRENT_TIMESTAMP, null, null)
INSERT INTO Historico (idProduto, comprador, descricao, data, dataavaliado, dataavaliadocomp) VALUES  (20, 'gerente', 'Venda', CURRENT_TIMESTAMP, null, null)
INSERT INTO Historico (idProduto, comprador, descricao, data, dataavaliado, dataavaliadocomp) VALUES  (22, 'gerente', 'Venda', CURRENT_TIMESTAMP, null, null)
INSERT INTO Historico (idProduto, comprador, descricao, data, dataavaliado, dataavaliadocomp) VALUES  (21, 'gerente', 'Venda', CURRENT_TIMESTAMP, null, null)
INSERT INTO Historico (idProduto, comprador, descricao, data, dataavaliado, dataavaliadocomp) VALUES  (16, 'gerente', 'Venda', CURRENT_TIMESTAMP, null, null)
INSERT INTO Historico (idProduto, comprador, descricao, data, dataavaliado, dataavaliadocomp) VALUES  (8, 'kamikaze', 'Venda', CURRENT_TIMESTAMP, null, null)
INSERT INTO Historico (idProduto, comprador, descricao, data, dataavaliado, dataavaliadocomp) VALUES  (13, 'kamikaze', 'Venda', CURRENT_TIMESTAMP, null, null)
INSERT INTO Historico (idProduto, comprador, descricao, data, dataavaliado, dataavaliadocomp) VALUES  (18, 'kamikaze', 'Venda', CURRENT_TIMESTAMP, null, null)
INSERT INTO Historico (idProduto, comprador, descricao, data, dataavaliado, dataavaliadocomp) VALUES  (19, 'kamikaze', 'Venda', CURRENT_TIMESTAMP, null, null)



