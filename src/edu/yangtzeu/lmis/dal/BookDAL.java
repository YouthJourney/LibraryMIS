package edu.yangtzeu.lmis.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.yangtzeu.lmis.model.AbstractModel;
import edu.yangtzeu.lmis.model.Book;

public class BookDAL extends AbstractDAL {

	// 指定各个字段的在Jtable标题栏中显示出来
	private String[] dispColNames = new String[] { "ID", "索书号", "书名", "作者", "出版社", "出版日期", "ISBN", "分类号", "语种", "页数",
			"价格", "入馆时间", "状态" };
	// 指定字段在ReaderType实体类中的获取方法
	private String[] methodNames = new String[] { "getBkID", "getBkCode", "getBkName", "getBkAuthor", "getBkPress",
			"getBkDatePress", "getBkISBN", "getBkCatalog", "getBkLanguage", "getBkPages", "getBkPrice", "getBkDateIn",
			"getBkStatus" };

	@Override
	public AbstractModel[] getAllObjects() throws Exception {
		ArrayList<Book> objects = new ArrayList<Book>();
		ResultSet rs = SQLHelper.getResultSet("select bkID,bkCode,bkName,bkAuthor"
				+ ",bkPress,bkDatePress,bkISBN,bkCatalog,bkLanguage,bkPages,bkPrice"
				+ ",bkDateIn,bkBrief,bkCover,bkStatus from TB_Book");
		if (rs != null) {
			while (rs.next()) {
				Book rt = initBook(rs);
				objects.add(rt);
			}
			rs.close();
		}
		if (objects.size() > 0) {
			Book[] types = new Book[objects.size()];
			objects.toArray(types);
			return types;
		}
		return null;
	}

	public Book[] getAllBooksByID(int bkID) throws SQLException {
		ArrayList<Book> objects = new ArrayList<Book>();
		ResultSet rs = SQLHelper.getResultSet("select bkID,bkCode,bkName,bkAuthor"
				+ ",bkPress,bkDatePress,bkISBN,bkCatalog,bkLanguage,bkPages,bkPrice"
				+ ",bkDateIn,bkBrief,bkCover,bkStatus from TB_Book where bkID = " + bkID);
		if (rs != null) {
			while (rs.next()) {
				Book rt = initBook(rs);
				objects.add(rt);
			}
			rs.close();
		}
		Book[] types = new Book[objects.size()];
		objects.toArray(types);
		return types;
	}

	public Book[] getAllBooksInfoBybkNames(String name) throws SQLException {
		ArrayList<Book> objects = new ArrayList<Book>();
		ResultSet rs = SQLHelper.getResultSet("select bkID,bkCode,bkName,bkAuthor"
				+ ",bkPress,bkDatePress,bkISBN,bkCatalog,bkLanguage,bkPages,bkPrice"
				+ ",bkDateIn,bkBrief,bkCover,bkStatus from TB_Book where bkName like " + "'%" + name + "%'");
		if (rs != null) {
			while (rs.next()) {
				Book rt = initBook(rs);
				objects.add(rt);
			}
			rs.close();
		}
		Book[] types = new Book[objects.size()];
		objects.toArray(types);
		return types;
	}

	public Book[] getAllBooksInfoByAuthor(String name) throws SQLException {
		ArrayList<Book> objects = new ArrayList<Book>();
		ResultSet rs = SQLHelper.getResultSet("select bkID,bkCode,bkName,bkAuthor"
				+ ",bkPress,bkDatePress,bkISBN,bkCatalog,bkLanguage,bkPages,bkPrice"
				+ ",bkDateIn,bkBrief,bkCover,bkStatus from TB_Book where bkAuthor like " + "'%" + name + "%'");
		if (rs != null) {
			while (rs.next()) {
				Book rt = initBook(rs);
				objects.add(rt);
			}
			rs.close();
		}
		Book[] types = new Book[objects.size()];
		objects.toArray(types);
		return types;
	}

	public Book[] getAllBooksInfoByPress(String name) throws SQLException {
		ArrayList<Book> objects = new ArrayList<Book>();
		ResultSet rs = SQLHelper.getResultSet("select bkID,bkCode,bkName,bkAuthor"
				+ ",bkPress,bkDatePress,bkISBN,bkCatalog,bkLanguage,bkPages,bkPrice"
				+ ",bkDateIn,bkBrief,bkCover,bkStatus from TB_Book where bkPress like " + "'%" + name + "%'");
		if (rs != null) {
			while (rs.next()) {
				Book rt = initBook(rs);
				objects.add(rt);
			}
			rs.close();
		}
		Book[] types = new Book[objects.size()];
		objects.toArray(types);
		return types;
	}

