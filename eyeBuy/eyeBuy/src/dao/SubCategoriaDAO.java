package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubCategoriaDAO extends AbstractDAO {

	public String[] getArraySubCategorias(String categoria) throws SQLException {
		getConnection();
		if(categoria.equals("Todas")) return new String[0];
		StringBuilder sql = new StringBuilder("SELECT * FROM Subcategorias WHERE categoria = '" + categoria + "'"); 
		ResultSet rs = stmt.executeQuery(sql.toString());
		String [] res = new String [getNumeroSubcategorias(categoria)];
		int i = 0;
		while (rs.next()){
			res[i] = rs.getString("Nome");
			i++;
		}
		stmt.close();
		return res;
	}
	public int getNumeroSubcategorias(String categoria) throws SQLException{
		getConnection();
		StringBuilder sql = new StringBuilder("SELECT count(1) FROM Subcategorias WHERE categoria = '" + categoria + "'");
		ResultSet rs = stmt.executeQuery(sql.toString());
		rs.first();
		int res = rs.getInt("count(1)");
		stmt.close();
		return res;
	}
	public ArrayList<String>  getListSubCategorias(String categoria) throws SQLException {
		getConnection();
		if(categoria.equals("Todas")) return new ArrayList<String>();
		StringBuilder sql = new StringBuilder("SELECT Nome FROM Subcategorias WHERE categoria = '" + categoria + "'");
		ResultSet rs = stmt.executeQuery(sql.toString());
		ArrayList<String> res = new ArrayList<String>();  
		while (rs.next()){res.add(rs.getString("Nome"));}
		stmt.close();
		return res;
	}
}
