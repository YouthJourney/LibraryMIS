package edu.yangtzeu.lmis.bll;

import edu.yangtzeu.lmis.dal.AbstractDAL;

public abstract class LibraryBLL {
	protected AbstractDAL dal;
	
	public String[] getDisplayColumnNames() {
		return dal.getPrettyColumnNames();
	}
	
	public String[] getMethodNames() {
		return dal.getMethodNames();
	}
}
