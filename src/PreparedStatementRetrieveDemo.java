import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementRetrieveDemo {

	public static void main(String[] args) throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = DBUtil.getConnection(DBType.MYSQLDB);
			
			String sql = "Select * From city where Population < ? and CountryCode = ?";
			
			pstmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

				
			prepareStatement(pstmt,100000,"USA");
			System.out.println("-------------------");
			prepareStatement(pstmt,600000,"NLD");
			
			
		}
		catch(SQLException ex){
			DBUtil.showErrorMessage(ex);
		}
	}

	private static void prepareStatement(PreparedStatement pstmt,int population, String countryCode) throws SQLException {
		
		ResultSet rs;
		
		pstmt.setInt(1,  population);
		pstmt.setString(2, countryCode);
		
		rs = pstmt.executeQuery();
		
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
		
		rs.last();
		
		System.out.println("Total Cities:  " + rs.getRow());
		
	}
}
