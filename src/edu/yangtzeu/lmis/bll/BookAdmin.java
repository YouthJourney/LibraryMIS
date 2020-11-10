package edu.yangtzeu.lmis.bll;

import java.sql.SQLException;

import edu.yangtzeu.lmis.dal.BookDAL;
import edu.yangtzeu.lmis.model.Book;

public class BookAdmin extends LibraryBLL {
    private BookDAL bkDal = new BookDAL();

    public BookAdmin() {
        dal = new BookDAL();
    }

    public Book getBook(int bkID) {
        try {
            return (Book) bkDal.getObjectByID(bkID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Book[] getBooksById(int bkID) {
        try {
            return bkDal.getAllBooksByID(bkID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int addBook(Book book) {
        try {
            return bkDal.add(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteBook(Book book) {
        try {
            return bkDal.delete(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updateBook(Book book) {
        try {
            return bkDal.update(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updateBkStatus(Book book) {
        try {
            return bkDal.updateBookStatus(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Book[] getAllBooks() {
        try {
            return (Book[]) bkDal.getAllObjects();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Book[] getBooksBy(Book book) {
        try {
            return bkDal.getBooksBy(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Book[] getBookInfosBybkName(String bknames) {
        try {
            return bkDal.getAllBooksInfoBybkNames(bknames);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Book[] getBookInfosBybkAuthor(String bkAuthors) {
        try {
            return bkDal.getAllBooksInfoByAuthor(bkAuthors);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Book[] getBookInfosBybkPresses(String bkPresses) {
        try {
            return bkDal.getAllBooksInfoByPress(bkPresses);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
