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
import bean.Type;
import util.DBUtil;

/**
 * @author MJCoder
 *
 */
public class TypeDao {
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	/**
	 * 
	 */
	public TypeDao() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 查询levelId(级别：小学、初中、高中)下对应的所有题目类型
	 * 
	 * @param levelId
	 * @return
	 */
	public static List<Type> selectType(int levelId) {
		List<Type> types = new ArrayList<Type>();
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(
					"select * from type where id in (select distinct type from big_question where level = ?)");
			ps.setInt(1, levelId);
			rs = ps.executeQuery();
			while (rs.next()) {
				types.add(new Type(rs.getInt("id"), rs.getString("type"), rs.getString("remark")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, ps, rs);
		}
		return types;
	}
}
