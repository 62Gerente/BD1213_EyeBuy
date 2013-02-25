package main;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SCI {

	public static Connection conn;
	public static Statement stmt;
	public static ArrayList<Observer> observers = new ArrayList<Observer>();

	public static void connect(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("3");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LoginData loginAccounts = new LoginData();
		String url = loginAccounts.getURL();
		String username = loginAccounts.getUsername();
		String password = loginAccounts.getPassword();
		System.out.println(loginAccounts.getURL() + loginAccounts.getUsername() + loginAccounts.getPassword());
		try {
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("4");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			System.out.println("5");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Statement newStatement() {
		try {
			return conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException ex) {
			Logger.getLogger(SCI.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	public static void disconnect() throws SQLException {
		conn.close();
	}
	
	public static void teste() throws SQLException {
		ResultSet categorias = stmt.executeQuery("SELECT * FROM localidades");
		while (categorias.next()) {
			String nome = categorias.getString("NOME");
			System.out.println(nome);
		}
	}

	public static void main(String[] args){
		try {
			System.out.println(java.net.InetAddress.getLocalHost().getHostName());
			System.out.println("1");
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		connect();
		System.out.println("2");
		
		try {
			teste();
			System.out.println("6");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			disconnect();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
}


