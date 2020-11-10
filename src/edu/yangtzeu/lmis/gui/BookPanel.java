package edu.yangtzeu.lmis.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import edu.yangtzeu.lmis.bll.BookAdmin;
import edu.yangtzeu.lmis.gui.commons.ImageFilter;
import edu.yangtzeu.lmis.model.Book;

public class BookPanel extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BookAdmin bookBll = new BookAdmin();

	// 初始化各个文本框
	private JTextField tfBkId;
	private JTextField tfBkCode;
	private JTextField tfBkName;
	private JTextField tfBkAuthor;
	private JTextField tfBkPress;
	private JTextField tfBkDatePress;
	private JTextField tfBkISBN;
	private JTextField tfBkPages;
	private JTextField tfBkCatalog;
	private JTextField tfBkPrice;
	private JTextField tfBkDateIn;
	private JTextField tfBkQty;
	private JTextPane tpBkBrief;

	private JPanel bkInfoPanel;
	private JPanel bkContentPanel;
	private JPanel bkCoverPanel;
	private JPanel bkFunctionPanel;

	private JLabel lblBkPhoto;
	@SuppressWarnings("rawtypes")
	private JComboBox bkLanguageComboBox;

	private JButton btnBkPhotoAdd;
	private JButton btnAddBk;
	private JButton btnReturnBk;
	private JButton btnUpdateBk;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initBkInfoPanel() {
		bkInfoPanel = new JPanel();
		bkInfoPanel.setBorder(new LineBorder(Color.GRAY));
		bkInfoPanel.setBounds(10, 45, 237, 403);
		getContentPane().add(bkInfoPanel);
		bkInfoPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("图书起始序号：");
		lblNewLabel.setBounds(10, 10, 91, 15);
		bkInfoPanel.add(lblNewLabel);

		tfBkId = new JTextField();
		tfBkId.setEnabled(false);
		tfBkId.setBounds(103, 8, 98, 21);
		bkInfoPanel.add(tfBkId);
		tfBkId.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("索书号：");
		lblNewLabel_1.setBounds(10, 39, 54, 15);
		bkInfoPanel.add(lblNewLabel_1);

		tfBkCode = new JTextField();
		tfBkCode.setBounds(75, 37, 150, 21);
		bkInfoPanel.add(tfBkCode);
		tfBkCode.setColumns(10);

		JLabel label = new JLabel("图书名称：");
		label.setBounds(10, 64, 71, 15);
		bkInfoPanel.add(label);

		tfBkName = new JTextField();
		tfBkName.setBounds(75, 62, 150, 21);
		bkInfoPanel.add(tfBkName);
		tfBkName.setColumns(10);

		JLabel label_1 = new JLabel("图书作者：");
		label_1.setBounds(11, 93, 70, 15);
		bkInfoPanel.add(label_1);

		tfBkAuthor = new JTextField();
		tfBkAuthor.setBounds(75, 90, 150, 21);
		bkInfoPanel.add(tfBkAuthor);
		tfBkAuthor.setColumns(10);

		JLabel label_2 = new JLabel("出版社：");
		label_2.setBounds(10, 122, 54, 15);
		bkInfoPanel.add(label_2);

		tfBkPress = new JTextField();
		tfBkPress.setBounds(75, 120, 150, 21);
		bkInfoPanel.add(tfBkPress);
		tfBkPress.setColumns(10);

		JLabel label_3 = new JLabel("出版日期：");
		label_3.setBounds(10, 154, 71, 15);
		bkInfoPanel.add(label_3);

		tfBkDatePress = new JTextField();
		tfBkDatePress.setBounds(75, 150, 150, 21);
		bkInfoPanel.add(tfBkDatePress);
		tfBkDatePress.setColumns(10);

		JLabel lblIsbn = new JLabel("ISBN：");
		lblIsbn.setBounds(10, 180, 54, 15);
		bkInfoPanel.add(lblIsbn);

		tfBkISBN = new JTextField();
		tfBkISBN.setBounds(75, 176, 150, 21);
		bkInfoPanel.add(tfBkISBN);
		tfBkISBN.setColumns(10);

		JLabel label_4 = new JLabel("分类名：");
		label_4.setBounds(10, 205, 54, 15);
		bkInfoPanel.add(label_4);

		tfBkPages = new JTextField();
		tfBkPages.setBounds(75, 289, 150, 21);
		bkInfoPanel.add(tfBkPages);
		tfBkPages.setColumns(10);

		JLabel label_5 = new JLabel("分类号：");
		label_5.setBounds(10, 233, 54, 15);
		bkInfoPanel.add(label_5);

		tfBkCatalog = new JTextField();
		tfBkCatalog.setBounds(75, 230, 150, 21);
		bkInfoPanel.add(tfBkCatalog);
		tfBkCatalog.setColumns(10);

		String[] cataNames = new String[] { "TP-自动化技术", "TP3-计算技术", "TP31-计算机软件", "TP311-软件工程", "TP312-算法语言",
				"TP311.13-数据库理论与系统" };
		JComboBox bkCataNameComboBox = new JComboBox(cataNames);
		bkCataNameComboBox.setBounds(75, 201, 150, 23);
		bkInfoPanel.add(bkCataNameComboBox);

		JLabel label_6 = new JLabel("语种：");
		label_6.setBounds(10, 263, 54, 15);
		bkInfoPanel.add(label_6);

		String[] languages = new String[] { "中文", "英文", "日文", "德文", "俄文", "法文" };
		// @SuppressWarnings({ "rawtypes", "unchecked" })
		bkLanguageComboBox = new JComboBox(languages);
		bkLanguageComboBox.setBounds(75, 257, 150, 23);
		bkInfoPanel.add(bkLanguageComboBox);

		JLabel label_7 = new JLabel("图书页数：");
		label_7.setBounds(10, 293, 71, 15);
		bkInfoPanel.add(label_7);

		JLabel label_8 = new JLabel("图书价格：");
		label_8.setBounds(10, 323, 71, 15);
		bkInfoPanel.add(label_8);

		tfBkPrice = new JTextField();
		tfBkPrice.setBounds(75, 319, 150, 21);
		bkInfoPanel.add(tfBkPrice);
		tfBkPrice.setColumns(10);

		JLabel label_9 = new JLabel("入馆日期：");
		label_9.setBounds(10, 350, 71, 15);
		bkInfoPanel.add(label_9);

		tfBkDateIn = new JTextField();
		tfBkDateIn.setBounds(75, 347, 150, 21);
		bkInfoPanel.add(tfBkDateIn);
		tfBkDateIn.setColumns(10);

		JLabel label_10 = new JLabel("图书本数：");
		label_10.setBounds(10, 378, 71, 15);
		bkInfoPanel.add(label_10);

		tfBkQty = new JTextField();
		tfBkQty.setBounds(75, 375, 150, 21);
		bkInfoPanel.add(tfBkQty);
		tfBkQty.setColumns(10);
	}

	private void initBkContentPanel() {
		bkContentPanel = new JPanel();
		bkContentPanel.setBorder(new LineBorder(Color.GRAY));
		bkContentPanel.setBounds(253, 45, 275, 403);
		getContentPane().add(bkContentPanel);
		bkContentPanel.setLayout(null);

		JLabel label_11 = new JLabel("内容简介：");
		label_11.setBounds(10, 10, 71, 15);
		bkContentPanel.add(label_11);

		tpBkBrief = new JTextPane();
		tpBkBrief.setBounds(9, 32, 258, 362);
		bkContentPanel.add(tpBkBrief);
	}

	private void initBkCoverPanel() {
		bkCoverPanel = new JPanel();
		bkCoverPanel.setBorder(new LineBorder(Color.GRAY));
		bkCoverPanel.setBounds(538, 45, 260, 403);
		getContentPane().add(bkCoverPanel);
		bkCoverPanel.setLayout(null);

		JLabel label_12 = new JLabel("封面：");
		label_12.setBounds(10, 10, 54, 15);
		bkCoverPanel.add(label_12);

		lblBkPhoto = new JLabel("");
		lblBkPhoto.setBorder(new LineBorder(Color.GRAY));
		lblBkPhoto.setBounds(10, 36, 240, 315);
		bkCoverPanel.add(lblBkPhoto);

		btnBkPhotoAdd = new JButton("添加图片");
		btnBkPhotoAdd.setBounds(85, 365, 93, 23);
		bkCoverPanel.add(btnBkPhotoAdd);
	}

	private void initBkFunctionPanel() {
		bkFunctionPanel = new JPanel();
		bkFunctionPanel.setBounds(10, 458, 788, 61);
		getContentPane().add(bkFunctionPanel);
		bkFunctionPanel.setLayout(null);

		btnAddBk = new JButton("添加");
		btnAddBk.setBounds(202, 10, 93, 23);
		bkFunctionPanel.add(btnAddBk);

		btnUpdateBk = new JButton("保存修改");
		btnUpdateBk.setBounds(324, 10, 93, 23);
		bkFunctionPanel.add(btnUpdateBk);

		btnReturnBk = new JButton("返回");
		btnReturnBk.setBounds(445, 10, 93, 23);
		bkFunctionPanel.add(btnReturnBk);
	}

	private void addActionListeners() {
		btnBkPhotoAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fChooser = new JFileChooser();
				fChooser.addChoosableFileFilter(new ImageFilter());
				int returnVal = fChooser.showOpenDialog(BookPanel.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fChooser.getSelectedFile();
					try {
						BufferedImage image = ImageIO.read(file);
						// scal image to fill the label
						Image dimg = image.getScaledInstance(lblBkPhoto.getWidth(), lblBkPhoto.getHeight(),
								Image.SCALE_SMOOTH);
						ImageIcon icon = new ImageIcon(dimg);
						lblBkPhoto.setIcon(icon);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

		btnAddBk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Book book = null;
				try {
					book = getBookInfoAddText();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (bookBll.addBook(book) <= 0) {
					JOptionPane.showMessageDialog(null, "添加失败!");
				} else {
					JOptionPane.showMessageDialog(null, "添加成功!");
					setTextToBlank();
				}
			}
		});

		btnReturnBk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				closeGUI();
			}
		});

		btnUpdateBk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Book book = null;
				try {
					book = getBookInfoFromText();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (bookBll.updateBook(book) <= 0) {
					JOptionPane.showMessageDialog(null, "修改失败!");
				} else {
					JOptionPane.showMessageDialog(null, "修改成功!");
					setTextToBlank();
				}
			}
		});
	}

	public void setBookInfoToText(Book book) {
		tfBkId.setText(String.valueOf(book.getBkID()));
		tfBkCode.setText(book.getBkCode());
		tfBkName.setText(book.getBkName());
		tfBkAuthor.setText(book.getBkAuthor());
		tfBkPress.setText(book.getBkPress());
		tfBkDatePress.setText(book.getBkDatePress().toString());
		tfBkISBN.setText(book.getBkISBN());
		tfBkCatalog.setText(book.getBkCatalog());
		tfBkPages.setText(String.valueOf(book.getBkPages()));
		tfBkPrice.setText(String.valueOf(book.getBkPrice()));
		tfBkDateIn.setText(book.getBkDateIn().toString());

		bkLanguageComboBox.setSelectedItem(book.getBkLanguage());
		// tfBkQty.setText(String.valueOf(book.getbk));
		tpBkBrief.setText(book.getBkBrief());

		if (book.getBkCover() != null) {
			// byte[]转Image
			ByteArrayInputStream bais = new ByteArrayInputStream(book.getBkCover());
			BufferedImage bImage = null;
			try {
				bImage = ImageIO.read(bais);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Image image = bImage;
			lblBkPhoto.setIcon(new ImageIcon(image));
		}
	}

	private Book getBookInfoAddText() throws IOException {
		Book book = new Book();
		// book.setBkID(bkID);
		book.setBkCode(tfBkCode.getText());
		book.setBkName(tfBkName.getText().trim());
		book.setBkAuthor(tfBkAuthor.getText().trim());
		book.setBkPress(tfBkPress.getText().trim());

		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		Date date = null;
		try {
			date = format.parse(tfBkDatePress.getText());
		} catch (ParseException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "时间格式有问题或没有输入", "", JOptionPane.WARNING_MESSAGE);
		}
		java.sql.Date sql_data = new java.sql.Date(date.getTime());
		book.setBkDatePress(sql_data);

		book.setBkISBN(tfBkISBN.getText());
		book.setBkCatalog(tfBkCatalog.getText());
		book.setBkLanguage(bkLanguageComboBox.getSelectedItem().toString());
		book.setBkPages(Integer.valueOf(tfBkPages.getText()));
		book.setBkPrice(Float.valueOf(tfBkPrice.getText()));
		book.setBkBrief(tpBkBrief.getText());

		if (lblBkPhoto.getIcon() != null) {
			Image image = ((ImageIcon) lblBkPhoto.getIcon()).getImage();
			BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null),
					BufferedImage.TYPE_4BYTE_ABGR);
			Graphics2D g2 = bi.createGraphics();
			g2.drawImage(image, 0, 0, null);
			g2.dispose();
			ByteArrayOutputStream oStream = new ByteArrayOutputStream();
			try {
				ImageIO.write(bi, "png", oStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 自己的思考，直接将其转化为byte[]
//			reader.setRdPhoto(oStream.toByteArray());
			// 通过转换
			InputStream fis = new ByteArrayInputStream(oStream.toByteArray());
			book.setBkCover(fis.readAllBytes());
		}

		return book;
	}

	private Book getBookInfoFromText() throws IOException {
		Book book = new Book(Integer.valueOf(tfBkId.getText()));
		// book.setBkID(bkID);
		book.setBkCode(tfBkCode.getText());
		book.setBkName(tfBkName.getText().trim());
		book.setBkAuthor(tfBkAuthor.getText().trim());
		book.setBkPress(tfBkPress.getText().trim());

		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		Date date = null;
		try {
			date = format.parse(tfBkDatePress.getText());
		} catch (ParseException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "时间格式有问题或没有输入", "", JOptionPane.WARNING_MESSAGE);
		}
		java.sql.Date sql_data = new java.sql.Date(date.getTime());
		book.setBkDatePress(sql_data);

		book.setBkISBN(tfBkISBN.getText());
		book.setBkCatalog(tfBkCatalog.getText());
		book.setBkLanguage(bkLanguageComboBox.getSelectedItem().toString());
		book.setBkPages(Integer.valueOf(tfBkPages.getText()));
		book.setBkPrice(Float.valueOf(tfBkPrice.getText()));
		book.setBkBrief(tpBkBrief.getText());

		if (lblBkPhoto.getIcon() != null) {
			Image image = ((ImageIcon) lblBkPhoto.getIcon()).getImage();
			BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null),
					BufferedImage.TYPE_4BYTE_ABGR);
			Graphics2D g2 = bi.createGraphics();
			g2.drawImage(image, 0, 0, null);
			g2.dispose();
			ByteArrayOutputStream oStream = new ByteArrayOutputStream();
			try {
				ImageIO.write(bi, "png", oStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 自己的思考，直接将其转化为byte[]
//			reader.setRdPhoto(oStream.toByteArray());
			// 通过转换
			InputStream fis = new ByteArrayInputStream(oStream.toByteArray());
			book.setBkCover(fis.readAllBytes());
		}

		return book;
	}

	private void setTextToBlank() {
		JTextField[] fields = new JTextField[] { tfBkId, tfBkCode, tfBkName, tfBkAuthor, tfBkPress, tfBkDatePress,
				tfBkISBN, tfBkCatalog, tfBkPages, tfBkPrice, tfBkDateIn };
		for (int i = 0; i < fields.length; i++) {
			fields[i].setText("");
		}
		tpBkBrief.setText("");
		lblBkPhoto.setIcon(null);
	}

	private void closeGUI() {
		this.dispose();
	}

	public BookPanel() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("images//icon.png"));
		setSize(new Dimension(820, 580));
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("新书入馆");
		getContentPane().setLayout(null);

		initBkInfoPanel();
		initBkContentPanel();
		initBkCoverPanel();
		initBkFunctionPanel();
		// 事件监听
		addActionListeners();
	}
}
