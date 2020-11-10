package edu.yangtzeu.lmis.model;

/**
 * @version 1.0
 * @author 22053
 * @declare 读者类别号
 */

public class ReaderType extends AbstractModel {
	private int rdType; // 读者类别
	private String rdTypeName;// 读者类别名称
	private int canLendQty;// 可借书数量
	private int canLendDay;// 可借书天数
	private int canContinueTimes;// 可续借的次数
	private float punishRate;// 罚款率
	private int dateValid;// 证书有效期

	public ReaderType() {
	}

	// 复制构造函数
	public ReaderType(int rdType) {
		setRdType(rdType);
	}

	public int getRdType() {
		return rdType;
	}

	public void setRdType(int rdType) {
		this.rdType = rdType;
	}

	public String getRdTypeName() {
		return rdTypeName;
	}

	public void setRdTypeName(String rdTypeName) {
		this.rdTypeName = rdTypeName;
	}

	public int getCanLendQty() {
		return canLendQty;
	}

	public void setCanLendQty(int canLendQty) {
		this.canLendQty = canLendQty;
	}

	public int getCanLendDay() {
		return canLendDay;
	}

	public void setCanLendDay(int canLendDay) {
		this.canLendDay = canLendDay;
	}

	public int getCanContinueTimes() {
		return canContinueTimes;
	}

	public void setCanContinueTimes(int canContinueTimes) {
		this.canContinueTimes = canContinueTimes;
	}

	public float getPunishRate() {
		return punishRate;
	}

	public void setPunishRate(float punishRate) {
		this.punishRate = punishRate;
	}

	public int getDateValid() {
		return dateValid;
	}

	public void setDateValid(int dateValid) {
		this.dateValid = dateValid;
	}
}
