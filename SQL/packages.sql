
  CREATE OR REPLACE PACKAGE STR IS
   FUNCTION Split(sText IN VARCHAR2, sDel IN VARCHAR2) RETURN str_array;
   FUNCTION Split(sText IN clob, sDel IN VARCHAR2) RETURN str_array;
   
END Str;

/


  CREATE OR REPLACE PACKAGE BODY STR IS
FUNCTION Split(sText IN VARCHAR2, sDel IN VARCHAR2) RETURN str_array IS
    nStartIdx PLS_INTEGER := 1;
    nEndIdx PLS_INTEGER := 1;
    oRet str_array := str_array();
BEGIN

    IF sText IS NULL THEN RETURN oRet; END IF;

    LOOP

       nEndIdx := INSTR(sText, sDel, nStartIdx);

       IF nEndIdx > 0 THEN

          oRet.Extend;
          oRet(oRet.LAST) := SUBSTR(sText, nStartIdx, nEndIdx - nStartIdx);
          nStartIdx := nEndIdx + 1;

       ELSE

          oRet.Extend;
          oRet(oRet.LAST) := SUBSTR(sText, nStartIdx);
          EXIT;

       END IF;

    END LOOP;

    RETURN oRet;

END Split;

FUNCTION Split(sText IN clob, sDel IN VARCHAR2) RETURN str_array IS
    nStartIdx PLS_INTEGER := 1;
    nEndIdx PLS_INTEGER := 1;
    oRet str_array := str_array();
BEGIN

    IF sText IS NULL THEN RETURN oRet; END IF;
    IF DBMS_LOB.getlength(sText) = 0 THEN RETURN oRet; END IF;

    LOOP

       nEndIdx := DBMS_LOB.INSTR(sText, sDel, nStartIdx);

       IF nEndIdx > 0 THEN

          oRet.Extend;
          oRet(oRet.LAST) := DBMS_LOB.SUBSTR(sText, nEndIdx - nStartIdx, nStartIdx);
          nStartIdx := nEndIdx + LENGTH(sDel);

       ELSE

          oRet.Extend;
          oRet(oRet.LAST) := DBMS_LOB.SUBSTR(lob_loc => sText, offset => nStartIdx);
          EXIT;

       END IF;

    END LOOP;

    RETURN oRet;

END Split;

END Str;