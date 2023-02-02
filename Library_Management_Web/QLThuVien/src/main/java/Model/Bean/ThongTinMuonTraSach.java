package Model.Bean;

import java.sql.Date;

public class ThongTinMuonTraSach {
	
	private String MaTTMuonTra;
	private Date NgayDangKy;
	private Date HanTra;
	private String TrangThai;
	private Double TienCoc;
	private String MaNguoiMuon;
	
	public ThongTinMuonTraSach() {
		
	}
	
	public ThongTinMuonTraSach(String MaTTMuonTra, Date NgayDangKy, Date HanTra, String TrangThai, Double TienCoc, String MaNguoiMuon) {
		this.MaTTMuonTra = MaTTMuonTra;
		this.NgayDangKy = NgayDangKy;
		this.HanTra = HanTra;
		this.TrangThai = TrangThai;
		this.TienCoc = TienCoc;
		this.MaNguoiMuon = MaNguoiMuon;
	}
	
	public String getMaTTMuonTra() {
		return MaTTMuonTra;
	}
	
	public void setMaTTMuonTra(String MaTTMuonTra) {
		this.MaTTMuonTra = MaTTMuonTra;
	}
	
	public Date getNgayDangKy() {
		return NgayDangKy;
	}
	
	public void setNgayDangKy(Date NgayDangKy) {
		this.NgayDangKy = NgayDangKy;
	}
	
	public Date getHanTra() {
		return HanTra;
	}
	
	public void setHanTra(Date HanTra) {
		this.HanTra = HanTra;
	}
	
	public String getTrangThai() {
		return TrangThai;
	}
	
	public void setTrangThai(String TrangThai) {
		this.TrangThai = TrangThai;
	}
	
	public Double getTienCoc() {
		return TienCoc;
	}
	
	public void setTienCoc(Double TienCoc) {
		this.TienCoc = TienCoc;
	}
	
	public String getMaNguoiMuon() {
		return MaNguoiMuon;
	}
	
	public void setMaNguoiMuon(String MaNguoiMuon) {
		this.MaNguoiMuon = MaNguoiMuon;
	}
}
