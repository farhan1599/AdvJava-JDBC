package demo.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insert_Image {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "system";
		String password = "farhanSQL";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
			String query = "insert into images values(?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "earth");
			File file = new File("H:\\PicsArt");
			FileInputStream fis = new FileInputStream(file);
			// pstmt.setBinaryStream(2, fis);
			pstmt.setBlob(2, fis);
			pstmt.executeUpdate();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
