	package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import model.Categoria;
import model.Localidade;
import model.Produto;
import model.SubCategoria;

public class ProdutoDAO extends AbstractDAO{

	public static Produto getProduto(int id) throws SQLException{
		getConnection();
		ResultSet rsp = stmt.executeQuery("SELECT * FROM Produtos WHERE id = "+ id);
		if (rsp.next()){
			Produto p = new Produto ();
			p.setId(id);
			p.setApagado( (rsp.getInt("APAGADO") > 0) ? true : false);
			Categoria c = new Categoria(rsp.getString("CATEGORIA"));
			p.setCategoria(c);
			GregorianCalendar gcC = new GregorianCalendar(); 
			gcC.setTime(rsp.getDate("DATACOLOCACAO"));
			p.setDataColocacao(gcC);
			try {
				rsp.getDate("DATAVENDA");
				p.setVendido(true);
			} catch (Exception e) {
				p.setVendido(false);
			}	
			try {
				rsp.getDate("DATAAVALIACAO");
				p.setAvaliado(true);
			} catch (Exception e) {
				p.setAvaliado(false);
			}	
			p.setDescricao(rsp.getString("DESCRICAO"));
			p.setEstado(rsp.getString("ESTADO"));
			p.setId(rsp.getInt("ID"));
			p.setLocalidade(new Localidade(rsp.getString("LOCALIDADE")));
			p.setMetodoVenda(rsp.getString("METODOVENDA"));
			p.setNome(rsp.getString("NOME"));
			p.setNrImagens(rsp.getInt("NRIMAGENS"));
			p.setPreco(rsp.getDouble("PRECO"));
			p.setQuantidade(rsp.getInt("QUANTIDADE"));
			p.setSubCategoria( new SubCategoria (rsp.getString("SUBCATEGORIA"), c ));
			String idutilizador = rsp.getString("NOMEUTILIZADOR");
			stmt.close();
			p.setUtilizador( UtilizadorDAO.getUtilizador(idutilizador));


			return p;
		}
		return null;
	}

	public ArrayList<Produto> getProdutosPodemInteressar(String nomeUtilizador, int posicaoactual, int nrprodutos) throws SQLException {
		getConnection();

		String sql = "SELECT * FROM (SELECT id, ROWNUM rn FROM (SELECT id, ROWNUM FROM v_ProdutosMontra WHERE nomeUtilizador NOT LIKE '" + nomeUtilizador + "' ORDER BY f_nrcoincidencias(nome, '"+ nomeUtilizador +"') ) WHERE ROWNUM < "+ (posicaoactual + nrprodutos + 1) +") WHERE rn  >" + posicaoactual;

		ArrayList<Produto> res = new ArrayList<Produto>();
		ArrayList<Integer> indxs = new ArrayList<Integer>();
		ResultSet rs = stmt.executeQuery(sql.toString());
		while (rs.next()){
			indxs.add(rs.getInt("id"));
		}
		stmt.close();
		for(int id : indxs) res.add(ProdutoDAO.getProduto(id));
		return res;
	}
	public ArrayList<Produto> getNovosProdutos(String nomeUtilizador, int posicaoactual, int nrprodutos) throws SQLException {
		getConnection();
		String sql = "SELECT * FROM (SELECT id, ROWNUM rn FROM v_novosProdutos WHERE nomeUtilizador NOT LIKE '" + nomeUtilizador + "' AND ROWNUM < "+ (posicaoactual + nrprodutos + 1) +")  WHERE RN > " + posicaoactual;
		ArrayList<Produto> res = new ArrayList<Produto>();
		ArrayList<Integer> indxs = new ArrayList<Integer>();
		ResultSet rs = stmt.executeQuery(sql.toString());
		while (rs.next()){
			indxs.add(rs.getInt("id"));
		}
		stmt.close();
		for(int id : indxs) res.add(ProdutoDAO.getProduto(id));
		return res;
	}

