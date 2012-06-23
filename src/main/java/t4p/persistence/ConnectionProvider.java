package t4p.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	private static Connection theConnection;
	static {
		try{
		}catch (Exception e){
			
		}
	}
	public static Connection getConnection(){
		
		try {
		
		if (theConnection == null){
				Class.forName("org.postgresql.Driver");
			String connParam = "jdbc:postgresql://localhost/test?user=fred&password=secret";
			//theConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PersonProject?" + "user=root&password=mysql" );
			/*Local
			 * theConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/aaarrgh?user=manu&password=manu");
			 */
			
				theConnection = DriverManager.getConnection("jdbc:postgresql://ec2-23-23-223-68.compute-1.amazonaws.com/zjvhatsnun?user=zjvhatsnun&password=sMxj1CQfWzFEFpMhx1xJ");
		}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return(theConnection);
	}
}
