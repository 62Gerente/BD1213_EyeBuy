package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

import model.Localidade;
import model.Utilizador;

public class UtilizadorDAO extends AbstractDAO {
			
	public Utilizador verificaLogin(String nomeUtilizador_Email, String password) throws SQLException {
		getConnection();
		ResultSet users = stmt.executeQuery("SELECT * FROM Utilizadores WHERE nomeutilizador = '"+nomeUtilizador_Email +"' or EMAIL = '"+nomeUtilizador_Email +"'");
		if (users.next()){
			String pass = users.getString("PASSWORD");
			if(pass.equals(password)){
				GregorianCalendar gc = new GregorianCalendar(); 
				gc.setTime(users.getDate("DATANASCIMENTO"));
				GregorianCalendar gcregisto = new GregorianCalendar(); 
				gcregisto.setTime(users.getDate("DATAREGISTO"));
				Utilizador user = new Utilizador(users.getString("NOMEUTILIZADOR"), users.getString("NOME"),
				users.getString("EMAIL"), users.getString("PASSWORD"), gc, new Localidade(users.getString("LOCALIDADE"))); // 62 (pass to PASSWORD)
				user.setMorada(users.getString("MORADA"));
				user.setTelemovel(users.getString("TELEMOVEL"));
				user.setCodigoPostal(users.getString("CODIGOPOSTAL"));
				user.setContaPaypal(users.getString("CONTAPAYPAL"));
				user.setContaMBNet(users.getString("CONTAMBNET"));
				user.setAvaliacaoComprador(users.getDouble("AVALIACAOCOMPRADOR"));
				user.setAvaliacaoVendedor(users.getDouble("AVALIACAOVENDEDOR"));
				user.setAvaliacoesNegativas(users.getInt("AVALIACOESNEGATIVAS"));
				user.setAvaliacoesPositivas(users.getInt("AVALIACOESPOSITIVAS"));
				user.setTotalGasto(users.getDouble("TOTALGASTO"));
				user.setTotalAVender(users.getInt("TOTALAVENDER"));
				user.setTotalGanho(users.getDouble("TOTALGANHO"));
				user.setNrAvaliacoesVendaNeg(users.getInt("NRAVALIACOESVENDAPOS"));
				user.setNrAvaliacoesVendaPos(users.getInt("NRAVALIACOESVENDANEG"));
				user.setNrCompras(users.getInt("NRCOMPRAS"));
				user.setNrVendas(users.getInt("NRVENDAS"));
				user.setDescricao(users.getString("DESCRICAO"));
				if(users.getInt("DADOSCOMPLETOS") > 0)user.setPerfilCompleto(true);
				else user.setPerfilCompleto(false);
				user.setDataRegisto(gcregisto);
				Blob imagem = users.getBlob("IMAGEM");
				user.setImagem(imagem);
				if(users.getInt("APAGADO") > 0)user.setApagado(true);
				else user.setApagado(false);
			return user;
			}
		}	
		return null;
	}		
	
	public static Utilizador getUtilizador (String nomeUtilizador) throws SQLException{
		getConnection();
		ResultSet users = stmt.executeQuery("SELECT * FROM Utilizadores WHERE nomeutilizador = '"+nomeUtilizador+"'");
		users.first();
		GregorianCalendar gc = new GregorianCalendar(); 
		gc.setTime(users.getDate("DATANASCIMENTO"));
		GregorianCalendar gcregisto = new GregorianCalendar(); 
		gcregisto.setTime(users.getDate("DATAREGISTO"));
		Utilizador user = new Utilizador(users.getString("NOMEUTILIZADOR"), users.getString("NOME"),
		users.getString("EMAIL"), users.getString("PASSWORD"), gc, new Localidade(users.getString("LOCALIDADE"))); // 62 (pass to PASSWORD)
		user.setMorada(users.getString("MORADA"));
		user.setTelemovel(users.getString("TELEMOVEL"));
		user.setCodigoPostal(users.getString("CODIGOPOSTAL"));
		user.setContaPaypal(users.getString("CONTAPAYPAL"));
		user.setContaMBNet(users.getString("CONTAMBNET"));
		user.setAvaliacaoComprador(users.getDouble("AVALIACAOCOMPRADOR"));
		user.setAvaliacaoVendedor(users.getDouble("AVALIACAOVENDEDOR"));
		user.setAvaliacoesNegativas(users.getInt("AVALIACOESNEGATIVAS"));
		user.setAvaliacoesPositivas(users.getInt("AVALIACOESPOSITIVAS"));
		user.setTotalGasto(users.getDouble("TOTALGASTO"));
		user.setTotalAVender(users.getInt("TOTALAVENDER"));
		user.setTotalGanho(users.getDouble("TOTALGANHO"));
		user.setNrAvaliacoesVendaNeg(users.getInt("NRAVALIACOESVENDAPOS"));
		user.setNrAvaliacoesVendaPos(users.getInt("NRAVALIACOESVENDANEG"));
		user.setNrCompras(users.getInt("NRCOMPRAS"));
		user.setNrVendas(users.getInt("NRVENDAS"));
		user.setDescricao(users.getString("DESCRICAO"));
		if(users.getInt("DADOSCOMPLETOS") > 0)user.setPerfilCompleto(true);
		else user.setPerfilCompleto(false);
		user.setDataRegisto(gcregisto);
		Blob imagem = users.getBlob("IMAGEM");
		user.setImagem(imagem);
		if(users.getInt("APAGADO") > 0)user.setApagado(true);
		else user.setApagado(false);
		stmt.close();
		return user;
	}
			
