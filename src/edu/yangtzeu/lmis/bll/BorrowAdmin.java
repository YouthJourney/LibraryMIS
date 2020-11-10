package edu.yangtzeu.lmis.bll;

import java.sql.SQLException;

//import java.sql.SQLException;

import edu.yangtzeu.lmis.dal.BorrowDAL;
import edu.yangtzeu.lmis.model.Borrow;

public class BorrowAdmin extends LibraryBLL{
	private BorrowDAL borrowDAL = new BorrowDAL();
	
	public BorrowAdmin() {
		dal = new BorrowDAL();
	}
	
	public Borrow getBorrow(int bkID) {
		try {
			return borrowDAL.getBorrowByBkId(bkID);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int addBorrow(Borrow borrow) {
		try {
			return borrowDAL.add(borrow);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int deleteBorrow(Borrow borrow) {
		try {
			return borrowDAL.delete(borrow);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int updateBorrow (Borrow borrow) {
		try {
			return borrowDAL.update(borrow);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int updateBorrowDatePlan(Borrow borrow) {
		try {
			return borrowDAL.updateBorrowDatePlan(borrow);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public Borrow[] getAllBorrows() {
		try {
			return (Borrow[]) borrowDAL.getAllObjects();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Borrow[] getBorrowsByrdId(int rdId) {
		try {
			return (Borrow[]) borrowDAL.getObjectsById(rdId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
//	public Borrow[] getBorrowsBy(Borrow Borrow) {
//		try {
//			return borrowDAL.getBorrowsBy(Borrow);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	public Borrow[] getBorrowInfosBybkName(String bknames) {
//		try {
//			return borrowDAL.getAllBorrowsInfoBybkNames(bknames);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	public Borrow[] getBorrowInfosBybkAuthor(String bkAuthors) {
//		try {
//			return borrowDAL.getAllBorrowsInfoByAuthor(bkAuthors);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	public Borrow[] getBorrowInfosBybkPresses(String bkPresses) {
//		try {
//			return borrowDAL.getAllBorrowsInfoByPress(bkPresses);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

}
