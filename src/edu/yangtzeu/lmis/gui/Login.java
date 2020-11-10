package edu.yangtzeu.lmis.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import edu.yangtzeu.lmis.bll.ReaderAdmin;
import edu.yangtzeu.lmis.model.Reader;

public class Login extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField tfUserName; // 用户名文本框
	private JPasswordField tfPassword; // 密码文本框
	private JLabel labelLoginInfo;
	private JButton btnLogin; // 登陆按钮
	private JButton btnClose; // 退出按钮
	private int loginTimes = 0; // 登陆次数
	private ReaderAdmin readerBLL = new ReaderAdmin();
	private static Reader reader = null;// 登陆用户信息，可用于多个程序。

	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("images//icon.png"));
		setSize(new Dimension(350, 250));
		setResizable(false);
		setTitle("长江大学图书管理信息系统");
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(204, 255, 255));
		getContentPane().setVisible(true);

		JLabel labelUserName = new JLabel("用户编号：");
		labelUserName.setFont(new Font("宋体", Font.PLAIN, 12));
		labelUserName.setBounds(66, 40, 69, 23);
		getContentPane().add(labelUserName);

		JLabel labelPassword = new JLabel("用户密码：");
		labelPassword.setFont(new Font("宋体", Font.PLAIN, 12));
		labelPassword.setBounds(66, 81, 69, 23);
		getContentPane().add(labelPassword);

		tfUserName = new JTextField("7");
		tfUserName.setBounds(129, 41, 101, 21);
		getContentPane().add(tfUserName);
		tfUserName.setColumns(10);

		tfPassword = new JPasswordField("123");// Jpasswordfield密码框直接使用getpassword()方法
		tfPassword.setFont(new Font("宋体", Font.PLAIN, 12));
		tfPassword.setBounds(129, 82, 101, 21);
		getContentPane().add(tfPassword);
		tfPassword.setColumns(10);

		btnLogin = new JButton("登  陆");
		btnLogin.setFont(new Font("宋体", Font.PLAIN, 12));
		btnLogin.setBounds(71, 132, 76, 23);
		getContentPane().add(btnLogin);

		btnClose = new JButton("退  出");
		btnClose.setFont(new Font("宋体", Font.PLAIN, 12));
		btnClose.setBounds(171, 132, 76, 23);
		getContentPane().add(btnClose);

		labelLoginInfo = new JLabel("");
		labelLoginInfo.setForeground(Color.RED);
		labelLoginInfo.setFont(new Font("宋体", Font.PLAIN, 12));
		labelLoginInfo.setBounds(129, 108, 101, 21);
		getContentPane().add(labelLoginInfo);
	}

	public void addButtonClickEventHanders() {
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				loginTimes++;
				String errorMsg = "";
				int rdID = -1;
				try {
					rdID = Integer.valueOf(tfUserName.getText().trim());
				} catch (NumberFormatException e) {
					errorMsg = "用户名无效";
					tfUserName.requestFocus();
				}
				if (rdID != -1) {
					reader = readerBLL.getReader(rdID);
					if (reader == null) {
						errorMsg = "用户名无效";
						tfUserName.requestFocus();
					} else if (reader.getRdPwd().equals(new String(tfPassword.getPassword()).trim())) { // 注意两种方法
						loadMainGUI();
					} else {
						errorMsg = "密码有误";
						tfPassword.requestFocus();
					}
				}
				if (loginTimes > 6) {
					errorMsg = "超过输入次数";
				}
				if (errorMsg.length() > 0) {
					labelLoginInfo.setText(errorMsg);
					labelLoginInfo.setFont(new Font("宋体", Font.PLAIN, 12));
				}
			}
		});

		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();// 关闭当前窗体
			}
		});
	}

	public String getTfUsername() {
		return tfUserName.getText();
	}

	// 加载main窗口
	private void loadMainGUI() {
		this.dispose();

		Main mainGUI = new Main();
		mainGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainGUI.setLocationRelativeTo(null);
		mainGUI.setVisible(true);
	}

	public static void main(String[] args) {
		Login login = new Login();
		login.start();
		login.addButtonClickEventHanders();

	}

	public void start() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
