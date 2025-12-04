
package DBCon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBconn {
//database connection

	        private static final String URL = "jdbc:mysql://localhost:3306/onlinebank?useSSL=false&serverTimezone=UTC";
		    private static final String USER = "root";      
		    private static final String PASSWORD="123456";

		    static {
		        try { 
		        	Class.forName("com.mysql.cj.jdbc.Driver"); 
		        }
		        catch (ClassNotFoundException e) { 
		        	throw new RuntimeException("MySQL driver not found.", e); 
		        	}
		    }

		    public static Connection getConnection() throws Exception{
		    	
		    	return DriverManager.getConnection(URL, USER, PASSWORD);
		    }
}
