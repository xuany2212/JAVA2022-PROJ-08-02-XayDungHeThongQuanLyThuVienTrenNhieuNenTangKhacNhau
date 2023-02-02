package Model;

import java.sql.*;
import java.util.*;

public class DetailLentBook extends DataAccessHelper {

    private String         detail_lent_book_ID;
    private int         detail_lent_book_Status;  // 0: da dki ; 1: Dang muon ; 2 : da tra 
    private String      detail_lent_book_LentDate; // ngay muon
    private String      detail_lent_book_ReturnDate; // ngay tra
    private int         detail_lent_book_Fine; //tien phat
    private String      detail_lent_book_Reason; //lý do phạt

    private final String GET_DETAIL_LENT_BOOK = "SELECT * FROM QuanLyThuVien_3.chitietmuonsach where MaTTMuonTra=?";
    private final String DELETE_COPYOFBOOK_OF_DETAIL_BORROWER = "DELETE FROM chitietmuonsach WHERE MaTTMuonTra = ? and MaSachCP=?";
    private final String UPDATE_DETAIL_LENT_BOOK = "UPDATE chitietmuonsach SET trangThai='1',ngayMuon=? WHERE MaTTMuonTra=? and MaSachCP=? ";

    public DetailLentBook() {
    }

    public DetailLentBook(String detail_lent_book_ID, int detail_lent_book_Status, String detail_lent_book_LentDate, String detail_lent_book_ReturnDate, int detail_lent_book_Fine, String detail_lent_book_Reason) {
        this.detail_lent_book_ID = detail_lent_book_ID;
        this.detail_lent_book_Status = detail_lent_book_Status;
        this.detail_lent_book_LentDate = detail_lent_book_LentDate;
        this.detail_lent_book_ReturnDate = detail_lent_book_ReturnDate;
        this.detail_lent_book_Fine = detail_lent_book_Fine;
        this.detail_lent_book_Reason = detail_lent_book_Reason;
    }

    public String getdetail_lent_book_ID() {
        return detail_lent_book_ID;
    }

    public void setdetail_lent_book_ID(String detail_lent_book_ID) {
        this.detail_lent_book_ID = detail_lent_book_ID;
    }

    public int getdetail_lent_book_Status() {
        return detail_lent_book_Status;
    }

    public void setdetail_lent_book_Status(int detail_lent_book_Status) {
        this.detail_lent_book_Status = detail_lent_book_Status;
    }

    public String getdetail_lent_book_LentDate() {
        return detail_lent_book_LentDate;
    }

    public void setdetail_lent_book_LentDate(String detail_lent_book_LentDate) {
        this.detail_lent_book_LentDate = detail_lent_book_LentDate;
    }

    public String getdetail_lent_book_ReturnDate() {
        return detail_lent_book_ReturnDate;
    }

    public void setdetail_lent_book_ReturnDate(String detail_lent_book_ReturnDate) {
        this.detail_lent_book_ReturnDate = detail_lent_book_ReturnDate;
    }

    public int getdetail_lent_book_Fine() {
        return detail_lent_book_Fine;
    }

    public void setdetail_lent_book_Fine(int detail_lent_book_Fine) {
        this.detail_lent_book_Fine = detail_lent_book_Fine;
    }

    public String getdetail_lent_book_Reason() {
        return detail_lent_book_Reason;
    }

    public void setdetail_lent_book_Reason(String detail_lent_book_Reason) {
        this.detail_lent_book_Reason = detail_lent_book_Reason;
    }

    /**
     * Hàm này thực hiện chức năng lấy ra tất cả chi tiết mượn sách bao gồm mã
     * bản sao sách ngày mượn,trả tương ứng với mã thông tin mượn
     *
     * @param idBorrowerInformation mã thông tin mượn trả
     * @return Danh sách các thông tin chi tiết mượn trả sách
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<DetailLentBook> getListDetailLentBookByIdBorrowInfor(String idBorrowerInformation) throws ClassNotFoundException, SQLException {
        ArrayList<DetailLentBook> arr = new ArrayList<>();
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(GET_DETAIL_LENT_BOOK);
        ps.setString(1, idBorrowerInformation);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String book_ID = rs.getString("MaSachCP");
            int status = rs.getInt("trangThai");
            String lentDate = rs.getString("ngayMuon");
            String returnDate = rs.getString("ngayTra");
            int fineBorrower = rs.getInt("tienPhat");
            String reason = rs.getString("lyDoPhat");
            arr.add(new DetailLentBook(book_ID, status, lentDate, returnDate, fineBorrower, reason));
        }
        closeDatabase();
        return arr;
    }

    public void deleteDetailLentBook(String idBorrowingInfo, String book_ID) throws ClassNotFoundException, SQLException {
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(DELETE_COPYOFBOOK_OF_DETAIL_BORROWER);
        ps.setString(1, idBorrowingInfo);
        ps.setString(2, book_ID);
        ps.executeUpdate();
        closeDatabase();
    }

//    UPDATE chitietmuonsach SET trangThai='1',ngayMuon=? WHERE maThongTinMuonSach=? and maBanSaoSach=? 
    public void updateDetailLentBook(String idBorrowInfo) throws SQLException, ClassNotFoundException {
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(UPDATE_DETAIL_LENT_BOOK);
        ps.setString(1, detail_lent_book_LentDate);
        ps.setString(2, idBorrowInfo);
        ps.setString(3, detail_lent_book_ID);
        ps.executeUpdate();
        closeDatabase();
    }

    public void addInfomationDetail(Book book, int numberOfCopy, String maThongTinMuonTra) throws SQLException, ClassNotFoundException {
        connectDatabase();
        String sqlCommand = "INSERT INTO chitietmuonsach (MaTTMuonTra, MaSachCP,trangThai) VALUES (?, ?, 0)";
        PreparedStatement st = conn.prepareStatement(sqlCommand);
        st.setString(1, maThongTinMuonTra);
        st.setString(2, new CopyOfBook().getListCopyOfBookByIdBook(book.getbook_ID()).get(numberOfCopy - 1).getcopy_of_book_ID());
        st.executeUpdate();

    }

    /**
     * Hàm này trả về số sách còn đăng ký
     *
     * @param idBorrowingInfo mã thông tin đăng ký mượn
     * @return số sách còn đăng ký
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int getNumberBook(String idBorrowingInfo) throws ClassNotFoundException, SQLException {
        connectDatabase();
        int numberBook = 0;
        String sqlCommand = "select count(MaTTMuonTra) from chitietmuonsach where MaTTMuonTra =?";
        PreparedStatement st = conn.prepareStatement(sqlCommand);
        st.setString(1, idBorrowingInfo);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {

            numberBook = rs.getInt("count(MaTTMuonTra)");
        }

        closeDatabase();
        return numberBook;

    }
}
