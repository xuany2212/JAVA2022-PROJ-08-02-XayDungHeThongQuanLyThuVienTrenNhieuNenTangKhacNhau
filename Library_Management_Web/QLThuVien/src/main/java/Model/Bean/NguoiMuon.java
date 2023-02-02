package Model.Bean;


public class NguoiMuon {

	private String MaNguoiMuon;
	private String MaTK;
	private int MSSV;
	private Double TienCoc;
	private String GiaiDoanHoc;

	public NguoiMuon() {

	}

	public NguoiMuon(String MaNguoiMuon, String MaTK, int MSSV, Double TienCoc, String GiaiDoanHoc) {
		this.MaNguoiMuon = MaNguoiMuon;
		this.MaTK = MaTK;
		this.MSSV = MSSV;
		this.TienCoc = TienCoc;
		this.GiaiDoanHoc = GiaiDoanHoc;
	}
	
	public String getMaNguoiMuon() {
		return MaNguoiMuon;
	}
	
	public void setMaNguoiMuon(String MaNguoiMuon) {
		this.MaNguoiMuon = MaNguoiMuon;
	}
	
	public String getMaTK() {
		return MaTK;
	}
	
	public void setMaTK(String MaTK) {
		this.MaTK = MaTK;
	}
	
	public int getMSSV() {
		return MSSV;
	}
	
	public void setMSSV(int MSSV) {
		this.MSSV = MSSV;
	}
	
	public Double getTienCoc() {
		return TienCoc;
	}
	
	public void setTienCoc(Double TienCoc) {
		this.TienCoc = TienCoc;
	}
	
	public String getGiaiDoanHoc() {
		return GiaiDoanHoc;
	}
	
	public void setGiaiDoanHoc(String GiaiDoanHoc) {
		this.GiaiDoanHoc = GiaiDoanHoc;
	}

}
