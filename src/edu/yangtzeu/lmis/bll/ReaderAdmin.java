package edu.yangtzeu.lmis.bll;

import java.sql.SQLException;

import edu.yangtzeu.lmis.dal.ReaderDAL;
import edu.yangtzeu.lmis.model.DepartmentType;
import edu.yangtzeu.lmis.model.Reader;
import edu.yangtzeu.lmis.model.ReaderType;

public class ReaderAdmin extends LibraryBLL{
	private ReaderDAL rdDal = new ReaderDAL();
	
	public ReaderAdmin() {
		dal = new ReaderDAL(); //继承父类对象并初始化
	}
	
	public Reader getReader(int rdID) {
		try {
			return (Reader) rdDal.getObjectByID(rdID);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int addReader(Reader reader) {
		try {
			return rdDal.add(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int UpdateReaderPassword(Reader reader) {
		try {
			return rdDal.updatePassword(reader);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int updateReaderBorrowedQty(Reader reader){
		try {
			return rdDal.updateReaderBorrowQty(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int updateReader(Reader reader) {
		try {
			return rdDal.update(reader);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int deleteReader(Reader reader) {
		try {
			return rdDal.delete(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public Reader[] getAllReaders() {
		try {
			return (Reader[]) rdDal.getAllObjects();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int updateReaderStatus(Reader reader) {
		try {
			return rdDal.updateReaderStatus(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public Reader[] retrieveReaders(ReaderType rdType, DepartmentType deptType, String userName) {
		try {
			return rdDal.getReadersBy(rdType, deptType, userName);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
