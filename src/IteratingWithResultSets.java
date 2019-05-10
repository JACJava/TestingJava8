import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IteratingWithResultSets {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection(DBType.MYSQLDB);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("Select * from city");
			
			//String format = "%-4s%-20s%-25s%-10f\n"; 
			
			String format = "%04d%-35s%-3s%-20s%-11f\n"; 
			
			while (rs.next()) {
				System.out.format(format,
						rs.getInt("ID"),
						rs.getString("Name"),
						rs.getString("CountryCode"),
						rs.getString("District"),
						rs.getFloat("Population"))
						;
			}					
			
		} catch (SQLException e) {
			DBUtil.showErrorMessage(e);
		}
		finally{
			rs.close();
			stmt.close();
			conn.close();
		}
		
	}

}