	public ArrayList<Produto> getProdutosMelhoresVendedores(String nomeUtilizador, int posicaoactual, int nrprodutos) throws SQLException {
		getConnection();
		String sql = "SELECT * FROM (SELECT id, ROWNUM rn FROM v_dosMelhores WHERE nomeUtilizador NOT LIKE '" + nomeUtilizador + "' AND ROWNUM < "+ (posicaoactual + nrprodutos + 1) +") WHERE rn > " + posicaoactual;
		ArrayList<Produto> res = new ArrayList<Produto>();
		ArrayList<Integer> indxs = new ArrayList<Integer>();
		ResultSet rs = stmt.executeQuery(sql.toString());
		while (rs.next()){
			indxs.add(rs.getInt("id"));
		}
		stmt.close();
		for(int id : indxs) res.add(ProdutoDAO.getProduto(id));
		return res;
	}

	public ArrayList<Produto> getProdutosDesejados(String nomeUtilizador, int posicaoactual, int nrprodutos) throws SQLException {
		getConnection();
		String sql = "SELECT * FROM (Select p.idProduto, ROWNUM rn from produtosdesejados p where p.nomeUtilizador = '"+nomeUtilizador+"' and ROWNUM < "+ (posicaoactual + nrprodutos + 1) +") WHERE rn > "+ posicaoactual;

		ArrayList<Produto> res = new ArrayList<Produto>();
		ArrayList<Integer> indxs = new ArrayList<Integer>();
		ResultSet rs = stmt.executeQuery(sql.toString());
		while (rs.next()){
			indxs.add(rs.getInt("idProduto"));
		}
		stmt.close();
		for(int id : indxs) res.add(ProdutoDAO.getProduto(id));
		return res;
	}

	public ArrayList<Produto> getProdutosSeguidos(String nomeUtilizador, int posicaoactual, int nrprodutos) throws SQLException {
		getConnection();
		ArrayList<Produto> res = new ArrayList<Produto>();
		String sql = "SELECT * FROM (Select p.idProduto, ROWNUM rn from produtosseguidos p where p.nomeUtilizador = '"+nomeUtilizador+"' and ROWNUM < "+ (posicaoactual + nrprodutos + 1) +") WHERE rn > "+ posicaoactual;
		ArrayList<Integer> indxs = new ArrayList<Integer>();
		ResultSet rs = stmt.executeQuery(sql.toString());
		while (rs.next()){
			indxs.add(rs.getInt("idProduto"));
		}
		stmt.close();
		for(int id : indxs) res.add(ProdutoDAO.getProduto(id));
		return res;
	}

	public void removeProdutoSeguido(String nomeUtilizador, int idProduto) throws SQLException {
		getConnection();
		String sql = "DELETE FROM ProdutosSeguidos WHERE nomeUtilizador = '"+ nomeUtilizador +"' AND idProduto = "+ idProduto;
		stmt.executeUpdate(sql);

	}

	public void removeProdutoDesejado(String nomeUtilizador, int idProduto) throws SQLException {
		getConnection();
		String sql = "DELETE FROM ProdutosDesejados WHERE nomeUtilizador = '"+ nomeUtilizador +"' AND idProduto = "+ idProduto;
		stmt.executeUpdate(sql);

	}

