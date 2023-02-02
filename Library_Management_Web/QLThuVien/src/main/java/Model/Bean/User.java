package Model.Bean;

public class User {
	private String id;
	private String MaTK;
	private String MatKhau;
	private String HoTen;
	private String GioiTinh;
	private String SDT;
	private String Email;

	public User() {

	}

	public User(String MaTK, String MatKhau, String HoTen, String GioiTinh, String SDT, String Email) {
		this.MaTK = MaTK;
		this.MatKhau = MatKhau;
		this.HoTen = HoTen;
		this.GioiTinh = GioiTinh;
		this.SDT = SDT;
		this.Email = Email;
	}

	public String getMaTK() {
		return MaTK;
	}

	public void setMaTK(String MaTK) {
		this.MaTK = MaTK;
	}

	public String getMatKhau() {
		return MatKhau;
	}

	public void setMatKhau(String MatKhau) {
		this.MatKhau = MatKhau;
	}

	public String getHoTen() {
		return HoTen;
	}

	public void setHoTen(String HoTen) {
		this.HoTen = HoTen;
	}

	public String getGioiTinh() {
		return GioiTinh;
	}

	public void setGioiTinh(String GioiTinh) {
		this.GioiTinh = GioiTinh;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String SDT) {
		this.SDT = SDT;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String Email) {
		this.Email = Email;
	}
	
}
