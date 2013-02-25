package dao;

import java.sql.SQLException;

public class MetodoPagamentoDAO extends AbstractDAO{

	public void addMetodoPagamento(int idProduto, String metodo) throws SQLException {
		getConnection();
		String sql = "INSERT INTO  MetodosPagamento (idProduto , nomeMetodo) VALUES ('"+ idProduto +"','"+ metodo +"')";
		stmt.executeUpdate(sql);
		stmt.close();
	}
}