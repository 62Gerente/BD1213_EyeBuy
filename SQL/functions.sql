/*____________________________________   PASSWORDS   ____________________________________*/

/* Função que codifica a password com o algoritmo MD5 */

CREATE OR REPLACE FUNCTION md5 (valor VARCHAR) RETURN VARCHAR2 IS
   v_input VARCHAR2(2000) := valor;
   hexkey VARCHAR2(32) := NULL;
BEGIN
   hexkey := RAWTOHEX(DBMS_OBFUSCATION_TOOLKIT.md5(input => UTL_RAW.cast_to_raw(v_input)));
   RETURN NVL(hexkey,'');
END;
/

/*____________________________________   PRODUTOS   ____________________________________*/

/* Função que dado um ID de um produto, retorna o nome do produto correspondente */

create or replace 
FUNCTION f_getNomeProduto (argIDProduto number) RETURN VARCHAR2 IS

  v_resNomeProduto varchar(100);
  
BEGIN

  SELECT nome INTO v_resNomeProduto FROM produtos
  WHERE id = argIDProduto;
  
  RETURN v_resNomeProduto;
  
END f_getNomeProduto;
/

/* Função que verifica se há algum produto com aquele id na montra */

create or replace
FUNCTION f_naMontra (argIDProduto number) RETURN BOOLEAN IS

  v_res boolean;
  v_nr number(1);
  
BEGIN

  SELECT count(*) INTO v_nr FROM v_produtosMontra
  WHERE ID = argIDProduto;
  
  IF(v_nr > 0) THEN
    RETURN TRUE;
  ELSE
    RETURN FALSE;
  END IF;
  
END f_naMontra;
/

/*____________________________________   PESQUISAS   ____________________________________*/

/* Função que verifica as coincidencias entre o nome de um produto e as pesquisas de um utilizador */

create or replace 
FUNCTION f_nrcoincidencias(arg_nome VARCHAR, arg_nomeutilizador VARCHAR) RETURN number IS

v_res number(5) := 0;
v_pesq varchar2(100);

CURSOR c_palavraspesquisadas IS
SELECT pesquisa FROM 
(SELECT * FROM pesquisas
WHERE nomeutilizador = arg_nomeutilizador
ORDER BY numeropesquisas DESC)
WHERE numeropesquisas/(SELECT sum(numeropesquisas) FROM pesquisas) 
> 0.1;

BEGIN

OPEN c_palavraspesquisadas;

LOOP

EXIT WHEN c_palavraspesquisadas%NOTFOUND;

FETCH c_palavraspesquisadas INTO v_pesq;

IF (upper(arg_nome) LIKE ('%' || upper(v_pesq) || '%')) THEN
  v_res := v_res +1;
END IF;

END LOOP;

CLOSE c_palavraspesquisadas;

RETURN v_res;

END f_nrcoincidencias;
/

/* Função que verifica número de coincidências entre o nome de um produto e as palavras na pesquisa de um utilizador */



 CREATE OR REPLACE FUNCTION F_NRCOINCIDENCIASPARCIAIS (arg_nome VARCHAR, arg_nomeutilizador VARCHAR) RETURN number IS

v_res number(5) := 0;
v_pesq varchar2(100);
oStr str_array;

BEGIN

  oStr := Str.Split(arg_nome, ' ');
  IF oStr.COUNT > 0 THEN
    FOR i IN oStr.FIRST .. oStr.LAST
    LOOP
        IF (length(oStr(i)) > 3) THEN
          v_res := v_res + f_nrcoincidencias(oStr(i),arg_nomeutilizador);
        END IF;
    END LOOP;
  END IF;

RETURN v_res;

END f_nrcoincidenciasparciais;
/

