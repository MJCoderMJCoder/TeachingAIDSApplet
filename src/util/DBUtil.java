/**
 * 
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author MJCoder
 *
 */
public class DBUtil {
	// SQL Server
	// private static final String JDBC_DRIVER =
	// "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // JDBC 驱动名
	// private static final String DB_URL =
	// "jdbc:sqlserver://localhost:1433;databaseName=police"; // 数据库 URL
	// static final String USER = "sa"; // 数据库的用户名
	// static final String PASS = "6003"; // 数据库的密码

	// MySQL
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; // JDBC 驱动名
	// private static final String DB_URL = "jdbc:mysql://localhost:3306/sys"; //
	// 数据库 URL
	private static final String DB_URL = "jdbc:mysql://localhost:3306/teaching_aids_applet"; // 数据库 URL
	static final String USER = "root"; // 数据库的用户名
	static final String PASS = "MJCoder"; // 数据库的密码

	/**
	 * 
	 */
	public DBUtil() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param conn
	 * @param ps
	 * @param rs
	 */
	public static void closeDB(Connection conn, PreparedStatement ps, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
