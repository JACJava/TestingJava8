import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertCLOBDataToEmpResume {

	public static void main(String[] args) throws SQLException, FileNotFoundException{
		
		Connection conn = DBUtil.getConnection(DBType.MYSQLDB);
		PreparedStatement pstmt = null;
		
		String sql = "Update NewEmployees set Resume = ? where Employee_ID = 100";
		pstmt = conn.prepareStatement(sql);
		
		String resumeFile = "c:/Users/Julie/eclipse-workspace/TestingJava8/src/JulieDoc.txt";
		File file = new File(resumeFile);
		FileReader reader = new FileReader(file);
		
		pstmt.setCharacterStream(1, reader, (int)file.length());
	
		pstmt.executeUpdate();

		System.out.println("Resume Updated Successfully...");
		pstmt.close();
		conn.close();
	}

}
