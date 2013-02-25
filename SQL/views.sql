/*____________________________________   PRODUTOS   ____________________________________*/

/* Lista de produtos auxiliar */

CREATE OR REPLACE 
VIEW v_produtosaux AS
SELECT * FROM produtos;

/* Lista de produtos que estão a ver vendidos */

CREATE OR REPLACE 
VIEW v_produtosMontra AS
SELECT * FROM v_produtosaux 
WHERE datavenda IS NULL
AND apagado = 0;

/* Produtos ordenados pelos melhores vendedores */

CREATE OR REPLACE 
VIEW v_dosMelhores AS
SELECT m.* FROM v_produtosmontra m, utilizadores u
WHERE m.nomeutilizador = u.nomeutilizador
ORDER BY u.avaliacaovendedor DESC;

/* Produtos ordenados pelos mais recentes */

CREATE OR REPLACE 
VIEW v_novosProdutos AS
SELECT * FROM v_produtosmontra
ORDER BY datacolocacao DESC;

/*____________________________________   PRODUTOS DESEJADOS   ____________________________________*/

/* Lista de produtos auxiliar */

CREATE OR REPLACE 
VIEW v_produtosdesejadosaux AS
SELECT * FROM produtosdesejados;

/*____________________________________   HISTÓRICO   ____________________________________*/

/*Histórico para consulta*/

create or replace 
VIEW historicoParaConsulta as
select comprador , preco, nomeUtilizador as vendedor, h.descricao, data, dataAvaliado
from historico h, produtos p
where h.idproduto = p.id;


/*____________________________________   MENSAGENS   ____________________________________*/

CREATE OR REPLACE 
VIEW HISTORICOPARACONSULTA AS 
select comprador , preco, nomeUtilizador as vendedor, h.descricao, data, dataAvaliado
from historico h, produtos p
where h.idproduto = p.id;







