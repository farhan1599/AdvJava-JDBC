//Database= MySQL

package demo.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class Cstmt_Demo {

	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3306/test";
		String username = "root";
		String password="farhanmySQL";
		Connection conn=null;
		CallableStatement cstmt=null;
		
		try {
			conn= DriverManager.getConnection(url,username,password);
			String query="{ ?= CALL experience_of_emp(?)}";
			cstmt=conn.prepareCall(query);
			cstmt.registerOutParameter(1, Types.DOUBLE);
			cstmt.setInt(2, 102);
			cstmt.execute();
			double k=cstmt.getDouble(1);
			System.out.println("Experience of 102 in years :"+k);
		} catch (Exception e) {
		e.printStackTrace();
		}
		finally {
			try {
				if(cstmt!=null) {
					cstmt.close();
				}
				if(conn !=null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
		}

}
