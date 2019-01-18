/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.BigQuestion;
import bean.LearnRecord;
import bean.User;
import util.DBUtil;

/**
 * @author MJCoder
 *
 */
public class LearnRecordDao {
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	/**
	 * 
	 */
	public LearnRecordDao() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 更新或是插入学习记录
	 * 
	 * @param learnRecord
	 * @return
	 */
	public static int updateLearnRecord(LearnRecord learnRecord) {
		int temp = -6003;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(
					"select * from learn_record where user = ? and big_question = ? and question = ?");
			ps.setInt(1, learnRecord.getUser());
			ps.setInt(2, learnRecord.getBigQuestion());
			ps.setInt(3, learnRecord.getQuestion());
			rs = ps.executeQuery();
			if (rs.first()) {
				ps = conn.prepareStatement(
						"update learn_record set user = ?, big_question = ?, question = ?, score = ? where id = "
								+ rs.getInt("id"));
			} else {
				ps = conn.prepareStatement(
						"insert into learn_record (user,big_question, question, score) values (?,?,?,?)");
			}
			ps.setInt(1, learnRecord.getUser());
			ps.setInt(2, learnRecord.getBigQuestion());
			ps.setInt(3, learnRecord.getQuestion());
			ps.setInt(4, learnRecord.getScore());
			temp = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, ps, rs);
		}
		return temp;
	}

	/**
	 * 查询最新的一条学习记录
	 * 
	 * @return 返回用户最后一次在本系统是做到第几题了
	 */
	public static BigQuestion selectLatestLearnRecord(int userId) {
		BigQuestion bigQuestion = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(
					"select * from big_question where id = (select distinct big_question from learn_record where timestamp = (select max(timestamp) as timestamp from learn_record where user = ?))");
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				bigQuestion = new BigQuestion(rs.getInt("id"), rs.getInt("level"), rs.getInt("type"),
						rs.getString("question"), rs.getString("detail"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, ps, rs);
		}
		return bigQuestion;
	}

	/**
	 * 查询用户上次所做题的总个数和正确数（方便算及格率）
	 * 
	 * @param user
	 * @return
	 */
	public static Map<String, Float> selectScore(User user) {
		Map<String, Float> map = new HashMap<String, Float>();
		int[] temp = { 0, 0 };
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(
					"select sum(score) as score, count(*) as total from learn_record where user = ? and big_question in (select id from big_question where level = ? and type = ?)");
			ps.setInt(1, user.getId());
			ps.setInt(2, user.getLevel().getId());
			ps.setInt(3, user.getType().getId());
			rs = ps.executeQuery();
			while (rs.next()) {
				map.put("score", rs.getFloat("score"));
				map.put("total", rs.getFloat("total"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, ps, rs);
		}
		return map;
	}

	/**
	 * 查询用户曾经做错的题
	 * 
	 * @param userId
	 * @return
	 */
	public static List<BigQuestion> selectErrorQuestion(int userId) {
		List<BigQuestion> bigQuestions = new ArrayList<BigQuestion>();
		BigQuestion bigQuestion = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(
					"select * from big_question where id in (select distinct big_question from learn_record where score = 0 and user = ?)");
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				bigQuestions.add(new BigQuestion(rs.getInt("id"), rs.getInt("level"), rs.getInt("type"),
						rs.getString("question"), rs.getString("detail")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, ps, rs);
		}
		return bigQuestions;
	}
}
