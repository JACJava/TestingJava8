import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.sql.CallableStatement;


public class TestCallableIn {

	public static void main(String[] args) throws SQLException  {
		
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQLDB);
				CallableStatement callableStatement = conn.prepareCall("{call AddNewCity(?,?,?,?,?)}");
				)
		
		{
			 Scanner scanner = new Scanner(System.in);
			 
			 System.out.print("Enter City ID:");
			 int cityId = Integer.parseInt(scanner.nextLine());
			 
			 System.out.print("Enter City Name:");
			 String name = scanner.nextLine();
			 
			 System.out.print("Enter Country Code:");
			 String countryCode = scanner.nextLine();
			 
			 System.out.print("Enter District:");
			 String district = scanner.nextLine();
			 
			 System.out.print("Enter Population:  ");
			 double population = scanner.nextDouble();	
			 
			 callableStatement.setInt(1, cityId);
			 callableStatement.setString(2, name);
			 callableStatement.setString(3, countryCode);
			 callableStatement.setString(4, district);
			 callableStatement.setDouble(5, population);
		
			 
			 callableStatement.execute();
			 
			 System.out.println("Record Added Successfully.");
			 
			 scanner.close();
			 conn.close();
			 
		}
		catch(SQLException ex) {
			DBUtil.showErrorMessage(ex);	
		}
	}
}
	