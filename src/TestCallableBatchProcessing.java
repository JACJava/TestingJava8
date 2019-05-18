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


public class TestCallableBatchProcessing {

	public static void main(String[] args) throws SQLException  {
		
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQLDB);
				CallableStatement callableStatement = conn.prepareCall("{call AddNewCity(?,?,?,?,?)}");
				)
		
		{
			 
			String option = null;
			
			do {
				
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
		
				callableStatement.addBatch();
				
				// have to add this, because nextDouble() does not WAIT FOR THE LINE RETURN!!!!!!!!!!!!!!!!!!!!
				scanner.nextLine();
				
				System.out.print("Do you want to add another record? (yes/no):  ");
				option = scanner.nextLine();
				
				//scanner.close();
		
				
			}while (option.equals("yes"));
			
			int []updateCounts = callableStatement.executeBatch();
			 
			System.out.println("Total Records Inserted:  "+updateCounts.length);
			 
			conn.close();
			//scanner.close();
			 
		}
		catch(SQLException ex) {
			DBUtil.showErrorMessage(ex);	
		}
	}
}
	