package Model;

import java.sql.*;
import java.util.*;

public class Publisher extends DataAccessHelper {

    private int     publisher_ID;
    private String  publisher_Name;
    
    private final String GET_LIST_PUBLISHER = "SELECT * FROM QuanLyThuVien_3.nhaphathanh";
    private final String GET_MAX_ID = "SELECT MAX(MaNPH) FROM QuanLyThuVien_3.nhaphathanh";
    private final String ADD_PUBLISHER = "INSERT INTO QuanLyThuVien_3.nhaphathanh VALUES (?,?)";

    public Publisher() {}

    public Publisher(int publisher_ID, String publisher_Name) {
        this.publisher_ID = publisher_ID;
        this.publisher_Name = publisher_Name;
    }

    public int getpublisher_ID() {
        return publisher_ID;
    }

    public void setpublisher_ID(int publisher_ID) {
        this.publisher_ID = publisher_ID;
    }

    public String getpublisher_Name() {
        return publisher_Name;
    }

    public void setpublisher_Name(String publisher_Name) {
        this.publisher_Name = publisher_Name;
    }

    /**
     * Hàm này lấy ra tên nhà phát hành từ mã nhà phát hành
     * @param idPublisher mã nhà phát hành bạn muốn lấy tên
     * @return tên tác giả
     * @throws ClassNotFoundException
     * @throws SQLException
     */

    public Publisher getPublisherByIdPublisher(int idPublisher) throws SQLException, ClassNotFoundException {
        Publisher publisher = null;
        connectDatabase();
        PreparedStatement st = conn.prepareStatement("SELECT * FROM QuanLyThuVien_3.nhaphathanh where MaNPH =?");
        st.setInt(1, idPublisher);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            String namePublisher = rs.getString("TenNPH");
            publisher = new Publisher(idPublisher, namePublisher);
        }
        closeDatabase();
        return publisher;
    }

    /**
     * Hàm này lấy ra danh sách các nhà phát hành có trong hệ thống.
     * @return danh sách các nhà phát hành có trong hệ thống
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<Publisher> getListPublishers() throws ClassNotFoundException, SQLException {
        ArrayList<Publisher> publishers = new ArrayList<>();
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(GET_LIST_PUBLISHER);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int idPublisher = rs.getInt("MaNPH");
            String namePublisher = rs.getString("TenNPH");
            publishers.add(new Publisher(idPublisher, namePublisher));
        }
        closeDatabase();
        return publishers;
    }

    /**
     * Hàm này thêm mới một nhà phát hành vào trong hệ thống.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void addPublisher() throws ClassNotFoundException, SQLException {
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(ADD_PUBLISHER);
        ps.setInt(1, publisher_ID);
        ps.setString(2, publisher_Name);
        ps.executeUpdate();
        closeDatabase();
    }

    /**
     * Hàm này lấy ra mã lớn nhất của các nhà phát hànhcó trong hệ thống.
     * @return mã lớn nhất của nhà phát hành có trong hệ thống
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int getMaxIdPublisher() throws ClassNotFoundException, SQLException {
        int maxId = -1;
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(GET_MAX_ID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
           maxId = rs.getInt(1);
        }
        closeDatabase();
        return maxId;
    }

}
