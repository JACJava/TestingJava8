
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;


public class TestExceptionHandling {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection(DBType.MYSQLDB);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);			
			
			// generate an error to show error statements
			//rs = stmt.executeQuery("select * from juliecountry");
			
			rs = stmt.executeQuery("select * from country");
			
			rs.last();
			System.out.println("Total Number of Rows:  " + rs.getRow());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
			System.err.println();
			DBUtil.showErrorMessage(e);
		}
		finally{
			conn.close();
		}

	}

}

