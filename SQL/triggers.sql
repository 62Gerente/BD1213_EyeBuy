/*____________________________________   MENSAGENS   ____________________________________*/

/* Trigger que apaga uma mensagem definitivamente quando o emissor e receptor a apagam*/

CREATE OR REPLACE 
TRIGGER trg_apagamensagens
FOR UPDATE ON mensagens
COMPOUND TRIGGER

AFTER STATEMENT IS
BEGIN

  DELETE FROM mensagens WHERE apagadaor = 1 AND apagadadt = 1;

END AFTER STATEMENT;
END trg_apagamensagens;
/

/*____________________________________   PRODUTOS   ____________________________________*/


/* Trigger que apaga um produto de todas as listas (excepto lista de produtos) quando este é vendido */

CREATE OR REPLACE 
TRIGGER trg_updateproduto
BEFORE UPDATE ON produtos

FOR EACH ROW

BEGIN

IF ((:NEW.DATAVENDA IS NOT NULL) OR (:NEW.APAGADO = 1)) THEN
  DELETE FROM produtosdesejados WHERE produtosdesejados.idProduto 	= :NEW.ID;
  DELETE FROM produtosseguidos  WHERE produtosseguidos.idProduto 	= :NEW.ID; 
  DELETE FROM carrinhocompras   WHERE carrinhocompras.idProduto 	= :NEW.ID;
  DELETE FROM trocas            WHERE trocas.idProdutoInteressado 	= :NEW.ID 
  								OR trocas.idProdutoParaTroca 		= :NEW.ID;
END IF;

END trg_updateproduto;
/

/*____________________________________   PRODUTOS DESEJADOS   ____________________________________*/

/* Trigger que notifica um utilizador quando foi removido pelo sistema um produto da sua lista de produtos desejados.*/

CREATE OR REPLACE
TRIGGER trg_removeprodutosdesejados
AFTER DELETE ON produtosdesejados

FOR EACH ROW

BEGIN

  p_notificaNaoDisponivel(:OLD.NOMEUTILIZADOR,:OLD.IDPRODUTO,'desejado');
  
END trg_removeprodutosdesejados;
/

/*____________________________________   PRODUTOS SEGUIDOS   ____________________________________*/

/* Trigger que notifica um utilizador quando foi removido pelo sistema um produto da sua lista de produtos seguidos.*/

CREATE OR REPLACE
TRIGGER trg_removeprodutosseguidos
AFTER DELETE ON produtosseguidos

FOR EACH ROW

BEGIN

  p_notificaNaoDisponivel(:OLD.NOMEUTILIZADOR,:OLD.IDPRODUTO,'seguido');
  
END trg_removeprodutosseguidos;
/

/*____________________________________   CARRINHO DE COMPRAS    ____________________________________*/

/* Trigger que notifica um utilizador quando foi removido pelo sistema um produto do carrinho de compras.*/

CREATE OR REPLACE
TRIGGER trg_removecarrinhocompras
AFTER DELETE ON carrinhocompras

FOR EACH ROW

BEGIN

  p_notificaNaoDisponivel(:OLD.NOMEUTILIZADOR,:OLD.IDPRODUTO,'do carrinho de compras');
  
END trg_removecarrinhodecompras;
/

/*____________________________________   TROCAS    ____________________________________*/

/* Trigger que notifica um utilizador quando foi removido pelo sistema uma troca.*/

CREATE OR REPLACE
TRIGGER trg_removetroca
AFTER DELETE ON trocas

FOR EACH ROW

BEGIN

  p_notificaTrocaNaoDisponivel(:OLD.INTERESSADO,:OLD.ID);
  p_notificaTrocaNaoDisponivel(:OLD.VENDEDOR,:OLD.ID);
  
END trg_removecarrinhodecompras;
/

/*____________________________________   CATEGORIAS    ____________________________________*/

/* Inserir subcategoria outros associada à nova categoria inserida*/
CREATE OR REPLACE
TRIGGER trg_insertCategoria
AFTER INSERT ON categorias
FOR EACH ROW
BEGIN
INSERT INTO subcategorias (nome, categoria) VALUES ('Outros', :new.nome);
END;
/
/* Quando uma categoria é apagada, todos os produtos são colocados na categoria Outros.*/

CREATE OR REPLACE
TRIGGER trg_removecategorias
BEFORE DELETE ON categorias

FOR EACH ROW

BEGIN

  INSERT INTO subcategorias (nome,categoria) VALUES (:OLD.NOME,'Outros');
  
  UPDATE produtos      SET categoria = 'Outros',
  subcategoria = :OLD.NOME
  WHERE categoria = :OLD.NOME;
  
  DELETE FROM subcategorias WHERE categoria = :OLD.NOME;
  
END trg_removecategorias;
/

/*____________________________________   SUBCATEGORIAS    ____________________________________*/

/* Quando uma subcategoria é apagada, todos os produtos são colocados na subcategoria Outros.*/

CREATE OR REPLACE
TRIGGER trg_removesubcategorias
BEFORE DELETE ON subcategorias

FOR EACH ROW

BEGIN

  UPDATE produtos      SET subcategoria = 'Outros' WHERE subcategoria = :OLD.NOME AND categoria = :OLD.CATEGORIA;
  
END trg_removesubcategorias;
/

/*____________________________________   UTILIZADORES    ____________________________________*/

/* Quando uma subcategoria é apagada, todos os produtos são colocados na subcategoria Outros.*/

CREATE OR REPLACE 
TRIGGER trg_updateutilizador
BEFORE UPDATE ON utilizadores

FOR EACH ROW

BEGIN

IF (:NEW.APAGADO = 1) THEN
  DELETE FROM produtosdesejados WHERE produtosdesejados.nomeUtilizador = :NEW.NOMEUTILIZADOR;
  DELETE FROM produtosseguidos  WHERE produtosseguidos.nomeUtilizador = :NEW.NOMEUTILIZADOR; 
  DELETE FROM carrinhocompras   WHERE carrinhocompras.nomeUtilizador = :NEW.NOMEUTILIZADOR;
  UPDATE produtos SET apagado = 1 WHERE produtos.nomeUtilizador = :NEW.NOMEUTILIZADOR;
  p_notificaBanido(:NEW.NOMEUTILIZADOR);
  
END IF;

END trg_updateutilizador;
/




/*____________________________________   LEILÕES    ____________________________________*/

/* Update valor actual do leilão com base na maior licitação */

  CREATE OR REPLACE TRIGGER TRG_UPDATELEILAO
  after insert on propostas
  for each row
BEGIN
  update leiloes set precoactual = :new.valor where idproduto=:new.idproduto;
END;
/
