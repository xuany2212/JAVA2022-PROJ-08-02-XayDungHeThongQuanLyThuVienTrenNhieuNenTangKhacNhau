package Model.Bean;


public class BanSaoSach {
	private String MaSachCP;
	private String ViTri;
	private int TrangThai;
	private String MaSach;
	
	public BanSaoSach() {
	}
	
	public BanSaoSach(String MaSachCP, String ViTri, int TrangThai, String MaSach) {
		this.MaSachCP = MaSachCP;
		this.ViTri = ViTri;
		this.TrangThai = TrangThai;
		this.MaSach = MaSach;
	}
	
	public String getMaSachCP() {
		return MaSachCP;
	}
	
	public void setMaSachCP(String MaSachCP) {
		this.MaSachCP = MaSachCP;
	}
	
	public String getViTri() {
		return ViTri;
	}
	
	public void setViTri(String ViTri) {
		this.ViTri = ViTri;
	}
	
	public int getTrangThai() {
		return TrangThai;
	}
	
	public void setTrangThai(int TrangThai) {
		this.TrangThai = TrangThai;
	}
	
	public String getMaSach() {
		return MaSach;
	}
	
	public void setMaSach(String MaSach) {
		this.MaSach = MaSach;
	}
	
}
