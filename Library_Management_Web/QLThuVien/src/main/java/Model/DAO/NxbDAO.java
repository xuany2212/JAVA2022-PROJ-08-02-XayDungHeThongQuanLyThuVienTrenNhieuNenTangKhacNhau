package Model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Bean.Book;
import Model.Bean.NhaPhatHanh;

public class NxbDAO extends ConnectDatabase {

	public NxbDAO(String jdbcURL) throws SQLException {
		super(jdbcURL);
		// TODO Auto-generated constructor stub
	}
	
	public int insertNxb(NhaPhatHanh nxb) throws SQLException, ClassNotFoundException {
		openConnection();
		int result = 0;
		String sql = "INSERT INTO NhaPhatHanh(TenNPH) VALUES (N'"+nxb.getTenNPH()+"')";
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		result = statement.executeUpdate();
		System.out.println("Ketqua NXB" + result);
		return result;
	}

	public List<NhaPhatHanh> getAllNxb() throws ClassNotFoundException, SQLException {
		openConnection();
		List<NhaPhatHanh> list = new ArrayList<>();
		String sql = "select * from NhaPhatHanh";
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setEscapeProcessing(true);
		statement.setQueryTimeout(15);
		ResultSet res = statement.executeQuery();

		while (res.next()) {
			list.add(new NhaPhatHanh(res.getInt(1), res.getString(2)));
		}
		closeConnection();
		return list;
	}
}