	public boolean verificaExistenciaNomeUtilizador(String nomeUtilizador) throws SQLException {
		getConnection();
		ResultSet rs = stmt.executeQuery("SELECT * FROM Utilizadores WHERE nomeutilizador = "+"'"+nomeUtilizador+"'");
		if (rs.first()) return true;
		return false;
	}
	
	public boolean verificaExistenciaEmail(String email) throws SQLException {
		getConnection();
		ResultSet rs = stmt.executeQuery("SELECT * FROM Utilizadores WHERE EMAIL = "+"'"+email+"'");
		if (rs.first()) return true;
		return false;
	}

	public boolean novoUtilizador(Utilizador newUser) throws SQLException{
		ConnectionFactory cf = new ConnectionFactory();
		Connection c = cf.getConnection();  
		String sql = "INSERT INTO utilizadores (nomeUtilizador, nome, email, password, localidade, datanascimento) VALUES ('"+ newUser.getNomeUtilizador() +"', '"+ newUser.getNome() +"', '"+ newUser.getEmail() +"', '"+ newUser.getPassword() +"', '"+ newUser.getLocalidade().getNome() +"', TO_TIMESTAMP(?, 'YYYY-MM-DD HH24:MI:SS.FF')) ";
		PreparedStatement stmt =  c.prepareStatement(sql);
		GregorianCalendar dataNascimento = newUser.getDataNascimento();
		Timestamp timestamp = new Timestamp(dataNascimento.getTimeInMillis());
		stmt.setString(1, timestamp.toString());
		try {
			stmt.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public boolean editaUtilizador(Utilizador newuser) throws SQLException {
		getConnection();
		StringBuilder update = new StringBuilder("UPDATE utilizadores SET ");
		update.append("nome = ");
		update.append("'"+newuser.getNome()+"' ,");
		update.append("email = ");
		update.append("'"+newuser.getEmail()+"' ,");
		update.append("password = ");
		update.append("'"+newuser.getPassword()+"' ,");
		update.append("morada = ");
		update.append("'"+newuser.getMorada()+"' ,");
		update.append("telemovel = ");
		update.append("'"+newuser.getTelemovel()+"' ,");
		update.append("localidade = ");
		update.append("'"+newuser.getLocalidade().getNome()+"' ,");
		update.append("codigoPostal = ");
		update.append("'"+newuser.getCodigoPostal()+"' ,");
		update.append("contaPaypal = ");
		update.append("'"+newuser.getContaPaypal()+"' ,");
		update.append("contaMBNet = ");
		update.append("'"+newuser.getContaMBNet()+"' ,");
		update.append("descricao = ");
		update.append("'"+newuser.getDescricao()+"' ,");	
		update.append("dadoscompletos = ");
		update.append("1 ");
		update.append("WHERE ");
		update.append("nomeUtilizador = '");
		update.append(newuser.getNomeUtilizador()+"'");		
		try{	

		stmt.executeUpdate(update.toString());
		stmt.close();

		editaImagemUtilizador(newuser);
		} catch(SQLException e){
			return false;
		}
		return true;
	}
	public boolean editaImagemUtilizador(Utilizador newuser) throws SQLException {
		ConnectionFactory cf = new ConnectionFactory();
		Connection c = cf.getConnection();  
		
		String sql = "UPDATE utilizadores SET imagem = ? where nomeUtilizador = '"+ newuser.getNomeUtilizador() +"'";
		PreparedStatement stmt = c.prepareStatement(sql);

	    long tamanho = newuser.getImagem().length();
	    byte[] blob = newuser.getImagem().getBytes(1, (int)tamanho);
	    stmt.setObject(1,blob);
		stmt.executeUpdate();
		return true;
	}
	public boolean verificaSeUtilizadorExiste(String idUtilizador) throws SQLException {
		getConnection();
		ResultSet rs = stmt.executeQuery("SELECT * FROM Utilizadores WHERE nomeUtilizador= "+ idUtilizador);
		if (rs!=null) return true;
		return false;
	}
}
