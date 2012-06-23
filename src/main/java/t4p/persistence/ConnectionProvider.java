package t4p.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	private static Connection theConnection;
	static {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String connParam = "jdbc:postgresql://localhost/test?user=fred&password=secret";
			//theConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PersonProject?" + "user=root&password=mysql" );
			//theConnection = DriverManager.getConnection("jdbc:postgresql://localhost:3306/aaarrgh?user=postgres");
			theConnection = DriverManager.getConnection("jdbc:postgresql://ec2-23-23-223-68.compute-1.amazonaws.com:3306/zjvhatsnun?user=zjvhatsnun&password=sMxj1CQfWzFEFpMhx1xJ");
		}catch (Exception e){
			
		}
	}
	public static Connection getConnection() throws SQLException{
		return(theConnection);
	}
}
