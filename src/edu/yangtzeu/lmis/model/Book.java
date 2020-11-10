package edu.yangtzeu.lmis.model;

/**
 * @version 1.0 
 * @author 22053
 * @declare 图书
 * 
 */

import java.util.Date;

public class Book extends AbstractModel{
	private int bkID;//图书序号
	private String bkCode;//图书编号或条码号
	private String bkName;//书名
	private String bkAuthor;//作者
	private String bkPress;//出版社
	private Date bkDatePress;//出版日期
	private String bkISBN;//ISBN书号
	private String bkCatalog;//分类号
	private String bkLanguage;//语言
	private int bkPages;//页数
	private float bkPrice;//价格
	private Date bkDateIn;//入馆日期
	private String bkBrief;//内容简介
	private byte[] bkCover;//图书封面照片
	private String bkStatus;//图书状态
	
	public Book() {
		
	}
	
	public Book(int bkId) {
		setBkID(bkId);
	}
	
	public int getBkID() {
		return bkID;
	}	
	public void setBkID(int bkID) {
		this.bkID = bkID;
	}
	public String getBkCode() {
		return bkCode;
	}
	public void setBkCode(String bkCode) {
		this.bkCode = bkCode;
	}
	public String getBkAuthor() {
		return bkAuthor;
	}
	public void setBkAuthor(String bkAuthor) {
		this.bkAuthor = bkAuthor;
	}
	public String getBkPress() {
		return bkPress;
	}
	public void setBkPress(String bkPress) {
		this.bkPress = bkPress;
	}
	public Date getBkDatePress() {
		return bkDatePress;
	}
	public void setBkDatePress(Date bkDatePress) {
		this.bkDatePress = bkDatePress;
	}
	public String getBkISBN() {
		return bkISBN;
	}
	public void setBkISBN(String bkISBN) {
		this.bkISBN = bkISBN;
	}
	public String getBkCatalog() {
		return bkCatalog;
	}
	public void setBkCatalog(String bkCatalog) {
		this.bkCatalog = bkCatalog;
	}
	public String getBkLanguage() {
		return bkLanguage;
	}
	public void setBkLanguage(String bkLanguage) {
		this.bkLanguage = bkLanguage;
	}
	public int getBkPages() {
		return bkPages;
	}
	public void setBkPages(int bkPages) {
		this.bkPages = bkPages;
	}
	public float getBkPrice() {
		return bkPrice;
	}
	public void setBkPrice(float bkPrice) {
		this.bkPrice = bkPrice;
	}
	public Date getBkDateIn() {
		return bkDateIn;
	}
	public void setBkDateIn(Date bkDateIn) {
		this.bkDateIn = bkDateIn;
	}
	public String getBkBrief() {
		return bkBrief;
	}
	public void setBkBrief(String bkBrief) {
		this.bkBrief = bkBrief;
	}
	public byte[] getBkCover() {
		return bkCover;
	}
	public void setBkCover(byte[] bkCover) {
		this.bkCover = bkCover;
	}
	public String getBkStatus() {
		return bkStatus;
	}
	public void setBkStatus(String bkStatus) {
		this.bkStatus = bkStatus;
	}
	public String getBkName() {
		return bkName;
	}
	public void setBkName(String bkName) {
		this.bkName = bkName;
	}	
	
}
