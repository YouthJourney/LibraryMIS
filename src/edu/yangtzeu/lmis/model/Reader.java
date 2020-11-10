package edu.yangtzeu.lmis.model;

/**
 * @author 22053
 * @version 1.0
 * @declare 读者类
 */

import java.util.Date;

public class Reader extends AbstractModel{
	private int rdID;//读者编号/借书证号
	private String rdName;//读者姓名
	private String rdSex; //读者性别
	private int rdType;//读者类别 
	private int rdDept;//单位代码/单位名称
	private String rdPhone;//电话号码
	private String rdEmail;//电子邮箱
	private Date rdDateReg;//读者登记日期/办证日期
	private byte[] rdPhoto;//读者照片
	private String rdStatus;//证件状态
	private int rdBorrowQty;//已借书数量
	private String rdPwd;//读者密码
	
	private static int rdAdminRoles;//管理角色
	
	public Reader() {
		
	}
	
	public Reader(int rd) {
		setRdID(rd);
	}
	
	public int getRdID() {
		return rdID;
	}
	public void setRdID(int rdID) {
		this.rdID = rdID;
	}
	
	public String getRdName() {
		return rdName;
	}
	public void setRdName(String rdName) {
		this.rdName = rdName;
	}
	
	public int getRdDept() {
		return rdDept;
	}
	public void setRdDept(int i) {
		this.rdDept = i;
	}
	
	public String getRdSex() {
		return rdSex;
	}
	public void setRdSex(String rdSex) {
		this.rdSex = rdSex;
	}
	
	public String getRdPhone() {
		return rdPhone;
	}
	public void setRdPhone(String rdPhone) {
		this.rdPhone = rdPhone;
	}
	
	public String getRdEmail() {
		return rdEmail;
	}
	public void setRdEmail(String rdEmail) {
		this.rdEmail = rdEmail;
	}
	
	public Date getRdDateReg() {
		return rdDateReg;
	}
	public void setRdDateReg(Date rdDateReg) {
		this.rdDateReg = rdDateReg;
	}
	
	public byte[] getRdPhoto() {
		return rdPhoto;
	}
	public void setRdPhoto(byte[] rdPhoto) {
		this.rdPhoto = rdPhoto;
	}
	
	public String getRdStatus() {
		return rdStatus;
	}
	public void setRdStatus(String rdStatus) {
		this.rdStatus = rdStatus;
	}
	
	public int getRdBorrowQty() {
		return rdBorrowQty;
	}
	public void setRdBorrowQty(int rdBorrowQty) {
		this.rdBorrowQty = rdBorrowQty;
	}
	
	public String getRdPwd() {
		return rdPwd;
	}
	public void setRdPwd(String rdPwd) {
		this.rdPwd = rdPwd;
	}
	
	public int getRdType() {
		return rdType;
	}
	public void setRdType(int rdType) {
		this.rdType = rdType;
	}
	
	//读者角色管理
	public int getRdAdminRoles() {
		return rdAdminRoles;
	}
	public void setRdAdminRoles(int rdAdminRoles) {
		Reader.rdAdminRoles = rdAdminRoles;
	}
	public static boolean isReaderAdmin() {
		return (Reader.rdAdminRoles & 1) > 0;
	}
	
	public static boolean isBookAdmin() {
		return (Reader.rdAdminRoles & 2) > 0;
	}
	
	public static boolean isBorrowAdmin() {
		return (Reader.rdAdminRoles & 4) > 0;
	}
	
	public static boolean isSysAdmin() {
		return (Reader.rdAdminRoles & 8) > 0;
	}
	
}
