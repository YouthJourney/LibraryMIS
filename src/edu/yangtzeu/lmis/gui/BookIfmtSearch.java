package edu.yangtzeu.lmis.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import edu.yangtzeu.lmis.bll.BookAdmin;
import edu.yangtzeu.lmis.gui.commons.CustomizedTableModel;
import edu.yangtzeu.lmis.model.Book;
import edu.yangtzeu.lmis.model.Reader;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class BookIfmtSearch extends JPanel {
	/*
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BookAdmin bookBll = new BookAdmin();

	private JTextField tfQueryContent;
	private JTextField tfBkPressYear;
	private JTextField tfBkName;
	private JTextField tfBkAuthor;
	private JTextField tfBkDescription;
	private JTextField tfBkPress;
	private JTextField tfBkCatalog;

	private JTable searchResultTable;

	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnExcel;
	private JButton btnReturn;
	private JButton btnSimpleQuery;
	private JButton btnCplxQuery;

	@SuppressWarnings("rawtypes")
	private JComboBox indexWordComboBox;

	private void initFunctionPanel() {
		JPanel functionPanel = new JPanel();
		functionPanel.setBounds(10, 502, 776, 29);
		add(functionPanel);
		functionPanel.setLayout(null);

		btnUpdate = new JButton("修改");
		btnUpdate.setBounds(179, 0, 93, 23);
		functionPanel.add(btnUpdate);

		btnDelete = new JButton("删除");
		btnDelete.setBounds(282, 0, 93, 23);
		functionPanel.add(btnDelete);

		btnExcel = new JButton("导出Excel");
		btnExcel.setBounds(385, 0, 93, 23);
		functionPanel.add(btnExcel);

		btnReturn = new JButton("返回");
		btnReturn.setBounds(488, 0, 93, 23);
		functionPanel.add(btnReturn);
	}

	private void initSearchResultPanel() {
		JScrollPane searchResultPanel = new JScrollPane(searchResultTable);
		searchResultPanel.setBorder(new TitledBorder(null, "\u67E5\u8BE2\u7ED3\u679C", TitledBorder.LEADING,
				TitledBorder.TOP, null, Color.GRAY));
		searchResultPanel.setBounds(10, 180, 780, 312);
		add(searchResultPanel);
//		searchResultPanel.setLayout(null);

		CustomizedTableModel<Reader> tableModel = new CustomizedTableModel<Reader>(bookBll.getDisplayColumnNames(),
				bookBll.getMethodNames());
		searchResultTable = new JTable(tableModel);
//		searchResultTable.setBounds(10, 10, 1, 1);
		searchResultPanel.add(searchResultTable);
		searchResultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		searchResultPanel.setViewportView(searchResultTable);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initSearchTabPane() {
		JTabbedPane searchTabPane = new JTabbedPane(JTabbedPane.TOP);
		searchTabPane.setBounds(10, 42, 780, 128);
		add(searchTabPane);

		JPanel simpleSearchPanel = new JPanel();
		searchTabPane.addTab("简单查询", null, simpleSearchPanel, null);
		simpleSearchPanel.setLayout(null);

		JLabel label = new JLabel("检索字段：");
		label.setBounds(10, 42, 75, 15);
		simpleSearchPanel.add(label);

		String[] indexNames = new String[] { "书名", "作者名", "出版社" };
		// @SuppressWarnings({ "rawtypes", "unchecked" })
		indexWordComboBox = new JComboBox(indexNames);
		indexWordComboBox.setBounds(84, 38, 145, 23);
		simpleSearchPanel.add(indexWordComboBox);

		tfQueryContent = new JTextField();
		tfQueryContent.setBounds(241, 39, 260, 21);
		simpleSearchPanel.add(tfQueryContent);
		tfQueryContent.setColumns(10);

		btnSimpleQuery = new JButton("查询");
		btnSimpleQuery.setBounds(527, 39, 93, 23);
		simpleSearchPanel.add(btnSimpleQuery);

		JPanel complexSearchPanel = new JPanel();
		searchTabPane.addTab("复杂查询", null, complexSearchPanel, null);
		complexSearchPanel.setLayout(null);

		JLabel label_1 = new JLabel("图书名称：");
		label_1.setBounds(17, 18, 69, 15);
		complexSearchPanel.add(label_1);

		tfBkName = new JTextField();
		tfBkName.setBounds(85, 15, 132, 21);
		complexSearchPanel.add(tfBkName);
		tfBkName.setColumns(10);

		JLabel label_2 = new JLabel("图书作者：");
		label_2.setBounds(226, 18, 69, 15);
		complexSearchPanel.add(label_2);

		tfBkAuthor = new JTextField();
		tfBkAuthor.setBounds(290, 15, 119, 21);
		complexSearchPanel.add(tfBkAuthor);
		tfBkAuthor.setColumns(10);

		JLabel label_3 = new JLabel("图书描述：");
		label_3.setBounds(424, 18, 69, 15);
		complexSearchPanel.add(label_3);

		tfBkDescription = new JTextField();
		tfBkDescription.setBounds(489, 15, 91, 21);
		complexSearchPanel.add(tfBkDescription);
		tfBkDescription.setColumns(10);

		JLabel label_4 = new JLabel("出版社名：");
		label_4.setBounds(17, 62, 69, 15);
		complexSearchPanel.add(label_4);

		tfBkPress = new JTextField();
		tfBkPress.setBounds(85, 59, 132, 21);
		complexSearchPanel.add(tfBkPress);
		tfBkPress.setColumns(10);

		JLabel label_5 = new JLabel("分类号：");
		label_5.setBounds(226, 62, 54, 15);
		complexSearchPanel.add(label_5);

		tfBkCatalog = new JTextField();
		tfBkCatalog.setBounds(290, 59, 119, 21);
		complexSearchPanel.add(tfBkCatalog);
		tfBkCatalog.setColumns(10);

		JLabel label_6 = new JLabel("出版年：");
		label_6.setBounds(424, 62, 54, 15);
		complexSearchPanel.add(label_6);

		tfBkPressYear = new JTextField();
		tfBkPressYear.setBounds(489, 59, 90, 21);
		complexSearchPanel.add(tfBkPressYear);
		tfBkPressYear.setColumns(10);

		btnCplxQuery = new JButton("查询");
		btnCplxQuery.setBounds(600, 36, 69, 23);
		complexSearchPanel.add(btnCplxQuery);
	}

	public BookIfmtSearch() {
		setSize(new Dimension(800, 560));
		setLayout(null);

		initFunctionPanel();
		initSearchResultPanel();
		initSearchTabPane();

		addActionListeners();
	}

	// 导出到Excel表
	private int exportExcel(List<Book> excelDatas, String[] colnames, String fileName) {
		try {

			WritableWorkbook wwb = null;

			// 创建可写入的Excel工作簿
			String fileName1 = "C:\\Users\\22053\\Desktop\\" + fileName + ".xls";
			File file = new File(fileName1);
			if (!file.exists()) {
				file.createNewFile();
			}
			// 以fileName为文件名来创建一个Workbook
			wwb = Workbook.createWorkbook(file);

			// 创建工作表
			WritableSheet ws = wwb.createSheet("Test Shee 1", 0);

//				((Object) ws).setAlignment(jxl.format.Alignment.CENTRE);// 单元格内容水平居中  
//				cell.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 单元格内容垂直居中  
			// 查询数据库中所有的数据
			List<Book> list = excelDatas;
			// 要插入到的Excel表格的行号，默认从0开始
			Label labelId = new Label(0, 0, colnames[0]);// 表示第
			Label labelName = new Label(1, 0, colnames[1]);
			Label labelSex = new Label(2, 0, colnames[2]);
			Label labelType = new Label(3, 0, colnames[3]);
			Label labelDept = new Label(4, 0, colnames[4]);
			Label labelPhone = new Label(5, 0, colnames[5]);
			Label labelEmail = new Label(6, 0, colnames[6]);
			Label labelStatus = new Label(7, 0, colnames[7]);
			Label labelBorrowQty = new Label(8, 0, colnames[8]);
			Label labelDateAct = new Label(9, 0, colnames[9]);
			Label labelPrice = new Label(10, 0, colnames[10]);
			Label labelDateIn = new Label(11, 0, colnames[11]);
			Label labelBkStatus = new Label(12, 0, colnames[12]);

			ws.addCell(labelId);
			ws.addCell(labelName);
			ws.addCell(labelSex);
			ws.addCell(labelType);
			ws.addCell(labelDept);
			ws.addCell(labelPhone);
			ws.addCell(labelEmail);
			ws.addCell(labelStatus);
			ws.addCell(labelBorrowQty);
			ws.addCell(labelDateAct);
			ws.addCell(labelPrice);
			ws.addCell(labelDateIn);
			ws.addCell(labelBkStatus);

			for (int i = 0; i < list.size(); i++) {
				Label labelId_i = new Label(0, i + 1, String.valueOf(list.get(i).getBkID()));
				Label labelName_i = new Label(1, i + 1, list.get(i).getBkCode());
				Label labelSex_i = new Label(2, i + 1, list.get(i).getBkName());
				Label labelType_i = new Label(3, i + 1, list.get(i).getBkAuthor());
				Label labelDept_i = new Label(4, i + 1, list.get(i).getBkPress());
				Label labelPhone_i = new Label(5, i + 1, list.get(i).getBkDatePress().toString());
				Label labelEmail_i = new Label(6, i + 1, list.get(i).getBkISBN());
				Label labelStatus_i = new Label(7, i + 1, list.get(i).getBkCatalog());
				Label labelBorrowQty_i = new Label(8, i + 1, list.get(i).getBkLanguage());
				Label labelDateAct_i = new Label(9, i + 1, String.valueOf(list.get(i).getBkPages()));
				Label labelPrice_i = new Label(10, i + 1, String.valueOf(list.get(i).getBkPrice()));
				Label labelDateIn_i = new Label(11, i + 1, String.valueOf(list.get(i).getBkDateIn()));
				Label labelBkStatus_i = new Label(12, i + 1, String.valueOf(list.get(i).getBkStatus()));
				ws.addCell(labelId_i);
				ws.addCell(labelName_i);
				ws.addCell(labelSex_i);
				ws.addCell(labelType_i);
				ws.addCell(labelDept_i);
				ws.addCell(labelPhone_i);
				ws.addCell(labelEmail_i);
				ws.addCell(labelStatus_i);
				ws.addCell(labelBorrowQty_i);
				ws.addCell(labelDateAct_i);
				ws.addCell(labelPrice_i);
				ws.addCell(labelDateIn_i);
				ws.addCell(labelBkStatus_i);

			}

			// 写进文档
			wwb.write();
			// 关闭Excel工作簿对象
			System.out.println("数据导出成功!");
			wwb.close();
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	private void addActionListeners() {
		btnExcel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String fileName = JOptionPane.showInputDialog(null, "请输入文件名：", "导出excel", JOptionPane.OK_CANCEL_OPTION);
				if (fileName == null) {
					return;
				}
				if (fileName.equals("")) {
					JOptionPane.showMessageDialog(null, "文件名不能为空!", "提示信息", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String[] colnames = new String[] { "ID", "索书号", "书名", "作者", "出版社", "出版日期", "ISBN", "分类号", "语种", "页数",
						"价格", "入馆时间", "状态" };
				Book[] books = bookBll.getAllBooks();
				List<Book> excelDatas = new ArrayList<Book>();
				for (Book book : books) {
					excelDatas.add(book);
				}
				if (exportExcel(excelDatas, colnames, fileName) == 1) {
					JOptionPane.showMessageDialog(null, "导出excel成功!");
				} else {
					JOptionPane.showMessageDialog(null, "导出excel失败!");
				}
			}
		});

		btnSimpleQuery.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (indexWordComboBox.getSelectedItem().toString().equals("书名")) {
					updateResultTable(bookBll.getBookInfosBybkName(tfQueryContent.getText().trim()));
				} else if (indexWordComboBox.getSelectedItem().toString().equals("作者名")) {

					updateResultTable(bookBll.getBookInfosBybkAuthor(tfQueryContent.getText().trim()));
				} else {
					updateResultTable(bookBll.getBookInfosBybkPresses(tfQueryContent.getText().trim()));
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
					int bookId = Integer.valueOf(searchResultTable.getValueAt(selectedRow, 0).toString());
					if (bookBll.deleteBook(bookBll.getBook(bookId)) <= 0) {
						JOptionPane.showMessageDialog(null, "删除失败");
					} else {
						JOptionPane.showMessageDialog(null, "删除成功");
						updateResultTable(bookBll.getAllBooks());
					}
				} else if (response == 1) {
					return;
				}

			}
		});

		btnCplxQuery.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Book book = null;
				try {
					book = getBookFromComText();
				} catch (IOException e) {
					e.printStackTrace();
				}
				updateResultTable(bookBll.getBooksBy(book));
			}
		});
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = searchResultTable.getSelectedRow();
				if (selectedRow < 0) {
					JOptionPane.showMessageDialog(null, "请先选中一条记录", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				int bkID = Integer.valueOf(searchResultTable.getValueAt(selectedRow, 0).toString());
				Book book = bookBll.getBook(bkID);
				BookPanel bookPanel = new BookPanel();
				bookPanel.setVisible(true);
				bookPanel.setBookInfoToText(book);

			}
		});
	}

	private void updateResultTable(Book[] books) {
		if (books == null) {
			JOptionPane.showMessageDialog(null, "没有找到符合要求的记录!");
			return;
		}
		@SuppressWarnings("unchecked")
		CustomizedTableModel<Book> tableModel = (CustomizedTableModel<Book>) searchResultTable.getModel();
		tableModel.setRecords(books);
		// 更新表格
		tableModel.fireTableDataChanged();
	}

	private Book getBookFromComText() throws IOException {
		Book book = new Book();
		book.setBkName(tfBkName.getText().trim());
		book.setBkAuthor(tfBkAuthor.getText().trim());
		book.setBkBrief(tfBkDescription.getText().trim());
		book.setBkPress(tfBkPress.getText().trim());
		book.setBkCatalog(tfBkCatalog.getText().trim());
		DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		Date datePress = null;
		try {
			datePress = format.parse(tfBkPressYear.getText().trim());
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("转换出错");
		}
		// java的date不能直接插入到SQL server
		@SuppressWarnings("deprecation")
		java.sql.Date sql_datePress = new java.sql.Date(datePress.getDate()); // 转换成java.sql.Date
		System.out.println(sql_datePress);

		book.setBkDatePress(sql_datePress);

		return book;
	}
}
