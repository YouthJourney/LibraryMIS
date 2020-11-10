package edu.yangtzeu.lmis.bll;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.yangtzeu.lmis.dal.ReaderTypeDAL;
import edu.yangtzeu.lmis.model.ReaderType;

public class ReaderTypeAdmin extends LibraryBLL{
	private ReaderTypeDAL rdTypeDal = new ReaderTypeDAL();
	
	public ReaderTypeAdmin() {
		dal = new ReaderTypeDAL();
	}
	
	public int addRdType(ReaderType readerType) {
		try {
			return rdTypeDal.add(readerType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int deleteRdType(ReaderType readerType) {
		try {
			return rdTypeDal.delete(readerType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int updateRdType(ReaderType readerType) {
		try {
			return rdTypeDal.update(readerType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public ReaderType[] getAllReaderTypes() {
		try {
			return (ReaderType[]) rdTypeDal.getAllObjects();
			//返回的是个readertype类型的对象，即是readertypename的数组
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String[] getReaderTypeNames() {
		try {
			ReaderType[] readerTypes = getAllReaderTypes();
			//ComboBox显示的是字符串，故用String类型的arraylist数组存储typenames
			ArrayList<String> arrayList = new ArrayList<String>();
			for(ReaderType readerType : readerTypes) {
				arrayList.add(readerType.getRdTypeName());
			}
			String[] rdTypeNames = new String[arrayList.size()];
			arrayList.toArray(rdTypeNames);
			return rdTypeNames;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ReaderType getObjectByID(int rdType) {
		try {
			return (ReaderType) rdTypeDal.getObjectByID(rdType);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ReaderType getrdTypeNamesBy(String rdTypeName) {
		try {
			return rdTypeDal.geReaderTypeNames(rdTypeName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	public String getrdTypeIdBy(String rdTypeName) {
//		try {
//			return rdTypeDal.getRdTypeIds(rdTypeName).toString();
//		}catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
}
