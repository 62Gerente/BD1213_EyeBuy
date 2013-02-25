package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Utilizador;


public class HistoricoDAO extends AbstractDAO{

	public String[] listaUltimasVendas(String nomeUtilizador) throws SQLException {
		getConnection();
		ResultSet rs = stmt.executeQuery("SELECT * FROM HistoricoParaConsulta where vendedor ='" + nomeUtilizador +"'");
		String [] res = new String [getNumeroHistoricos(nomeUtilizador)];
		int i = 0;
		while (rs.next()){
			res[i] =(rs.getInt("preco")) + " € \t" +
					(rs.getString("comprador")) + "\t" + 
					(rs.getTimestamp("data"));
			i++;
		}
		return res;
	}

	public String[] listaHistorico(String nomeUtilizador) throws SQLException {
		getConnection();
		ResultSet rs = stmt.executeQuery("SELECT * FROM HistoricoParaConsulta where comprador ='"+ nomeUtilizador+"' or vendedor ='" + nomeUtilizador +"'");
		String [] res = new String [getNumeroHistoricos(nomeUtilizador)];
		int i = 0;
		while (rs.next()){
			res[i] =(rs.getInt("preco")) + " € \t" + 
					(rs.getString("vendedor")) + "\t" + 
					(rs.getString("comprador")) + "\t" + 
					(rs.getTimestamp("data"));
			i++;
		}
		return res;
	}

	public String[] listaHistoricoCompras(String nomeUtilizador) throws SQLException {
		getConnection();
		ResultSet rs = stmt.executeQuery("SELECT * FROM HistoricoParaConsulta WHERE comprador = '"+nomeUtilizador +"'");
		String [] res = new String [getNumeroHistoricos(nomeUtilizador)];
		int i = 0;
		while (rs.next()){
			res[i] =(rs.getInt("preco")) + " € \t" + 
					(rs.getString("vendedor")) + "\t" + 
					(rs.getTimestamp("data"));
			i++;
		}
		return res;
	}

	public int getNumeroHistoricos(String nomeUtilizador) throws SQLException {
		getConnection();
		ResultSet rs = stmt.executeQuery("SELECT count(1) FROM HistoricoParaConsulta where comprador ='"+ nomeUtilizador+"' or vendedor ='" + nomeUtilizador +"'");
		rs.first();
		int num = rs.getInt("count(1)");
		return num;
	}

	public int getNumeroCompras(String nomeUtilizador) throws SQLException {
		getConnection();
		ResultSet rs = stmt.executeQuery("SELECT count(1) FROM HistoricoParaConsulta where comprador ='"+ nomeUtilizador+"'");
		rs.first();
		int num = rs.getInt("count(1)");
		return num;
	}

	public int getNumeroVendas(String nomeUtilizador) throws SQLException {
		getConnection();
		ResultSet rs = stmt.executeQuery("SELECT count(1) FROM HistoricoParaConsulta where vendedor ='"+ nomeUtilizador+"'");
		rs.first();
		int num = rs.getInt("count(1)");
		return num;
	}

	public int getTotalGasto(String nomeUtilizador) throws SQLException {
		getConnection();
		int res = 0;
		ResultSet rs = stmt.executeQuery("SELECT SUM(preco) FROM HistoricoParaConsulta where comprador ='"+ nomeUtilizador+"'");
		rs.first();
		res = rs.getInt("SUM(preco)");
		return res;
	}

	public int getTotalGanho(String nomeUtilizador) throws SQLException {
		getConnection();
		int res = 0;
		ResultSet rs = stmt.executeQuery("SELECT SUM(preco) FROM HistoricoParaConsulta where vendedor ='"+ nomeUtilizador+"'");
		rs.first();
		res = rs.getInt("SUM(preco)");
		return res;
	}

