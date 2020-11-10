package edu.yangtzeu.lmis.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import edu.yangtzeu.lmis.bll.ReaderTypeAdmin;
import edu.yangtzeu.lmis.gui.commons.CustomizedTableModel;
import edu.yangtzeu.lmis.model.Reader;
import edu.yangtzeu.lmis.model.ReaderType;

public class ReaderTypePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ReaderTypeAdmin readerTypeBll = new ReaderTypeAdmin();
	int rdTypeID = 0;

	private JTextField tfContinueBorrQty;
	private JTextField tfTypeName;
	private JTextField tfBorrowedQty;
	private JTextField tfPunishRate;
	private JTextField tfValid;
	private JTextField tfBorrowedDay;
	private JTable searchResultTable;

	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnReturn;
	private JButton btnQuery;
	private JButton btnSubmitUpdate;

	private JPanel editPanel;
	private JScrollPane searchPanel;
	private JPanel functionPanel;

	private void initEditPanel() {
		editPanel = new JPanel();
		editPanel.setBounds(62, 36, 679, 91);
		add(editPanel);
		editPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("类型名称：");
		lblNewLabel.setBounds(10, 23, 65, 15);
		editPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("可续借次数：");
		lblNewLabel_1.setBounds(10, 53, 78, 15);
		editPanel.add(lblNewLabel_1);

		tfContinueBorrQty = new JTextField();
		tfContinueBorrQty.setBounds(81, 50, 66, 21);
		editPanel.add(tfContinueBorrQty);
		tfContinueBorrQty.setColumns(10);

		tfTypeName = new JTextField();
		tfTypeName.setBounds(81, 20, 66, 21);
		editPanel.add(tfTypeName);
		tfTypeName.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("可借数量：");
		lblNewLabel_2.setBounds(182, 23, 65, 15);
		editPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("罚金率：");
		lblNewLabel_3.setBounds(182, 53, 54, 15);
		editPanel.add(lblNewLabel_3);

		tfBorrowedQty = new JTextField();
		tfBorrowedQty.setBounds(243, 20, 66, 21);
		editPanel.add(tfBorrowedQty);
		tfBorrowedQty.setColumns(10);

		tfPunishRate = new JTextField();
		tfPunishRate.setBounds(243, 50, 66, 21);
		editPanel.add(tfPunishRate);
		tfPunishRate.setColumns(10);

		JLabel label = new JLabel("可借天数：");
		label.setBounds(336, 23, 65, 15);
		editPanel.add(label);

		JLabel label_1 = new JLabel("证件有效期：");
		label_1.setBounds(336, 53, 84, 15);
		editPanel.add(label_1);

		tfValid = new JTextField();
		tfValid.setBounds(410, 50, 76, 21);
		editPanel.add(tfValid);
		tfValid.setColumns(10);

		tfBorrowedDay = new JTextField();
		tfBorrowedDay.setBounds(410, 20, 76, 21);
		editPanel.add(tfBorrowedDay);
		tfBorrowedDay.setColumns(10);

		btnQuery = new JButton("查找");
		btnQuery.setBounds(515, 19, 86, 23);
		editPanel.add(btnQuery);

		btnSubmitUpdate = new JButton("确定修改");
		btnSubmitUpdate.setBounds(515, 49, 86, 23);
		editPanel.add(btnSubmitUpdate);
	}

	private void initSearchPanel() {
		searchPanel = new JScrollPane(searchResultTable);
		searchPanel.setBorder(
				new TitledBorder(null, "\u67E5\u8BE2\u7ED3\u679C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		searchPanel.setBounds(62, 137, 679, 359);
		add(searchPanel);

		CustomizedTableModel<Reader> tableModel = new CustomizedTableModel<Reader>(
				readerTypeBll.getDisplayColumnNames(), readerTypeBll.getMethodNames());
		searchResultTable = new JTable(tableModel);
//		searchPanel.setColumnHeaderView(searchResultTable);
		searchResultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		searchPanel.setViewportView(searchResultTable);
	}

	private void initFunctionPanel() {
		functionPanel = new JPanel();
		functionPanel.setBounds(113, 506, 546, 53);
		add(functionPanel);
		functionPanel.setLayout(null);

		btnAdd = new JButton("添加");
		btnAdd.setBounds(86, 0, 93, 23);
		functionPanel.add(btnAdd);

		btnUpdate = new JButton("修改");
		btnUpdate.setBounds(189, 0, 93, 23);
		functionPanel.add(btnUpdate);

		btnDelete = new JButton("删除");
		btnDelete.setBounds(292, 0, 93, 23);
		functionPanel.add(btnDelete);

		btnReturn = new JButton("返回");
		btnReturn.setBounds(395, 0, 93, 23);
		functionPanel.add(btnReturn);
	}

	private void addActionListeners() {
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ReaderType readerType = null;
				try {
					readerType = getReaderTypeFromText();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (readerTypeBll.addRdType(readerType) <= 0) {
					JOptionPane.showMessageDialog(null, "添加失败");
					return;
				} else {
					JOptionPane.showMessageDialog(null, "添加成功");
					setTextBlank();
					updateResultTable(readerTypeBll.getAllReaderTypes());
				}
			}
		});

		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = searchResultTable.getSelectedRow();
				if (selectedRow < 0) {
					JOptionPane.showMessageDialog(null, "请先选中一条记录", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Object[] options = { "确定", "取消" };
				int response = JOptionPane.showOptionDialog(null, "确定要删除吗?", "删除", JOptionPane.YES_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if (response == 0) {
					int readerType = Integer.valueOf(searchResultTable.getValueAt(selectedRow, 0).toString());

					if (readerTypeBll.deleteRdType(readerTypeBll.getObjectByID(readerType)) <= 0) {
						JOptionPane.showMessageDialog(null, "删除失败");
					} else {
						JOptionPane.showMessageDialog(null, "删除成功");
						updateResultTable(readerTypeBll.getAllReaderTypes());
					}
				} else if (response == 1) {
					return;
				}

			}
		});

		btnReturn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
//				editPanel.setVisible(false);
//				searchPanel.setVisible(false);
//				functionPanel.setVisible(false);
			}
		});

		btnQuery.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ReaderType[] readerTypes = readerTypeBll.getAllReaderTypes();
				updateResultTable(readerTypes);
			}
		});

		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = searchResultTable.getSelectedRow();
				if (selectedRow < 0) {
					JOptionPane.showMessageDialog(null, "请选中一条记录", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				rdTypeID = Integer.valueOf(searchResultTable.getValueAt(selectedRow, 0).toString());
				setReaderTypeToText(readerTypeBll.getObjectByID(rdTypeID));
			}
		});

		btnSubmitUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ReaderType readerType = null;
				try {
					readerType = getrdTypeFromText();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (readerTypeBll.updateRdType(readerType) <= 0) {
					JOptionPane.showMessageDialog(null, "修改失败");
				} else {
					JOptionPane.showMessageDialog(null, "修改成功");
					updateResultTable(readerTypeBll.getAllReaderTypes());
					setTextBlank();
				}
			}
		});
	}

	private ReaderType getReaderTypeFromText() throws IOException {
		ReaderType readerType = new ReaderType();
		readerType.setRdTypeName(tfTypeName.getText().trim());
		readerType.setCanLendQty(Integer.valueOf(tfBorrowedQty.getText()));
		readerType.setCanLendDay(Integer.valueOf(tfBorrowedDay.getText()));

		readerType.setCanContinueTimes(Integer.valueOf(tfContinueBorrQty.getText()));
		readerType.setPunishRate(Float.valueOf(tfPunishRate.getText()));
		readerType.setDateValid(Integer.valueOf(tfValid.getText()));

		return readerType;
	}

	private ReaderType getrdTypeFromText() throws IOException {
		ReaderType readerType = new ReaderType(rdTypeID);
		readerType.setRdTypeName(tfTypeName.getText().trim());
		readerType.setCanLendQty(Integer.valueOf(tfBorrowedQty.getText()));
		readerType.setCanLendDay(Integer.valueOf(tfBorrowedDay.getText()));

		readerType.setCanContinueTimes(Integer.valueOf(tfContinueBorrQty.getText()));
		readerType.setPunishRate(Float.valueOf(tfPunishRate.getText()));
		readerType.setDateValid(Integer.valueOf(tfValid.getText()));

		return readerType;
	}

	private void setReaderTypeToText(ReaderType readerType) {
		tfTypeName.setText(readerType.getRdTypeName());
		tfBorrowedQty.setText(String.valueOf(readerType.getCanLendQty()));
		tfBorrowedDay.setText(String.valueOf(readerType.getCanLendDay()));
		tfContinueBorrQty.setText(String.valueOf(readerType.getCanContinueTimes()));
		tfPunishRate.setText(String.valueOf(readerType.getPunishRate()));
		tfValid.setText(String.valueOf(readerType.getDateValid()));
	}

	private void updateResultTable(ReaderType[] readerTypes) {
		if (readerTypes == null) {
			JOptionPane.showMessageDialog(null, "没有找到符合要求的记录!");
			return;
		}
		@SuppressWarnings("unchecked")
		CustomizedTableModel<ReaderType> tableModel = (CustomizedTableModel<ReaderType>) searchResultTable.getModel();
		tableModel.setRecords(readerTypes);
		// 更新表格
		tableModel.fireTableDataChanged();
	}

	private void setTextBlank() {
//		JTextField[] jTextFields= {tfTypeName,tfBorrowedQty,tfBorrowedDay,
//				tfContinueBorrQty,tfPunishRate,tfValid};
		tfTypeName.setText("");
		tfBorrowedDay.setText("");
		tfBorrowedQty.setText("");
		tfContinueBorrQty.setText("");
		tfPunishRate.setText("");
		tfValid.setText("");
	}

	public ReaderTypePanel() {
		setSize(new Dimension(800, 574));
		setLayout(null);

		// 初始化各个panel
		initEditPanel();
		initSearchPanel();
		initFunctionPanel();

		// 添加动作监听器
		addActionListeners();
	}
}
