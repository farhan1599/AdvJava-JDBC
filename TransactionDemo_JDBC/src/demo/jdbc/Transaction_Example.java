/*local transaction example..

 * acc1=10011011
 * acc2=20022022
 */

package demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Transaction_Example {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/test";
		String username = "root";
		String password = "farhanmySQL";
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, username, password);
			conn.setAutoCommit(false);
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter your account number :");
			int fromAcc = sc.nextInt();
			System.out.println("Enter payee account number :");
			int toAcc = sc.nextInt();
			System.out.println("Enter the amount :");
			double amount = sc.nextDouble();

			boolean flag = transferFunds(conn, fromAcc, toAcc, amount);
			if (flag == false) {
				throw new Exception();
			} else {
				conn.commit();
				System.out.println("Transaction Successful");
			}
		} catch (Exception e) {
			try {
				if (conn != null)
					conn.rollback();
				System.out.println("Transaction failed..!!");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	private static boolean transferFunds(Connection conn, int fromAcc, int toAcc, double amount) throws Exception {
		try {
			Statement stmt = conn.createStatement();
			String query1 = "select ACCOUNT_BALANCE from ACCOUNT where ACCOUNT_NUMBER=" + fromAcc;
			ResultSet rs1 = stmt.executeQuery(query1);
			rs1.next();
			double bal_fromAcc = rs1.getDouble("ACCOUNT_BALANCE");
			rs1.close();
			String query2 = "select ACCOUNT_BALANCE from ACCOUNT where ACCOUNT_NUMBER=" + toAcc;
			ResultSet rs2 = stmt.executeQuery(query2);
			rs2.next();
			double bal_toAcc = rs2.getDouble("ACCOUNT_BALANCE");
			rs2.close();
			stmt.executeUpdate(
					"update ACCOUNT set ACCOUNT_BALANCE=" + (bal_fromAcc - amount) + "where ACCOUNT_NUMBER=" + fromAcc);
			stmt.executeUpdate(
					"update ACCOUNT set ACCOUNT_BALANCE=" + (bal_toAcc + amount) + "where ACCOUNT_NUMBER=" + toAcc);
			stmt.close();

			if (bal_fromAcc - amount < 0) {
				return false;
			} else {
				return true;
			}

		} finally {

		}

	}

}
