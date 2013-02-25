package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public abstract class AbstractDAO extends Observable{
	static Connection conn= null;
    static Statement stmt = null;
    ResultSet resultSet = null;
    public static ArrayList<Observer> observers = new ArrayList<Observer>();
    
    protected static Connection getConnection() throws SQLException {
        //Connection conn;
        if(conn == null) conn = ConnectionFactory.getInstance().getConnection();
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return conn;
    }
}
