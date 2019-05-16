import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdatableResultSetDemo {
	public static void main(String[] args) throws SQLException{
		
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQLDB);
				
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				
				ResultSet rs = stmt.executeQuery("Select ID, Name, CountryCode, District, Population from city");
				)
		{
			
			rs.absolute(6);  // positions cursor to sixth row
			
			rs.updateString("Name", "RotterdamUPDATEDAGAIN");
			rs.updateRow();
			
			System.out.println("Record Updated Successfully");
			
			
			rs.moveToInsertRow();
			rs.updateInt("ID", 9999);
			rs.updateString("Name", "Julie City");
			rs.updateString("CountryCode", "USA");
			rs.updateString("District", "Illinois");
			rs.updateInt("Population", 444444);
			rs.insertRow();
			
			System.out.println("Record Inserted Successfully");
			
		}
		catch(SQLException ex){
			DBUtil.showErrorMessage(ex);
		}
	}

}
