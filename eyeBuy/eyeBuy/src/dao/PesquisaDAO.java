package dao;

import java.sql.ResultSet;
import java.sql.SQLException;



public class PesquisaDAO extends AbstractDAO{

	public void addPesquisa(String nomeUtilizador, String pesquisa) throws SQLException {
		getConnection();
		ResultSet rs = stmt.executeQuery("SELECT * FROM Pesquisas WHERE nomeUtilizador ='"+ nomeUtilizador+"' and pesquisa ='" + pesquisa+"'");
		
		if(!rs.next()){
			stmt.executeUpdate("INSERT INTO Pesquisas (nomeUtilizador, pesquisa) VALUES ('"+nomeUtilizador+"', '"+ pesquisa +"')");
		}
		else{
			int nrPesquisas = rs.getInt("numeroPesquisas") + 1;
			stmt.executeUpdate("UPDATE Pesquisas SET numeroPesquisas = '"+ nrPesquisas+"' WHERE nomeUtilizador ='"+ nomeUtilizador+"' and pesquisa ='" + pesquisa+"'");
		}
	}


}