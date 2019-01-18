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

import bean.BigQuestion;
import bean.Question;
import bean.Type;
import util.DBUtil;

/**
 * @author MJCoder
 *
 */
public class QuestionDao {
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	/**
	 * 
	 */
	public QuestionDao() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 查询所有大题
	 * 
	 * @param levelId
	 * @param typeId
	 * @return
	 */
	public static List<BigQuestion> selectQuestion(int levelId, int typeId) {
		List<BigQuestion> bigQuestions = new ArrayList<BigQuestion>();
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("select * from big_question where level = ? and type = ? order by id asc");
			ps.setInt(1, levelId);
			ps.setInt(2, typeId);
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

	/**
	 * 根据大题ID查询相关的所有小题
	 * 
	 * @param bigQuestionId
	 * @return
	 */
	public static List<Question> selectQuestion(int bigQuestionId) {
		List<Question> questions = new ArrayList<Question>();
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("select * from questions where big_question = ? order by id asc");
			ps.setInt(1, bigQuestionId);
			rs = ps.executeQuery();
			while (rs.next()) {
				questions.add(new Question(rs.getInt("id"), rs.getInt("big_question"), rs.getString("question"),
						rs.getString("answer"), rs.getString("image"), rs.getString("audio")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeDB(conn, ps, rs);
		}
		return questions;
	}
}
