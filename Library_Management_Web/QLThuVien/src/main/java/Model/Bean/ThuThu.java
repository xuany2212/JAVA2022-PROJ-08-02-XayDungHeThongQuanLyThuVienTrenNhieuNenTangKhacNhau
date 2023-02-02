package Model.Bean;

import java.sql.Date;

public class ThuThu {
	private int MaThuThu;
	private String MaTK;
	private Date NgaySinh;
	
	public ThuThu() {
		
	}
	
	public ThuThu(int MaThuThu, String MaTK, Date NgaySinh) {
		this.MaThuThu = MaThuThu;
		this.MaTK = MaTK;
		this.NgaySinh = NgaySinh;
	}
	
	public int getMaThuThu() {
		return MaThuThu;
	}
	
	public void setMaThuThu(int MaThuThu) {
		this.MaThuThu = MaThuThu;
	}
	
	public String getMaTK() {
		return MaTK;
	}
	
	public void setMaTK(String MaTK) {
		this.MaTK = MaTK;
	}
	
	public Date getNgaySinh() {
		return NgaySinh;
	}
	
	public void setNgaySinh(Date NgaySinh) {
		this.NgaySinh = NgaySinh;
	}

}
