package Model.Bean;

public class Book {

	private String MaSach;
	private String TenSach;
	private String TenTacGia;
	private Double GiaThue;
	private String MoTa;
	private String ViTri;
	private int SoLuongCP;
	private int MaNPH;

	public Book() {
	}

	public Book(String maSach, String tenSach, String tenTacGia, Double giaThue, String moTa, String viTri,
			int soLuongCP, int maNPH) {
		super();
		MaSach = maSach;
		TenSach = tenSach;
		TenTacGia = tenTacGia;
		GiaThue = giaThue;
		MoTa = moTa;
		ViTri = viTri;
		SoLuongCP = soLuongCP;
		MaNPH = maNPH;
	}

	public String getMaSach() {
		return MaSach;
	}

	public void setMaSach(String MaSach) {
		this.MaSach = MaSach;
	}

	public String getTenSach() {
		return TenSach;
	}

	public void setTenSach(String TenSach) {
		this.TenSach = TenSach;
	}

	public String getTenTacGia() {
		return TenTacGia;
	}

	public void setTenTacGia(String TenTacGia) {
		this.TenTacGia = TenTacGia;
	}

	public int getMaNPH() {
		return MaNPH;
	}

	public void setMaNPH(int MaNPH) {
		this.MaNPH = MaNPH;
	}

	public Double getGiaThue() {
		return GiaThue;
	}

	public void setGiaThue(Double GiaThue) {
		this.GiaThue = GiaThue;
	}

	public String getMoTa() {
		return MoTa;
	}

	public void setMoTa(String MoTa) {
		this.MoTa = MoTa;
	}

	public String getViTri() {
		return ViTri;
	}

	public void setViTri(String viTri) {
		ViTri = viTri;
	}

	public int getSoLuongCP() {
		return SoLuongCP;
	}

	public void setSoLuongCP(int soLuongCP) {
		SoLuongCP = soLuongCP;
	}

	@Override
	public String toString() {
		return "Sach{" + "MaSach=" + MaSach + ", TenSach=" + TenSach + ", TenTacGia=" + TenTacGia + ", GiaThue="
				+ GiaThue + ", MoTa=" + MoTa + ", MaNPH=" + MaNPH + '}';
	}
}
