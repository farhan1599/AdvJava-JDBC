package demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdatableExample {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/test";
		String uname = "root";
		String pwd = "farhanmySQL";
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(url, uname, pwd);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String query = "select *from ACCOUNT";
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			rs.updateDouble(3, 6499.0);
			rs.updateRow();
			System.out.println("Row updated.....");
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

}
