import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetScrollingDemo {

	public static void main(String[] args) throws SQLException  {
	
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQLDB);
				// create the statement with scroll insensitive and read-only
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery("Select * From City Where ID <= 10");
				
				)
		{
			//String format = "%-4s%-20s%-25s%-10f\n"; 
			
			String format = "%04d%-35s%-3s%-20s%-11f\n"; 
			
			rs.beforeFirst();
			
			System.out.println();
			System.out.println("*** First 10 Rows ***");
			System.out.println();
			
			while(rs.next()){
				System.out.format(format,
						rs.getInt("ID"),
						rs.getString("Name"),
						rs.getString("CountryCode"),
						rs.getString("District"),
						rs.getFloat("Population"))
						;
			}
			
			rs.afterLast();
			
			System.out.println();
			System.out.println("*** Last 10 Rows ***");
			System.out.println();
			
			while(rs.previous()){
				System.out.format(format,
						rs.getInt("ID"),
						rs.getString("Name"),
						rs.getString("CountryCode"),
						rs.getString("District"),
						rs.getFloat("Population"))
						;
			}
			
			rs.first();
			
			System.out.println();
			System.out.println("*** First Record ***");
			System.out.println();
			
			System.out.format(format,
					rs.getInt("ID"),
					rs.getString("Name"),
					rs.getString("CountryCode"),
					rs.getString("District"),
					rs.getFloat("Population"))
					;
			
			rs.last();

			System.out.println();
			System.out.println("*** Last Record ***");
			System.out.println();
			
			System.out.format(format,
					rs.getInt("ID"),
					rs.getString("Name"),
					rs.getString("CountryCode"),
					rs.getString("District"),
					rs.getFloat("Population"))
					;
			
			rs.absolute(4);
			
			System.out.println();
			System.out.println("*** Record at 4th Row ***");
			System.out.println();
			
			System.out.format(format,
					rs.getInt("ID"),
					rs.getString("Name"),
					rs.getString("CountryCode"),
					rs.getString("District"),
					rs.getFloat("Population"))
					;
			
			rs.relative(2);
			
			System.out.println();
			System.out.println("*** Record at 6th Row ***");
			System.out.println();
			
			System.out.format(format,
					rs.getInt("ID"),
					rs.getString("Name"),
					rs.getString("CountryCode"),
					rs.getString("District"),
					rs.getFloat("Population"))
					;
			
			rs.relative(-4);
			
			System.out.println();
			System.out.println("*** Record at 2nd Row ***");
			System.out.println();
		
			System.out.format(format,
					rs.getInt("ID"),
					rs.getString("Name"),
					rs.getString("CountryCode"),
					rs.getString("District"),
					rs.getFloat("Population"))
					;
		}
		catch(SQLException e){
			DBUtil.showErrorMessage(e);
		}
			
	}

}
