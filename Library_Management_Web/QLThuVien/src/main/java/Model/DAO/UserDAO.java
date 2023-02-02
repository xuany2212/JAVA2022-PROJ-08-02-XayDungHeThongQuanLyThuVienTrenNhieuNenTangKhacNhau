package Model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Model.Bean.User;

public class UserDAO extends ConnectDatabase {

	public UserDAO(String jdbcURL) throws SQLException {
		super(jdbcURL);
		// TODO Auto-generated constructor stub
	}

	public User getUser(String username, String password) throws ClassNotFoundException, SQLException {
		openConnection();
		String sql = "select * from TaiKhoan\r\n" + "where [MaTK] = ? and [MatKhau] = ?";
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setEscapeProcessing(true);
		statement.setQueryTimeout(15);
		statement.setString(1, username);
		statement.setString(2, password);
		ResultSet res = statement.executeQuery();
		while (res.next()) {
			return new User(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5),
					res.getString(5));
		}
		return null;
	}

	public User findUser(String username) throws ClassNotFoundException, SQLException {
//		if (conn == null)
//			conn = ConnectDatabase.getMySQLConnection();
//		String sql = "Select * from User where username=?";
//
//		PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
//		pstm.setString(1, username);
//		ResultSet rs = pstm.executeQuery();
//
//		while (rs.next()) {
//			String id = rs.getString("id");
//			String password = rs.getString("password");
//			User user = new User();
//			user.setId(id);
//			user.setUsername(username);
//			user.setPassword(password);
//			return user;
//		}
		return null;
	}

}
