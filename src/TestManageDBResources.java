import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestManageDBResources {

	
	public static void main(String[] args) throws SQLException {
	
		Connection conn = null;
		
		try {
			//conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			
			conn = DBUtil.getConnection(DBType.MYSQLDB);
			
			
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
