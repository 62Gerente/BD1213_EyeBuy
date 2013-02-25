/*____________________________________   MENSAGENS   ____________________________________*/

/* Notifica um utilizador quando um produto deixa de estar nas suas listas */

create or replace 
PROCEDURE p_notificaTrocaNaoDisponivel 
(argNomeUtilizador varchar2, argIDTroca number) IS

BEGIN

	INSERT INTO MENSAGENS (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) VALUES 
	(mensagens_id.nextval, null,argNomeUtilizador, 'Mensagem Sistema', 'Troca indisponível', 
		
		'O EyeBuy vem por este meio notificar que a troca com o id "' || argIDTroca || 
    	'" deixou de estar . Se não foi o utilizador a remover a rejeitar a troca, então algum produto envolvido pode ter sido vendido ou removido pelo utilizador que o tinha disponibilizado. Obrigado, Cumprimentos da equipa EyeBuy', 
		
		CURRENT_TIMESTAMP, 0, 0, 0);

END p_notificaTrocaNaoDisponivel;



/* Notifica um utilizador quando uma troca deixa de estar disponivel */
/
	
create or replace 
PROCEDURE p_notificaNaoDisponivel 
(argNomeUtilizador varchar2, argIDProduto number, argLocalizacao varchar2) IS

BEGIN

	INSERT INTO MENSAGENS (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) VALUES 
  (mensagens_id.nextval, null,argNomeUtilizador, 'Mensagem Sistema', 	  	
  'Produto ' || argLocalizacao || ' indisponível', 
		
    'O EyeBuy vem por este meio notificar que o produto ' || argLocalizacao || ' com o id "' || argIDProduto || 
    '" foi removido de uma das suas listas. Se não foi o utilizador a remover o produto este pode ter sido vendido ou removido pelo utilizador que o tinha disponibilizado. Obrigado, Cumprimentos da equipa EyeBuy', 

		CURRENT_TIMESTAMP, 0, 0, 0);

END p_notificaNaoDisponivel;
/

/* Notifica um utilizador quando é banido */
/
	
create or replace 
PROCEDURE p_notificaBanido
(argNomeUtilizador varchar2) IS

BEGIN

	INSERT INTO MENSAGENS (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) VALUES 
  (mensagens_id.nextval, null,argNomeUtilizador, 'Mensagem Sistema', 	  	
  'Banido do sistema', 
		
    'O EyeBuy vem por este meio notificar que foi banido do sistema. No entanto tem a oportunidade de acabar os negocios já aceites. Contacte os administradores para mais informações. Obrigado, Cumprimentos da equipa EyeBuy', 

		CURRENT_TIMESTAMP, 0, 0, 0);

END p_notificaBanido;
/






/*____________________________________   LEILÕES   ____________________________________*/

/* Procedimento que termina um leilão e insere no histórico o vencedor com a melhor proposta e marca como vendido */


create or replace 
procedure p_acabaleilao
	IS
		cursor c_leiloes is SELECT p.* from propostas p, produtos prod, leiloes l where p.idproduto=prod.id and prod.datavenda=null and rownum<=1 and l.datafim<=CURRENT_TIMESTAMP;
		rowprop c_leiloes%ROWTYPE;
   BEGIN
      open c_leiloes;
      LOOP
      	fetch c_leiloes into rowprop;
      	EXIT WHEN c_leiloes%NOTFOUND;
      	insert into historico (idproduto,comprador, descricao, data,dataavaliado) values (rowprop.idproduto, rowprop.idutilizador,null, rowprop.data, null);
      	UPDATE produtos set datavenda=rowprop.data where id=rowprop.idproduto;
      END LOOP;
      close c_leiloes;
   END p_acabaleilao;
/

/*____________________________________   CARRINHO   ____________________________________*/

/* Procedimento que marca como vendidos os produtos do carrinho e realiza operações na bd para coerência de dados */

create or replace procedure p_checkoutcarrinho (nomeuser varchar2, mpay varchar2)
	IS
		var_idprod number; 
  		cursor c_carrinho is SELECT car.idproduto from carrinhocompras car inner join metodospagamento m on car.idproduto=m.idproduto where car.nomeutilizador=nomeuser and m.nomemetodo=mpay;  
	BEGIN
		open c_carrinho;
		LOOP
      		fetch c_carrinho into var_idprod;
      		EXIT WHEN c_carrinho%NOTFOUND;
			UPDATE produtos set datavenda=CURRENT_TIMESTAMP where id=var_idprod;
			INSERT into historico (idproduto, comprador, descricao, data, dataavaliado) values (var_idprod, nomeuser, null, CURRENT_TIMESTAMP, null);
		END LOOP;
		close c_carrinho;
	END p_checkoutcarrinho;
/