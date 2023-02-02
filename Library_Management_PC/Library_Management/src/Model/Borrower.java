package Model;

import java.util.*;
import java.sql.*;

public class Borrower extends User {

    private String borrower_ID;
    private BorrowingCard borrower_Card;
    private float borrower_Deposit;
    private String borrower_Student_ID;
    private String borrower_Study_Period;
    private String borrower_Username;

    private final String GET_BORROWER_BY_USERNAME = "SELECT * FROM QuanLyThuVien_3.taikhoan,QuanLyThuVien_3.nguoimuon where taikhoan.MaTK = ? and taikhoan.MaTK = nguoimuon.MaTK";
    private final String GET_LIST_BORROWER = "SELECT * FROM QuanLyThuVien_3.nguoimuon,themuon,taikhoan where nguoimuon.MaTK = taikhoan.MaTK and nguoimuon.maNguoiMuon = themuon.maNguoiMuon  ";
    private final String SEARCH_BORROWER = "SELECT nguoimuon.maNguoiMuon,taikhoan.HoTen, taikhoan.matKhau,taikhoan.MaTK,"
            + "taikhoan.email,taikhoan.gioiTinh,taikhoan.SDT, nguoimuon.TienCoc, "
            + "nguoimuon.mssv, nguoimuon.giaiDoanHoc,themuon.maThe, themuon.maKichHoat,themuon.maKichHoat,themuon.ngayHetHan  "
            + "FROM QuanLyThuVien_3.nguoimuon,taikhoan,themuon "
            + "where (nguoimuon.MaTK = taikhoan.MaTK) and "
            + "(nguoimuon.maNguoiMuon = themuon.maNguoiMuon) and "
            + "((nguoimuon.maNguoiMuon like ?) or (taikhoan.ten like ?))";

    public Borrower() {
    }

    public Borrower(String borrower_ID, String borrower_Student_ID, float borrower_Deposit, String borrower_Study_Period,
            String borrower_Username) {
        super();
        this.borrower_ID = borrower_ID;
        this.borrower_Deposit = borrower_Deposit;
        this.borrower_Student_ID = borrower_Student_ID;
        this.borrower_Study_Period = borrower_Study_Period;
        this.borrower_Username = borrower_Username;
    }

    public Borrower(String borrower_ID, float borrower_Deposit, String borrower_Student_ID, String borrower_Study_Period, String user_Name, String user_Username,
            String user_Password, String user_Email, boolean user_isMale, String user_Phone) {
        super(user_Name, user_Username, user_Password, user_Email, user_isMale, user_Phone);
        this.borrower_ID = borrower_ID;
        this.borrower_Deposit = borrower_Deposit;
        this.borrower_Student_ID = borrower_Student_ID;
        this.borrower_Study_Period = borrower_Study_Period;
    }

    public Borrower(String borrower_ID, float borrower_Deposit, String borrower_Student_ID, String borrower_Study_Period, BorrowingCard borrower_Card, String user_Name, String user_Username,
    String user_Password, String user_Email, boolean user_isMale, String user_Phone) {
        super(user_Name, user_Username, user_Password, user_Email, user_isMale, user_Phone);
        this.borrower_Card = borrower_Card;
        this.borrower_ID = borrower_ID;
        this.borrower_Deposit = borrower_Deposit;
        this.borrower_Student_ID = borrower_Student_ID;
        this.borrower_Study_Period = borrower_Study_Period;
    }

    public String getborrower_ID() {
        return borrower_ID;
    }

    public void setborrower_ID(String borrower_ID) {
        this.borrower_ID = borrower_ID;
    }

    public BorrowingCard getborrower_Card() {
        return borrower_Card;
    }

    public void setborrower_Card(BorrowingCard borrower_Card) {
        this.borrower_Card = borrower_Card;
    }

    public float getborrower_Deposit() {
        return borrower_Deposit;
    }

    public void setborrower_Deposit(int borrower_Deposit) {
        this.borrower_Deposit = borrower_Deposit;
    }

    public String getborrower_Student_ID() {
        return borrower_Student_ID;
    }

    public void setborrower_Student_ID(String borrower_Student_ID) {
        this.borrower_Student_ID = borrower_Student_ID;
    }

    public String getborrower_Study_Period() {
        return borrower_Study_Period;
    }

    public void setborrower_Study_Period(String borrower_Study_Period) {
        this.borrower_Study_Period = borrower_Study_Period;
    }

    public String getborrower_Username() {
        return borrower_Username;
    }

    public void setborrower_Username(String borrower_Username) {
        this.borrower_Username = borrower_Username;
    }

