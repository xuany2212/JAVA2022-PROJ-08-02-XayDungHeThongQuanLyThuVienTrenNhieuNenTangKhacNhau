package Model.Bean;

public class TacGia {
	
	private String MaTacGia;
	private String TenTacGia;
	
	public TacGia() {
		
	}
	
	public TacGia(String MaTacGia, String TenTacGia) {
		this.MaTacGia = MaTacGia;
		this.TenTacGia = TenTacGia;
	}
	
	public String getMaTacGia() {
		return MaTacGia;
	}
	
	public void setMaTacGia(String MaTacGia) {
		this.MaTacGia = MaTacGia;
	}
	
	public String getTenTacGia() {
		return TenTacGia;
	}
	
	public void setTenTacGia(String TenTacGia) {
		this.TenTacGia = TenTacGia;
	}
}
