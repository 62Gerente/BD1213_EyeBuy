package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.transacoes.Proposta;

public class PropostaDAO extends AbstractDAO{

	public int getNumeroProporsta(int id) throws SQLException {
		getConnection();
		ResultSet rs = stmt.executeQuery("SELECT count(1) FROM Propostas WHERE idProduto ="+ id);
		rs.first();
		int num = rs.getInt("count(1)");
		stmt.close();
		return num;
	}
	
	public String[] listaPropostas(int id) throws SQLException {
		String [] res = new String[getNumeroProporsta(id)];
		getConnection();
		ResultSet rs = stmt.executeQuery("select * from propostas where idProduto = " +id);
		int i = 0;
		while(rs.next()){
			res[i] =(rs.getDouble("valor"))+" € \t"+(rs.getString("idUtilizador"))+"\t"+(rs.getTimestamp("data"))+"\n";
			i++;
		}
		stmt.close();
		return res;
	}

	public boolean addProposta(Proposta p) throws SQLException {
		
		int id = p.getProduto().getId();
		String nomeUtilizador = p.getComprador().getNomeUtilizador();
		Double valor = p.getPrecoFinal();
		getConnection();
		String sql = "INSERT INTO Propostas (id, idProduto, idUtilizador, valor, data) VALUES ( propostas_id.nextval," + id + ", '" + nomeUtilizador + "', " + valor + ", CURRENT_TIMESTAMP)";
		stmt.executeQuery(sql);
		// TODO Auto-generated method stub
		stmt.close();
		return true;
	}

}
