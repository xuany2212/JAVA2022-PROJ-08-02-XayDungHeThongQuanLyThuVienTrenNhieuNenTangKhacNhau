package Model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Bean.Book;

public class BookDAO extends ConnectDatabase {

	public BookDAO(String jdbcURL) throws SQLException {
		super(jdbcURL);
		// TODO Auto-generated constructor stub
	}

//	Connection conn = null;
//	Statement st = null;
//	PreparedStatement preSt = null;
//	CategoryBO categoryBO = new CategoryBO();
//	ReaderBO readerBO = new ReaderBO();
//
	public Book findBook(String id) throws SQLException, ClassNotFoundException {
		openConnection();
		String sql = "Select * from Sach where MaSach = ?";
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, id);

		ResultSet res = statement.executeQuery();

		while (res.next()) {
			Book book = new Book(res.getString(1), res.getString(2), res.getString(3), res.getDouble(4),
					res.getString(5), res.getString(6), res.getInt(7), res.getInt(8));
			return book;
		}
		return null;
	}

	public int insertBook(Book book) throws SQLException, ClassNotFoundException {
		openConnection();
		int result = 0;
		String sql = "INSERT INTO Sach(TenSach, TenTacGia, GiaThue, MoTa, ViTri, SoLuongCP, MaNPH) " + "VALUES (N'"
				+ book.getTenSach() + "', N'" + book.getTenTacGia() + "', " + book.getGiaThue() + ", N'"
				+ book.getMoTa() + "', N'" + book.getViTri() + "', " + book.getSoLuongCP() + ", " + book.getMaNPH()
				+ ")";
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
//		statement.setString(1, book.getTenSach());
//		statement.setString(2, book.getTenTacGia());
//		statement.setDouble(3, book.getGiaThue());
////		statement.setString(4, book.getMoTa());
//		statement.setInt(4, book.getMaNPH());
		result = statement.executeUpdate();
		System.out.println("Ketqua" + result);
		return result;
	}

	public List<Book> getAllBook() throws ClassNotFoundException, SQLException {
		openConnection();
		List<Book> list = new ArrayList<>();
		String sql = "Select * from Sach";
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setEscapeProcessing(true);
		statement.setQueryTimeout(15);
		ResultSet res = statement.executeQuery();

		while (res.next()) {
			list.add(new Book(res.getString(1), res.getString(2), res.getString(3), res.getDouble(4), res.getString(5),
					res.getString(6), res.getInt(7), res.getInt(8)));
		}
		closeConnection();
		return list;
	}

	public List<Book> getSearchBook(String name_search) throws ClassNotFoundException, SQLException {
		openConnection();
		List<Book> list = new ArrayList<>();
		String sql = "Select * from Sach where TenSach like '%" + name_search + "%';";
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		ResultSet res = statement.executeQuery();

		while (res.next()) {
			list.add(new Book(res.getString(1), res.getString(2), res.getString(3), res.getDouble(4), res.getString(5),
					res.getString(6), res.getInt(7), res.getInt(8)));
		}
		closeConnection();
		System.out.println(list);
		return list;
	}

	public int updateBook(Book book) throws SQLException, ClassNotFoundException {
		openConnection();
		System.out.println("book " + book);
		int result = 0;
		String sql = "Update Sach set TenSach = N'"+book.getTenSach()+ "' ,TenTacGia = N'"+book.getTenTacGia()+"',"
				+ "GiaThue ="+book.getGiaThue()+",MoTa =N'"+book.getMoTa()+"',"
						+ "ViTri=N'"+book.getViTri()+"', SoLuongCP="+book.getSoLuongCP()+", MaNPH="+book.getMaNPH()+" where MaSach= N'"+book.getMaSach()+"' ";
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		System.out.println("Thanh cong khong " + statement);
		result = statement.executeUpdate();
		System.out.println("Thanh cong khong");
		return result;
	}

	public int deleteAllBook() throws ClassNotFoundException, SQLException {
		openConnection();
		List<Book> list = new ArrayList<>();
		int result = 0;

		// readerBO.deleteAllReader();
		String sql = "Delete From Sach";
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		result = statement.executeUpdate();
		return result;
	}

	public int deleteBook(String id) throws ClassNotFoundException, SQLException {
		openConnection();
		int result = 0;
//		
//		readerBO.deleteBookReader(id);
		String sql = "Delete From Sach where MaSach= ?";
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, id);
		result = statement.executeUpdate();
		return result;
	}

//	public int deleteBookCategory(String category_id) throws ClassNotFoundException, SQLException {
//		int result = 0;
//		if (conn == null)
//			conn = ConnectDatabase.getMySQLConnection();
//		readerBO.deleteBookReaderCategory(category_id);
////		String sql = "DELETE  Reader, Book FROM Reader INNER JOIN Book ON Reader.book_id = Book.id WHERE Book.category_id=?;";
//		String sql = "Delete From Book where category_id= ?";
//		PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
//		pstm.setString(1, category_id);
//		result = pstm.executeUpdate();
//		return result;
//	}
}
