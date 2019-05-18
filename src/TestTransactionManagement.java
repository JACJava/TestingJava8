import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestTransactionManagement {

	public static void main(String[] args) throws SQLException{
		
		try{
		Connection conn = DBUtil.getConnection(DBType.MYSQLDB);
		
		/* turn off autocommit so that we can use transaction management */
		conn.setAutoCommit(false);
		
		
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		
		//conn.setAutoCommit(false);

		PreparedStatement pstmt = null;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("PSBank Transactions");
		System.out.println("----------------------");
		
		System.out.print("Enter From Account Number:  ");
		int fromAcno = Integer.parseInt(scanner.nextLine());
		
		System.out.print("Enter To Account Number:  ");
		int toAcno = Integer.parseInt(scanner.nextLine());
		
		System.out.print("Enter Amount To Transfer: ");
		double amount = Double.parseDouble(scanner.nextLine());
		
		System.out.println();
		System.out.println("*** Before the withdrawal ***");
		System.out.println();
		pstmt = printAccountInfo(conn, fromAcno, toAcno);
		
		String withdrawSQL = "Update psbank set AMOUNT = AMOUNT - ? where ACNO = ?";
		pstmt = conn.prepareStatement(withdrawSQL);
		pstmt.setDouble(1, amount);
		pstmt.setInt(2, fromAcno);
		pstmt.executeUpdate();
		
		System.out.println();
		System.out.println("*** After the withdrawal and before the deposit ***");
		System.out.println();
		pstmt = printAccountInfo(conn, fromAcno, toAcno);
		
		
		String depositSQL = "Update psbank set AMOUNT = AMOUNT + ? where ACNO = ?";
		pstmt = conn.prepareStatement(depositSQL);
		pstmt.setDouble(1, amount);
		pstmt.setInt(2, toAcno);
		pstmt.executeUpdate();
		
		System.out.println();
		System.out.println("*** After the deposit ***");
		System.out.println();
		pstmt = printAccountInfo(conn, fromAcno, toAcno);
		
		
		String sql = "Select AMOUNT From psbank where ACNO = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, fromAcno);
		rs = pstmt.executeQuery();
		double balanceAmount=0;
		if( rs.next()){
			balanceAmount = rs.getDouble("Amount");
		}
		
		System.out.println("Amount for Account Number "+fromAcno+" is:  "+balanceAmount);
		
		// commit if the balance amount is >= 5000
		
		if( balanceAmount >= 5000){
			conn.commit();
			System.out.println("Amount Transferred Successfully...");
		}
		else{
			conn.rollback();
			System.out.println("Insufficient Funds:  " + balanceAmount + " Transactions Rolled Back...");
		}
		
		scanner.close();
		pstmt.close();
		conn.close();
		}
		catch(Exception ex){
			System.err.println(ex.getMessage());
		}
	}

	private static PreparedStatement printAccountInfo(Connection conn, int fromAcno, int toAcno) throws SQLException {
		ResultSet rs;
		PreparedStatement pstmt;
		String checkSQL = "select * from psbank where ACNO = ? or ACNO = ?";
		pstmt = conn.prepareStatement(checkSQL);
		pstmt.setInt(1, fromAcno);
		pstmt.setInt(2, toAcno);
		rs = pstmt.executeQuery();
		
		System.out.println();
		System.out.println("Acct No    Amount");
		System.out.println("---------- ------");
		String format = "%-11d%-11d\n"; 
		 
		while(rs.next()){
			System.out.format(format,
					rs.getInt("ACNO"),
					rs.getInt("AMOUNT"))
					;
		}
		 
		rs.last(); //moves cursor to last record
		System.out.println("Total Number of Rows:  " + rs.getRow());  //integer which represents current row number
		return pstmt;
	}

}
