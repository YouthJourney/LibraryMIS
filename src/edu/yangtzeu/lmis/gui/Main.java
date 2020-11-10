package edu.yangtzeu.lmis.gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import edu.yangtzeu.lmis.model.Reader;

public class Main extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private JMenu MN_BookMgt; // 图书管理
    private JMenu MN_ReaderMgt;// 读者管理
    private JMenu MN_BorrowMgt;// 借阅管理
    private JMenu MN_UserMgt; // 用户管理
    private JMenuBar menuBar;
//	private JMenuItem MI_ReaderMgt;//借书证管理

    // 定义各个面板和对应的名字
    private final String blankPanelName = "Blank";
    private final String readerPanelName = "Reader";
    //	private final String bookPanelName = "Book";
//	private final String borrowPanelName = "Borrow";
    private final String userPanelName = "User";
    private final String rdTypePanelName = "RdType";
    private final String bookInformationName = "bkInformation";
    public final String displayPanelName = "displayReader";

    public JPanel cards;
    private ReaderPanel readerPanel;
    //	private BookPanel bookPanel;
//	private BorrowPanel borrowPanel;
    private UserPanel userPanel;
    private ReaderTypePanel readerTypePanel;
    private BookIfmtSearch bookIfmtSearch;
    private DisplayPanel displayPanel;

    public Main() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("images//icon.png"));
        setResizable(false);
        setSize(new Dimension(820, 600));
        setTitle("\u957F\u6C5F\u5927\u56FE\u4E66\u9986\u7BA1\u7406\u4FE1\u606F\u7CFB\u7EDF");
        setLocationRelativeTo(null);
//		getContentPane().setLayout(null);

        initComponents();
        initMenu();
        initCardPanels();

        CardLayout c1 = (CardLayout) cards.getLayout();
        c1.show(cards, displayPanelName);
    }

    // 初始化菜单栏的内容
    private void initComponents() {
        menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 804, 23);
        getContentPane().add(menuBar);

        MN_BookMgt = new JMenu("图书管理");
        menuBar.add(MN_BookMgt);

        JMenuItem MI_NewBook = new JMenuItem("新书入库");
        MN_BookMgt.add(MI_NewBook);
        MI_NewBook.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                LoadBookGUI();
            }
        });

        JMenuItem MI_ChangeBookInfo = new JMenuItem("图书信息查询");
        MN_BookMgt.add(MI_ChangeBookInfo);
        MI_ChangeBookInfo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                CardLayout c1 = (CardLayout) cards.getLayout();
                c1.show(cards, bookInformationName);
            }
        });

        MN_ReaderMgt = new JMenu("读者管理");
        menuBar.add(MN_ReaderMgt);

        JMenuItem MI_ReaderMgt = new JMenuItem("借书证管理");
        MN_ReaderMgt.add(MI_ReaderMgt);
        MI_ReaderMgt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                CardLayout c1 = (CardLayout) cards.getLayout();
                c1.show(cards, readerPanelName);
            }
        });

        JMenuItem MI_ReaderTypeMgt = new JMenuItem("读者类型管理");
        MN_ReaderMgt.add(MI_ReaderTypeMgt);
        MI_ReaderTypeMgt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                CardLayout c1 = (CardLayout) cards.getLayout();
                c1.show(cards, rdTypePanelName);
            }
        });

        MN_BorrowMgt = new JMenu("借阅管理");
        menuBar.add(MN_BorrowMgt);

        JMenuItem MI_BorrowMgt = new JMenuItem("借阅信息管理");
        MN_BorrowMgt.add(MI_BorrowMgt);
        MI_BorrowMgt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                LoadBorrowGUI();
            }
        });

        MN_UserMgt = new JMenu("用户管理");
        menuBar.add(MN_UserMgt);

        JMenuItem MI_PermissionMgt = new JMenuItem("权限管理");
        MN_UserMgt.add(MI_PermissionMgt);
        MI_PermissionMgt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                closeMain();
                LoadChangePermGUI();
            }
        });

        JMenuItem MI_UpdatePassword = new JMenuItem("密码修改");
        MN_UserMgt.add(MI_UpdatePassword);
        MI_UpdatePassword.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                closeMain();
                LoadChangePwdGUI();
            }
        });
    }

    // 初始化和添加面板
    public void initCardPanels() {
        JPanel blankPanel = new JPanel();

        readerPanel = new ReaderPanel();
        readerPanel.setVisible(false);

        userPanel = new UserPanel();
        userPanel.setVisible(false);

        readerTypePanel = new ReaderTypePanel();
        readerTypePanel.setVisible(false);

        bookIfmtSearch = new BookIfmtSearch();
        bookIfmtSearch.setVisible(false);

        cards = new JPanel(new CardLayout());
        cards.add(blankPanel, blankPanelName);
        blankPanel.setLayout(new GridLayout(1, 0, 0, 0));

        // bookPanel = new BookPanel();
        // bookPanel.setVisible(false);

        // borrowPanel = new BorrowPanel();
        // borrowPanel.setVisible(false);
        displayPanel = new DisplayPanel();
        displayPanel.setVisible(false);
        // cards.add(bookPanel,bookPanelName);
        cards.add(displayPanel, displayPanelName);
        cards.add(readerPanel, readerPanelName);
        cards.add(userPanel, userPanelName);
        cards.add(readerTypePanel, rdTypePanelName);
        cards.add(bookIfmtSearch, bookInformationName);

        getContentPane().add(cards);
    }

    private void initMenu() {
        MN_ReaderMgt.setEnabled(Reader.isReaderAdmin());
        MN_BookMgt.setEnabled(Reader.isBookAdmin());
        MN_BorrowMgt.setEnabled(Reader.isBorrowAdmin());
        MN_UserMgt.setEnabled(Reader.isSysAdmin());
    }

    private void LoadBorrowGUI() {
        BorrowWindows borrowGUI = new BorrowWindows();
        borrowGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        borrowGUI.setLocationRelativeTo(null);
        borrowGUI.setVisible(true);
    }

    private void LoadBookGUI() {
        BookPanel bookPanel = new BookPanel();
        bookPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        bookPanel.setLocationRelativeTo(null);
        bookPanel.setVisible(true);
    }

    private void LoadChangePwdGUI() {
        PasswordChage passwordChage = new PasswordChage();
        passwordChage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        passwordChage.setLocationRelativeTo(null);
        passwordChage.setVisible(true);
    }

    private void closeMain() {
        this.dispose();
    }

    private void LoadChangePermGUI() {
        PermissionChage permissionChage = new PermissionChage();
        permissionChage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        permissionChage.setLocationRelativeTo(null);
        permissionChage.setVisible(true);
    }

//	public static void closeMainGUI() {
//		
//	}
}
