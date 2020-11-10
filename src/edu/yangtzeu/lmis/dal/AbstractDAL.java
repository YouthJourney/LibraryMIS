package edu.yangtzeu.lmis.dal;

import java.sql.SQLException;

import edu.yangtzeu.lmis.model.AbstractModel;

public abstract class AbstractDAL {
	public abstract AbstractModel[] getAllObjects() throws Exception;
	public abstract int add(AbstractModel object) throws Exception;
	public abstract int delete(AbstractModel object) throws Exception;
	public abstract int update(AbstractModel object) throws Exception;
	public abstract AbstractModel getObjectByID(int id) throws SQLException;
	public abstract String[] getPrettyColumnNames();
	public abstract String[] getMethodNames();
	
	//其中，getPrettyColumnNames和getMethodNames方法用于实现实体类到UI组件JTable数据的转换，
	//比如以列表形式显示查询得到的读者、借阅记录等（参见第6.8（3）节）。 
	//getAllObjects()方法获取该表中的所有记录，这在生成读者类型下拉列表和单位下拉列表时有用（参见第6.8（2）节）。
}
