import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import bean.Level;
import bean.User;
import dao.LevelDao;
import dao.UserDao;
import javax.swing.JRadioButton;

public class Login extends JApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4767213216249714181L;
	private JTextField accountText;
	private JPasswordField password;
	private final JButton button = new JButton("登   录");
	private JPanel accountPanel;
	private JLabel accountLabel;
	private JPanel passwordPanel;
	private JLabel passwordLabel;
	private JPanel btnPanel;
	private String gap = "   ";
	private JLabel hintLabel;
	private JPanel hintPanel;
	private JPanel radioPanel;
	private List<Level> levels;
	private String levelStr = "";

	/**
	 * Create the applet.
	 */
	public Login() {
		getContentPane().setForeground(Color.WHITE);
		getContentPane().setFont(new Font("楷体", Font.PLAIN, 25));
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(new GridLayout(20, 1, 0, 0));

		accountPanel = new JPanel();
		accountPanel.setForeground(Color.BLACK);
		accountPanel.setBackground(Color.WHITE);
		getContentPane().add(accountPanel);
		accountPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		accountLabel = new JLabel("账号：");
		accountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		accountLabel.setForeground(Color.BLACK);
		accountLabel.setBackground(Color.WHITE);
		accountLabel.setFont(new Font("楷体", Font.PLAIN, 20));
		accountPanel.add(accountLabel);

		accountText = new JTextField();
		accountPanel.add(accountText);
		accountText.setHorizontalAlignment(SwingConstants.LEFT);
		accountText.setFont(new Font("楷体", Font.PLAIN, 20));
		accountText.setToolTipText("请输入账号");
		accountText.setForeground(Color.BLACK);
		accountText.setBackground(Color.WHITE);
		accountText.setColumns(15);

		passwordPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) passwordPanel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		passwordPanel.setBackground(Color.WHITE);
		passwordPanel.setForeground(Color.BLACK);
		getContentPane().add(passwordPanel);

		passwordLabel = new JLabel("密码：");
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setBackground(Color.WHITE);
		passwordLabel.setForeground(Color.BLACK);
		passwordLabel.setFont(new Font("楷体", Font.PLAIN, 20));
		passwordPanel.add(passwordLabel);

		password = new JPasswordField();
		password.setForeground(Color.BLACK);
		password.setBackground(Color.WHITE);
		password.setColumns(15);
		passwordPanel.add(password);
		password.setHorizontalAlignment(SwingConstants.LEFT);
		password.setEchoChar('*');
		password.setFont(new Font("楷体", Font.PLAIN, 20));
		password.setToolTipText("请输入密码");

		radioPanel = new JPanel();
		radioPanel.setBackground(Color.WHITE);
		getContentPane().add(radioPanel);

		ButtonGroup btnGroup = new ButtonGroup(); // 创建一个按钮组
		levels = LevelDao.selectLevel();
		for (Level level : levels) {
			JRadioButton school = new JRadioButton(level.getLevel());
			school.setForeground(Color.BLACK);
			school.setHorizontalAlignment(SwingConstants.CENTER);
			// school.setSelected(true);
			school.setFont(new Font("楷体", Font.PLAIN, 20));
			school.setBackground(Color.WHITE);
			radioPanel.add(school);
			btnGroup.add(school);
			school.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					levelStr = school.getText();
				}
			});
		}

		hintPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) hintPanel.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		hintPanel.setBackground(Color.WHITE);
		getContentPane().add(hintPanel);

		hintLabel = new JLabel("");
		hintLabel.setFont(new Font("楷体", Font.PLAIN, 15));
		hintLabel.setHorizontalAlignment(SwingConstants.CENTER);
		hintLabel.setForeground(Color.RED);
		hintLabel.setBackground(Color.WHITE);
		hintPanel.add(hintLabel);

		btnPanel = new JPanel();
		btnPanel.setForeground(Color.BLACK);
		btnPanel.setBackground(Color.WHITE);
		FlowLayout fl_btnPanel = (FlowLayout) btnPanel.getLayout();
		fl_btnPanel.setVgap(0);
		fl_btnPanel.setHgap(0);
		getContentPane().add(btnPanel);
		btnPanel.add(button);
		button.setBackground(Color.WHITE);
		button.setForeground(Color.BLACK);
		button.setFont(new Font("楷体", Font.PLAIN, 25));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hintLabel.setText("");
				String accountStr = accountText.getText();
				String passwordStr = password.getText();
				System.out.println(accountStr + "\t" + passwordStr + "\t" + levelStr);
				if (accountStr == null || accountStr.equals("")) {
					hintLabel.setText("请输入您的账号");
				} else if (passwordStr == null || passwordStr.equals("")) {
					hintLabel.setText("请输入您的密码");
				} else if (levelStr.equals("")) {
					hintLabel.setText("请选择您相应的学业类型");
				} else {
					User user = UserDao.selectUser(accountStr, passwordStr);
					if (user == null) {
						hintLabel.setText("账号或密码有误（账号密码不匹配）");
					} else {
						for (Level level : levels) {
							if (level.getLevel().equals(levelStr)) {
								user.setLevel(level);
								break;
							}
						}
						skipType(user);
					}
				}
			}
		});
	}

	/**
	 * 跳转至选择题型界面（下一步）
	 * 
	 * @param user
	 *            用户
	 */
	private void skipType(User user) {
		getContentPane().removeAll();
		getContentPane().setForeground(Color.WHITE);
		getContentPane().setFont(new Font("楷体", Font.PLAIN, 25));
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(new GridLayout(1, 1, 0, 0));
		getContentPane().add(new TypesQuestion(this, user));
		repaint();
	}
}
