package demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectFrom_Emp {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String username = "system";
		String password = "farhanSQL";
		Connection connection = null;
		Statement statement = null;

		try {
			connection = DriverManager.getConnection(url, username, password);
			statement = connection.createStatement();
			String query = "select *from emp";
			ResultSet resultSet = statement.executeQuery(query);
			System.out.println("======Records of EMP table=======");

			while (resultSet.next()) {
				System.out.println(resultSet.getInt("empno") + "," + resultSet.getString("ename") + ","
						+ resultSet.getDouble("sal") + "," + resultSet.getDate("hiredate") + ","
						+ resultSet.getInt("deptno"));
			}
			resultSet.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
}
