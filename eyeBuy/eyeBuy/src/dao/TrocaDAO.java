package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;

import model.Produto;
import model.Utilizador;
import model.transacoes.Troca;

public class TrocaDAO extends AbstractDAO{

	
	// SE VIRES QUE PRECISAS DE ID NAS TROCAS! ACRESCENTA E AVISA O INSATISFEITO
	
	public int getNumeroTrocas(String nomeUtilizador) throws SQLException{
		getConnection();
		ResultSet rs = stmt.executeQuery("SELECT count(1) FROM Trocas WHERE vendedor = '"+ nomeUtilizador +"'");
		rs.first();
		int num = rs.getInt("count(1)");
		stmt.close();
		return num;
	}
	
	public String[] listaTrocas(String nomeUtilizador) throws SQLException {
		String [] res = new String[getNumeroTrocas(nomeUtilizador)];
		getConnection();
		ResultSet rs = stmt.executeQuery("select * from trocas where vendedor = '" + nomeUtilizador + "'");
		int i = 0;
		while(rs.next()){
			res[i] =(rs.getInt("id"))+"\t"+ rs.getInt("idprodutointeressado") +"\t"+rs.getInt("idprodutoparatroca")+"\t" + rs.getInt("novopreco");
			i++;
		}
		stmt.close();
		return res;
	}

	public Troca getTrocaListada(String stringListaTroca) throws NumberFormatException, SQLException {
		StringTokenizer st = new StringTokenizer (stringListaTroca, "\t");
		String id = st.nextToken();
		Troca t = TrocaDAO.getTroca(Integer.parseInt(id));
		return t;
	}

	public static Troca getTroca(int id) throws SQLException{
		getConnection();
		ResultSet rsp = stmt.executeQuery("SELECT * FROM Trocas WHERE id = "+ id);
		
		Troca res = new Troca();
		
		if (rsp.next()){
			res.setId(rsp.getInt("id"));
			res.setInteressado(UtilizadorDAO.getUtilizador(rsp.getString("interessado")));
			res.setNovoPreco(rsp.getInt("novopreco"));
			res.setProdutoInteressado(ProdutoDAO.getProduto(rsp.getInt("idprodutointeressado")));
			res.setProdutoParaTroca(ProdutoDAO.getProduto(rsp.getInt("idprodutoparatroca")));
			res.setVendedor(UtilizadorDAO.getUtilizador(rsp.getString("vendedor")));
		}
		
		stmt.close();
		return res;
	}
	
	public boolean trocapossivel(int troca) throws SQLException{
		getConnection();
		ResultSet rs = stmt.executeQuery("SELECT count(1) FROM Trocas WHERE id = "+ troca);
		rs.first();
		int num = rs.getInt("count(1)");
		stmt.close();
		
		if (num>0)
			return true;
		else
			return false;
		
	}
	
	public boolean aceitaTroca(Troca troca) throws SQLException {
		
		if(trocapossivel(troca.getId())){
			getConnection();
			String sql = "UPDATE Produtos SET apagado = 1, preco = 0 where id = " + troca.getProdutoParaTroca().getId();
			stmt.executeUpdate(sql);
			stmt.close();
			
			getConnection();
			sql = "UPDATE Produtos SET apagado = 1, preco = " + troca.getNovoPreco() + " where id = " + troca.getProdutoInteressado().getId();
			stmt.executeUpdate(sql);
			stmt.close();
			
			getConnection();
			sql = "INSERT into carrinhocompras values ('" + troca.getVendedor().getNomeUtilizador() + "' ," + troca.getProdutoParaTroca().getId() + ")";
			stmt.executeQuery(sql);
			stmt.close();
		
			getConnection();
			sql = "INSERT into carrinhocompras values ('" + troca.getInteressado().getNomeUtilizador() + "' ," + troca.getProdutoInteressado().getId() + ")";
			stmt.executeQuery(sql);
			stmt.close();
			
			return true;
		}else{
			return false;
		}
	}

	public void removeTroca(Troca troca) throws SQLException {
		getConnection();
		stmt.executeQuery("DELETE FROM trocas where id = " + troca.getId());
		stmt.close();
	}
	
	public boolean criaTroca(Troca troca) throws SQLException {
		getConnection();
		stmt.executeQuery("INSERT INTO trocas values (trocas_id.nextval, '" + troca.getInteressado().getNomeUtilizador() + "', '" + troca.getVendedor().getNomeUtilizador() + "', " + troca.getProdutoInteressado().getId() +", " + troca.getProdutoParaTroca().getId() + ", " + troca.getNovoPreco() +")");
		stmt.close();
		return true;
	}

	public int getNumeroTrocasPossiveis(Utilizador utilizadorComprador) throws SQLException{
		getConnection();
		ResultSet rs = stmt.executeQuery("SELECT count(1) FROM v_produtosMontra WHERE nomeUtilizador = '"+ utilizadorComprador.getNomeUtilizador() +"'");
		rs.first();
		int num = rs.getInt("count(1)");
		stmt.close();
		return num;
	}
	
	public String[] listaPossiveisTrocas(int idProduto, Utilizador utilizadorComprador) throws SQLException {
		String [] res = new String[getNumeroTrocasPossiveis(utilizadorComprador)];
		getConnection();
		ResultSet rs = stmt.executeQuery("select id,nome from v_produtosMontra where nomeutilizador = '" + utilizadorComprador.getNomeUtilizador() + "'");
		int i = 0;
		while(rs.next()){
			res[i] =idProduto+"\t"+ rs.getInt("id") +"\t"+rs.getString("nome");
			i++;
		}
		stmt.close();
		return res;
	}

	public Produto getPossivelTrocaListada(String possivelTrocaListada) throws NumberFormatException, SQLException {
		StringTokenizer st = new StringTokenizer (possivelTrocaListada, "\t");
		st.nextToken();
		String produtoParaTroca = st.nextToken();
		int idpt = Integer.parseInt(produtoParaTroca);
		
		Produto t = ProdutoDAO.getProduto(idpt);
		
		return t;
	}

	public boolean novaTroca(Troca troca) throws SQLException {
		return criaTroca(troca);
	}
}