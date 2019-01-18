/**
 * 
 */
package bean;

import java.util.List;

/**
 * CREATE TABLE `big_question` ( `id` int(11) NOT NULL AUTO_INCREMENT, `level`
 * int(11) NOT NULL, `type` int(11) NOT NULL, `question` varchar(200) NOT NULL,
 * PRIMARY KEY (`id`), UNIQUE KEY `id_UNIQUE` (`id`), KEY `type_idx` (`type`),
 * KEY `level_idx` (`level`), CONSTRAINT `level` FOREIGN KEY (`level`)
 * REFERENCES `level` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION, CONSTRAINT
 * `type` FOREIGN KEY (`type`) REFERENCES `type` (`id`) ON DELETE NO ACTION ON
 * UPDATE NO ACTION ) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=utf8
 * COMMENT='大题'
 * 
 * @author MJCoder
 *
 */
public class BigQuestion {
	private int id;
	private int level;
	private int type;
	private String question;
	private String detail;
	private List<Question> questions;

	/**
	 * 
	 */
	public BigQuestion() {
		// TODO Auto-generated constructor stub
	}

	public BigQuestion(int id, int level, int type, String question, String detail) {
		super();
		this.id = id;
		this.level = level;
		this.type = type;
		this.question = question;
		this.detail = detail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "BigQuestion [id=" + id + ", level=" + level + ", type=" + type + ", question=" + question + ", detail="
				+ detail + ", questions=" + questions + "]";
	}
}
