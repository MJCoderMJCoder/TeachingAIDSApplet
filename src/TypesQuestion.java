import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import java.util.List;
import java.util.Map;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.SwingConstants;

import bean.BigQuestion;
import bean.Type;
import bean.User;
import dao.LearnRecordDao;
import dao.QuestionDao;
import dao.TypeDao;

import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JApplet;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class TypesQuestion extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6762695212894826225L;
	private List<Type> types;
	private String typeStr = "";

	/**
	 * Create the panel.
	 */
	public TypesQuestion(JApplet jApplet, User user) {
		setForeground(Color.BLACK);
		setBackground(Color.WHITE);
		setLayout(new GridLayout(20, 1, 0, 0));

		JPanel typePanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) typePanel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		typePanel.setForeground(Color.BLACK);
		typePanel.setBackground(Color.WHITE);
		add(typePanel);

		ButtonGroup buttonGroup = new ButtonGroup();
		types = TypeDao.selectType(user.getLevel().getId());
		for (Type type : types) {
			JRadioButton jRadioButton = new JRadioButton(type.getType());
			typePanel.add(jRadioButton);
			jRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
			jRadioButton.setFont(new Font("楷体", Font.PLAIN, 20));
			jRadioButton.setForeground(Color.BLACK);
			jRadioButton.setBackground(Color.WHITE);
			buttonGroup.add(jRadioButton);
			jRadioButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					typeStr = jRadioButton.getText();
				}
			});
		}

		JPanel hintPanel = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) hintPanel.getLayout();
		flowLayout_2.setVgap(0);
		flowLayout_2.setHgap(0);
		hintPanel.setBackground(Color.WHITE);
		hintPanel.setForeground(Color.BLACK);
		add(hintPanel);

		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.RED);
		label.setFont(new Font("楷体", Font.PLAIN, 15));
		label.setBackground(Color.WHITE);
		hintPanel.add(label);

		JPanel btnPanel = new JPanel();
		btnPanel.setForeground(Color.BLACK);
		btnPanel.setBackground(Color.WHITE);
		FlowLayout flowLayout_1 = (FlowLayout) btnPanel.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(50);
		add(btnPanel);

		JButton start = new JButton("开始做题");
		start.setForeground(Color.BLACK);
		start.setFont(new Font("楷体", Font.PLAIN, 20));
		start.setBackground(Color.WHITE);
		btnPanel.add(start);
		start.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (typeStr.equals("")) {
					label.setText("请先选一类题型，再开始做题。");
				} else {
					for (Type type : types) {
						if (type.getType().equals(typeStr)) {
							user.setType(type);
							break;
						}
					}
					skipType(jApplet, user, 0, null);
				}
			}
		});

		JPanel logPanel = new JPanel();
		logPanel.setForeground(Color.BLACK);
		logPanel.setBackground(Color.WHITE);
		FlowLayout flowLayout_3 = (FlowLayout) logPanel.getLayout();
		flowLayout_3.setVgap(0);
		flowLayout_3.setHgap(0);

		add(logPanel);

		if (user.getType() == null) {
			BigQuestion bigQuestion = LearnRecordDao.selectLatestLearnRecord(user.getId());
			if (bigQuestion != null) {
				errorReview(jApplet, user, btnPanel);
				List<BigQuestion> bigQuestions = QuestionDao.selectQuestion(bigQuestion.getLevel(),
						bigQuestion.getType());
				for (int i = 0; i < bigQuestions.size(); i++) {
					if (bigQuestion.getId() == bigQuestions.get(i).getId()) {
						JLabel logLabel = new JLabel("您上次做到第" + (i + 1) + "题：" + bigQuestion.getQuestion() + "  共"
								+ bigQuestions.size() + "题；是否继续。");
						logLabel.setHorizontalAlignment(SwingConstants.CENTER);
						logLabel.setForeground(new Color(0, 0, 255));
						logLabel.setFont(new Font("楷体", Font.BOLD, 15));
						logLabel.setBackground(Color.WHITE);
						logPanel.add(logLabel);
						final int temp = i;
						logLabel.addMouseListener(new MouseListener() {

							@Override
							public void mouseReleased(MouseEvent e) {
								// TODO Auto-generated method stub

							}

							@Override
							public void mousePressed(MouseEvent e) {
								// TODO Auto-generated method stub

							}

							@Override
							public void mouseExited(MouseEvent e) {
								// TODO Auto-generated method stub

							}

							@Override
							public void mouseEntered(MouseEvent e) {
								// TODO Auto-generated method stub

							}

							@Override
							public void mouseClicked(MouseEvent e) {
								for (Type type : types) {
									if (type.getId() == bigQuestion.getType()) {
										user.setType(type);
										break;
									}
								}
								skipType(jApplet, user, temp, null);
							}
						});
						break;
					}
				}
			}
		} else {
			errorReview(jApplet, user, btnPanel);
			Map<String, Float> map = LearnRecordDao.selectScore(user);
			System.out.println(map);
			StringBuffer stringBuffer = new StringBuffer("本次做题正确率为：");
			float temp = map.get("score") / map.get("total");
			System.out.println(temp);
			if (temp <= 0.1) {
				stringBuffer.append((temp * 100) + "%；你这个学渣，我不认识你。");
			} else if (temp > 0.1 && temp <= 0.2) {
				stringBuffer.append((temp * 100) + "%；好差劲，☹又更加努力哦。");
			} else if (temp > 0.2 && temp <= 0.3) {
				stringBuffer.append((temp * 100) + "%；别灰心，上升空间好大的哦。");
			} else if (temp > 0.3 && temp <= 0.4) {
				stringBuffer.append((temp * 100) + "%；继续加油哇，下次的进步奖得住就是你。");
			} else if (temp > 0.4 && temp <= 0.5) {
				stringBuffer.append((temp * 100) + "%；很明显，你还没有及格哎。好意思么？");
			} else if (temp > 0.5 && temp < 0.6) {
				stringBuffer.append((temp * 100) + "%；差点就及格，别问老师为什么会挂科。");
			} else if (temp >= 0.6 && temp <= 0.7) {
				stringBuffer.append((temp * 100) + "%；666，考试这么厉害呢，分数把握的这么好。");
			} else if (temp > 0.7 && temp <= 0.8) {
				stringBuffer.append((temp * 100) + "%；不要骄傲，你还不算是学霸。");
			} else if (temp > 0.8 && temp <= 0.9) {
				stringBuffer.append((temp * 100) + "%；学霸呀，你；天霸动霸tua。☺");
			} else if (temp > 0.9) {
				stringBuffer.append((temp * 100) + "%；我不知道该咋形容你，只能是望尘莫及。");
			}
			JLabel logLabel = new JLabel(stringBuffer.toString());
			logLabel.setHorizontalAlignment(SwingConstants.CENTER);
			logLabel.setForeground(new Color(0, 0, 255));
			logLabel.setFont(new Font("楷体", Font.BOLD, 15));
			logLabel.setBackground(Color.WHITE);
			logPanel.add(logLabel);
		}
	}

	/**
	 * 跳转至做题界面（下一步）
	 * 
	 * @param jApplet
	 * @param user
	 * @param index
	 * @param bigQuestions
	 */
	private void skipType(JApplet jApplet, User user, int index, List<BigQuestion> bigQuestions) {
		jApplet.getContentPane().removeAll();
		jApplet.getContentPane().setForeground(Color.WHITE);
		jApplet.getContentPane().setFont(new Font("楷体", Font.PLAIN, 25));
		jApplet.getContentPane().setBackground(Color.WHITE);
		jApplet.getContentPane().setLayout(new GridLayout(1, 1, 0, 0));
		jApplet.getContentPane().add(new Question(jApplet, user, index, bigQuestions));
		repaint();
	}

	/**
	 * 错题类型回顾
	 */
	private void errorReview(JApplet jApplet, User user, JPanel btnPanel) {
		JButton errorButton = new JButton("错题类型回顾");
		errorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<BigQuestion> bigQuestions = LearnRecordDao.selectErrorQuestion(user.getId());
				skipType(jApplet, user, 0, bigQuestions);
			}
		});
		errorButton.setForeground(Color.BLACK);
		errorButton.setFont(new Font("楷体", Font.PLAIN, 20));
		errorButton.setBackground(Color.WHITE);
		btnPanel.add(errorButton);
	}
}
