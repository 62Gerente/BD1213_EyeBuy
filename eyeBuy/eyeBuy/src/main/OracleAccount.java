package main;

public class OracleAccount {

	private String pc_name;
	private String URL;
	private String username;
	private String password;

	public OracleAccount(String pc_name, String URL, String username, String password) {
		this.pc_name = pc_name;
		this.URL = URL;
		this.username = username;
		this.password = password;
	}

	public String getPC_name() {
		return pc_name;
	}
	
	public String getURL() {
		return URL;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	
}
