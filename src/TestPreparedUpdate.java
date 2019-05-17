import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class TestPreparedUpdate {

	public static void main(String[] args) throws SQLException  {

		 Connection conn = DBUtil.getConnection(DBType.MYSQLDB);
		 
		 String sql = "update city set population = ? where id = ?";
		 
		 Scanner scanner = new Scanner(System.in);
		 
		 System.out.print("Enter City ID:");
		 int cityId = scanner.nextInt();
		 
		 System.out.print("Enter New Population:  ");
		 double population = scanner.nextDouble();	 
		 
		 System.out.println("cityId is:  "+cityId);
		 System.out.println("new population is:  "+population);

		 PreparedStatement pstmt  = conn.prepareStatement(sql);
		 
		 pstmt.setDouble(1, population);
		 pstmt.setInt(2, cityId);
		 
		 int result = pstmt.executeUpdate();
		 
		 if( result == 1 ) //updated one record
		 {
			 System.out.println("Record Updated Successfully.");
		 }
		 else{
			 System.err.println("Error while updating the record.");
		 }

		 scanner.close();
		 pstmt.close();
		 conn.close();
		 
	}

}
