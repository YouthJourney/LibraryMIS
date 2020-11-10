package edu.yangtzeu.lmis.gui;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import edu.yangtzeu.lmis.bll.UserAdmin;
import edu.yangtzeu.lmis.model.Reader;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PermissionChage extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField tfrdId;
	private JTextField tfPermissionId;
	
	public PermissionChage() {
		setSize(new Dimension(450, 300));
		setResizable(false);
		setTitle("权限修改");
		getContentPane().setLayout(null);
		
		JLabel lblid = new JLabel("用 户 ID");
		lblid.setFont(new Font("宋体", Font.PLAIN, 12));
		lblid.setBounds(127, 71, 54, 15);
		getContentPane().add(lblid);
		
		tfrdId = new JTextField();
		tfrdId.setBounds(191, 68, 100, 21);
		getContentPane().add(tfrdId);
		tfrdId.setColumns(10);
		Login login = new Login();
		tfrdId.setText(login.getTfUsername());
		
		JLabel label = new JLabel("权 限 号");
		label.setFont(new Font("宋体", Font.PLAIN, 12));
		label.setBounds(127, 127, 54, 15);
		getContentPane().add(label);
		
		tfPermissionId = new JTextField();
		tfPermissionId.setBounds(191, 124, 100, 21);
		getContentPane().add(tfPermissionId);
		tfPermissionId.setColumns(10);
		
		JButton btnChgQuery = new JButton("确定修改");
		btnChgQuery.setFont(new Font("宋体", Font.PLAIN, 12));
		btnChgQuery.setBounds(127, 191, 86, 23);
		getContentPane().add(btnChgQuery);
		btnChgQuery.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(tfrdId.getText() == null || tfPermissionId.getText() == null) {
					JOptionPane.showMessageDialog(null, "用户ID或权限号不能为空", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				UserAdmin userBll = new UserAdmin();
				Reader reader = userBll.getUser(Integer.valueOf(tfrdId.getText()));
				reader.setRdAdminRoles(Integer.valueOf(tfPermissionId.getText()));
				if(userBll.UpdateUserPermission(reader) > 0) {
					JOptionPane.showMessageDialog(null, "修改权限成功");
					loadMainGUI();
				}
			}
		});
		
		JButton btnReturn = new JButton("返回");
		btnReturn.setFont(new Font("宋体", Font.PLAIN, 12));
		btnReturn.setBounds(233, 191, 66, 23);
		getContentPane().add(btnReturn);
		btnReturn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadMainGUI();
			}
		});
	}
	
//	private void close() {
//		this.dispose();
//	}
	
	private void loadMainGUI() {
		this.dispose();
		
		Main main = new Main();
		main.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		main.setLocationRelativeTo(null);
		main.setVisible(true);
	}
}
