/**
 * 
 */
package bean;

import java.sql.Timestamp;

/**
 * CREATE TABLE `learn_record` ( `id` int(11) NOT NULL AUTO_INCREMENT, `user`
 * int(11) NOT NULL, `big_question` int(11) NOT NULL, `question` int(11) NOT
 * NULL DEFAULT '0', `score` int(11) NOT NULL, `timestamp` timestamp NOT NULL
 * DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, PRIMARY KEY (`id`),
 * UNIQUE KEY `idlog_UNIQUE` (`id`), KEY `test_idx` (`big_question`), KEY
 * `foreign_tiny_idx` (`question`), KEY `user_idx` (`user`), CONSTRAINT
 * `foreign_big` FOREIGN KEY (`big_question`) REFERENCES `big_question` (`id`)
 * ON DELETE NO ACTION ON UPDATE NO ACTION, CONSTRAINT `foreign_tiny` FOREIGN
 * KEY (`question`) REFERENCES `questions` (`id`) ON DELETE NO ACTION ON UPDATE
 * NO ACTION, CONSTRAINT `user` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON
 * DELETE NO ACTION ON UPDATE NO ACTION ) ENGINE=InnoDB DEFAULT CHARSET=utf8
 * COMMENT='学习记录'
 * 
 * @author MJCoder
 *
 */
public class LearnRecord {

	private int id;
	private int user;
	private int bigQuestion;
	private int question;
	private int score;
	private Timestamp timestamp;

	/**
	 * 
	 */
	public LearnRecord() {
		// TODO Auto-generated constructor stub
	}

	public LearnRecord(int id, int user, int bigQuestion, int question, int score, Timestamp timestamp) {
		super();
		this.id = id;
		this.user = user;
		this.bigQuestion = bigQuestion;
		this.question = question;
		this.score = score;
		this.timestamp = timestamp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public int getBigQuestion() {
		return bigQuestion;
	}

	public void setBigQuestion(int bigQuestion) {
		this.bigQuestion = bigQuestion;
	}

	public int getQuestion() {
		return question;
	}

	public void setQuestion(int question) {
		this.question = question;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "LearnRecord [id=" + id + ", user=" + user + ", bigQuestion=" + bigQuestion + ", question=" + question
				+ ", score=" + score + ", timestamp=" + timestamp + "]";
	}

}
