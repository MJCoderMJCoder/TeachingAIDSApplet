/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.User;
import util.DBUtil;

/**
 * @author MJCoder
 *
 */
public class UserDao {
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	/**
	 * 
	 */
	public UserDao() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 根据账户和密码查询用户；并验证登录
	 * 
	 * @param account
	 * @param password
	 * @return
	 */
	public static User selectUser(String account, String password) {
		User user = null;
		try {
			conn = DBUtil.getConnection();
			// ps = conn.prepareStatement("SELECT * FROM sys.sys_config");
			ps = conn.prepareStatement("SELECT * FROM user where account = ? and password = ?");
			ps.setString(1, account);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"),
						rs.getString("account"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, ps, rs);
		}
		return user;

	}
}
