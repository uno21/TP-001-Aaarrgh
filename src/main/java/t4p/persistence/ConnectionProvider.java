package t4p.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	private static Connection theConnection;
	static {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			theConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PersonProject?" + "user=root&password=mysql" );
		}catch (Exception e){
			
		}
	}
	public static Connection getConnection() throws SQLException{
		return(theConnection);
	}
}
