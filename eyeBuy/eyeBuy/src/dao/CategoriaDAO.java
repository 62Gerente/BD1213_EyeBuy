package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaDAO extends AbstractDAO {
	
	public String[] getArrayCategorias() throws SQLException {
		getConnection();
		int numCategorias=0;
		ResultSet num = stmt.executeQuery("SELECT count(1) FROM Categorias");
		num.first();
		numCategorias = num.getInt("count(1)");
		String[] res = new String[numCategorias];
		ResultSet rs = stmt.executeQuery("SELECT * FROM Categorias");
		int i = 0;
		while (rs.next()){
			res[i] = new String(rs.getString("nome"));
			i++;
		}
		return res;
	}
		
	public ArrayList<String> getListCategorias() throws SQLException {
		getConnection();
		ArrayList<String> res = new ArrayList<String>();
		ResultSet rs = stmt.executeQuery("SELECT * FROM Categorias");
		while (rs.next()){
			res.add(new String(rs.getString("nome")));
		}
		return res;
	}
}