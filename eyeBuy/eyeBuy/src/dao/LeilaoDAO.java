package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

import model.transacoes.Leilao;

public class LeilaoDAO extends AbstractDAO{

	public void addLeilao(Leilao novoleilao) throws SQLException {
		ConnectionFactory cf = new ConnectionFactory();
		Connection c = cf.getConnection(); 
		String sql = "INSERT INTO leiloes VALUES (" + novoleilao.getIDProduto() + ", ? , " + novoleilao.getPrecoActual() +")";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setTimestamp(1, ( new Timestamp(novoleilao.getDatafim().getTimeInMillis())));
		ps.executeQuery();
		ps.close();
	}

	public GregorianCalendar getDataFim(int idProduto) throws SQLException {
		getConnection();
		String sql = "SELECT datafim FROM leiloes WHERE idProduto = '" + idProduto + "'";
		ResultSet rs = stmt.executeQuery(sql);
		rs.first();
		GregorianCalendar res = new GregorianCalendar();
		res.setTime(rs.getDate("datafim"));
		stmt.close();
		return res;
	}

	public double getPrecoActual(int idProduto) throws SQLException {
		getConnection();
		String sql = "SELECT precoactual FROM leiloes WHERE idProduto = '" + idProduto + "'";
		ResultSet rs = stmt.executeQuery(sql);
		rs.first();
		int res = rs.getInt("precoactual");
		stmt.close();
		System.out.println(res);
		return res;
	}

	public Leilao getLeilao(int idProduto) throws SQLException {
		getConnection();
		String sql = "SELECT * FROM leiloes WHERE idProduto = '" + idProduto + "'";
		ResultSet rs = stmt.executeQuery(sql);
		rs.first();
		
		Leilao res = new Leilao();
		
		res.setPrecoActual(rs.getInt("precoactual"));
		res.setIDProduto(rs.getInt("idProduto"));
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(rs.getDate("datafim"));
		
		res.setDatafim(gc);
		
		stmt.close();
		return res;
	}

	public void editLeilao(Leilao leilao) throws SQLException {
		ConnectionFactory cf = new ConnectionFactory();
		Connection c = cf.getConnection(); 
		String sql = "UPDATE leiloes SET datafim =  ? , precoactual = " + leilao.getPrecoActual() +" where  idProduto = " + leilao.getIDProduto();
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setTimestamp(1, ( new Timestamp(leilao.getDatafim().getTimeInMillis())));
		ps.executeQuery();
		ps.close();
	}
}