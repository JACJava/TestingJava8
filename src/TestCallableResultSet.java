import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.sql.CallableStatement;


public class TestCallableResultSet {

	public static void main(String[] args) throws SQLException  {
		
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQLDB);
				CallableStatement callableStatement = conn.prepareCall("{call GetCitiesbyRefCursor(?)}");
				Scanner scanner = new Scanner(System.in);
				)
		
		{
			 
			 System.out.print("Enter District:");
			 String district = scanner.nextLine();
			 
			 callableStatement.setString(1, district);
			 
			 ResultSet rs = callableStatement.executeQuery();
			 
			 String format = "%04d%-35s%-3s%-20s%-11f\n"; 
			 
			 while(rs.next()){
					System.out.format(format,
							rs.getInt("ID"),
							rs.getString("Name"),
							rs.getString("CountryCode"),
							rs.getString("District"),
							rs.getFloat("Population"))
							;
				}
			 
			 scanner.close();
			 conn.close();
			 
		}
		catch(SQLException ex) {
			DBUtil.showErrorMessage(ex);	
		}
	}
}
	