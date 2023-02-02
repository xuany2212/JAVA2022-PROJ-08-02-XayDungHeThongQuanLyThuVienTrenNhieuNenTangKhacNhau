package Model.Bean;


import java.sql.Date;

public class ChiTietMuonSach {
	
	private int STT;
	private String MaTTMuonTra;
	private int TrangThai;
	private Date NgayMuon;
	private Date NgayTra;
	private Double TienPhat;
	private String LyDoPhat;
	
	public ChiTietMuonSach() {
		super();
	}
	
	public ChiTietMuonSach(int STT, String MaTTMuonTra, int TrangThai, Date NgayMuon, Date NgayTra, Double TienPhat, String LyDoPhat) {
		this.STT = STT;
		this.MaTTMuonTra = MaTTMuonTra;
		this.TrangThai = TrangThai;
		this.NgayMuon = NgayMuon;
		this.NgayTra = NgayTra;
		this.TienPhat = TienPhat;
		this.LyDoPhat = LyDoPhat;
	}
	
	public int getSTT() {
		return STT;
	}
	
	public void setSTT(int STT) {
		this.STT = STT;
	}
	
	public String getMaTTMuonTra() {
		return MaTTMuonTra;
	}
	
	public void setMaTTMuonTra(String MaTTMuonTra) {
		this.MaTTMuonTra = MaTTMuonTra;
	}
	
	public int getTrangThai() {
		return TrangThai;
	}
	
	public void setTrangThai(int TrangThai) {
		this.TrangThai = TrangThai;
	}
	
	public Date getNgayMuon() {
		return NgayMuon;
	}
	
	public void setNgayMuon(Date NgayMuon) {
		this.NgayMuon = NgayMuon;
	}
	
	public Date getNgayTra() {
		return NgayTra;
	}
	
	public void setNgayTra(Date NgayTra) {
		this.NgayTra = NgayTra;
	}
	
	public Double getTienPhat() {
		return TienPhat;
	}
	
	public void setTienPhat(Double TienPhat) {
		this.TienPhat = TienPhat;
	}
	
	public String getLyDoPhat() {
		return LyDoPhat;
	}
	
	public void setLyDoPhat(String LyDoPhat) {
		this.LyDoPhat = LyDoPhat;
	}
	
}
