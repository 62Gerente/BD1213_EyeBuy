package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import model.Utilizador;
import model.mensagens.Mensagem;
import model.mensagens.MensagemPessoal;
import model.mensagens.MensagemSistema;


public class MensagemDAO extends AbstractDAO{

 public String[] listaMensagensRecebidas(String nomeUtilizador) throws SQLException {
  getConnection();
  String sql = "SELECT * FROM Mensagens WHERE destino = '"+ nomeUtilizador +"' and apagadadt = 0";
  ResultSet rs = stmt.executeQuery(sql);
  ArrayList<String> list = new ArrayList<String>();
  while(rs.next()){
   list.add(rs.getInt("id")+" " + rs.getString("assunto"));
  }
  String [] res = list.toArray(new String[list.size()]);
  stmt.close();
  return res;
 }

 public String[] listaMensagensEnviadas(String nomeUtilizador) throws SQLException {
  getConnection();
  String sql = "SELECT * FROM Mensagens WHERE origem = '"+ nomeUtilizador +"' and apagadaor = 0";
  ResultSet rs = stmt.executeQuery(sql);
  ArrayList<String> list = new ArrayList<String>();
  while(rs.next()){
   list.add(rs.getInt("id")+" " + rs.getString("assunto"));
  }
  String [] res = list.toArray(new String[list.size()]);
  stmt.close();
  return res;
 }
 
 public String[] listaMensagensNaoLidas(String nomeUtilizador) throws SQLException {
  getConnection();
  String sql = "SELECT * FROM Mensagens WHERE destino = '"+ nomeUtilizador +"' AND lida = 0  and apagadadt = 0";
  ResultSet rs = stmt.executeQuery(sql);
  ArrayList<String> list = new ArrayList<String>();
  while(rs.next()){
   list.add(rs.getInt("id")+" " + rs.getString("assunto"));
  }
  String [] res = list.toArray(new String[list.size()]);
  stmt.close();
  return res;
 }
 
 private static Mensagem getMensagem(int id) throws SQLException {
  getConnection();
  ResultSet rsp = stmt.executeQuery("SELECT * FROM Mensagens WHERE id = "+ id);
  
  Mensagem m;
  
  if (rsp.next()){
   if(!(rsp.getString("ORIGEM") == null)){
    m = new MensagemPessoal();
    m.setId(id);
    m.setAssunto(rsp.getString("ASSUNTO"));
    m.setCorpo(rsp.getString("CORPO"));
    m.setDestinatario(UtilizadorDAO.getUtilizador(rsp.getString("DESTINO")));
    m.setLida((rsp.getInt("LIDA") > 0) ? true : false);
    m.setRemetente(UtilizadorDAO.getUtilizador(rsp.getString("ORIGEM")));
    GregorianCalendar gcC = new GregorianCalendar(); 
    gcC.setTime(rsp.getDate("DATA"));
    m.setData(gcC);
   }
   else{
    m = new MensagemSistema();
    m.setId(id);
    m.setAssunto(rsp.getString("ASSUNTO"));
    m.setCorpo(rsp.getString("CORPO"));
    m.setDestinatario(UtilizadorDAO.getUtilizador(rsp.getString("DESTINO")));
    m.setLida((rsp.getInt("LIDA") > 0) ? true : false);
    m.setRemetente(new Utilizador());
    GregorianCalendar gcC = new GregorianCalendar(); 
    gcC.setTime(rsp.getDate("DATA"));
    m.setData(gcC);
   }
    stmt.close();
    return m;
  }
  return null;
 }
 
 public Mensagem getMensagemRecebidaListada(String nomeUtilizador,
   String mensagemlistada) {
  StringTokenizer st = new StringTokenizer (mensagemlistada, " ");
  String id = st.nextToken();
  Mensagem m = new MensagemPessoal();
  try {
   m = MensagemDAO.getMensagem(Integer.parseInt(id));
  } catch (NumberFormatException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  } catch (SQLException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  return m;
 }

 public Mensagem getMensagemEnviadaListada(String nomeUtilizador,
   String mensagemlistada) {
  StringTokenizer st = new StringTokenizer (mensagemlistada, " ");
  String id = st.nextToken();
  Mensagem m = new MensagemPessoal();
  try {
   m = MensagemDAO.getMensagem(Integer.parseInt(id));
  } catch (NumberFormatException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  } catch (SQLException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  return m;
 }

 public static void removeMensagemListada(String nomeUtilizador, String mensagemListada) {
  StringTokenizer st = new StringTokenizer (mensagemListada, " ");
  String id = st.nextToken();
  try {
   MensagemDAO.removeMensagem(nomeUtilizador, Integer.parseInt(id));
  } catch (NumberFormatException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  } catch (SQLException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
 }

 public static void removeMensagem(String nomeUtilizador, int idMensagem) throws SQLException {
  getConnection();
  String sql = "SELECT * FROM Mensagens WHERE  id= "+ idMensagem;
  ResultSet rs = stmt.executeQuery(sql);
  rs.first();
  String destino = rs.getString("Destino");
  stmt.close();
  if(nomeUtilizador.equals(destino)){
   sql = "UPDATE Mensagens SET apagadadt = 1 WHERE id = "+ idMensagem;
  }
  else{ 
   sql = "UPDATE Mensagens SET apagadaor = 1 WHERE id = "+ idMensagem;
  }
  getConnection();
  stmt.executeUpdate(sql);
  stmt.close();
 }
 
 

 public void marcaLidaMensagemListada(String nomeUtilizador, String mensagemListada){
  StringTokenizer st = new StringTokenizer (mensagemListada, " ");
  String id = st.nextToken();
  try {
   MensagemDAO.marcaComoLida(Integer.parseInt(id));
  } catch (NumberFormatException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  } catch (SQLException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
 }

 public static void marcaComoLida(int id) throws SQLException {
  getConnection(); 
  String sql = "UPDATE Mensagens SET lida = 1 WHERE id = "+ id;
  stmt.executeUpdate(sql);
  stmt.close();
  
 }

 public void novaMensagem(Mensagem novaMensagem) throws SQLException {
  ConnectionFactory cf = new ConnectionFactory();
  Connection c = cf.getConnection(); 
  StringBuilder sql = new StringBuilder("INSERT INTO Mensagens (id, origem, destino, tipo, assunto, corpo, data) VALUES (mensagens_id.nextval,"); 
  
  sql.append("'"+ novaMensagem.getRemetente().getNomeUtilizador() +"', "); 
  if(novaMensagem.getClass().getSimpleName().equals("MensagemPessoal")){
	  sql.append("'"+ novaMensagem.getDestinatario().getNomeUtilizador() +"', ");
	  sql.append("'Mensagem pessoal', ");
  }else{
	  sql.append("null, ");
	  sql.append("'Mensagem Administrador', ");
  }
  sql.append("'"+ novaMensagem.getAssunto()+"', ");
  sql.append("'"+ novaMensagem.getCorpo()+"', ");
  sql.append("CURRENT_TIMESTAMP) ");
  
  PreparedStatement stmt =  c.prepareStatement(sql.toString());
  stmt.executeUpdate(sql.toString());
  stmt.close();
  
 }
}