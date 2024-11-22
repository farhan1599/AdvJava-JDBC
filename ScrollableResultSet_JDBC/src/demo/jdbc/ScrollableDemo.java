package demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ScrollableDemo {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String uname = "system";
		String pwd = "farhanSQL";
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DriverManager.getConnection(url, uname, pwd);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String query = "select * from emp";
			ResultSet rs = stmt.executeQuery(query);
			rs.afterLast();
			System.out.println("=====printing the record form bottom to top=======");
			while (rs.previous()) {
				System.out.println(rs.getInt(1) + ", " + rs.getString(2) + ", " + rs.getDouble(3) + ", " + rs.getDate(4)
						+ ", " + rs.getInt(5));

			}
			System.out.println("=====printing 3rd row of result set=====");
			rs.absolute(3);
			System.out.println(rs.getInt(1) + ", " + rs.getString(2) + ", " + rs.getDouble(3) + ", " + rs.getDate(4)
					+ ", " + rs.getInt(5));

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
