import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class TestPreparedDelete {

	public static void main(String[] args) throws SQLException  {

		 Connection conn = DBUtil.getConnection(DBType.MYSQLDB);
		 
		 String sql = "delete from city where id = ?";
		 
		 Scanner scanner = new Scanner(System.in);
		 
		 System.out.print("Enter City ID:");
		 int cityId = scanner.nextInt();
		 
		 PreparedStatement pstmt  = conn.prepareStatement(sql);
		 
		 pstmt.setInt(1, cityId);
		 
		 int result = pstmt.executeUpdate();
		 
		 if( result == 1 ) //deleted one record
		 {
			 System.out.println("Record Deleted Successfully.");
		 }
		 else{
			 System.err.println("Error while deleting the record.");
		 }

		 scanner.close();
		 pstmt.close();
		 conn.close();
		 
	}

}
