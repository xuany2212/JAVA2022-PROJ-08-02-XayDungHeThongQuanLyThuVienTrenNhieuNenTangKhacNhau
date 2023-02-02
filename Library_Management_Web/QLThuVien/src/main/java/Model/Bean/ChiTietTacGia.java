package Model.Bean;


import java.sql.Date;

public class ChiTietTacGia {
	private int STT;
	private String MaTacGia;
	private String MaSach;
	
	public ChiTietTacGia() {
		
	}
	
	public ChiTietTacGia(int STT, String MaTacGia, String MaSach) {
		this.STT = STT;
		this.MaTacGia = MaTacGia;
		this.MaSach = MaSach;
	}
	
	public int getSTT() {
		return STT;
	}
	
	public void setSTT(int STT) {
		this.STT = STT;
	}
	
	public String getMaTacGia() {
		return MaTacGia;
	}
	
	public void setMaTacGia(String MaTacGia) {
		this.MaTacGia = MaTacGia;
	}
	
	public String getMaSach() {
		return MaSach;
	}
	
	public void setMaSach(String MaSach) {
		this.MaSach = MaSach;
	}
}
