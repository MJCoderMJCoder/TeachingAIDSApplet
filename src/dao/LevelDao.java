/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Level;
import bean.User;
import util.DBUtil;

/**
 * @author MJCoder
 *
 */
public class LevelDao {
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	/**
	 * 
	 */
	public LevelDao() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 查询所有级别（小学、初中、高中）
	 * 
	 * @return
	 */
	public static List<Level> selectLevel() {
		List<Level> levels = new ArrayList<Level>();
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("SELECT * FROM level");
			rs = ps.executeQuery();
			while (rs.next()) {
				levels.add(new Level(rs.getInt("id"), rs.getString("level"), rs.getString("remark")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, ps, rs);
		}
		return levels;
	}
}
