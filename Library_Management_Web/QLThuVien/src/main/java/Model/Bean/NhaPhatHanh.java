package Model.Bean;

public class NhaPhatHanh {
	private int maNPH;
	private String tenNPH;

	public NhaPhatHanh(int maNPH) {
		super();
		this.maNPH = maNPH;
	}

	public NhaPhatHanh() {
		super();

	}

	public NhaPhatHanh(int maNPH, String tenNPH) {
		super();
		this.maNPH = maNPH;
		this.tenNPH = tenNPH;
	}

	public int getMaNPH() {
		return maNPH;
	}

	public void setMaNPH(int maNPH) {
		this.maNPH = maNPH;
	}

	public String getTenNPH() {
		return tenNPH;
	}

	public void setTenNPH(String tenNPH) {
		this.tenNPH = tenNPH;
	}

}
