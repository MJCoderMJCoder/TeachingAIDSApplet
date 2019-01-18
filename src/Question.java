import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.SwingConstants;

import bean.BigQuestion;
import bean.LearnRecord;
import bean.User;
import dao.LearnRecordDao;
import dao.QuestionDao;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;

public class Question extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2826740222183284085L;
	private List<BigQuestion> bigQuestions;
	private Player player;
	private int index = 0;
	private int width = 60; // 这是图片和imageLabel的宽度
	private int height = 50; // 这是图片和imageLabel的高度

	/**
	 * Create the panel.
	 */
	public Question(JApplet jApplet, User user, int index, List<BigQuestion> bigQuestions) {
		try {
			this.index = index;
			if (bigQuestions != null) {
				this.bigQuestions = bigQuestions;
			} else {
				this.bigQuestions = QuestionDao.selectQuestion(user.getLevel().getId(), user.getType().getId());
			}
			for (BigQuestion bigQuestion : this.bigQuestions) {
				bigQuestion.setQuestions(QuestionDao.selectQuestion(bigQuestion.getId()));
			}
			next(jApplet, user);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 对应某一题的界面选择
	 * 
	 * @throws MalformedURLException
	 */
	private void next(JApplet jApplet, User user) throws MalformedURLException {
		this.setForeground(Color.BLACK);
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout(0, 0));

		JPanel bigProblemPanel = new JPanel();
		bigProblemPanel.setForeground(Color.BLACK);
		bigProblemPanel.setBackground(Color.WHITE);
		FlowLayout fl_bigProblemPanel = (FlowLayout) bigProblemPanel.getLayout();
		fl_bigProblemPanel.setVgap(0);
		fl_bigProblemPanel.setHgap(0);
		add("North", bigProblemPanel);

		JScrollPane problemPanel = new JScrollPane();
		// problemPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		problemPanel.setForeground(Color.BLACK);
		problemPanel.setBackground(Color.WHITE);
		add("Center", problemPanel);
		if (bigQuestions.size() > 0) {
			BigQuestion bigQuestion = bigQuestions.get(index);
			JLabel bigProblemLabel = new JLabel("第" + (index + 1) + "题  "
					+ bigQuestion.getQuestion().replaceAll(" ", " ") + "  共" + bigQuestions.size() + "题");
			bigProblemPanel.add(bigProblemLabel);
			bigProblemLabel.setHorizontalAlignment(SwingConstants.CENTER);
			bigProblemLabel.setForeground(Color.BLACK);
			bigProblemLabel.setFont(new Font("楷体", Font.PLAIN, 20));
			bigProblemLabel.setBackground(Color.WHITE);

			JPanel itemPanel = new JPanel();
			itemPanel.setForeground(Color.BLACK);
			itemPanel.setBackground(Color.WHITE);
			problemPanel.add(itemPanel);
			problemPanel.setViewportView(itemPanel);
			if (bigQuestion.getDetail() != null) {
				itemPanel.setLayout(new GridLayout(bigQuestion.getQuestions().size() * 2 + 1, 1, 0, 0));
				try {
					JLabel detailLabel = new JLabel();
					detailLabel.setHorizontalAlignment(SwingConstants.CENTER);
					detailLabel.setForeground(Color.BLACK);
					detailLabel.setFont(new Font("楷体", Font.PLAIN, 15));
					detailLabel.setBackground(Color.WHITE);
					detailLabel.setSize(jApplet.getWidth(), 0);// 注意JLabel一定要设置宽度
					labelBR(detailLabel, bigQuestion.getDetail().replaceAll(" ", " "));
					itemPanel.add(detailLabel);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				itemPanel.setLayout(new GridLayout(bigQuestion.getQuestions().size() * 2, 1, 0, 0));
			}
			for (bean.Question question : bigQuestion.getQuestions()) {
				JPanel questionPanel = new JPanel();
				questionPanel.setForeground(Color.BLACK);
				questionPanel.setBackground(Color.WHITE);
				itemPanel.add(questionPanel);
				questionPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

				JLabel questionLabel = new JLabel(question.getQuestion().replaceAll(" ", " "));
				questionPanel.add(questionLabel);
				questionLabel.setToolTipText("这道题1分");
				questionLabel.setHorizontalAlignment(SwingConstants.LEFT);
				questionLabel.setForeground(Color.BLACK);
				questionLabel.setFont(new Font("楷体", Font.PLAIN, 15));
				questionLabel.setBackground(Color.WHITE);

				String imageStr = question.getImage();
				if (imageStr != null) {
					JLabel imageLabel = new JLabel();
					ImageIcon image = new ImageIcon(new URL(jApplet.getDocumentBase(), "image/" + imageStr));
					// getScaledInstance()：创建image的缩放版本
					image.setImage(image.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
					imageLabel.setIcon(image);
					imageLabel.setSize(width, height);
					imageLabel.setHorizontalAlignment(SwingConstants.LEFT);
					imageLabel.setForeground(Color.BLACK);
					imageLabel.setFont(new Font("楷体", Font.PLAIN, 15));
					imageLabel.setBackground(Color.WHITE);
					questionPanel.add(imageLabel);
				}

				JPanel answerPanel = new JPanel();
				answerPanel.setToolTipText("请输入正确答案，多个答案请以英文逗号隔开。");
				answerPanel.setForeground(Color.BLACK);
				answerPanel.setBackground(Color.WHITE);
				FlowLayout flowLayout_2 = (FlowLayout) answerPanel.getLayout();
				flowLayout_2.setAlignment(FlowLayout.LEFT);
				flowLayout_2.setVgap(0);
				flowLayout_2.setHgap(0);
				itemPanel.add(answerPanel);

				JLabel answerLabel = new JLabel("请输入正确答案，多个答案请以英文逗号隔开：");
				answerPanel.add(answerLabel);
				answerLabel.setForeground(Color.BLACK);
				answerLabel.setToolTipText("请输入正确答案，多个答案请以英文逗号隔开。");
				answerLabel.setHorizontalAlignment(SwingConstants.LEFT);
				answerLabel.setFont(new Font("楷体", Font.PLAIN, 15));
				answerLabel.setBackground(Color.WHITE);

				JTextField textField = new JTextField();
				textField.setToolTipText("请输入正确答案，多个答案请以英文逗号隔开。");
				textField.setHorizontalAlignment(SwingConstants.LEFT);
				textField.setForeground(Color.BLACK);
				textField.setFont(new Font("楷体", Font.PLAIN, 15));
				textField.setBackground(Color.WHITE);
				answerPanel.add(textField);
				textField.setColumns(10);
				question.setjTextField(textField);
			}
		} else {
			JLabel nullLabel = new JLabel("抱歉，暂无相关试题，敬请期待。");
			nullLabel.setToolTipText("抱歉，暂无相关试题，敬请期待。");
			nullLabel.setHorizontalAlignment(SwingConstants.CENTER);
			nullLabel.setForeground(Color.BLACK);
			nullLabel.setFont(new Font("楷体", Font.PLAIN, 15));
			nullLabel.setBackground(Color.WHITE);
			problemPanel.setViewportView(nullLabel);
		}
		JPanel btnPanel = new JPanel();
		btnPanel.setForeground(Color.BLACK);
		btnPanel.setBackground(Color.WHITE);
		add("South", btnPanel);
		btnPanel.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel panel = new JPanel();
		panel.setForeground(Color.BLACK);
		panel.setBackground(Color.WHITE);
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		btnPanel.add(panel);

		JLabel btnLabel = new JLabel("注意：提交后再不可返回修改。");
		btnLabel.setHorizontalAlignment(SwingConstants.CENTER);
		btnLabel.setForeground(Color.RED);
		btnLabel.setFont(new Font("楷体", Font.PLAIN, 15));
		btnLabel.setBackground(Color.WHITE);
		btnPanel.add(btnLabel);

		JButton button = new JButton("下一题");
		panel.add(button);
		button.setForeground(Color.BLACK);
		button.setFont(new Font("楷体", Font.PLAIN, 20));
		button.setBackground(Color.WHITE);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchNext(jApplet, user, btnLabel);
			}
		});
	}

	/**
	 * 切换到下一题的动作
	 */
	private void switchNext(JApplet jApplet, User user, JLabel btnLabel) {
		BigQuestion bigQuestion = bigQuestions.get(index);
		for (bean.Question question : bigQuestion.getQuestions()) {
			if (question.getjTextField().getText().equals("")) {
				btnLabel.setText("还有题没答，要仔细检查哦。");
				return;
			} else if (question.getAnswer().trim().equalsIgnoreCase((question.getjTextField().getText()).trim())) {
				LearnRecordDao.updateLearnRecord(
						new LearnRecord(0, user.getId(), bigQuestion.getId(), question.getId(), 1, null));
			} else {
				LearnRecordDao.updateLearnRecord(
						new LearnRecord(0, user.getId(), bigQuestion.getId(), question.getId(), 0, null));
			}
		}
		if (player != null) {
			player.close();
		}
		++index;
		if (index < bigQuestions.size()) {
			try {
				this.removeAll();
				next(jApplet, user);
				repaint();
				playMP3(jApplet);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} else {
			jApplet.getContentPane().removeAll();
			jApplet.getContentPane().setForeground(Color.WHITE);
			jApplet.getContentPane().setFont(new Font("楷体", Font.PLAIN, 25));
			jApplet.getContentPane().setBackground(Color.WHITE);
			jApplet.getContentPane().setLayout(new GridLayout(1, 1, 0, 0));
			jApplet.getContentPane().add(new TypesQuestion(jApplet, user));
			repaint();
		}
	}

	/**
	 * 播放MP3文件
	 * 
	 * @param jApplet
	 */
	private void playMP3(JApplet jApplet) {
		new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				BigQuestion bigQuestion = bigQuestions.get(index);
				if (bigQuestion.getQuestions().size() > 0 && bigQuestion.getQuestions().get(0).getAudio() != null) {
					try {
						URL url = new URL(jApplet.getDocumentBase(),
								"audio/" + bigQuestion.getQuestions().get(0).getAudio());
						BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(url.getFile()));
						player = new Player(buffer);
						player.play();
					} catch (JavaLayerException e1) {
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();

	}

	/**
	 * 实现JLabel自动换行
	 * 
	 * @param jLabel
	 * @param longString
	 * @throws InterruptedException
	 */
	private void labelBR(JLabel jLabel, String longString) throws InterruptedException {
		StringBuilder builder = new StringBuilder("<html>");
		char[] chars = longString.toCharArray();
		FontMetrics fontMetrics = jLabel.getFontMetrics(jLabel.getFont());
		int start = 0;
		int len = 0;
		while (start + len < longString.length()) {
			while (true) {
				len++;
				if (start + len > longString.length())
					break;
				if (fontMetrics.charsWidth(chars, start, len) > jLabel.getWidth()) {
					break;
				}
			}
			builder.append(chars, start, len - 1).append("<br/>");
			start = start + len - 1;
			len = 0;
		}
		builder.append(chars, start, longString.length() - start);
		builder.append("<hr/></html>");
		jLabel.setText(builder.toString());
	}
}
