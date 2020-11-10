package edu.yangtzeu.lmis.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.yangtzeu.lmis.model.AbstractModel;
import edu.yangtzeu.lmis.model.Borrow;
//import edu.yangtzeu.lmis.model.Reader;

public class BorrowDAL extends AbstractDAL{
	private String[] dispColNames = new String[] {
			"图书序号","图书名称","图书作者","续借次数","借阅日期","应还日期","超期天数",
			"超期金额"
	};
	//指定字段在ReaderType实体类中的获取方法
	private String[] methodNames = new String[] {"getBkID","getBkName",
			"getBkAuthor","getIdContinueTimes","getIdDateOut","getIdDateRetPlan","getIdOverDay",
			"getIdOverMoney"};

	@Override
	public AbstractModel[] getAllObjects() throws Exception {
		ArrayList<Borrow> objects = new ArrayList<Borrow>();
		ResultSet rs = SQLHelper.getResultSet("select * from TB_Borrow");
		if(rs != null) {
			while(rs.next()) {
				Borrow rt = initBorrow(rs);
				objects.add(rt);
			}
			rs.close();
		}
		if(objects.size() > 0) {
			Borrow[] types = new Borrow[objects.size()];
			objects.toArray(types);
			return types;
		}
		return null;
	}
	
	public Borrow[] getObjectsById(int rdId) throws SQLException {
		ArrayList<Borrow> borrows = new ArrayList<Borrow>();
		ResultSet rs = SQLHelper.getResultSet("select * from TB_Borrow where rdID = "+rdId);
		if(rs != null) {
			while(rs.next()) {
				Borrow rt = initBorrow(rs);
				borrows.add(rt);
			}
			rs.close();
		}
		if(borrows.size() > 0) {
			Borrow[] types = new Borrow[borrows.size()];
			borrows.toArray(types);
			return types;
		}
		return null;
	}
	
	public Borrow getBorrowByBkId(int bkId) throws SQLException {
		Borrow rt = null;
		ResultSet rs = SQLHelper.getResultSet("select * from TB_Borrow where bkID="
				+bkId);
		if(rs != null) {
			if(rs.next()) {
				rt = initBorrow(rs);
			}
			rs.close();
		}
		return rt;
	}
	
	private Borrow initBorrow(ResultSet rs) throws SQLException {
		Borrow rt = new Borrow();
		rt.setBorrowID(rs.getLong("BorrowID"));
		rt.setRdID(rs.getInt("rdID"));
		rt.setBkID(rs.getInt("bkID"));
		rt.setIdContinueTimes(rs.getInt("IdContinueTimes"));
		rt.setIdDateOut(rs.getDate("IdDateOut"));
		rt.setIdDateRetPlan(rs.getDate("IdDateRetPlan"));
		rt.setIdDateRetAct(rs.getDate("IdDateRetAct"));
		rt.setIdOverDay(rs.getInt("IdOverDay"));
		rt.setIdOverMoney(rs.getFloat("IdOverMoney"));
		rt.setIdPunishMoney(rs.getFloat("IdPunishMoney"));
		rt.setIsHasReturn(rs.getBoolean("isHasReturn"));
		rt.setOperatorLend(rs.getString("OperatorLend"));
		rt.setOperatorRet(rs.getString("OperatorRet"));
		
		return rt;
	}

	@Override
	public int add(AbstractModel object) throws Exception {
		if(object instanceof Borrow == false) {
			throw new Exception("Can only handle Borrow");
		}
		Borrow rt = (Borrow) object;
//		String sql = "insert into TB_Borrow (BorrowID,rdID,bkID"
//				+"IdContinueTimes,IdDateOut,IdDateRetPlan,IdDateRetAct,IdOverDay,"
//				+"IdOverMoney,IdPunishMoney,IsHasReturn,OperatorLend,OperatorRet) "
//				+"values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String sql = "{call usp_BorrowBook(?,?)}";
		Object[] params = new Object[] {rt.getRdID(),rt.getBkID()};
//		params[0] = rt.getBorrowID();
//		params[1] = rt.getRdID();
//		params[2] = rt.getBkID();
//		params[3] = rt.getIdContinueTimes();
//		params[4] = rt.getIdDateOut();
//		params[5] = rt.getIdDateRetPlan();
//		params[6] = rt.getIdDateRetAct();
//		params[7] = rt.getIdOverDay();
//		params[8] = rt.getIdOverMoney();
//		params[9] = rt.getIdPunishMoney();
//		params[10] = rt.isIsHasReturn();
//		params[11] = rt.getOperatorLend();
//		params[12] = rt.getOperatorRet();
		
		return SQLHelper.ExecSql(sql,params);
	}

	@Override
	public int delete(AbstractModel object) throws Exception {
		if(object instanceof Borrow == false) {
			throw new Exception("Can only handle Borrow");
		}
		Borrow rt = (Borrow) object;
		String sql = "delete from TB_Borrow where BorrowID = ?";
		Object[] params = new Object[] {rt.getBorrowID()};
		int rows = SQLHelper.ExecSql(sql, params);
		return rows;
	}

	@Override
	public int update(AbstractModel object) throws Exception {
		if(object instanceof Borrow == false) {
			throw new Exception("Can only handle Borrow");
		}
		Borrow rt = (Borrow) object;
		String sql = "update TB_Borrow set IdContinueTimes = ?, "
				+"IdDateOut = ?,IdDateRetPlan = ?, IdDateRetAct = ?,IdOverDay = ?,"
				+"IdOverMoney = ?,IdPunishMoney = ?, IsHasReturn = ?,"
				+"OperatorLend = ?,OperatorRet = ? where BorrowID = ?";
		Object[] params = new Object[] {rt.getIdContinueTimes(),
				rt.getIdDateOut(),rt.getIdDateRetPlan(),rt.getIdDateRetAct(),
				rt.getIdOverDay(),rt.getIdOverMoney(),rt.getIdPunishMoney(),
				rt.isIsHasReturn(),rt.getOperatorLend(),rt.getOperatorRet(),rt.getBorrowID()};
		return SQLHelper.ExecSql(sql, params);
	}

	public int updateBorrowDatePlan(AbstractModel object) throws Exception {
		if(object instanceof Borrow == false) {
			throw new Exception("Can only handle Borrow");
		}
		Borrow rt = (Borrow) object;
		String sql = "{call usp_Renew(?)}";
		Object[] params = new Object[] {rt.getBorrowID()};
		return SQLHelper.ExecSql(sql, params);
	}
	
	@Override
	public AbstractModel getObjectByID(int id) throws SQLException {
		return null;
	}
	
	@Override
	public String[] getPrettyColumnNames() {
		return dispColNames;
	}

	@Override
	public String[] getMethodNames() {
		return methodNames;
	}

}