	public int getNrAvaliacoesVendaDisponiveis(Utilizador utilizador, Utilizador aVer) throws SQLException {
		getConnection();
		String sql = "SELECT count(1) FROM Historico h INNER JOIN Produtos p ON p.id = h.idProduto WHERE h.dataAvaliado is null AND h.Comprador = '"
					+ utilizador.getNomeUtilizador() + "' AND p.nomeUtilizador = '"+ aVer.getNomeUtilizador() +"'";
		ResultSet rs = stmt.executeQuery(sql);
		rs.first();
		int res = rs.getInt("count(1)");
		return res;
	}

	public int getNrAvaliacoesCompraDisponiveis(Utilizador utilizador, Utilizador aVer) throws SQLException {
		getConnection();
		String sql = "SELECT count(1) FROM Historico h INNER JOIN Produtos p ON p.id = h.idProduto WHERE h.dataAvaliado is null AND h.Comprador = '"
					+ aVer.getNomeUtilizador() + "' AND p.nomeUtilizador = '"+ utilizador.getNomeUtilizador() +"'";
		ResultSet rs = stmt.executeQuery(sql);
		rs.first();
		int res = rs.getInt("count(1)");
		return res;
	}

	public void avaliaPosComprador(Utilizador utilizador, String user) throws SQLException {
		getConnection();
		String sql = "SELECT avaliacoesPositivas, avaliacoesNegativas FROM Utilizadores WHERE nomeutilizador ='"+ user +"'";
		ResultSet rs = stmt.executeQuery(sql);
		rs.first();
		int avaliacaoP = rs.getInt("avaliacoesPositivas") + 1;
		int avaliacaoN = rs.getInt("avaliacoesNegativas");
		int avaliacao = ((avaliacaoP * 100 )/ (avaliacaoP + avaliacaoN));
		stmt.close();
		getConnection();
		sql = "UPDATE Utilizadores SET avaliacoesPositivas = "+ avaliacaoP  +" WHERE nomeutilizador ='"+ user +"'";
		stmt.executeUpdate(sql);
		stmt.close();
		getConnection();
		sql = "UPDATE Utilizadores SET avaliacaoComprador = "+ avaliacao  +" WHERE nomeutilizador ='"+ user +"'";
		stmt.executeUpdate(sql);
		stmt.close();
		getConnection();
		 sql = "SELECT idproduto FROM Historico h INNER JOIN Produtos p ON p.id = h.idProduto WHERE h.dataAvaliado is null AND h.Comprador = '"
				+ user + "' AND p.nomeUtilizador = '"+ utilizador.getNomeUtilizador()  +"'";
		rs = stmt.executeQuery(sql);
		rs.first();
		int idprod = rs.getInt("idproduto");
		stmt.close();
		getConnection();
		sql="UPDATE historico set dataavaliado=CURRENT_TIMESTAMP where idproduto = "+idprod;
		stmt.executeUpdate(sql);
		stmt.close();
	}
	public void avaliaNegComprador(Utilizador utilizador, String user) throws SQLException {
		getConnection();
		String sql = "SELECT avaliacoesPositivas, avaliacoesNegativas FROM Utilizadores WHERE nomeutilizador ='"+ user +"'";
		ResultSet rs = stmt.executeQuery(sql);
		rs.first();
		int avaliacaoP = rs.getInt("avaliacoesPositivas");
		int avaliacaoN = rs.getInt("avaliacoesNegativas")  + 1;
		int avaliacao = ((avaliacaoP * 100 )/ (avaliacaoP + avaliacaoN));
		stmt.close();
		getConnection();
		sql = "UPDATE Utilizadores SET avaliacoesNegativas = "+ avaliacaoN  +" WHERE nomeutilizador ='"+ user +"'";
		stmt.executeUpdate(sql);
		stmt.close();
		getConnection();
		sql = "UPDATE Utilizadores SET avaliacaoComprador = "+ avaliacao  +" WHERE nomeutilizador ='"+ user +"'";
		stmt.executeUpdate(sql);
		stmt.close();
		getConnection();
		 sql = "SELECT idproduto FROM Historico h INNER JOIN Produtos p ON p.id = h.idProduto WHERE h.dataAvaliado is null AND h.Comprador = '"
				+ user + "' AND p.nomeUtilizador = '"+ utilizador.getNomeUtilizador() +"'";
		rs = stmt.executeQuery(sql);
		rs.first();
		int idprod = rs.getInt("idproduto");
		stmt.close();
		getConnection();
		sql="UPDATE historico set dataavaliado=CURRENT_TIMESTAMP where idproduto = "+idprod;
		stmt.executeUpdate(sql);
		stmt.close();
	}
	public void avaliaPosVendedor(Utilizador utilizador, String user) throws SQLException {
		getConnection();
		String sql = "SELECT nrAvaliacoesVendaPos, nrAvaliacoesVendaNeg FROM Utilizadores WHERE nomeutilizador ='"+ user +"'";
		ResultSet rs = stmt.executeQuery(sql);
		rs.first();
		int avaliacaoP = rs.getInt("nrAvaliacoesVendaPos") + 1;
		int avaliacaoN = rs.getInt("nrAvaliacoesVendaNeg");
		int avaliacao = ((avaliacaoP * 100 )/ (avaliacaoP + avaliacaoN));
		stmt.close();
		getConnection();
		sql = "UPDATE Utilizadores SET nrAvaliacoesVendaPos = "+ avaliacaoP  +" WHERE nomeutilizador ='"+ user +"'";
		stmt.executeUpdate(sql);
		stmt.close();
		getConnection();
		sql = "UPDATE Utilizadores SET avaliacaoVendedor = "+ avaliacao  +" WHERE nomeutilizador ='"+ user +"'";
		stmt.executeUpdate(sql);
		stmt.close();
		getConnection();
		 sql = "SELECT idproduto FROM Historico h INNER JOIN Produtos p ON p.id = h.idProduto WHERE h.dataAvaliado is null AND h.Comprador = '"
				+ utilizador.getNomeUtilizador() + "' AND p.nomeUtilizador = '"+ user +"'";
		rs = stmt.executeQuery(sql);
		rs.first();
		int idprod = rs.getInt("idproduto");
		stmt.close();
		getConnection();
		sql="UPDATE historico set dataavaliado=CURRENT_TIMESTAMP where idproduto = "+idprod;
		stmt.executeUpdate(sql);
		stmt.close();
	}
	public void avaliaNegVendedor(Utilizador utilizador, String user) throws SQLException {
		getConnection();
		String sql = "SELECT nrAvaliacoesVendaPos, nrAvaliacoesVendaNeg FROM Utilizadores WHERE nomeutilizador ='"+user +"'";
		ResultSet rs = stmt.executeQuery(sql);
		rs.first();
		int avaliacaoP = rs.getInt("nrAvaliacoesVendaPos");
		int avaliacaoN = rs.getInt("nrAvaliacoesVendaNeg") + 1;
		int avaliacao = ((avaliacaoP * 100 )/ (avaliacaoP + avaliacaoN));
		stmt.close();
		getConnection();
		sql = "UPDATE Utilizadores SET nrAvaliacoesVendaNeg = "+ avaliacaoN  +" WHERE nomeutilizador ='"+ user+"'";
		stmt.executeUpdate(sql);
		stmt.close();
		getConnection();
		sql = "UPDATE Utilizadores SET avaliacaoVendedor = "+ avaliacao  +" WHERE nomeutilizador ='"+ user +"'";
		stmt.executeUpdate(sql);
		stmt.close();
		sql = "SELECT idproduto FROM Historico h INNER JOIN Produtos p ON p.id = h.idProduto WHERE h.dataAvaliado is null AND h.Comprador = '"
				+ utilizador.getNomeUtilizador() + "' AND p.nomeUtilizador = '"+ user +"'";
		rs = stmt.executeQuery(sql);
		rs.first();
		int idprod = rs.getInt("idproduto");
		stmt.close();
		getConnection();
		sql="UPDATE historico set dataavaliado = CURRENT_TIMESTAMP where idproduto = "+idprod;
		stmt.executeUpdate(sql);
		stmt.close();
	}
}