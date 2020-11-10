package edu.yangtzeu.lmis.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.yangtzeu.lmis.model.AbstractModel;
import edu.yangtzeu.lmis.model.DepartmentType;
import edu.yangtzeu.lmis.model.Reader;
import edu.yangtzeu.lmis.model.ReaderType;

public class ReaderDAL extends AbstractDAL{
	//指定各个字段在JTable标题栏中显示出来
	private String[] dispColNames = new String[] {
			"ID","姓名","性别","类型","院系","电话","Email","状态","已借书数量","注册日期"
	};
	//指定字段在Reader实体类中的获取方法
	private String[] methodNames = new String[] {"getRdID",
			"getRdName","getRdSex","getRdType","getRdDept",
			"getRdPhone","getRdEmail","getRdStatus","getRdBorrowQty","getRdDateReg"
	};

	@Override
	public AbstractModel[] getAllObjects() throws Exception {
		ArrayList<Reader> objects = new ArrayList<Reader>();
		ResultSet rs = SQLHelper.getResultSet("select * from TB_Reader");
		return getReaders(objects, rs);
	}

	private Reader initReader(ResultSet rs) throws SQLException{
		Reader rt = new Reader();
		rt.setRdID(rs.getInt("rdID"));
		rt.setRdName(rs.getString("rdName"));
		rt.setRdSex(rs.getString("rdSex"));
		rt.setRdType(rs.getInt("rdType"));
		rt.setRdDept(rs.getInt("rdDept"));
		rt.setRdPhone(rs.getString("rdPhone"));
		rt.setRdEmail(rs.getString("rdEmail"));
		rt.setRdDateReg(rs.getDate("rdDateReg"));
		rt.setRdPhoto(rs.getBytes("rdPhoto"));
		rt.setRdStatus(rs.getString("rdStatus"));
		rt.setRdBorrowQty(rs.getInt("rdBorrowQty"));
		rt.setRdPwd(rs.getString("rdPwd"));
		rt.setRdAdminRoles(rs.getInt("rdAdminRoles"));
		return rt;
	}

	@Override
	public int add(AbstractModel object) throws Exception {
		if(object instanceof Reader == false) {
			throw new Exception("Can only handle Reader");
		}
		Reader rt = (Reader) object;
		String sql = "insert into TB_Reader (rdID,"
				+"rdName,rdSex,rdType,rdDept,rdPhone,rdEmail,"
				+"rdPhoto,rdPwd) "
				+"values(?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[9];
		params[0] = rt.getRdID();
		params[1] = rt.getRdName();
		params[2] = rt.getRdSex();
		params[3] = rt.getRdType();
		params[4] = rt.getRdDept();
		params[5] = rt.getRdPhone();
		params[6] = rt.getRdEmail();
		params[7] = rt.getRdPhoto();
		params[8] = rt.getRdPwd();
		
		return SQLHelper.ExecSql(sql,params);
	}

	@Override
	public int delete(AbstractModel object) throws Exception {
		if(object instanceof Reader == false) {
			throw new Exception("Can only handle Reader");
		}
		Reader rt = (Reader) object;
		String sql = "delete from TB_Reader where rdID = ?";
		Object[] params = new Object[] {rt.getRdID()};
		int rows = SQLHelper.ExecSql(sql, params);
		return rows;
	}

	@Override
	public int update(AbstractModel object) throws Exception {
		if(object instanceof Reader == false) {
			throw new Exception("Can only handle Reader");
		}
		Reader rt = (Reader) object;
		String sql = "update TB_Reader set rdName = ?, rdSex = ?,"
				+"rdType = ?,rdDept = ?, rdPhone = ?,rdEmail = ?,"
				+"rdPhoto = ?,rdPwd = ? where rdID = ?";
		Object[] params = new Object[] {rt.getRdName(),rt.getRdSex(),
				rt.getRdType(),rt.getRdDept(),rt.getRdPhone(),
				rt.getRdEmail(),rt.getRdPhoto(),rt.getRdPwd(),rt.getRdID()};
		return SQLHelper.ExecSql(sql, params);
	}
	public int updateReaderStatus(AbstractModel object) throws Exception {
		if(object instanceof Reader == false) {
			throw new Exception("Can only handle Reader");
		}
		Reader rt = (Reader) object;
		String sql = "update TB_Reader set rdStatus = ? where rdID = ?";
		Object[] params = new Object[] {rt.getRdStatus(),rt.getRdID()};
		return SQLHelper.ExecSql(sql, params);
	}
	
