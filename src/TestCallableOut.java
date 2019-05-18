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


public class TestCallableOut {

	public static void main(String[] args) throws SQLException  {
		
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQLDB);
				CallableStatement callableStatement = conn.prepareCall("{call GetTotalCitiesbyDistrict(?,?)}");
				Scanner scanner = new Scanner(System.in);
				)
		
		{
			 
			 System.out.print("Enter District:");
			 String district = scanner.nextLine();
			 
			 callableStatement.setString(1, district);
			 callableStatement.registerOutParameter(2, Types.INTEGER);
		
			 callableStatement.execute();
			 
			 int totalCities = callableStatement.getInt(2);
			 
			 System.out.println("Total Number of Cities for "+district+" is:  "+totalCities);
			 
			 scanner.close();
			 conn.close();
			 
		}
		catch(SQLException ex) {
			DBUtil.showErrorMessage(ex);	
		}
	}
}
	