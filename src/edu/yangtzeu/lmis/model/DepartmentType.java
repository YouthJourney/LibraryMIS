package edu.yangtzeu.lmis.model;

public class DepartmentType extends AbstractModel{
	private int dtType;//单位类型
	private String dtName;//单位名称
	private String dtAddress;//单位地址
	private String dtPhone;//单位电话
	
	public int getDtType() {
		return dtType;
	}
	public void setDtType(int dtType) {
		this.dtType = dtType;
	}
	
	public String getDtName() {
		return dtName;
	}
	public void setDtName(String dtName) {
		this.dtName = dtName;
	}
	
	public String getDtAddress() {
		return dtAddress;
	}
	public void setDtAddress(String dtAddress) {
		this.dtAddress = dtAddress;
	}
	
	public String getDtPhone() {
		return dtPhone;
	}
	public void setDtPhone(String dtPhone) {
		this.dtPhone = dtPhone;
	}
}
