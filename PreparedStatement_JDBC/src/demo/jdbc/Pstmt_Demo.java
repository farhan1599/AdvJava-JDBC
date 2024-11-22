package demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Pstmt_Demo {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "system";
		String password = "farhanSQL";
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DriverManager.getConnection(url, username, password);
			String query = "insert into student values(?,?,?)";
			preparedStatement = connection.prepareStatement(query);

			Scanner scanner = new Scanner(System.in);
			for (int i = 1; i <= 5; i++) {
				System.out.println("Please enter student ID :");
				int sId = scanner.nextInt();
				preparedStatement.setInt(1, sId);
				scanner.nextLine();
				System.out.println("Please enter student Name :");
				String sName = scanner.nextLine();
				preparedStatement.setString(2, sName);
				System.out.println("Please enter student Marks :");
				int marks = scanner.nextInt();
				preparedStatement.setInt(3, marks);

				preparedStatement.executeUpdate();
				System.out.println(" rows inserted...");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
