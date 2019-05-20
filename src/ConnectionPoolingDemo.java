import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import javax.sql.PooledConnection;

//import oracle.jdbc.pool.OracleConnectionPoolDataSource;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.impl.GenericObjectPool;


public class ConnectionPoolingDemo {

	public static void main(String[] args) throws SQLException {
		
		OracleConnectionPoolDataSource ds = new OracleConnectionPoolDataSource();
		
		ds.setDriverType("thin");
		ds.setServerName("localhost");
		ds.setPortNumber(1521);
		ds.setServiceName("xe");
		ds.setUser("hr");
		ds.setPassword("hr");
		
		PooledConnection pconn = ds.getPooledConnection();
		
		Connection conn = pconn.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("Select * From Departments");
		
		String format = "%-30s%-50s%-25s\n";
		System.out.format(format,"Department #","Department Name","Location");
		System.out.format(format,"-------------","-----------------","-------------");
		
		while(rs.next()){
			System.out.format(format,rs.getString("Department_ID"),rs.getString("Department_Name"), rs.getString("Location_Id"));
		}
		
		rs.close();
		stmt.close();
		conn.close();
		pconn.close();
	}
}
