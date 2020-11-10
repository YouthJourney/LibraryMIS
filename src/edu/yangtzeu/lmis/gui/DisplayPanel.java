package edu.yangtzeu.lmis.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
//import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
//import javax.swing.JButton;

public class DisplayPanel extends JPanel {
	public DisplayPanel() {
		setSize(new Dimension(780, 590));
		setLayout(null);

		JLabel label = new JLabel("长江大学图书管理系统");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLUE);
		label.setFont(new Font("宋体", Font.ITALIC, 25));
		label.setBounds(246, 184, 261, 78);
		add(label);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
