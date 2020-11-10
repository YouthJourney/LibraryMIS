package edu.yangtzeu.lmis.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import edu.yangtzeu.lmis.bll.UserAdmin;
import edu.yangtzeu.lmis.model.Reader;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

public class PasswordChage extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	Login login = new Login();
//	Main main = new Main();
	
	private JTextField tfNewPassword;
	private JPasswordField tfSurePassword;
	
	private void closeGUI() {
		this.dispose();
	}
	
	public PasswordChage() {
		setSize(new Dimension(416, 300));
		setResizable(false);
		setFont(new Font("宋体", Font.PLAIN, 12));
		setTitle("密码修改");
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("新密码：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel.setBounds(90, 74, 54, 15);
		getContentPane().add(lblNewLabel);
		
		tfNewPassword = new JTextField();
		tfNewPassword.setBounds(152, 71, 128, 21);
		getContentPane().add(tfNewPassword);
		tfNewPassword.setColumns(10);
		
		JLabel label = new JLabel("确定密码：");
		label.setFont(new Font("宋体", Font.PLAIN, 12));
		label.setBounds(90, 120, 62, 15);
		getContentPane().add(label);
		
		tfSurePassword = new JPasswordField();
		tfSurePassword.setBounds(152, 117, 128, 21);
		getContentPane().add(tfSurePassword);
		tfSurePassword.setColumns(10);
		
		JLabel lblInfoPassword = new JLabel("");
		lblInfoPassword.setForeground(Color.RED);
		lblInfoPassword.setBounds(147, 148, 121, 15);
		getContentPane().add(lblInfoPassword);
		
		JButton btnQuery = new JButton("确定修改");
		btnQuery.setFont(new Font("宋体", Font.PLAIN, 12));
		btnQuery.setBounds(90, 196, 93, 23);
		getContentPane().add(btnQuery);
		btnQuery.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String errorMsg = "";
				String newPwd = tfNewPassword.getText().toString();
				String surePwd = String.valueOf(tfSurePassword.getPassword());
				System.out.println(tfSurePassword.getPassword());
				System.out.println(newPwd);
				System.out.println(surePwd);
				if(newPwd == null && surePwd == null) {
					errorMsg = "密码不能为空";
				}
				else if(surePwd.equals(newPwd) == false) {
					errorMsg = "两次输入的密码不相同";
					tfSurePassword.requestFocus();
				}
				if(errorMsg.length() > 0) {
					lblInfoPassword.setText(errorMsg);
					lblInfoPassword.setFont(new Font("宋体", Font.PLAIN, 12));
					return;
				}
				Login login = new Login();
				UserAdmin userBll = new UserAdmin();
				Reader reader = userBll.getUser(Integer.valueOf(login.getTfUsername()));
				reader.setRdPwd(String.valueOf(tfSurePassword.getPassword()));
				if(userBll.UpdateUserPassword(reader) > 0) {
					JOptionPane.showMessageDialog(null, "修改密码成功请用重新登录!");	
					loadNewGUI();
				}
				
			}
		});
		
		JButton btnReturn = new JButton("返回");
		btnReturn.setFont(new Font("宋体", Font.PLAIN, 12));
		btnReturn.setBounds(207, 196, 93, 23);
		getContentPane().add(btnReturn);
		btnReturn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				closeGUI();
				Main main = new Main();
				main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				main.setVisible(true);
				main.setLocationRelativeTo(null);
			}
		});
	}
	
	private void loadNewGUI() {
		this.dispose();
		
		Login login = new Login();
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setVisible(true);
		login.setLocationRelativeTo(null);
		login.addButtonClickEventHanders();
	}
}
