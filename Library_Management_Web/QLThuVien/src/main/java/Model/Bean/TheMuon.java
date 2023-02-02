package Model.Bean;


import java.sql.Date;

public class TheMuon {
	
	private String MaThe;
	private String MaNguoiMuon;
	private int TrangThai;
	private String MaKichHoat;
	private Date NgayHetHan;
	
	public TheMuon() {
		
	}
	
	public TheMuon(String MaThe, String MaNguoiMuon, int TrangThai, String MaKichHoat, Date NgayHetHan) {
		this.MaThe = MaThe;
		this.MaNguoiMuon = MaNguoiMuon;
		this.TrangThai = TrangThai;
		this.MaKichHoat = MaKichHoat;
		this.NgayHetHan = NgayHetHan;
	}
	
	public String getMaThe() {
		return MaThe;
	}
	
	public void setMaThe(String MaThe) {
		this.MaThe = MaThe;
	}
	
	public String getMaNguoiMuon() {
		return MaNguoiMuon;
	}
	
	public void setMaNguoiMuon(String MaNguoiMuon) {
		this.MaNguoiMuon = MaNguoiMuon;
	}
	
	public int getTrangThai() {
		return TrangThai;
	}
	
	public void setTrangThai(int TrangThai) {
		this.TrangThai = TrangThai;
	}
	
	public String getMaKichHoat() {
		return MaKichHoat;
	}
	
	public void setMaKichHoat(String MaKichHoat) {
		this.MaKichHoat = MaKichHoat;
	}
	
	public Date getNgayHetHan() {
		return NgayHetHan;
	}
	
	public void setNgayHetHan(Date NgayHetHan) {
		this.NgayHetHan = NgayHetHan;
	}

}
