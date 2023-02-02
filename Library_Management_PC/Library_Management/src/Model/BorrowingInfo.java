/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javafx.scene.input.ClipboardContent;
import static Model.DataAccessHelper.conn;

public class BorrowingInfo extends DataAccessHelper {

    private String          borrowing_info_ID;
    private Borrower        borrowing_info_Borrower;
    private String          borrowing_info_Borrowed_Date;
    private int             borrowing_info_Num_of_Book;
    private String          borrowing_info_Expect_Return_Date;
    private float             borrowing_info_Deposit;
    private BorrowStatus    borrowing_info_Status;

    private final String GET_LIST_BORROWINGINFORMATION_BY_BORROWER = "SELECT * FROM QuanLyThuVien_3.thongtinmuontrasach where maNguoiMuon = ?";
    private final String DELETE_BORROWING_INFOR_BY_ID = "DELETE FROM thongtinmuontrasach WHERE MaTTMuonTra=?";
    private final String UPDATE_LENT_BOOK = "UPDATE thongtinmuontrasach SET hanTra=?, trangThai=?, TienCoc=? WHERE MaTTMuonTra=?";

    public BorrowingInfo() {
    }

    public BorrowingInfo(String borrowing_info_ID, Borrower borrowing_info_Borrower, String borrowing_info_Borrowed_Date, String borrowing_info_Expect_Return_Date, float borrowing_info_Deposit, BorrowStatus borrowing_info_Status) {
        this.borrowing_info_ID = borrowing_info_ID;
        this.borrowing_info_Borrower = borrowing_info_Borrower;
        this.borrowing_info_Borrowed_Date = borrowing_info_Borrowed_Date;
        this.borrowing_info_Expect_Return_Date = borrowing_info_Expect_Return_Date;
        this.borrowing_info_Deposit = borrowing_info_Deposit;
        this.borrowing_info_Status = borrowing_info_Status;
    }

    public String getborrowing_info_ID() {
        return borrowing_info_ID;
    }

    public void setborrowing_info_ID(String borrowing_info_ID) {
        this.borrowing_info_ID = borrowing_info_ID;
    }

    public Borrower getborrowing_info_Borrower() {
        return borrowing_info_Borrower;
    }

    public void setborrowing_info_Borrower(Borrower borrowing_info_Borrower) {
        this.borrowing_info_Borrower = borrowing_info_Borrower;
    }

    public int getborrowing_info_Num_of_Book() {
        return borrowing_info_Num_of_Book;
    }

    public void setborrowing_info_Num_of_Book(int borrowing_info_Num_of_Book) {
        this.borrowing_info_Num_of_Book = borrowing_info_Num_of_Book;
    }

    public String getborrowing_info_Borrowed_Date() {
        return borrowing_info_Borrowed_Date;
    }

    public void setborrowing_info_Borrowed_Date(String borrowing_info_Borrowed_Date) {
        this.borrowing_info_Borrowed_Date = borrowing_info_Borrowed_Date;
    }

    public String getborrowing_info_Expect_Return_Date() {
        return borrowing_info_Expect_Return_Date;
    }

    public void setborrowing_info_Expect_Return_Date(String borrowing_info_Expect_Return_Date) {
        this.borrowing_info_Expect_Return_Date = borrowing_info_Expect_Return_Date;
    }

    public float getborrowing_info_Deposit() {
        return borrowing_info_Deposit;
    }

    public void setborrowing_info_Deposit(int borrowing_info_Deposit) {
        this.borrowing_info_Deposit = borrowing_info_Deposit;
    }

    public BorrowStatus getborrowing_info_Status() {
        return borrowing_info_Status;
    }

    public void setborrowing_info_Status(BorrowStatus borrowing_info_Status) {
        this.borrowing_info_Status = borrowing_info_Status;
    }

    public static enum BorrowStatus {
        BORROWED(0),
        LENDING(1),
        RETURNED(2);

        private int value;

        private BorrowStatus(int value) {
            this.value = value;
        }
    }

