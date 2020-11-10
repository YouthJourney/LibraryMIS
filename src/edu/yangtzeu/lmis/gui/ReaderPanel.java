package edu.yangtzeu.lmis.gui;

//import java.awt.CardLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
//import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import edu.yangtzeu.lmis.bll.DepartmentTypeAdmin;
import edu.yangtzeu.lmis.bll.ReaderAdmin;
import edu.yangtzeu.lmis.bll.ReaderTypeAdmin;
import edu.yangtzeu.lmis.gui.commons.CustomizedTableModel;
import edu.yangtzeu.lmis.gui.commons.ImageFilter;
import edu.yangtzeu.lmis.model.DepartmentType;
import edu.yangtzeu.lmis.model.Reader;
import edu.yangtzeu.lmis.model.ReaderType;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ReaderPanel extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private enum OpStatus { // 枚举出3种窗口操作状态
        inSelect, inNew, inChange, inClose
    }

    ;

    private OpStatus ops;
    private ReaderTypeAdmin readerTypeBll = new ReaderTypeAdmin();
    private DepartmentTypeAdmin deptTypeBll = new DepartmentTypeAdmin();
    private ReaderAdmin readerBll = new ReaderAdmin();
    CustomizedTableModel<Reader> tableModel = new CustomizedTableModel<Reader>(readerBll.getDisplayColumnNames(),
            readerBll.getMethodNames());

    private JTextField rdNameTextField;
    private JTable searchResultTable;
    private JTextField borrowTypeNumTxfd;
    private JTextField nameTxfd;
    private JPasswordField passwordTxfd;
    private JTextField borrowedTxfd;
    private JTextField statusTxfd;
    private JTextField rdRoleTxfd;
    private JTextField phoneTxfd;
    private JTextField emailTxfd;
    private JTextField dateTxfd;

    private JPanel searchPanel;
    private JScrollPane searchResultPanel;
    private JPanel readerInfoPanel;
    private JPanel functionCtrlPanel;
    private JPanel editCtrlPanel;

    private JButton btnAddReader;
    private JButton btnSubmitUpdate;
    private JButton btnNewReader;
    private JButton btnUpdateReader;
    private JButton btnCancelEdit;
    private JButton btnLoadPictureFile;
    private JButton btnQuery;
    private JButton btnClose;

    @SuppressWarnings("rawtypes")
    private JComboBox rdTypeComboBox;
    @SuppressWarnings("rawtypes")
    private JComboBox deptTypeComboBox;
    @SuppressWarnings("rawtypes")
    private JComboBox sexComboBox;
    @SuppressWarnings("rawtypes")
    private JComboBox rdTypeNumComboBox;
    @SuppressWarnings("rawtypes")
    private JComboBox rdDeptComboBox;

    private JLabel lblPhoto;

    private void initEditCtrlPanel() {
        editCtrlPanel = new JPanel();
        editCtrlPanel.setBounds(505, 484, 295, 79);
        add(editCtrlPanel);
        editCtrlPanel.setLayout(null);

        btnAddReader = new JButton("确认办证");
        btnAddReader.setBounds(10, 0, 93, 23);
        editCtrlPanel.add(btnAddReader);

        btnSubmitUpdate = new JButton("确认变更");
        btnSubmitUpdate.setBounds(113, 0, 93, 23);
        editCtrlPanel.add(btnSubmitUpdate);

        btnCancelEdit = new JButton("取消");
        btnCancelEdit.setBounds(216, 0, 69, 23);
        editCtrlPanel.add(btnCancelEdit);
    }

    private void initFunctionCtrlPanel() {
        functionCtrlPanel = new JPanel();
        functionCtrlPanel.setBounds(10, 484, 485, 79);
        add(functionCtrlPanel);
        functionCtrlPanel.setLayout(null);

        btnNewReader = new JButton("办理借书证");
        btnNewReader.setBounds(10, 0, 103, 23);
        functionCtrlPanel.add(btnNewReader);

        btnUpdateReader = new JButton("变更借书证");
        btnUpdateReader.setBounds(137, 0, 105, 23);
        functionCtrlPanel.add(btnUpdateReader);

        JButton btnLost = new JButton("挂失");
        btnLost.setBounds(267, 0, 93, 23);
        functionCtrlPanel.add(btnLost);
        btnLost.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                int selectedRow = searchResultTable.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "请选中一条读者记录!", "", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int rdId = Integer.valueOf(searchResultTable.getValueAt(selectedRow, 0).toString());
                Reader reader = readerBll.getReader(rdId);
                if (reader.getRdStatus().equals("挂失")) {
                    JOptionPane.showMessageDialog(null, "该读者已经处于挂失状态!", "", JOptionPane.WARNING_MESSAGE);
                    searchResultTable.getSelectionModel().clearSelection();
                    return;
                }
                reader.setRdStatus("挂失");
                if (readerBll.updateReaderStatus(reader) > 0) {
                    JOptionPane.showMessageDialog(null, "挂失成功!");
                } else {
                    JOptionPane.showMessageDialog(null, "挂失失败!");
                }
                searchResultTable.getSelectionModel().clearSelection();
            }
        });

        JButton btnFound = new JButton("解出挂失");
        btnFound.setBounds(382, 0, 93, 23);
        functionCtrlPanel.add(btnFound);
        btnFound.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                int selectedRow = searchResultTable.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "请选中一条读者记录!", "", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int rdId = Integer.valueOf(searchResultTable.getValueAt(selectedRow, 0).toString());
                Reader reader = readerBll.getReader(rdId);
                if (reader.getRdStatus().equals("有效")) {
                    JOptionPane.showMessageDialog(null, "该读者已是有效状态!", "", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                reader.setRdStatus("有效");
                if (readerBll.updateReaderStatus(reader) > 0) {
                    JOptionPane.showMessageDialog(null, "解挂成功!");
                } else {
                    JOptionPane.showMessageDialog(null, "解挂失败!");
                }
                searchResultTable.getSelectionModel().clearSelection();
            }
        });

        JButton btnCancelReader = new JButton("注销");
        btnCancelReader.setBounds(79, 46, 93, 23);
        functionCtrlPanel.add(btnCancelReader);
        btnCancelReader.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                int selectedRow = searchResultTable.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "请选中一条读者记录!", "", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Object[] options = {"确定", "取消"};
                int response = JOptionPane.showOptionDialog(null, "确定要撤销吗?", "撤销", JOptionPane.YES_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (response == 0) {
                    int rdId = Integer.valueOf(searchResultTable.getValueAt(selectedRow, 0).toString());
                    Reader reader = readerBll.getReader(rdId);
                    if (readerBll.deleteReader(reader) > 0) {
                        JOptionPane.showMessageDialog(null, "撤销成功!");
                        updateResultTable(readerBll.getAllReaders());
                    } else {
                        JOptionPane.showMessageDialog(null, "撤销失败!");
                    }
                } else if (response == 1) {
                    return;
                }
                searchResultTable.getSelectionModel().clearSelection();
            }
        });

        btnClose = new JButton("退出");
        btnClose.setBounds(319, 46, 93, 23);
        functionCtrlPanel.add(btnClose);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void initReaderInfoPanel() {
        readerInfoPanel = new JPanel();
        readerInfoPanel.setBorder(
                new TitledBorder(null, "\u8BFB\u8005\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        readerInfoPanel.setBounds(505, 70, 295, 404);
        add(readerInfoPanel);
        readerInfoPanel.setLayout(null);

        JLabel label_1 = new JLabel("借书证号");
        label_1.setBounds(10, 22, 54, 15);
        readerInfoPanel.add(label_1);

        JLabel lblNewLabel_2 = new JLabel("姓    名");
        lblNewLabel_2.setBounds(10, 53, 54, 15);
        readerInfoPanel.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("密    码");
        lblNewLabel_3.setBounds(10, 86, 54, 15);
        readerInfoPanel.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("性    别");
        lblNewLabel_4.setBounds(10, 118, 54, 15);
        readerInfoPanel.add(lblNewLabel_4);

        JLabel label_2 = new JLabel("已 借 书");
        label_2.setBounds(10, 150, 54, 15);
        readerInfoPanel.add(label_2);

        JLabel label_3 = new JLabel("证件状态");
        label_3.setBounds(10, 181, 54, 15);
        readerInfoPanel.add(label_3);

        JLabel lblNewLabel_5 = new JLabel("读者角色");
        lblNewLabel_5.setBounds(10, 212, 54, 15);
        readerInfoPanel.add(lblNewLabel_5);

        JLabel label_4 = new JLabel("读者类别");
        label_4.setBounds(10, 246, 54, 15);
        readerInfoPanel.add(label_4);

        JLabel label_5 = new JLabel("单    位");
        label_5.setBounds(10, 279, 54, 15);
        readerInfoPanel.add(label_5);

        JLabel label_6 = new JLabel("电话号码");
        label_6.setBounds(10, 311, 54, 15);
        readerInfoPanel.add(label_6);

        JLabel label_7 = new JLabel("电子邮件");
        label_7.setBounds(10, 342, 54, 15);
        readerInfoPanel.add(label_7);

        JLabel label_8 = new JLabel("办证日期");
        label_8.setBounds(10, 373, 54, 15);
        readerInfoPanel.add(label_8);

        borrowTypeNumTxfd = new JTextField();
        borrowTypeNumTxfd.setEditable(false);
        borrowTypeNumTxfd.setBounds(74, 19, 66, 21);
        readerInfoPanel.add(borrowTypeNumTxfd);
        borrowTypeNumTxfd.setColumns(10);

        nameTxfd = new JTextField();
        nameTxfd.setBounds(74, 50, 66, 21);
        readerInfoPanel.add(nameTxfd);
        nameTxfd.setColumns(10);

        passwordTxfd = new JPasswordField();
        passwordTxfd.setBounds(74, 83, 66, 21);
        readerInfoPanel.add(passwordTxfd);
        passwordTxfd.setColumns(10);

        String[] sexs = new String[]{"男", "女"};
        sexComboBox = new JComboBox(sexs);
        sexComboBox.setBounds(74, 114, 66, 23);
        readerInfoPanel.add(sexComboBox);

        borrowedTxfd = new JTextField();
        borrowedTxfd.setEditable(false);
        borrowedTxfd.setBounds(74, 147, 66, 21);
        readerInfoPanel.add(borrowedTxfd);
        borrowedTxfd.setColumns(10);

        statusTxfd = new JTextField();
        statusTxfd.setEditable(false);
        statusTxfd.setBounds(74, 178, 66, 21);
        readerInfoPanel.add(statusTxfd);
        statusTxfd.setColumns(10);

        rdRoleTxfd = new JTextField();
        rdRoleTxfd.setEditable(false);
        rdRoleTxfd.setBounds(74, 209, 66, 21);
        readerInfoPanel.add(rdRoleTxfd);
        rdRoleTxfd.setColumns(10);

        rdTypeNumComboBox = new JComboBox(readerTypeBll.getReaderTypeNames());
        rdTypeNumComboBox.setBounds(74, 242, 66, 23);
        readerInfoPanel.add(rdTypeNumComboBox);

        rdDeptComboBox = new JComboBox(deptTypeBll.getDeptTypeNames());
        rdDeptComboBox.setBounds(74, 275, 125, 23);
        readerInfoPanel.add(rdDeptComboBox);

        phoneTxfd = new JTextField();
        phoneTxfd.setBounds(74, 308, 125, 21);
        readerInfoPanel.add(phoneTxfd);
        phoneTxfd.setColumns(10);

        emailTxfd = new JTextField();
        emailTxfd.setBounds(74, 339, 125, 21);
        readerInfoPanel.add(emailTxfd);
        emailTxfd.setColumns(10);

        dateTxfd = new JTextField();
        dateTxfd.setEditable(false);
        dateTxfd.setBounds(74, 370, 125, 21);
        readerInfoPanel.add(dateTxfd);
        dateTxfd.setColumns(10);

        lblPhoto = new JLabel("");
        lblPhoto.setBorder(new LineBorder(Color.GRAY));
        lblPhoto.setBounds(165, 33, 120, 148);
        readerInfoPanel.add(lblPhoto);

        btnLoadPictureFile = new JButton("图片文件");
        btnLoadPictureFile.setBounds(180, 191, 93, 23);
        readerInfoPanel.add(btnLoadPictureFile);
    }

    private void initSearchResultPanel() {
        searchResultPanel = new JScrollPane(searchResultTable);
        searchResultPanel.setBorder(
                new TitledBorder(null, "\u67E5\u8BE2\u7ED3\u679C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        searchResultPanel.setBounds(10, 70, 485, 404);
        add(searchResultPanel);
//		searchResultPanel.setLayout(null);

        searchResultTable = new JTable(tableModel);
//		searchResultTable.setBounds(159, 69, 1, 1);
//		searchResultPanel.add(searchResultTable);
        searchResultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        searchResultPanel.setViewportView(searchResultTable);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void initSearchPanel() {
        searchPanel = new JPanel();
        searchPanel.setBounds(10, 24, 780, 36);
        add(searchPanel);
        searchPanel.setLayout(null);

        JLabel lblNewLabel = new JLabel("读者类别：");
        lblNewLabel.setBounds(10, 10, 68, 15);
        searchPanel.add(lblNewLabel);

        // rdTypeComboBox = new JComboBox<ReaderType>(readerTypeBll.getReaderTypes());
        rdTypeComboBox = new JComboBox(readerTypeBll.getReaderTypeNames());
        rdTypeComboBox.setBounds(82, 6, 98, 23);
        searchPanel.add(rdTypeComboBox);

        JLabel lblNewLabel_1 = new JLabel("单位：");
        lblNewLabel_1.setBounds(190, 10, 41, 15);
        searchPanel.add(lblNewLabel_1);

        // deptTypeComboBox = new
        // JComboBox<DepartmentType>(deptTypeBll.getDepartmentTypes());
        deptTypeComboBox = new JComboBox(deptTypeBll.getDeptTypeNames());
        deptTypeComboBox.setBounds(233, 6, 119, 23);
        searchPanel.add(deptTypeComboBox);

        JLabel label = new JLabel("姓名：");
        label.setBounds(362, 10, 40, 15);
        searchPanel.add(label);

        rdNameTextField = new JTextField();
        rdNameTextField.setBounds(405, 7, 78, 21);
        searchPanel.add(rdNameTextField);
        rdNameTextField.setColumns(10);

        btnQuery = new JButton("查找");
        btnQuery.setBounds(493, 6, 71, 23);
        searchPanel.add(btnQuery);

        JButton btnToExcel = new JButton("Excel");
        btnToExcel.setBounds(574, 6, 71, 23);
        searchPanel.add(btnToExcel);
        btnToExcel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
//				String input = JOptionPane.showInputDialog( "enter an input" );
                // Object[] options = {"确定","取消"};
                String fileName = JOptionPane.showInputDialog(null, "请输入文件名：", "导出excel", JOptionPane.OK_CANCEL_OPTION);
                if (fileName == null) {
                    return;
                }
                if (fileName.equals("")) {
                    JOptionPane.showMessageDialog(null, "文件名不能为空!", "提示信息", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String[] strings = new String[]{"ID", "姓名", "性别", "类型", "院系", "电话", "Email", "状态", "已借书数量", "注册日期"};
                Reader[] readers = readerBll.getAllReaders();
                List<Reader> readerDates = new ArrayList<Reader>();
                for (Reader reader : readers) {
                    readerDates.add(reader);
                }
                if (exportExcel(readerDates, strings, fileName) == 1) {
                    JOptionPane.showMessageDialog(null, "导出excel成功!");
                } else {
                    JOptionPane.showMessageDialog(null, "导出excel失败!");
                }
            }
        });
    }

    // 各个按钮的触发事件
    private void addActionListeners() {
        btnNewReader.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                borrowTypeNumTxfd.setEditable(true);
                setStatus(OpStatus.inNew);
            }
        });

        btnClose.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

//				CardLayout c1 = (CardLayout) Main.cards.getLayout();
//				c1.show(Main.cards, Main.displayPanelName);
            }
        });

        btnCancelEdit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                setStatus(OpStatus.inSelect);
                setTextToBlank();
            }
        });

        btnLoadPictureFile.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser fChooser = new JFileChooser();
                fChooser.addChoosableFileFilter(new ImageFilter());
                int returnVal = fChooser.showOpenDialog(ReaderPanel.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fChooser.getSelectedFile();
                    try {
                        BufferedImage image = ImageIO.read(file);
                        // scal image to fill the label
                        Image dimg = image.getScaledInstance(lblPhoto.getWidth(), lblPhoto.getHeight(),
                                Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(dimg);
                        lblPhoto.setIcon(icon);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        btnQuery.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ReaderType rdType = readerTypeBll.getrdTypeNamesBy(rdTypeComboBox.getSelectedItem().toString());
                DepartmentType deptType = deptTypeBll.getDeptTypeBy(deptTypeComboBox.getSelectedItem().toString());
                String userName = rdNameTextField.getText().trim();
                Reader[] hits = readerBll.retrieveReaders(rdType, deptType, userName);
                // 更新查询结果列表
                updateResultTable(hits);
            }
        });

        btnUpdateReader.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                int selectedRow = searchResultTable.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "请先选中一条记录!");
                    return;
                }
                int rdID = Integer.valueOf(searchResultTable.getValueAt(selectedRow, 0).toString());
                setReaderToText(readerBll.getReader(rdID));
                borrowTypeNumTxfd.setEditable(false);
                setStatus(OpStatus.inChange);
                searchResultTable.getSelectionModel().clearSelection();
            }
        });

        btnSubmitUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                Reader reader = null;
                try {
                    reader = getReaderFromText();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (readerBll.updateReader(reader) <= 0) {
                    JOptionPane.showMessageDialog(null, "更正失败");
                    return;
                } else {
                    JOptionPane.showMessageDialog(null, "更正成功");
                    updateResultTable(readerBll.getAllReaders());
                }

            }
        });

        btnAddReader.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                Reader reader = null;
                try {
                    reader = getReaderFromText();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (readerBll.addReader(reader) <= 0) {
                    JOptionPane.showMessageDialog(null, "办证失败");
                    return;
                } else {
                    JOptionPane.showMessageDialog(null, "办证成功");
                    updateResultTable(readerBll.getAllReaders());
                }
            }
        });
    }

    // 设置文本框的值
    private void setReaderToText(Reader reader) {
        borrowTypeNumTxfd.setText(String.valueOf(reader.getRdID()));
        nameTxfd.setText(String.valueOf(reader.getRdName()));
        passwordTxfd.setText(reader.getRdPwd());
        borrowedTxfd.setText(String.valueOf(reader.getRdBorrowQty()));
        statusTxfd.setText(reader.getRdStatus());
        rdRoleTxfd.setText(String.valueOf(reader.getRdAdminRoles()));
        phoneTxfd.setText(String.valueOf(reader.getRdPhone()));
        emailTxfd.setText(String.valueOf(reader.getRdEmail()));
        dateTxfd.setText(String.valueOf(reader.getRdDateReg()));
        sexComboBox.setSelectedItem(reader.getRdSex());
        rdTypeNumComboBox.setSelectedItem(readerTypeBll.getObjectByID(reader.getRdType()).getRdTypeName());
        rdDeptComboBox.setSelectedItem(deptTypeBll.getObjectById(Integer.valueOf(reader.getRdDept())).getDtName());
        if (reader.getRdPhoto() != null) {
            // byte[]转Image
            ByteArrayInputStream bais = new ByteArrayInputStream(reader.getRdPhoto());
            BufferedImage bImage = null;
            try {
                bImage = ImageIO.read(bais);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image image = bImage;
            lblPhoto.setIcon(new ImageIcon(image));
        }

    }

    // 获取文本框的值
    private Reader getReaderFromText() throws IOException {
        Reader reader = new Reader(Integer.valueOf(borrowTypeNumTxfd.getText()));
        reader.setRdName(nameTxfd.getText().trim());
        reader.setRdPwd(String.valueOf(passwordTxfd.getPassword()));
        reader.setRdSex(sexComboBox.getSelectedItem().toString());

        reader.setRdType(readerTypeBll.getrdTypeNamesBy(rdTypeNumComboBox.getSelectedItem().toString()).getRdType());
        reader.setRdDept(deptTypeBll.getDeptTypeBy(deptTypeComboBox.getSelectedItem().toString()).getDtType());
        reader.setRdPhone(phoneTxfd.getText().trim());
        reader.setRdEmail(emailTxfd.getText().trim());

        if (lblPhoto.getIcon() != null) {
            Image image = ((ImageIcon) lblPhoto.getIcon()).getImage();
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
            reader.setRdPhoto(fis.readAllBytes());
        }
        return reader;
    }

    private void setTextToBlank() {
        borrowTypeNumTxfd.setText("");
        nameTxfd.setText("");
        passwordTxfd.setText("");
        borrowedTxfd.setText("");
        phoneTxfd.setText("");
        emailTxfd.setText("");
        statusTxfd.setText("");
        rdRoleTxfd.setText("");
        dateTxfd.setText("");
        lblPhoto.setIcon(null);
    }

    /**
     * 将InputStream转换成byte数组
     *
     * @param in InputStream
     * @return byte[]
     * @throws IOException
     */
    public static byte[] InputStreamTOByte(InputStream in) throws IOException {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[4096];
        int count = -1;
        while ((count = in.read(data, 0, 4096)) != -1) {
            outStream.write(data, 0, count);
        }

        data = null;
        return outStream.toByteArray();
    }

    // 更新table
    private void updateResultTable(Reader[] readers) {
        if (readers == null) {
            JOptionPane.showMessageDialog(null, "没有找到符合要求的记录!");
            return;
        }
        @SuppressWarnings("unchecked")
        CustomizedTableModel<Reader> tableModel = (CustomizedTableModel<Reader>) searchResultTable.getModel();
        tableModel.setRecords(readers);
        // 更新表格
        tableModel.fireTableDataChanged();
    }

    // 设置几种面板显示状态
    private void setStatus(OpStatus opst) {
        ops = opst;
        switch (ops) {
            case inSelect:
                searchPanel.setEnabled(true);
//			searchPanel.setVisible(true);
//			searchResultPanel.setVisible(true);
//			functionCtrlPanel.setVisible(true);
                searchResultPanel.setEnabled(true);
                functionCtrlPanel.setEnabled(true);
                // 更改panel中组件状态
                setComponentStatusInPanel(functionCtrlPanel, true);
                readerInfoPanel.setEnabled(false);
                readerInfoPanel.setVisible(false);
                editCtrlPanel.setEnabled(false);
                editCtrlPanel.setVisible(false);
                setComponentStatusInPanel(editCtrlPanel, false);
                break;
            case inNew:
                searchPanel.setEnabled(false);
                searchResultPanel.setEnabled(false);
                functionCtrlPanel.setEnabled(false);
                // 更改panel中组件状态
                setComponentStatusInPanel(functionCtrlPanel, false);
                readerInfoPanel.setEnabled(true);
                readerInfoPanel.setVisible(true);
                editCtrlPanel.setEnabled(true);
                editCtrlPanel.setVisible(true);
                setComponentStatusInPanel(editCtrlPanel, true);
                btnSubmitUpdate.setEnabled(false);
                break;
            case inChange:
                searchPanel.setEnabled(false);
                searchResultPanel.setEnabled(false);
                functionCtrlPanel.setEnabled(false);
                // 更改panel中组件状态
                setComponentStatusInPanel(functionCtrlPanel, false);
                readerInfoPanel.setEnabled(true);
                readerInfoPanel.setVisible(true);
                editCtrlPanel.setEnabled(true);
                editCtrlPanel.setVisible(true);
                setComponentStatusInPanel(editCtrlPanel, true);
                btnAddReader.setEnabled(false);
                break;
            case inClose:
                searchPanel.setVisible(false);
                searchResultPanel.setVisible(false);
                functionCtrlPanel.setVisible(false);
                readerInfoPanel.setVisible(false);
                editCtrlPanel.setVisible(false);
                break;
        }
    }

    // 设置面板显示与否
    private void setComponentStatusInPanel(JPanel panel, boolean status) {
        for (Component comp : panel.getComponents()) {
            comp.setEnabled(status);
        }
    }

    // 导出数据到excel
    private int exportExcel(List<Reader> excelDatas, String[] colnames, String fileName) {
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

//			((Object) ws).setAlignment(jxl.format.Alignment.CENTRE);// 单元格内容水平居中  
//			cell.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 单元格内容垂直居中  
            // 查询数据库中所有的数据
            List<Reader> list = excelDatas;
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
//            Label labelPhone= new Label(6,0,colnames[10]);

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

            for (int i = 0; i < list.size(); i++) {
                Label labelId_i = new Label(0, i + 1, String.valueOf(list.get(i).getRdID()));
                Label labelName_i = new Label(1, i + 1, list.get(i).getRdName());
                Label labelSex_i = new Label(2, i + 1, list.get(i).getRdSex());
                Label labelType_i = new Label(3, i + 1, String.valueOf(list.get(i).getRdType()));
                Label labelDept_i = new Label(4, i + 1, String.valueOf(list.get(i).getRdDept()));
                Label labelPhone_i = new Label(5, i + 1, list.get(i).getRdPhone());
                Label labelEmail_i = new Label(6, i + 1, list.get(i).getRdEmail());
                Label labelStatus_i = new Label(7, i + 1, list.get(i).getRdStatus());
                Label labelBorrowQty_i = new Label(8, i + 1, String.valueOf(list.get(i).getRdBorrowQty()));
                Label labelDateAct_i = new Label(9, i + 1, list.get(i).getRdDateReg().toString());
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

    // 借书证管理(读者管理)面板
    public ReaderPanel() {
        setSize(new Dimension(800, 574));
        setLayout(null);// 在Main里面显示Panel

        // 初始化各个Panel
        initSearchPanel();
        initSearchResultPanel();
        initReaderInfoPanel();
        initFunctionCtrlPanel();
        initEditCtrlPanel();
        // 设置初始操作状态
        setStatus(OpStatus.inSelect);
        // 添加动作监听器
        addActionListeners();
    }
}
