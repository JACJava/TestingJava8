import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestMySQLConnection {

	
	static String jdbcURL = "jdbc:mysql://localhost:3306/world";
	static String jdbcUsername = "root";
	static String jdbcPassword = "zjak25";
	
	public static void main(String[] args) throws SQLException {
	
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			
			System.out.println("Connection Established to MYSQL Database");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			conn.close();
		}
		
	
	}

}