	public void removeProdutoCarrinho (String nomeUtilizador, int idProduto) throws SQLException {
		getConnection();
		String sql = "DELETE FROM CarrinhoCompras WHERE nomeUtilizador = '"+ nomeUtilizador +"' AND idProduto = "+ idProduto;
		stmt.executeUpdate(sql);
		stmt.close();

	}
	public void limpaCarrinho(String nomeUtilizador) throws SQLException {
		ArrayList<Integer> produtos = new ArrayList<Integer>();
		getConnection();
		String sql = "SELECT idProduto FROM CarrinhoCompras WHERE nomeUtilizador = '"+ nomeUtilizador + "'";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			produtos.add(rs.getInt("idProduto"));
		}
		stmt.close();
		for(Integer idProduto : produtos){
			removeProdutoCarrinho (nomeUtilizador, idProduto);
		}
	}

	public ArrayList<Produto> getMontraProdutos(String nomeUtilizador, int posicaoactual,int nrprodutos) throws SQLException {
		getConnection();
		ArrayList<Produto> res = new ArrayList<Produto>();
		String sql = "SELECT * FROM (Select m.id, ROWNUM rn from v_produtosmontra m where m.nomeUtilizador = '"+nomeUtilizador+"' and ROWNUM <"+ (posicaoactual + nrprodutos + 1) +") WHERE rn > "+ posicaoactual;
		ResultSet rs = stmt.executeQuery(sql.toString());
		ArrayList<Integer> indxs = new ArrayList<Integer>();
		while (rs.next()){
			indxs.add(rs.getInt("id"));
		}
		stmt.close();
		for(int id : indxs) res.add(ProdutoDAO.getProduto(id));
		return res;
	}



	public String[] listaCarrinho(String nomeUtilizador) throws SQLException {
		getConnection();
		String sql = "select p.* from produtos p, carrinhocompras c where p.id = c.idProduto and c.nomeUtilizador = '" + nomeUtilizador +"'";
		ResultSet rs = stmt.executeQuery(sql);

		ArrayList<String> list = new ArrayList<String>();
		while(rs.next()){
			list.add(rs.getInt("Id")+" "+rs.getString("Nome")+" "+ rs.getDouble("Preco"));
		}
		String [] res = list.toArray(new String[list.size()]);
		stmt.close();
		return res;
	}

	public String[] listaCarrinhoPaypal(String nomeUtilizador) throws SQLException {
		getConnection();
		String sql = "select p.* from carrinhocompras c, metodospagamento m, produtos p where p.id = c.idProduto and c.idProduto = m.idProduto AND c.nomeUtilizador = '" + nomeUtilizador +"' AND m.nomemetodo like 'Paypal'";
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<String> list = new ArrayList<String>();

		while(rs.next()){
			list.add(rs.getInt("Id")+" "+rs.getString("Nome")+" "+ rs.getDouble("Preco"));
		}
		String [] res = list.toArray(new String[list.size()]);
		stmt.close();
		return res;
	}

	public String[] listaCarrinhoMBNet(String nomeUtilizador) throws SQLException {
		getConnection();
		String sql = "select p.* from carrinhocompras c, metodospagamento m, produtos p where p.id = c.idProduto and c.idProduto = m.idProduto AND c.nomeUtilizador = '" + nomeUtilizador +"' AND m.nomemetodo like 'MBNet'";
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<String> list = new ArrayList<String>();

		while(rs.next()){
			list.add(rs.getInt("Id")+" "+rs.getString("Nome")+" "+ rs.getDouble("Preco"));
		}
		String [] res = list.toArray(new String[list.size()]);
		stmt.close();
		return res;
	}


	public String[] listaMontra(String nomeUtilizador) throws SQLException {
		getConnection();
		String sql = "SELECT * FROM v_ProdutosMontra WHERE nomeUtilizador = '" + nomeUtilizador +"'";
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<String> list = new ArrayList<String>();
		while(rs.next()){
			list.add(rs.getInt("Id")+" "+rs.getString("Nome")+" "+ rs.getDouble("Preco"));
		}
		String [] res = list.toArray(new String[list.size()]);
		stmt.close();
		return res;
	}

	public double getCustoTotalCarrinhoPaypal(String nomeUtilizador) throws SQLException {
		getConnection();
		String sql = "SELECT nvl(sum(p.preco),0) FROM Produtos p, CarrinhoCompras c, MetodosPagamento m WHERE c.nomeUtilizador = '"+ nomeUtilizador +"' AND p.id = c.idProduto AND m.idProduto = p.id AND m.nomeMetodo = 'Paypal'";
		ResultSet rs = stmt.executeQuery(sql);
		rs.first();
		return rs.getInt("nvl(sum(p.preco),0)");
	}

	public double getCustoTotalCarrinhoMBNet(String nomeUtilizador) throws SQLException {
		getConnection();
		String sql = "SELECT nvl(sum(p.preco),0) FROM Produtos p, CarrinhoCompras c, MetodosPagamento m WHERE c.nomeUtilizador = '"+ nomeUtilizador +"' AND p.id = c.idProduto AND m.idProduto = p.id AND m.nomeMetodo = 'MBNet'";		ResultSet rs = stmt.executeQuery(sql);
		rs.first();
		return rs.getInt("nvl(sum(p.preco),0)");
	}

	public double getCustoTotalCarrinho(String nomeUtilizador) throws SQLException {
		getConnection();
		String sql = "SELECT nvl(sum(Produtos.preco),0) FROM Produtos, CarrinhoCompras WHERE CarrinhoCompras.nomeUtilizador = '"+ nomeUtilizador +"' AND Produtos.id = CarrinhoCompras.idProduto";
		ResultSet rs = stmt.executeQuery(sql);
		rs.first();
		return rs.getInt("nvl(sum(Produtos.preco),0)");
	}

	public void removeProdutoCarrinhoListado(String nomeUtilizador, String produtocarrinholistado) throws SQLException {
		StringTokenizer st = new StringTokenizer(produtocarrinholistado, " ");
		String id =st.nextToken();
		removeProdutoCarrinho(nomeUtilizador, Integer.parseInt(id));
	}


	public void removeProdutoMontra(String nomeUtilizador,int idProduto) throws SQLException {
		getConnection();
		String sql = "UPDATE Produtos SET apagado = 1 WHERE id = "+ idProduto;
		stmt.executeUpdate(sql);
	}

	public int novoProduto(Produto novo) throws SQLException {
		ConnectionFactory cf = new ConnectionFactory();
		Connection c = cf.getConnection(); 
		StringBuilder sql = new StringBuilder("INSERT INTO Produtos (id, nome, preco, descricao, estado, quantidade, nrImagens, metodoVenda, localidade, categoria, subcategoria, nomeUtilizador) VALUES (produtos_id.nextval,");  
		sql.append("'"+ novo.getNome() +"', ");
		sql.append( novo.getPreco() +", ");
		sql.append("'"+ novo.getDescricao() +"', ");
		sql.append("'"+ novo.getEstado() +"', ");
		sql.append( novo.getQuantidade() +", ");
		sql.append( novo.getNrImagens() +", ");
		sql.append("'"+ novo.getMetodoVenda() +"', ");
		sql.append("'"+ novo.getLocalidade().getNome() +"', ");
		sql.append("'"+ novo.getCategoria().getNome() +"', ");
		sql.append("'"+ novo.getSubCategoria().getNome()+"', ");
		sql.append("'"+ novo.getUtilizador().getNomeUtilizador()+"') ");
		PreparedStatement stmt =  c.prepareStatement(sql.toString());

		stmt.executeUpdate(sql.toString());
		stmt.close();
		return utlimoProdutoInserido(novo.getUtilizador().getNomeUtilizador());
	}

	public static int utlimoProdutoInserido (String nomeUtilizador) throws SQLException{
		getConnection();
		ResultSet rs = stmt.executeQuery("SELECT id FROM produtos where nomeUtilizador ='"+ nomeUtilizador +"' order by id DESC ");
		rs.first();
		int id = rs.getInt("id");
		stmt.close();
		return id;
	}

	public Produto getProdutoRelacionadoListado(String produtoRelacionadoListado) throws SQLException {
		StringTokenizer st = new StringTokenizer (produtoRelacionadoListado, " ");
		String id = st.nextToken();
		Produto p = ProdutoDAO.getProduto(Integer.parseInt(id));
		return p;
	}

	public Produto getProdutoMontraListado(String produtoMontraListado) throws  SQLException {
		StringTokenizer st = new StringTokenizer (produtoMontraListado, " ");
		String id = st.nextToken();
		Produto p = ProdutoDAO.getProduto(Integer.parseInt(id));
		return p;
	}

	public boolean estaNosDesejados(String nomeUtilizador, int idProduto) throws SQLException {
		getConnection();
		String sql = "SELECT Count(1) FROM ProdutosDesejados WHERE nomeUtilizador = '"+ nomeUtilizador +"' AND idProduto = '"+ idProduto +"'";
		ResultSet rs = stmt.executeQuery(sql);
		rs.first();
		int val = rs.getInt("count(1)");
		stmt.close();
		if(val > 0) return true;
		return false;
	}

	public boolean estaNoCarrinho(String nomeUtilizador, int idProduto) throws SQLException {
		getConnection();
		String sql = "SELECT Count(1) FROM CarrinhoCompras WHERE nomeUtilizador = '"+ nomeUtilizador +"' AND idProduto = '"+ idProduto +"'";
		ResultSet rs = stmt.executeQuery(sql);
		rs.first();
		int val = rs.getInt("count(1)");
		stmt.close();
		if(val > 0) return true;
		return false;
	}

	public boolean compraProdutosPaypal(String nomeUtilizador) throws SQLException {
		getConnection();
		String sql = "CALL p_CheckoutCarrinho('"+nomeUtilizador+"', 'Paypal')";
		stmt.execute(sql);
		stmt.close();
		return true;
	}

	public boolean compraProdutosMBNet(String nomeUtilizador) throws SQLException {
		getConnection();
		String sql = "CALL p_CheckoutCarrinho('"+nomeUtilizador+"', 'MBNet')";
		stmt.execute(sql);
		stmt.close();
		return true;
	}

	public ArrayList<Produto> procura(String pesquisa, String categoria, String localidade,int posicaoactual,
			int nrprodutos) throws SQLException {
		getConnection();
		StringBuilder sql = new StringBuilder("SELECT * FROM (SELECT id, ROWNUM rn FROM v_ProdutosMontra WHERE upper(Nome) like '%"+ pesquisa.toUpperCase() +"%' "); 

		if(!categoria.equals("Todas")){
			sql.append("AND Categoria = '"+ categoria + "' ");
		}
		if(!localidade.equals("Todas")){
			sql.append("AND Localidade = '"+ localidade + "' ");
		}
		sql.append(" AND ROWNUM < "+ ( posicaoactual + nrprodutos+ 1) +" ) WHERE rn > ("+ posicaoactual +")");
		ArrayList<Produto> res = new ArrayList<Produto>();
		ArrayList<Integer> indxs = new ArrayList<Integer>();

		ResultSet rs = stmt.executeQuery(sql.toString());
		while (rs.next()){
			indxs.add(rs.getInt("id"));
		}
		stmt.close();
		for(int id : indxs) res.add(ProdutoDAO.getProduto(id));
		return res;
	}

	public ArrayList<Produto> procura(int posicaoactual, int nr, String pesquisa,
			String categoria, String subcategoria, String localidade,
			String estado, double precoMaiorQue, double precoMenorQue) throws SQLException {
		getConnection();
		StringBuilder sql = new StringBuilder("SELECT * FROM (SELECT id, ROWNUM rn FROM v_ProdutosMontra WHERE upper(Nome) like '%"+ pesquisa.toUpperCase() +"%' "); 

		if(!categoria.equals("Todas")){
			sql.append("AND Categoria = '"+ categoria + "' ");
		}
		if(!categoria.equals("Todas")){
			sql.append("AND SubCategoria = '"+ subcategoria + "' ");
		}
		if(!localidade.equals("Todas")){
			sql.append("AND Localidade = '"+ localidade + "' ");
		}
		if(!estado.equals("Qualquer")){
			sql.append("AND Estado = '"+ estado +"' ");
		}
		if(! (precoMenorQue == 0)){
			sql.append("AND preco between "+ precoMaiorQue + " and " + precoMenorQue + " ");
		}		
		sql.append("AND ROWNUM < "+ (posicaoactual + nr+ 1) +" ) WHERE rn > ("+ posicaoactual +")");
		ArrayList<Produto> res = new ArrayList<Produto>();
		ArrayList<Integer> indxs = new ArrayList<Integer>();

		ResultSet rs = stmt.executeQuery(sql.toString());
		while (rs.next()){
			indxs.add(rs.getInt("id"));
		}
		stmt.close();
		for(int id : indxs) res.add(ProdutoDAO.getProduto(id));
		return res;

	   }



			public ArrayList<Produto> procura(int posicaoactual, int nr, String pesquisa,
			String categoria, String subcategoria, String localidade,
			String estado, String metPagamento, String metVenda,
			int maiorQueQuantidade, int maiorQueNrImagens, String nomeVendedor, int classificacaoVendedor,
			double precoMaiorQue, double precoMenorQue) throws SQLException {
		getConnection();
		StringBuilder sql = new StringBuilder("SELECT * FROM (SELECT v.id, ROWNUM rn FROM v_ProdutosMontra v, MetodosPagamento m, Utilizadores u WHERE m.idProduto = v.id AND u.nomeUtilizador = v.nomeUtilizador AND upper(v.Nome) like '%"+ pesquisa.toUpperCase() +"%' "); 

		if(!categoria.equals("Todas")){
			sql.append("AND v.Categoria = '"+ categoria + "' ");
		}
		if(!categoria.equals("Todas")){
			sql.append("AND v.SubCategoria = '"+ subcategoria + "' ");
		}
		if(!localidade.equals("Todas")){
			sql.append("AND v.Localidade = '"+ localidade + "' ");
		}
		if(!estado.equals("Qualquer")){
			sql.append("AND v.Estado = '"+ estado +"' ");
		}
		if(!metPagamento.equals("Qualquer")){
			sql.append("AND  m.nomeMetodo = '"+ estado +"' ");
		}
		if(!metVenda.equals("Qualquer")){
			sql.append("AND v.metodoVenda = '"+ metVenda +"' ");
		}
		if(maiorQueQuantidade > 0){
			sql.append("AND v.quantidade > "+ maiorQueQuantidade +" ");
		}
		if(maiorQueNrImagens > 0){
			sql.append("AND v.nrImagens > "+ maiorQueNrImagens +" ");
		}
		if(!nomeVendedor.equals("")){
			sql.append("AND v.nomeUtilizador LIKE '%"+ nomeVendedor + "%' ");
		}
		if(classificacaoVendedor > 0){
			sql.append("AND  u.avaliacaoVendedor > "+ classificacaoVendedor + " ");
		}

		if(precoMenorQue <= 0){
			sql.append("AND preco between "+ precoMaiorQue + " and " + precoMenorQue + " ");
		}		
		sql.append("AND ROWNUM < "+ (posicaoactual + nr+ 1) +" ) WHERE rn > ("+ posicaoactual +")");
		ArrayList<Produto> res = new ArrayList<Produto>();
		ArrayList<Integer> indxs = new ArrayList<Integer>();

		ResultSet rs = stmt.executeQuery(sql.toString());
		while (rs.next()){
			indxs.add(rs.getInt("id"));
		}
		stmt.close();
		for(int id : indxs) res.add(ProdutoDAO.getProduto(id));
		return res;
	}


	public ArrayList<Produto> getProdutosRelacionados(String nomeUtilizador, int posicaoactual, int nr) throws SQLException {
		getConnection();
		String sql2 = "SELECT nome FROM produtos WHERE nomeUtilizador = '" + nomeUtilizador + "'";
		ResultSet rs2 = stmt.executeQuery(sql2);

		String nome= "";

		if(rs2.first()){			
			nome = rs2.getString("nome");
		}

		stmt.close();
		getConnection();

		String sql = "SELECT * FROM (SELECT id, ROWNUM rn FROM (SELECT id, ROWNUM FROM v_ProdutosMontra WHERE nomeUtilizador NOT LIKE '" + nomeUtilizador + "' ORDER BY f_nrcoincidenciasparciais(nome, '"+ nome +"') ) WHERE ROWNUM < "+ (posicaoactual + nr + 1) +") WHERE rn  >" + posicaoactual;

		ArrayList<Produto> res = new ArrayList<Produto>();
		ArrayList<Integer> indxs = new ArrayList<Integer>();
		ResultSet rs = stmt.executeQuery(sql.toString());
		while (rs.next()){
			indxs.add(rs.getInt("id"));
		}
		stmt.close();
		for(int id : indxs) res.add(ProdutoDAO.getProduto(id));
		return res;
	}

	public String[] listaProdutosRelacionados(String nomeUtilizador, int idProduto) throws SQLException {
		getConnection();
		String sql2 = "SELECT nome FROM produtos WHERE id = '" + idProduto + "'";
		ResultSet rs2 = stmt.executeQuery(sql2);

		rs2.first();
		String nome = rs2.getString("nome");

		stmt.close();
		getConnection();

		String sql = "SELECT * FROM v_ProdutosMontra WHERE nomeUtilizador NOT LIKE '" + nomeUtilizador + "' ORDER BY f_nrcoincidenciasparciais(nome, '"+ nome +"')";
		ResultSet rs = stmt.executeQuery(sql);

		ArrayList<String> list = new ArrayList<String>();
		while(rs.next()){
			list.add(rs.getInt("Id")+" "+rs.getString("Nome")+" "+ rs.getDouble("Preco"));
		}
		String [] res = list.toArray(new String[list.size()]);

		stmt.close();
		return res;
	}

	public void addProdutoDesejado(String nomeUtilizador, int id) throws SQLException {
		getConnection();
		String sql = "INSERT INTO PRODUTOSDESEJADOS values ('" + nomeUtilizador + "', " + id + ")";
		stmt.executeUpdate(sql);
		stmt.close();

	}


	public void addCarrinho(String nomeUtilizador, int id) throws SQLException {
		getConnection();
		String sql = "INSERT INTO CARRINHOCOMPRAS values ('" + nomeUtilizador + "', " + id + ")";
		stmt.executeUpdate(sql);
		stmt.close();


	}
}