	public int updatePassword(AbstractModel object) throws Exception {
		if(object instanceof Reader == false) {
			throw new Exception("Can only handle Reader");
		}
		Reader rt = (Reader) object;
		String sql = "update TB_Reader set rdPwd = ? where rdID = ?";
		Object[] params = new Object[] {rt.getRdPwd(),rt.getRdID()};
		return SQLHelper.ExecSql(sql, params);
	}
	
	public int updatePermission(AbstractModel object) throws Exception {
		if(object instanceof Reader == false) {
			throw new Exception("Can only handle Reader");
		}
		Reader rt = (Reader) object;
		String sql = "update TB_Reader set rdAdminRoles = ? where rdID = ?";
		Object[] params = new Object[] {rt.getRdAdminRoles(),rt.getRdID()};
		return SQLHelper.ExecSql(sql, params);
	}
	
	public int updateReaderBorrowQty(AbstractModel object) throws Exception {
		if(object instanceof Reader == false) {
			throw new Exception("Can only handle Reader");
		}
		Reader rt = (Reader) object;
		String sql = "update TB_Reader set rdBorrowQty = ? where rdID = ?";
		Object[] params = new Object[] {rt.getRdBorrowQty(),rt.getRdID()};
		return SQLHelper.ExecSql(sql, params);
	}
	
	@Override
	public AbstractModel getObjectByID(int rdID) throws SQLException {
		Reader rt = null;
		ResultSet rs = SQLHelper.getResultSet("select rdID,rdName,rdSex,rdType"
				+",rdDept,rdPhone,rdEmail,rdDateReg,rdPhoto,rdStatus,rdBorrowQty"
				+",rdPwd,rdAdminRoles from TB_Reader where rdID="
				+rdID);
		if(rs != null) {
			if(rs.next()) {
				rt = initReader(rs);
			}
			rs.close();
		}
		return rt;
	}

	@Override
	public String[] getPrettyColumnNames() {
		return dispColNames;
	}

	@Override
	public String[] getMethodNames() {
		return methodNames;
	}
	
	public Reader[] getReadersBy(ReaderType rdType, DepartmentType deptType, String userName) throws SQLException{
		ArrayList<Reader> readers = new ArrayList<Reader>();
		String sql = "select * from TB_Reader where rdType = ? and "
				+ "rdDept = ? and rdName like ?";
		Object[] params = new Object[] {rdType.getRdType(),
				deptType.getDtType(),"%"+userName+"%"};
		ResultSet rs = SQLHelper.getResultSet(sql, params);
		return getReaders(readers, rs);

//		if(rs != null) {
//			while(rs.next()) {
//				Reader reader = initReader(rs);
//				readers.add(reader);
//			}
//			rs.close();
//		}
//		if(readers.size() > 0) {
//			Reader[] array = new Reader[readers.size()];
//			readers.toArray(array);
//			return array;
//		}
//		return null;

	}

	private Reader[] getReaders(ArrayList<Reader> readers, ResultSet rs) throws SQLException {
		if(rs != null) {
			while(rs.next()) {
				Reader reader = initReader(rs);
				readers.add(reader);
			}
			rs.close();
		}
		if(readers.size() > 0) {
			Reader[] array = new Reader[readers.size()];
			readers.toArray(array);
			return array;
		}
		return null;
	}
}
