package main;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginData {

	private String currentPC;
	private HashMap<String, OracleAccount> accounts;

	public LoginData() {
		accounts = new HashMap<String, OracleAccount>();
		accounts.put("gerente-PC85", new OracleAccount("gerente-PC85", "jdbc:oracle:thin:@192.168.0.11:1521:database", "connector", "password"));
		accounts.put("MacWindows", new OracleAccount("MacWindows","jdbc:oracle:thin:@localhost:1521:database", "connector", "password"));
		try {
			currentPC = java.net.InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException ex) {
			Logger.getLogger(LoginData.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public String getURL() {
		return accounts.get(currentPC).getURL();
	}
	
	public String getUsername() {
		return accounts.get(currentPC).getUsername();
	}

	public String getPassword() {
		return accounts.get(currentPC).getPassword();
	}

}
