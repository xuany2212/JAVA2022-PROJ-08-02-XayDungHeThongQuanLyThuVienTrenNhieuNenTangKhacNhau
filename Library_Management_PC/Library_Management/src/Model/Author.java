package Model;

import java.sql.*;
import java.util.*;

public class Author extends DataAccessHelper {

    private String author_ID;
    private String author_Name;

    private final String GET_LIST_AUTHOR = "SELECT * FROM QuanLyThuVien_3.TacGia";
    private final String GET_MAX_ID = "SELECT Max(MaTacGia) FROM QuanLyThuVien_3.TacGia";
    private final String ADD_AUTHOR = "INSERT INTO QuanLyThuVien_3.TacGia VALUES (?,?)";

    public Author() {
    }

    public Author(String author_ID, String author_Name) {
        this.author_ID = author_ID;
        this.author_Name = author_Name;
    }

    public String getauthor_ID() {
        return author_ID;
    }

    public void setauthor_ID(String author_ID) {
        this.author_ID = author_ID;
    }

    public String getauthor_Name() {
        return author_Name;
    }

    public void setauthor_Name(String author_Name) {
        this.author_Name = author_Name;
    }

    /**
     * Hàm này lấy ra tên tác giả từ mã tác giả
     * 
     * @param idAuthor mã tác gải bạn muốn lấy tên
     * @return tên tác giả
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String getNameAuthorByIdAuthor(String idAuthor) throws ClassNotFoundException, SQLException {
        String nameAuthor = "";
        connectDatabase();
        PreparedStatement st = conn.prepareStatement("SELECT * FROM QuanLyThuVien_3.TacGia where MaTacGia =?");
        st.setString(1, idAuthor);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            nameAuthor = rs.getString("TenTacGia");
        }
        closeDatabase();
        return nameAuthor;
    }

    /**
     * Hàm này lấy ra danh sách các tác giả có trong hệ thống.
     * 
     * @return danh sách các tác giả có trong hệ thống
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<Author> getListAuthor() throws ClassNotFoundException, SQLException {
        ArrayList<Author> authors = new ArrayList<>();
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(GET_LIST_AUTHOR);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String idAuthor = rs.getString("MaTacGia");
            String nameAuthor = rs.getString("TenTacGia");
            authors.add(new Author(idAuthor, nameAuthor));
        }
        closeDatabase();
        return authors;
    }

    /**
     * Hàm này lấy ra mã lớn nhất của các tác giả có trong hệ thống.
     * 
     * @return mã lớn nhất của tác giả có trong hệ thống
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String getMaxIdAuthor() throws ClassNotFoundException, SQLException {
        String maxId = null;
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(GET_MAX_ID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            maxId = rs.getString(1);
        }
        closeDatabase();
        return maxId;
    }

    /**
     * Hàm này thêm mới một tác giả vào trong hệ thống.
     * 
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void addAuthor() throws ClassNotFoundException, SQLException {
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(ADD_AUTHOR);
        ps.setString(1, author_ID);
        ps.setString(2, author_Name);
        ps.executeUpdate();
        closeDatabase();
    }

}