    /**
     * Hàm này thực hiện chức năng lấy ra danh sách các thông tin mượn trả sách
     * tương ứng với người mượn
     *
     * @param borrower đối tượng Borrower muốn lấy thông tin mượn trả sách
     * @return danh sách các thông tin mượn trả cảu đối tượng người mượn ở trên
     */
    public ArrayList<BorrowingInfo> getListBorrowingInformationByBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
        ArrayList<BorrowingInfo> arr = new ArrayList<>();
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(GET_LIST_BORROWINGINFORMATION_BY_BORROWER);
        ps.setString(1, borrower.getborrower_ID());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String idBorrowingInformation = rs.getString("MaTTMuonTra");
            String borrowedDate = rs.getString("ngayDangKi");
            String expectReturnDate = rs.getString("hanTra");
            int deposit = rs.getInt("TienCoc");

            BorrowStatus borrowStatus = null;
            if (rs.getInt("trangThai") == 0) {
                borrowStatus = BorrowStatus.BORROWED;
            } else if (rs.getInt("trangThai") == 1) {
                borrowStatus = BorrowStatus.LENDING;
            } else if (rs.getInt("trangThai") == 2) {
                borrowStatus = BorrowStatus.RETURNED;
            };

            arr.add(new BorrowingInfo(idBorrowingInformation, borrower, borrowedDate, expectReturnDate, deposit, borrowStatus));
        }
        closeDatabase();
        return arr;
    }

    /**
     * Hàm này thực hiện chức năng xóa thông tin mượn trả sách trong  CSDL
     * @param idBorrowingInformation mã thông tin mượn trả muốn xóa
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void deleteBorrowingInfor(String idBorrowingInformation) throws ClassNotFoundException, SQLException {
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(DELETE_BORROWING_INFOR_BY_ID);
        ps.setString(1, idBorrowingInformation);
        ps.executeUpdate();
        closeDatabase();
    }

    /**
     * Hàm này thực hiện chức năng cập nhật thông tin mượn trả thành đang mượn
     * @return true Cập nhật thành công
     *         false nếu không thành công
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public boolean updateLentBook() throws ClassNotFoundException, SQLException {
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(UPDATE_LENT_BOOK);
        ps.setString(1, borrowing_info_Expect_Return_Date);
        ps.setInt(2, 1);
        ps.setFloat(3, borrowing_info_Deposit);
        ps.setString(4, borrowing_info_ID);
        int i = ps.executeUpdate();
        closeDatabase();
        return i == -1 ? false : true;
    }

    /**
     * Hàm này để lấy thông tin mượn theo mã người mượn
     * @param borrowerId là mã người mượn
     * @return ArrayList<BorrowingInformation> là thông tin mượn sách của người
     * mượn
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     *
     */
    public ArrayList<BorrowingInfo> getBorrowingInfo(String borrowerId) throws ClassNotFoundException, SQLException {
        ArrayList<BorrowingInfo> info = new ArrayList<>();
        connectDatabase();
        PreparedStatement st = conn.prepareStatement("SELECT  chitietmuonsach.ngayMuon, chitietmuonsach.ngayTra, bansaosach.maSach, sach.tenSach, tacgia.tenTacGia, nhaphathanh.TenNPH, thongtinmuontrasach.ngayDangKi, thongtinmuontrasach.hanTra, bansaosach.trangThai "
                + "FROM QuanLyThuVien_3.thongtinmuontrasach,QuanLyThuVien_3.bansaosach,QuanLyThuVien_3.chitietmuonsach, QuanLyThuVien_3.sach, QuanLyThuVien_3.tacgia,QuanLyThuVien_3.chitiettacgia,QuanLyThuVien_3.nhaphathanh "
                + "where thongtinmuontrasach.manguoimuon = ? and thongtinmuontrasach.maThongTinMuonTraSach = chitietmuonsach.maThongTinMuonSach and chitietmuonsach.maBanSaoSach = bansaosach.maBanSao and bansaosach.maSach = sach.maSach and sach.maSach = chitiettacgia.maSach "
                + "and tacgia.maTacGia = chitiettacgia.maTacGia and sach.maNhaPhatHanh = nhaphathanh.maNhaPhatHanh and thongtinmuontrasach.trangThai = 0  group by sach.maSach");

        st.setString(1, borrowerId);

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            String borrowedDate = rs.getString("ngayDangKi");
            String expectReturnDate = rs.getString("hanTra");
            BorrowingInfo temp = new BorrowingInfo();
            temp.setborrowing_info_Borrowed_Date(borrowedDate);
            temp.setborrowing_info_Expect_Return_Date(expectReturnDate);

            info.add(temp);
        }
        return info;
    }

    /*Hàm này để lấy về danh sách mã sách đã được mượn theo mã người mượn
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public Vector<String> getListIdBook(String maNguoiMuon) throws SQLException, ClassNotFoundException {
        connectDatabase();
        Vector<String> listIdBook = new Vector<>();
        PreparedStatement st1 = conn.prepareStatement("select bansaosach.maSach from nguoimuon, bansaosach, chitietmuonsach,thongtinmuontrasach where nguoimuon.maNguoiMuon=\"" + maNguoiMuon + "\"\n"
                + "and nguoimuon.maNguoiMuon=thongtinmuontrasach.maNguoiMuon\n" + " and thongtinmuontrasach.trangThai = 0 "
                + "and thongtinmuontrasach.maTTMuonTra= chitietmuonsach.maTTMuonTra\n"
                + " and chitietmuonsach.maSachCP = bansaosach.maSachCP group by bansaosach.maSach");
        ResultSet rs1 = st1.executeQuery();
        while (rs1.next()) {
            listIdBook.add(rs1.getString("maSach"));
            System.out.println(rs1.getString("maSach"));
        }
        return listIdBook;
    }

    /*Hàm này để lấy về số sách đã mượn theo mã người mượn
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public int getNumberBook(String maNguoiMuon) throws SQLException, ClassNotFoundException {
        connectDatabase();
        PreparedStatement st1 = conn.prepareStatement("SELECT count(MaTTMuonTra) FROM QuanLyThuVien_3.chitietmuonsach, thongtinmuontrasach where maNguoiMuon = \"" + maNguoiMuon + "\" and thongtinmuontrasach.trangThai =0 and thongtinmuontrasach.maTTMuonTra = chitietmuonsach.maTTMuonTra");
        ResultSet rs1 = st1.executeQuery();
        int numberCoppy = 0;
        while (rs1.next()) {
            numberCoppy = rs1.getInt("count(maTTMuonTra)");
        }
        return numberCoppy;
    }

    /*Hàm này để cập nhật thông tin mượn của người dùng và trạng thái sách sau khi hủy đăng ký mượn
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public boolean updateInfomation(Borrower borrower, Book book, int numberCopy) throws ClassNotFoundException, SQLException {
        connectDatabase();

        PreparedStatement st = conn.prepareStatement("Select MaSachCP from bansaosach where maSach = \"" + book.getbook_ID() + "\"and ViTri =" + numberCopy);
        ResultSet rs = st.executeQuery();
        String maBanSao = null;
        while (rs.next()) {
            maBanSao = rs.getString("MaSachCP");
        }
        PreparedStatement st1 = conn.prepareStatement("Select MaTTMuonTra from chitietmuonsach where MaSachCP = \"" + maBanSao + "\"and maThongTinMuonSach in (Select MaTTMuonTra from thongtinmuontrasach where maNguoiMuon= \"" + borrower.getborrower_ID() + "\")");
        ResultSet rs1 = st1.executeQuery();
        while (rs1.next()) {
            PreparedStatement st2 = conn.prepareStatement("Delete from chitietmuonsach where MaSachCP=\"" + maBanSao + "\"");
            st2.execute();
            PreparedStatement st4 = conn.prepareStatement("UPDATE bansaosach SET trangThai='0' WHERE MaSachCP=\"" + maBanSao + "\"");
            st4.execute();
        }
        return true;

    }

    public boolean checkRegistrationFromBorrower(String idBorrower) throws SQLException, ClassNotFoundException {
        connectDatabase();
        String sqlCommand = "Select * from thongtinmuontrasach where maNguoiMuon = ? and trangThai=0";

        PreparedStatement st = conn.prepareStatement(sqlCommand);
        st.setString(1, idBorrower);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            closeDatabase();
            return true;

        }

        closeDatabase();
        return false;
    }

    /*Hàm này để cập nhật thông tin mượn sách và trạng thái sách của người mượn sau khi đăng ký mượn
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public String addInfomation(Borrower borrower) throws ClassNotFoundException, SQLException {
        connectDatabase();
        Date date = new Date();
        String sqlCommand2 = "INSERT INTO thongtinmuontrasach (MaTTMuonTra, ngayDangKi, maNguoiMuon,trangThai) VALUES (? , ? ,  ? ,0)";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        PreparedStatement st1 = conn.prepareStatement(sqlCommand2);
        st1.setString(1, "TQB" + String.valueOf(timestamp.getTime()));
        st1.setString(2, new SimpleDateFormat("dd/MM/yyyy").format(date));
        st1.setString(3, borrower.getborrower_ID());
        st1.execute();

        return "TQB" + String.valueOf(timestamp.getTime()); // return id
    }

    /**
     * Hàm này trả về mã thông tin mượn sách tìm kiếm theo mã người mượn
     *
     * @param idBorrower là mã người mượn
     * @return idBorrowingInfo là mã thông tin mượn sách
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String getIdBorrowingInfo(String idBorrower) throws ClassNotFoundException, SQLException {
        connectDatabase();
        String idBorrowingInfo = "";

        String sqlCommand = "select MaTTMuonTra from thongtinmuontrasach where maNguoiMuon = ? and trangThai =0";
        PreparedStatement st = conn.prepareStatement(sqlCommand);
        st.setString(1, idBorrower);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            idBorrowingInfo = rs.getString("MaTTMuonTra");
        }
        closeDatabase();
        return idBorrowingInfo;
    }

    /**
     * Hàm này trả về danh sách mã bản sao sách tìm kiếm theo mã thông tin mượn
     * sách
     *
     * @param idBorrowingInfo là mã thông tin mượn trả sách
     * @return listIdBook là danh sách mã bản sao sách
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<String> getListIdBorrowed(String idBorrowingInfo) throws ClassNotFoundException, SQLException {
        ArrayList<String> listIdBook = new ArrayList<>();
        connectDatabase();
        String sqlCommand = "select MaSachCP from chitietmuonsach where MaTTMuonTra =?";
        PreparedStatement st = conn.prepareStatement(sqlCommand);
        st.setString(1, idBorrowingInfo);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            listIdBook.add(rs.getString("MaSachCP"));
        }

        closeDatabase();

        return listIdBook;
    }

    /**
     * Hàm này trả về danh sách sách đã đăng ký mượn
     *
     * @param listId danh sách mã bản sao sách đã đăng ký mượn
     * @return listBookBorrowed danh sách sách đã mượn
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Book> getBookByIdCoppy(ArrayList<String> listId) throws SQLException, ClassNotFoundException {
        ArrayList<Book> listBookBorrowed = new ArrayList<>();
        connectDatabase();

        String sqlCommand = "select tacgia.tenTacGia, nhaphathanh.MaNPH, sach.tenSach from bansaosach, sach, nhaphathanh, tacgia, chitiettacgia\n"
                + "where bansaosach.MaSachCP =? \n"
                + "and bansaosach.maSach = sach.maSach\n"
                + "and sach.MaNPH = nhaphathanh.MaNPH\n"
                + "and sach.maSach = chitiettacgia.maSach\n"
                + "and chitiettacgia.maTacGia = tacgia.maTacGia ";
        PreparedStatement st = conn.prepareStatement(sqlCommand);
        for (int i = 0; i < listId.size(); i++) {
            st.setString(1, listId.get(i));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setbook_Author(rs.getString("tenTacGia"));
                book.setbook_Title(rs.getString("tenSach"));
                book.setbook_Publisher(new Publisher().getPublisherByIdPublisher(rs.getInt("maNPH")));
                listBookBorrowed.add(book);
            }
        }
        closeDatabase();
        return listBookBorrowed;
    }

    /**
     * Hàm này trả về ngày đăng ký mượn sách
     *
     * @param idBorrowingInfo là mã thông tin đăng ký mượn
     * @return trả về ngày đăng ký mượn
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String getRegistrationDate(String idBorrowingInfo) throws ClassNotFoundException, SQLException {
        String date = "";
        connectDatabase();
        String sqlCommand = " select thongtinmuontrasach.ngayDangKi from thongtinmuontrasach where MaTTMuonTra =?";
        PreparedStatement st = conn.prepareStatement(sqlCommand);
        st.setString(1, idBorrowingInfo);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            date = rs.getString("ngayDangKi");
        }

        closeDatabase();
        return date;
    }

    /**
     * Hàm này để xóa thông tin mượn sách
     *
     * @param bookNumber số sách còn đăng ký
     * @param idBorrowingInfo mã thông tin mượn sách
     * @throws ClassNotFoundException
     * @throws SQLException
     */
}