    /**
     * Hàm này thực hiện chức năng lấy người mượn từ username
     * 
     * @param username tên tài khoản của người mượn
     * @return người mượn tương ứng với tên tài khoản của người mượn
     */
    public Borrower getBorrowerByUsername(String username) throws SQLException, ClassNotFoundException {
        Borrower borrower = null;
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(GET_BORROWER_BY_USERNAME);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String idBorrower = rs.getString("maNguoiMuon");
            String name = rs.getString("HoTen");
            String password = rs.getString("matKhau");
            String email = rs.getString("email");
            boolean isMale = rs.getString("gioiTinh").equalsIgnoreCase("nam");
            String phone = rs.getString("SDT");
            float deposit = rs.getFloat("TienCoc");
            String idStudent = rs.getString("mssv");
            String studyPeriod = rs.getString("giaiDoanHoc");
            borrower = new Borrower(idBorrower, deposit, idStudent, studyPeriod, name, username, password, email,
                    isMale, phone);
        }
        closeDatabase();
        return borrower;
    }

    /**
     * Hàm này thực hiện chức năng lấy ra danh sách người mượn có trong hệ thống
     * 
     * @return Danh sách người mượn có trong hệ thống
     */
    public ArrayList<Borrower> getListBorrower() throws SQLException, ClassNotFoundException {
        ArrayList<Borrower> arr = new ArrayList<>();
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(GET_LIST_BORROWER);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            BorrowingCard borrowingCard = new BorrowingCard(rs.getString("maThe"), rs.getString("maKichHoat"),
                    rs.getString("ngayHetHan"));
            String idBorrower = rs.getString("maNguoiMuon");
            String name = rs.getString("HoTen");
            String password = rs.getString("matKhau");
            String email = rs.getString("email");
            boolean isMale = rs.getString("gioiTinh").equalsIgnoreCase("nam");
            String phone = rs.getString("SDT");
            float deposit = rs.getFloat("TienCoc");
            String idStudent = rs.getString("mssv");
            String studyPeriod = rs.getString("giaiDoanHoc");
            String username = rs.getString("MaTK");
            Borrower borrower = new Borrower(idBorrower, deposit, idStudent, studyPeriod, borrowingCard, name,
                        username, password, email, isMale, phone);
            arr.add(borrower);
        }
        closeDatabase();
        return arr;
    }

    /**
     * Hàm này để lấy đối tượng người mượn theo mã người mượn
     * 
     * @param borrowerId mã người mượn của người mượn cần lấy
     * @return đối tượng người mượn tương ứng với mã người mượn
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public Borrower getBorrower(String borrowerId) throws ClassNotFoundException, SQLException {
        Borrower borrower = null;
        connectDatabase();
        PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM QuanLyThuVien_3.themuon,QuanLyThuVien_3.nguoimuon,QuanLyThuVien_3.taikhoan where themuon.maNguoiMuon = ? and themuon.maNguoiMuon = nguoimuon.maNguoiMuon and nguoiMuon.MaTK = taikhoan.MaTK");
        st.setString(1, borrowerId);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            String idBorrower = rs.getString("maNguoiMuon");
            String name = rs.getString("HoTen");
            String password = rs.getString("matKhau");
            String email = rs.getString("email");
            boolean isMale = rs.getString("gioiTinh").equalsIgnoreCase("nam");
            String phone = rs.getString("SDT");
            float deposit = rs.getFloat("TienCoc");
            String idStudent = rs.getString("mssv");
            String studyPeriod = rs.getString("giaiDoanHoc");
            String username = rs.getString("MaTK");
            
            borrower = new Borrower(idBorrower, deposit, idStudent, studyPeriod, name, username, password, email,
                    isMale, phone);
        }
        return borrower;
    }

    /**
     * Hàm này để tìm kiếm người mượn theo tên hoặc mã người mượn
     * 
     * @param key từ khóa mà có thể bao gồm trong tên hoặc mã người mượn
     * @return Danh sách các đối tượng người mượn thỏa mãn
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public ArrayList<Borrower> searchBorrower(String key) throws SQLException, ClassNotFoundException {
        ArrayList<Borrower> arr = new ArrayList<>();
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(SEARCH_BORROWER);
        ps.setString(1, "%" + key + "%");
        ps.setString(2, "%" + key + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            BorrowingCard borrowingCard = new BorrowingCard(rs.getString("maThe"), rs.getString("maKichHoat"),
                    rs.getString("ngayHetHan"));
            String idBorrower = rs.getString("maNguoiMuon");
            String name = rs.getString("HoTen");
            String username = rs.getString("MaTK");
            String password = rs.getString("matKhau");
            String email = rs.getString("email");
            boolean isMale = rs.getString("gioiTinh").equalsIgnoreCase("nam");
            String phone = rs.getString("SDT");
            float deposit = rs.getFloat("TienCoc");
            String idStudent = rs.getString("mssv");
            String studyPeriod = rs.getString("giaiDoanHoc");
            Borrower borrower = new Borrower(idBorrower, deposit, idStudent, studyPeriod, borrowingCard, name,
                    username, password, email, isMale, phone);
            arr.add(borrower);
        }
        closeDatabase();
        return arr;
    }

}
