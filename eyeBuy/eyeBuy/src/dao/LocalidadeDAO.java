package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class LocalidadeDAO extends AbstractDAO {

	public String[] getArrayLocalidades() throws SQLException {
		getConnection();
		int numLocalidades=0;
		ResultSet num = stmt.executeQuery("SELECT count(1) FROM Localidades");
		num.first();
		numLocalidades = num.getInt("count(1)");
		String[] res = new String[numLocalidades];
		ResultSet rs = stmt.executeQuery("SELECT * FROM Localidades");
		int i = 0;
		while (rs.next()){
			res[i] = new String(rs.getString("nome"));
			i++;
		}
		return res;
	}
	
	public ArrayList<String> getListLocalidades() throws SQLException {
		getConnection();
		ArrayList<String> res = new ArrayList<String>();
		ResultSet rs = stmt.executeQuery("SELECT * FROM Localidades");
		while (rs.next()){
			res.add(new String(rs.getString("nome")));
		}
		return res;
	}
}