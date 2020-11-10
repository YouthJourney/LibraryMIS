package edu.yangtzeu.lmis.bll;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.yangtzeu.lmis.dal.DepartmentTypeDAL;
import edu.yangtzeu.lmis.model.DepartmentType;

public class DepartmentTypeAdmin extends LibraryBLL{
	DepartmentTypeDAL deptType = new DepartmentTypeDAL();
	
	//获取单位类型对象
	public DepartmentType[] getDepartmentTypes() {
		try {
			return (DepartmentType[])deptType.getAllObjects();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//获取单位名称字符串
	public String[] getDeptTypeNames() {
		try {
			DepartmentType[] departmentTypes = getDepartmentTypes();
			
			ArrayList<String> arrayList = new ArrayList<String>();
			for(DepartmentType departmentType : departmentTypes) {
				arrayList.add(departmentType.getDtName());
			}
			String[] deptTypeNames = new String[arrayList.size()];
			arrayList.toArray(deptTypeNames);
			return deptTypeNames;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public DepartmentType getObjectById(int dtType) {	
		try {
			return (DepartmentType) deptType.getObjectByID(dtType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public DepartmentType getDeptTypeBy(String dtName) {
		try {
			return deptType.getDeptTypeNames(dtName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
