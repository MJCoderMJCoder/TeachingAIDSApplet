/**
 * 
 */
package bean;

import javax.swing.JTextField;

/**
 * CREATE TABLE `questions` ( `id` int(11) NOT NULL AUTO_INCREMENT,
 * `big_question` int(11) NOT NULL, `question` varchar(100) NOT NULL, `answer`
 * varchar(100) NOT NULL, `image` varchar(50) DEFAULT NULL, `audio` varchar(50)
 * DEFAULT NULL, PRIMARY KEY (`id`), UNIQUE KEY `idquestions_UNIQUE` (`id`), KEY
 * `big_question_idx` (`big_question`), CONSTRAINT `big_question` FOREIGN KEY
 * (`big_question`) REFERENCES `big_question` (`id`) ON DELETE NO ACTION ON
 * UPDATE NO ACTION ) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8
 * COMMENT='小题'
 * 
 * @author MJCoder
 *
 */
public class Question {

	private int id;
	private int bigQuesion;
	private String question;
	private String answer;
	private String image;
	private String audio;
	private JTextField jTextField; // 为了方便操作，新增用户界面字段

	/**
	 * 
	 */
	public Question() {
		// TODO Auto-generated constructor stub
	}

	public Question(int id, int bigQuesion, String question, String answer, String image, String audio) {
		super();
		this.id = id;
		this.bigQuesion = bigQuesion;
		this.question = question;
		this.answer = answer;
		this.image = image;
		this.audio = audio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBigQuesion() {
		return bigQuesion;
	}

	public void setBigQuesion(int bigQuesion) {
		this.bigQuesion = bigQuesion;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAudio() {
		return audio;
	}

	public void setAudio(String audio) {
		this.audio = audio;
	}

	public JTextField getjTextField() {
		return jTextField;
	}

	public void setjTextField(JTextField jTextField) {
		this.jTextField = jTextField;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", bigQuesion=" + bigQuesion + ", question=" + question + ", answer=" + answer
				+ ", image=" + image + ", audio=" + audio + ", jTextField=" + jTextField + "]";
	}

}
