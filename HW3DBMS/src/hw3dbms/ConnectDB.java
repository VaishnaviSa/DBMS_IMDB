package hw3dbms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	static Connection con;

	ConnectDB() {
		try {
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			String host = "localhost";
			String port = "1521";
			String db_name = "orcl";
			String user_name = "HR";
			String password = "1234";
			// Construct the JDBC URL
			String dbURL = "jdbc:oracle:thin:@" + host + ":" + port + ":" + db_name;
			con = DriverManager.getConnection(dbURL, user_name, password);
			System.out.println("Connection created");
		} catch (Exception e) {
			System.err.println("Errors occurs when communicating with the database server: " + e.getMessage());
		} finally {
		}
	}
		public Connection getConnection(){
			return con;
		}
		
		static void close_connectDB() {
		try {
			con.close();
		} catch (SQLException e) {
			System.err.println("Cannot close connection: " + e.getMessage());
		}
	}
}