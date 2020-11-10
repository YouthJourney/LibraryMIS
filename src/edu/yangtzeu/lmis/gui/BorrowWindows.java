package edu.yangtzeu.lmis.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import edu.yangtzeu.lmis.bll.BookAdmin;
import edu.yangtzeu.lmis.bll.BorrowAdmin;
import edu.yangtzeu.lmis.bll.DepartmentTypeAdmin;
import edu.yangtzeu.lmis.bll.ReaderAdmin;
import edu.yangtzeu.lmis.bll.ReaderTypeAdmin;
import edu.yangtzeu.lmis.gui.commons.CustomizedTableModel;
import edu.yangtzeu.lmis.model.Book;
import edu.yangtzeu.lmis.model.Borrow;
import edu.yangtzeu.lmis.model.Reader;

public class BorrowWindows extends JFrame {

	/**	
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int times = 0;
	private DefaultTableModel tableModel;
	private ReaderTypeAdmin readerTypeBll = new ReaderTypeAdmin();
	private DepartmentTypeAdmin deptTypeBll = new DepartmentTypeAdmin();
	private ReaderAdmin readerBll = new ReaderAdmin();
	private BorrowAdmin borrowBll = new BorrowAdmin();
	private BookAdmin bookBll = new BookAdmin();

	private JScrollPane bookResultInfoPane;
	private JPanel operatorPanel;
	private JPanel functionPanel;
	private JPanel bookInfoPanel;
	private JScrollPane borrowedPane;
	private JPanel readerInformatiomPanel;

	private JTextField tfRdId;
	private JTextField tfRdName;
	private JTextField tfCanLendQty;
	private JTextField tfRdDept;
	private JTextField tfCanLendDay;
	private JTextField tfRdType;
	private JTextField tfBorrowedQty;
	private JTable borrowedResultTable;
	private JTextField tfBookId;
	private JTextField tfBookName;
	private JTable bookResultInfoTable;

	private JLabel lblOperator;
	private JLabel lblOperatorTime;

	private void initReaderInformatiomPanel() {
		readerInformatiomPanel = new JPanel();
		readerInformatiomPanel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"\u8BFB\u8005\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		readerInformatiomPanel.setBounds(10, 10, 864, 77);
		getContentPane().add(readerInformatiomPanel);
		readerInformatiomPanel.setLayout(null);

		JLabel label = new JLabel("读者编号：");
		label.setBounds(10, 31, 66, 15);
		readerInformatiomPanel.add(label);

		tfRdId = new JTextField();
		tfRdId.setBounds(74, 29, 66, 21);
		readerInformatiomPanel.add(tfRdId);
		tfRdId.setColumns(10);

		JButton btnQuery = new JButton("query");
		btnQuery.setBounds(141, 28, 66, 23);
		readerInformatiomPanel.add(btnQuery);
		btnQuery.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// 将读者信息给到文本框
				if (tfRdId.getText().equals("") || tfRdId.getText() == null) {
					JOptionPane.showMessageDialog(null, "请输入读者ID号", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Reader reader = readerBll.getReader(Integer.valueOf(tfRdId.getText()));
				setReaderToText(reader);
				// 将读者借阅信息给到表格
				Borrow[] borrows = borrowBll.getBorrowsByrdId(Integer.valueOf(tfRdId.getText()));
				// 更新表格
				updateBorrowedResultTable(borrows);
			}
		});

		JLabel label_1 = new JLabel("读者姓名");
		label_1.setBounds(237, 18, 54, 15);
		readerInformatiomPanel.add(label_1);

		tfRdName = new JTextField();
		tfRdName.setEditable(false);
		tfRdName.setBounds(301, 15, 66, 21);
		readerInformatiomPanel.add(tfRdName);
		tfRdName.setColumns(10);

		JLabel label_2 = new JLabel("可借书数量");
		label_2.setBounds(237, 48, 66, 15);
		readerInformatiomPanel.add(label_2);

		tfCanLendQty = new JTextField();
		tfCanLendQty.setEditable(false);
		tfCanLendQty.setBounds(301, 45, 66, 21);
		readerInformatiomPanel.add(tfCanLendQty);
		tfCanLendQty.setColumns(10);

		JLabel label_3 = new JLabel("读者单位");
		label_3.setBounds(377, 18, 54, 15);
		readerInformatiomPanel.add(label_3);

		tfRdDept = new JTextField();
		tfRdDept.setEditable(false);
		tfRdDept.setBounds(441, 15, 66, 21);
		readerInformatiomPanel.add(tfRdDept);
		tfRdDept.setColumns(10);

		JLabel label_4 = new JLabel("可借书天数");
		label_4.setBounds(377, 48, 66, 15);
		readerInformatiomPanel.add(label_4);

		tfCanLendDay = new JTextField();
		tfCanLendDay.setEditable(false);
		tfCanLendDay.setBounds(441, 45, 66, 21);
		readerInformatiomPanel.add(tfCanLendDay);
		tfCanLendDay.setColumns(10);

		JLabel label_5 = new JLabel("读者类型");
		label_5.setBounds(517, 18, 54, 15);
		readerInformatiomPanel.add(label_5);

		tfRdType = new JTextField();
		tfRdType.setEditable(false);
		tfRdType.setBounds(581, 15, 66, 21);
		readerInformatiomPanel.add(tfRdType);
		tfRdType.setColumns(10);

		JLabel label_6 = new JLabel("已借数量");
		label_6.setBounds(517, 48, 54, 15);
		readerInformatiomPanel.add(label_6);

		tfBorrowedQty = new JTextField();
		// tfBorrowedQty.setBorder(new LineBorder(new Color(171, 173, 179)));
		tfBorrowedQty.setEditable(false);
		tfBorrowedQty.setBounds(581, 45, 66, 21);
		readerInformatiomPanel.add(tfBorrowedQty);
		tfBorrowedQty.setColumns(10);

		JButton btnCtueBorrow = new JButton("续借");
		btnCtueBorrow.setBounds(667, 27, 73, 23);
		readerInformatiomPanel.add(btnCtueBorrow);
		btnCtueBorrow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = borrowedResultTable.getSelectedRow();
				if (selectedRow < 0) {
					JOptionPane.showMessageDialog(null, "请选择一条借阅信息");
					return;
				}

				System.out.println(times);
				if (times >= 2) {
					JOptionPane.showMessageDialog(null, "超过续借次数!");
					return;
				}

				int bkId = Integer.valueOf(borrowedResultTable.getValueAt(selectedRow, 0).toString());
				// System.out.println(borrowBll.getBorrow(bkId).getRdID());
				if (borrowBll.updateBorrowDatePlan(borrowBll.getBorrow(bkId)) > 0) {
					JOptionPane.showMessageDialog(null, "续借成功!");
					times = times + 1;
				} else {
					JOptionPane.showMessageDialog(null, "续借失败!");
				}
				borrowedResultTable.getSelectionModel().clearSelection();
			}
		});

		JButton btnReturnBook = new JButton("还书");
		btnReturnBook.setBounds(761, 27, 73, 23);
		readerInformatiomPanel.add(btnReturnBook);
		btnReturnBook.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = borrowedResultTable.getSelectedRow();
				if (selectedRow < 0) {
					JOptionPane.showMessageDialog(null, "请选择一条借阅信息");
					return;
				}
				int bkId = Integer.valueOf(borrowedResultTable.getValueAt(selectedRow, 0).toString());
				// System.out.println("图书序号" +bkId);
				Book book = bookBll.getBook(bkId);
				book.setBkStatus("在馆");
				Reader reader = readerBll.getReader(borrowBll.getBorrow(bkId).getRdID());
				Borrow[] borrows = borrowBll.getBorrowsByrdId(reader.getRdID());
				reader.setRdBorrowQty(reader.getRdBorrowQty() - 1);
				if (borrowBll.deleteBorrow(borrowBll.getBorrow(bkId)) > 0 && bookBll.updateBkStatus(book) > 0
						&& readerBll.updateReaderBorrowedQty(reader) > 0) {
					JOptionPane.showMessageDialog(null, "还书成功!");
					updateBorrowedResultTable(borrows);
				} else {
					JOptionPane.showMessageDialog(null, "还书失败!");
				}
				borrowedResultTable.getSelectionModel().clearSelection();
			}
		});
	}

	private void initBorrowedPane() {
		borrowedPane = new JScrollPane();
		borrowedPane.setBackground(Color.DARK_GRAY);
		borrowedPane.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"\u5DF2\u501F\u56FE\u4E66", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		borrowedPane.setBounds(10, 95, 864, 139);
		getContentPane().add(borrowedPane);

		String[] dispColNames = new String[] { "图书序号", "图书名称", "图书作者", "续借次数", "借阅日期", "应还日期", "超期天数", "超期金额" };
		tableModel = new DefaultTableModel(dispColNames, 0);
		borrowedResultTable = new JTable(tableModel) {
			/**
			* 
			*/
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		borrowedPane.add(borrowedResultTable);
		borrowedResultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// borrowedResultTable.setEnabled(false);
		DefaultTableCellRenderer rtTableCellRenderer = new DefaultTableCellRenderer();
		rtTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
		borrowedResultTable.setDefaultRenderer(Object.class, rtTableCellRenderer);
		borrowedPane.setViewportView(borrowedResultTable);
	}

	private void initBookResultInfoPane() {
		bookResultInfoPane = new JScrollPane();
		bookResultInfoPane.setBounds(10, 303, 864, 247);
		getContentPane().add(bookResultInfoPane);

		// DefaultTableModel tableModel = new DefaultTableModel(arg0, arg1)
		CustomizedTableModel<Book> tableModel1 = new CustomizedTableModel<Book>(bookBll.getDisplayColumnNames(),
				bookBll.getMethodNames());
		bookResultInfoTable = new JTable(tableModel1);
		bookResultInfoPane.add(bookResultInfoTable);
		bookResultInfoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableCellRenderer rTableCellRenderer = new DefaultTableCellRenderer();
		rTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
		bookResultInfoTable.setDefaultRenderer(Object.class, rTableCellRenderer);
		// bookResultInfoTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		bookResultInfoPane.setViewportView(bookResultInfoTable);
	}

	private void initFunctionPanel() {
		functionPanel = new JPanel();
		functionPanel.setBounds(10, 607, 864, 44);
		getContentPane().add(functionPanel);
		functionPanel.setLayout(null);

		JButton btnBorrowed = new JButton("借阅");
		btnBorrowed.setBounds(316, 10, 93, 23);
		functionPanel.add(btnBorrowed);
		btnBorrowed.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = bookResultInfoTable.getSelectedRow();
				if (selectedRow < 0) {
					JOptionPane.showMessageDialog(null, "请选择一条记录", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				int bkId = Integer.valueOf(bookResultInfoTable.getValueAt(selectedRow, 0).toString());
				Book book = bookBll.getBook(bkId);
				book.setBkStatus("外借");
				Login login = new Login();
				Reader reader = readerBll.getReader(Integer.valueOf(login.getTfUsername()));
				reader.setRdBorrowQty(reader.getRdBorrowQty() + 1);
				if (reader.getRdStatus().equals("挂失")) {
					JOptionPane.showMessageDialog(null, "您的借书证处于挂失禁止借书!", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Borrow borrow = new Borrow();
				borrow.setRdID(reader.getRdID());
				borrow.setBkID(book.getBkID());
				if (borrowBll.addBorrow(borrow) > 0 && bookBll.updateBkStatus(book) > 0
						&& readerBll.updateReaderBorrowedQty(reader) > 0) {
					JOptionPane.showMessageDialog(null, "借书成功!");
					Date date = new Date();
					lblOperatorTime.setText(date.toString());
					lblOperator.setText(borrowBll.getBorrow(bkId).getOperatorLend());
				} else {
					JOptionPane.showMessageDialog(null, "借书失败!");
				}
				bookResultInfoTable.getSelectionModel().clearSelection();
			}
		});

		JButton btnReturn = new JButton("返回");
		btnReturn.setBounds(450, 10, 93, 23);
		functionPanel.add(btnReturn);
		btnReturn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				closeGUI();
			}
		});
	}

	private void initOperatorPanel() {
		operatorPanel = new JPanel();
		operatorPanel.setBorder(new LineBorder(Color.GRAY));
		operatorPanel.setBounds(10, 560, 864, 37);
		getContentPane().add(operatorPanel);
		operatorPanel.setLayout(null);

		JLabel label_7 = new JLabel("操作员");
		label_7.setBounds(10, 11, 54, 15);
		label_7.setFont(new Font("宋体", Font.PLAIN, 12));
		operatorPanel.add(label_7);

		lblOperator = new JLabel("");
		lblOperator.setBounds(74, 11, 70, 15);
		lblOperator.setForeground(Color.red);
		lblOperator.setFont(new Font("宋体", Font.PLAIN, 12));
		operatorPanel.add(lblOperator);

		lblOperatorTime = new JLabel("");
		lblOperatorTime.setBounds(683, 11, 171, 15);
		operatorPanel.add(lblOperatorTime);
	}

	private void initBookInfoPanel() {
		bookInfoPanel = new JPanel();
		bookInfoPanel.setBorder(
				new TitledBorder(null, "\u56FE\u4E66\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		bookInfoPanel.setBounds(10, 243, 864, 50);
		getContentPane().add(bookInfoPanel);
		bookInfoPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("图书序号");
		lblNewLabel.setBounds(30, 22, 54, 15);
		bookInfoPanel.add(lblNewLabel);

		tfBookId = new JTextField();
		tfBookId.setBounds(84, 19, 92, 21);
		bookInfoPanel.add(tfBookId);
		tfBookId.setColumns(10);

		JButton btnIdQuery = new JButton("序号查询");
		btnIdQuery.setBounds(186, 18, 92, 23);
		bookInfoPanel.add(btnIdQuery);
		btnIdQuery.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (tfBookId.getText().equals("") || tfBookId.getText() == null) {
					JOptionPane.showMessageDialog(null, "图书序号不能为空!");
					return;
				}
				Book[] books = bookBll.getBooksById(Integer.valueOf(tfBookId.getText()));
				updateBookResultInfoTable(books);
			}
		});

		JLabel lblNewLabel_1 = new JLabel("图书名称");
		lblNewLabel_1.setBounds(301, 22, 54, 15);
		bookInfoPanel.add(lblNewLabel_1);

		tfBookName = new JTextField();
		tfBookName.setBounds(365, 19, 81, 21);
		bookInfoPanel.add(tfBookName);
		tfBookName.setColumns(10);

		JButton btnNameQuery = new JButton("名称查询");
		btnNameQuery.setBounds(456, 18, 92, 23);
		bookInfoPanel.add(btnNameQuery);
		btnNameQuery.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Book[] books = bookBll.getBookInfosBybkName(tfBookName.getText());
				updateBookResultInfoTable(books);
			}
		});
	}

	private void setReaderToText(Reader reader) {
		tfRdName.setText(reader.getRdName());
		// 通过读者的类型号找到读者类型表获取读者类型名
		tfRdType.setText(readerTypeBll.getObjectByID(reader.getRdType()).getRdTypeName());
		// 通过读者的类型号找到单位类型表获取单位类型名
		tfRdDept.setText(deptTypeBll.getObjectById(reader.getRdDept()).getDtName());
		// 通过读者的类型号找到读者类型表获取可借书天数和可借书数量
		tfCanLendDay.setText(String.valueOf(readerTypeBll.getObjectByID(reader.getRdType()).getCanLendDay()));
		tfCanLendQty.setText(String.valueOf(readerTypeBll.getObjectByID(reader.getRdType()).getCanLendQty()));
		tfBorrowedQty.setText(String.valueOf(reader.getRdBorrowQty()));
	}

	// 更新借阅信息表格;
	private void updateBorrowedResultTable(Borrow[] borrows) {
		// System.out.println("测试1");
		if (borrows == null) {
			JOptionPane.showMessageDialog(null, "该读者没有目前借书信息");
			return;
		}
		tableModel.setRowCount(0);
		// System.out.println(borrows.length);
		for (Borrow borrow : borrows) {
			// 录入每行的每一列数据
			Vector<String> vector = new Vector<String>();
			System.out.println(borrow.getBkID());
			vector.add(String.valueOf(borrow.getBkID()));
			vector.add(bookBll.getBook(borrow.getBkID()).getBkName());
			vector.add(bookBll.getBook(borrow.getBkID()).getBkAuthor());
			vector.add(String.valueOf(borrow.getIdContinueTimes()));
			vector.add(borrow.getIdDateOut().toString());
			vector.add(borrow.getIdDateRetPlan().toString());
			vector.add(String.valueOf(borrow.getIdOverDay()));
			vector.add(String.valueOf(borrow.getIdOverMoney()));
			// 给表格每一栏添加数据信息
			System.out.println(vector);
			tableModel.addRow(vector);
			vector = null;
		}
	}

	// 更新图书信息表格;
	private void updateBookResultInfoTable(Book[] books) {
		if (books == null) {
			JOptionPane.showMessageDialog(null, "没有找到该图书信息!");
			return;
		}
		@SuppressWarnings("unchecked")
		CustomizedTableModel<Book> tableModel = (CustomizedTableModel<Book>) bookResultInfoTable.getModel();
		tableModel.setRecords(books);
		// 更新表格
		tableModel.fireTableDataChanged();
	}

	// 关闭窗口
	private void closeGUI() {
		this.dispose();
	}

	public BorrowWindows() {
		setResizable(false);
		setSize(new Dimension(900, 700));
		setTitle("借阅管理");
		getContentPane().setLayout(null);

		initBookResultInfoPane();
		initFunctionPanel();
		initOperatorPanel();
		initBookInfoPanel();
		initBorrowedPane();
		initReaderInformatiomPanel();
	}

}
