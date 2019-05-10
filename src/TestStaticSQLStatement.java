import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestStaticSQLStatement {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			conn = DBUtil.getConnection(DBType.MYSQLDB);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from country");
			
			rs.last(); //moves cursor to last record
			System.out.println("Total Number of Rows from Static SQL:  " + rs.getRow());  //integer which represents current row number
			
		}catch(SQLException ex){
			DBUtil.showErrorMessage(ex);
		}
		finally{
			//close in reverse order of opening
			if (rs!= null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
	}

}
