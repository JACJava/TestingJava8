import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class TestPreparedInsert {

	public static void main(String[] args) throws SQLException  {
		// TODO Auto-generated method stub

		 Connection conn = DBUtil.getConnection(DBType.MYSQLDB);
		 
		 int cityId;
		 String name, countryCode, district;
		 //java.sql.Date hiredate;
		 double population;
		 
		 Scanner scanner = new Scanner(System.in);
		 
		 System.out.print("Enter City ID:");
		 cityId = Integer.parseInt(scanner.nextLine());
		 
		 System.out.print("Enter City Name:");
		 name = scanner.nextLine();
		 
		 System.out.print("Enter Country Code:");
		 countryCode = scanner.nextLine();
		 
		 System.out.print("Enter District:");
		 district = scanner.nextLine();
		 
		 //System.out.print("Enter Date of Joining : ");
		 //hiredate = java.sql.Date.valueOf(scanner.nextLine());
		 
		 System.out.print("Enter Population:  ");
		 population = scanner.nextDouble();	 
		 
		 System.out.println("cityId is:  "+cityId);
		 System.out.println("name is:  "+name);
		 System.out.println("countryCode is:  "+countryCode);
		 System.out.println("district is:  "+district);
		 System.out.println("population is:  "+population);
	
		 String sql = "insert into city values ( ?,?,?,?,? )";
		 
		 PreparedStatement pstmt  = conn.prepareStatement(sql);
		 
		 pstmt.setInt(1, cityId);
		 pstmt.setString(2, name);
		 pstmt.setString(3, countryCode);
		 pstmt.setString(4, district);
		 pstmt.setDouble(5, population);
		 
		 int result = pstmt.executeUpdate();
		 
		 if( result == 1 ) //added one record
		 {
			 System.out.println("Record Inserted Successfully.");
		 }
		 else{
			 System.err.println("Error while adding the record.");
		 }
		 
		 scanner.close();
		 pstmt.close();
		 conn.close();
		 
	}

}
