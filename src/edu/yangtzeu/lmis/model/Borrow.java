package edu.yangtzeu.lmis.model;

/**
 * @version 1.0 
 * @author 22053
 * @declare 借阅
 */

import java.util.Date;

public class Borrow extends AbstractModel{
	private long BorrowID; //借书顺序号
	private int rdID; //读者Id
	private int bkID; //图书Id
	private int IdContinueTimes;//续借次数
	private Date IdDateOut;//借书日期
	private Date IdDateRetPlan;//应还日期
	private Date IdDateRetAct;//实际还书日期
	private int IdOverDay;//超期天数
	private float IdOverMoney;//超期金额
	private float IdPunishMoney;//罚款金额
	private boolean IsHasReturn;//是否已还书
	private String OperatorLend;//借书操作员
	private String OperatorRet;//还书操作员
	
	public long getBorrowID() {
		return BorrowID;
	}
	public void setBorrowID(long borrowID) {
		BorrowID = borrowID;
	}
	public int getIdContinueTimes() {
		return IdContinueTimes;
	}
	public void setIdContinueTimes(int idContinueTimes) {
		IdContinueTimes = idContinueTimes;
	}
	public Date getIdDateOut() {
		return IdDateOut;
	}
	public void setIdDateOut(Date idDateOut) {
		IdDateOut = idDateOut;
	}
	public Date getIdDateRetPlan() {
		return IdDateRetPlan;
	}
	public void setIdDateRetPlan(Date idDateRetPlan) {
		IdDateRetPlan = idDateRetPlan;
	}
	public Date getIdDateRetAct() {
		return IdDateRetAct;
	}
	public void setIdDateRetAct(Date idDateRetAct) {
		IdDateRetAct = idDateRetAct;
	}
	public int getIdOverDay() {
		return IdOverDay;
	}
	public void setIdOverDay(int idOverDay) {
		IdOverDay = idOverDay;
	}
	public float getIdOverMoney() {
		return IdOverMoney;
	}
	public void setIdOverMoney(float idOverMoney) {
		IdOverMoney = idOverMoney;
	}
	public float getIdPunishMoney() {
		return IdPunishMoney;
	}
	public void setIdPunishMoney(float idPunishMoney) {
		IdPunishMoney = idPunishMoney;
	}
	public boolean isIsHasReturn() {
		return IsHasReturn;
	}
	public void setIsHasReturn(boolean isHasReturn) {
		IsHasReturn = isHasReturn;
	}
	public String getOperatorLend() {
		return OperatorLend;
	}
	public void setOperatorLend(String operatorLend) {
		OperatorLend = operatorLend;
	}
	public String getOperatorRet() {
		return OperatorRet;
	}
	public void setOperatorRet(String operatorRet) {
		OperatorRet = operatorRet;
	}
	public int getRdID() {
		return rdID;
	}
	public void setRdID(int rdID) {
		this.rdID = rdID;
	}
	public int getBkID() {
		return bkID;
	}
	public void setBkID(int bkID) {
		this.bkID = bkID;
	}
	
}
