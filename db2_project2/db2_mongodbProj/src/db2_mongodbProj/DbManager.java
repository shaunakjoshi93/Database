package db2_mongodbProj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager { 
	Connection con;

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public void closeConnection() {

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
