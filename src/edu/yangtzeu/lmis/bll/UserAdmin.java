package edu.yangtzeu.lmis.bll;

import java.sql.SQLException;

import edu.yangtzeu.lmis.dal.ReaderDAL;
import edu.yangtzeu.lmis.model.Reader;

public class UserAdmin extends LibraryBLL{
private ReaderDAL rdDal = new ReaderDAL();
	
	public UserAdmin() {
		dal = new ReaderDAL(); //继承父类对象并初始化
	}
	
	public Reader getUser(int rdID) {
		try {
			return (Reader) rdDal.getObjectByID(rdID);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int UpdateUserPassword(Reader reader) {
		try {
			return rdDal.updatePassword(reader);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int UpdateUserPermission(Reader reader) {
		try {
			return rdDal.updatePermission(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
