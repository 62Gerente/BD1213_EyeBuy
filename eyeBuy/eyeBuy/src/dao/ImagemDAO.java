package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

import model.Imagem;

public class ImagemDAO extends AbstractDAO {

	public Blob getImagemProduto(int idProduto) throws SQLException {
		getConnection();
		Blob imagem = null;
		ResultSet rs = stmt.executeQuery("SELECT * FROM Fotos WHERE idProduto = "+idProduto);

		if(rs.next()) 
			imagem = rs.getBlob("FOTO");
		return imagem;
	}

	public Map<String,Imagem> getImagens(int idProduto) throws SQLException {
		Map<String,Imagem> res = new TreeMap<String,Imagem>();
		getConnection();
		Blob blob = null;
		String descricao;
		ResultSet rs = stmt.executeQuery("SELECT foto, descricao FROM Fotos WHERE idProduto = "+idProduto);
		while(rs.next()){ 
			blob = rs.getBlob("foto");
			descricao = rs.getString("descricao"); 
			Imagem imagem = new Imagem(descricao, blob);
			res.put(descricao,imagem);
		}
		return res;
	}

	public static void apagaImagensProduto (int idProduto) throws SQLException{
		getConnection();
		stmt.executeUpdate("DELETE FROM Fotos WHERE idProduto = '"+idProduto+"'");
		stmt.close();
	}
	public static void updateImagens(int idProduto, TreeMap<String, Imagem> imagens) throws SQLException {		
		apagaImagensProduto(idProduto);
		ConnectionFactory cf = new ConnectionFactory();
		Connection c = cf.getConnection(); 
		for(String s : imagens.keySet()){
			String sql = "INSERT INTO Fotos (idFoto ,idProduto, foto, descricao) VALUES (fotos_id.nextval ,"+ idProduto +", ? , '"+ s +"')";
		    PreparedStatement stmt = c.prepareStatement(sql);	         
		    long tamanho = imagens.get(s).getImagem().length();
		    byte[] blob = imagens.get(s).getImagem().getBytes(1, (int)tamanho);
		    stmt.setObject(1,blob);
			stmt.executeUpdate();
		    stmt.close();
			
		}
		getConnection();
		stmt.executeUpdate("UPDATE Produtos SET nrImagens = "+imagens.size()+" WHERE id = "+idProduto);
		
	}
	

}