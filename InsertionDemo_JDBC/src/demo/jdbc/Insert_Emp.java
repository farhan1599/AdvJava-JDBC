package demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Insert_Emp {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String username = "system";
		String password = "farhanSQL";
		Connection connection = null;
		Statement statement = null;

		try {
			connection = DriverManager.getConnection(url, username, password);
             statement=connection.createStatement();
             String query="insert into emp values(1575,'Head',5550.0,'15-MAY-99',40)";
             int count=statement.executeUpdate(query);
             System.out.println(count+" rows are inserted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(statement !=null)
					statement.close();
				if(connection != null)
					connection.close();
			} catch (SQLException e) {
			  e.printStackTrace();
			}
		}
	}

}
