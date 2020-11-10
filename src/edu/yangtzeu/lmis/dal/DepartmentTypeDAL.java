package edu.yangtzeu.lmis.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.yangtzeu.lmis.model.AbstractModel;
import edu.yangtzeu.lmis.model.DepartmentType;

public class DepartmentTypeDAL extends AbstractDAL{

	@Override
	public AbstractModel[] getAllObjects() throws Exception {
		ArrayList<DepartmentType> objects = new ArrayList<DepartmentType>();
		ResultSet rs = SQLHelper.getResultSet("select * from TB_DepartmentType");
		if(rs != null) {
			while(rs.next()) {
				DepartmentType rt = initDepartmentType(rs);
				objects.add(rt);
			}
			rs.close();
		}
		if(objects.size() > 0) {
			DepartmentType[] types = new DepartmentType[objects.size()];
			objects.toArray(types);
			return types;
		}
		return null;
	}
	
	private DepartmentType initDepartmentType(ResultSet rs) throws SQLException {
		DepartmentType rt = new DepartmentType();
		rt.setDtType(rs.getInt("dtType"));
		rt.setDtName(rs.getString("dtName"));
		rt.setDtAddress(rs.getString("dtAddress"));
		rt.setDtPhone(rs.getString("dtPhone"));

		return rt;
	}

	@Override
	public int add(AbstractModel object) throws Exception {
		if(object instanceof DepartmentType == false) {
			throw new Exception("Can only handle DepartmentType");
		}
		DepartmentType rt = (DepartmentType) object;
		String sql = "insert into TB_DepartmentType (dtType,"
				+"dtName,dtAddress,dtPhone) "
				+"values(?,?,?,?)";
		Object[] params = new Object[4];
		params[0] = rt.getDtType();
		params[1] = rt.getDtName();
		params[2] = rt.getDtAddress();
		params[3] = rt.getDtPhone();
		
		return SQLHelper.ExecSql(sql,params);
	}

	@Override
	public int delete(AbstractModel object) throws Exception {
		if(object instanceof DepartmentType == false) {
			throw new Exception("Can only handle DepartmentType");
		}
		DepartmentType rt = (DepartmentType) object;
		String sql = "delete from TB_DepartmentType where dtType = ?";
		Object[] params = new Object[] {rt.getDtType()};
		int rows = SQLHelper.ExecSql(sql, params);
		return rows;
	}

	@Override
	public int update(AbstractModel object) throws Exception {
		if(object instanceof DepartmentType) {
			throw new Exception("Can only handle DepartmentType");
		}
		DepartmentType rt = (DepartmentType) object;
		String sql = "update TB_DepartmentType set dtName = ?, "
				+"dtAddress = ?,dtPhone = ? where dtType = ?";
		Object[] params = new Object[] {rt.getDtName(),
				rt.getDtAddress(),rt.getDtPhone(),rt.getDtType()};
		return SQLHelper.ExecSql(sql, params);
	}

	@Override
	public AbstractModel getObjectByID(int dtType) throws SQLException {
		DepartmentType rt = null;
		ResultSet rs = SQLHelper.getResultSet("select dtType,dtName,"
				+"dtAddress,dtPhone "
				+"from TB_DepartmentType where dtType="
				+dtType);
		
		if(rs != null) {
			if(rs.next()) {
				rt = initDepartmentType(rs);
			}
			rs.close();
		}
		return rt;
	}

	@Override
	public String[] getPrettyColumnNames() {
		return null;
	}

	@Override
	public String[] getMethodNames() {
		return null;
	}
	
	public DepartmentType getDeptTypeNames(String name) throws SQLException {
		DepartmentType rt = null;
		ResultSet rs = SQLHelper.getResultSet("select dtType,dtName,"
				+"dtAddress,dtPhone "
				+"from TB_DepartmentType where dtName='"
				+name+"'");
		
		if(rs != null) {
			if(rs.next()) {
				rt = initDepartmentType(rs);
			}
			rs.close();
		}
		return rt;
	}
}