	private Book initBook(ResultSet rs) throws SQLException {
		Book rt = new Book();
		rt.setBkID(rs.getInt("bkID"));
		rt.setBkCode(rs.getString("bkCode"));
		rt.setBkName(rs.getString("bkName"));
		rt.setBkAuthor(rs.getString("bkAuthor"));
		rt.setBkPress(rs.getString("bkPress"));
		rt.setBkDatePress(rs.getDate("bkDatePress"));
		rt.setBkISBN(rs.getString("BKISBN"));
		rt.setBkCatalog(rs.getString("bkCatalog"));
		rt.setBkLanguage(rs.getString("bkLanguage"));
		rt.setBkPages(rs.getInt("bkPages"));
		rt.setBkPrice(rs.getFloat("bkPrice"));
		rt.setBkDateIn(rs.getDate("bkDateIn"));
		rt.setBkBrief(rs.getString("bkBrief"));
		rt.setBkCover(rs.getBytes("bkCover"));
		rt.setBkStatus(rs.getString("bkStatus"));
		return rt;
	}

	@Override
	public int add(AbstractModel object) throws Exception {
		if (object instanceof Book == false) {
			throw new Exception("Can only handle Book");
		}
		Book rt = (Book) object;
		String sql = "insert into TB_Book (bkCode," + "bkName,bkAuthor,bkPress,bkDatePress,BKISBN,"
				+ "bkCatalog,bkLanguage,bkPages,bkPrice,bkBrief,bkCover) " + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[12];
		params[0] = rt.getBkCode();
		params[1] = rt.getBkName();
		params[2] = rt.getBkAuthor();
		params[3] = rt.getBkPress();
		params[4] = rt.getBkDatePress();
		params[5] = rt.getBkISBN();
		params[6] = rt.getBkCatalog();
		params[7] = rt.getBkLanguage();
		params[8] = rt.getBkPages();
		params[9] = rt.getBkPrice();
		params[10] = rt.getBkBrief();
		params[11] = rt.getBkCover();

		return SQLHelper.ExecSql(sql, params);
	}

	@Override
	public int delete(AbstractModel object) throws Exception {
		if (object instanceof Book == false) {
			throw new Exception("Can only handle Book");
		}
		Book rt = (Book) object;
		String sql = "delete from TB_Book where bkID = ?";
		Object[] params = new Object[] { rt.getBkID() };
		int rows = SQLHelper.ExecSql(sql, params);
		return rows;
	}

	@Override
	public int update(AbstractModel object) throws Exception {
		if (object instanceof Book == false) {
			throw new Exception("Can only handle Book");
		}
		Book rt = (Book) object;
		String sql = "update TB_Book set bkCode = ?, bkName = ?, "
				+ "bkAuthor = ?,bkPress = ?, bkDatePress = ?,bkISBN = ?, bkCatalog = ?,"
				+ "bkLanguage = ?,bkPages = ?, bkPrice = ?, bkBrief = ?," + "bkCover = ? where bkID = ?";
		Object[] params = new Object[] { rt.getBkCode(), rt.getBkName(), rt.getBkAuthor(), rt.getBkPress(),
				rt.getBkDatePress(), rt.getBkISBN(), rt.getBkCatalog(), rt.getBkLanguage(), rt.getBkPages(),
				rt.getBkPrice(), rt.getBkBrief(), rt.getBkCover(), rt.getBkID() };
		return SQLHelper.ExecSql(sql, params);
	}

	public int updateBookStatus(AbstractModel object) throws Exception {
		if (object instanceof Book == false) {
			throw new Exception("Can only handle Book");
		}
		Book rt = (Book) object;
		String sql = "update TB_Book set bkStatus = ? where bkID = ?";
		Object[] params = new Object[] { rt.getBkStatus(), rt.getBkID() };
		return SQLHelper.ExecSql(sql, params);
	}

	@Override
	public AbstractModel getObjectByID(int bkID) throws SQLException {
		Book rt = null;
		ResultSet rs = SQLHelper.getResultSet("select bkID,bkCode,bkName,bkAuthor"
				+ ",bkPress,bkDatePress,bkISBN,bkCatalog,bkLanguage,bkPages,bkPrice"
				+ ",bkDateIn,bkBrief,bkCover,bkStatus from TB_Book where bkID=" + bkID);
		if (rs != null) {
			if (rs.next()) {
				rt = initBook(rs);
			}
			rs.close();
		}
		return rt;
	}

	public Book[] getBooksBy(Book book) throws SQLException {
		ArrayList<Book> books = new ArrayList<Book>();
		String sql = "select * from TB_Book where bkName like ? and "
				+ "bkAuthor like ? and bkBrief like ? and bkPress like ? and bkCatalog = ? and bkDatePress = ?";
		System.out.println(book.getBkAuthor() + book.getBkName());
		Object[] params = new Object[] { "%" + book.getBkName() + "%", "%" + book.getBkAuthor() + "%",
				"%" + book.getBkBrief() + "%", "%" + book.getBkPress() + "%", book.getBkCatalog(),
				book.getBkDatePress() };
		ResultSet rs = SQLHelper.getResultSet(sql, params);
		if (rs != null) {
			while (rs.next()) {
				Book reader = initBook(rs);
				books.add(reader);
			}
			rs.close();
		}
		if (books.size() > 0) {
			Book[] array = new Book[books.size()];
			books.toArray(array);
			return array;
		}
		return null;
	}

	@Override
	public String[] getPrettyColumnNames() {
		return dispColNames;
	}

	@Override
	public String[] getMethodNames() {
		return methodNames;
	}

}
