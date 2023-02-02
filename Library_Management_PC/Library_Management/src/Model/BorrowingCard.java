/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

public class BorrowingCard extends DataAccessHelper {

    
    private Borrower    borrowing_card_Borrower;
    private String      borrowing_card_ID;
    private String      borrowing_card_Actived_Code;
    private String      borrowing_card_Expried_Date;
    private int         borrowing_card_Status;
    private String      borrowing_card_Borrower_ID;
    
    public BorrowingCard() {
    }

    public BorrowingCard(String borrowingcard_ID, String borrowingcard_Actived_Code, String borrowingcard_Expried_Date) {
        this.borrowing_card_ID = borrowingcard_ID;
        this.borrowing_card_Actived_Code = borrowingcard_Actived_Code;
        this.borrowing_card_Expried_Date = borrowingcard_Expried_Date;
    }

    public String getborrowingcard_ID() {
        return borrowing_card_ID;
    }

    public void setborrowingcard_ID(String borrowingcard_ID) {
        this.borrowing_card_ID = borrowingcard_ID;
    }

    public Borrower getborrowingcard_Borrower() {
        return borrowing_card_Borrower;
    }

    public void setborrowingcard_Borrower(Borrower borrowingcard_Borrower) {
        this.borrowing_card_Borrower = borrowingcard_Borrower;
    }

    public String getborrowingcard_Actived_Code() {
        return borrowing_card_Actived_Code;
    }

    public void setborrowingcard_Actived_Code(String borrowingcard_Actived_Code) {
        this.borrowing_card_Actived_Code = borrowingcard_Actived_Code;
    }

    public String getborrowingcard_Expried_Date() {
        return borrowing_card_Expried_Date;
    }

    public void setborrowingcard_Expried_Date(String borrowingcard_Expried_Date) {
        this.borrowing_card_Expried_Date = borrowingcard_Expried_Date;
    }

    public int getborrowingcard_Status() {
        return borrowing_card_Status;
    }

    public void setborrowingcard_Status(int borrowingcard_Status) {
        this.borrowing_card_Status = borrowingcard_Status;
    }

    public String getborrowingcard_Borrower_ID() {
        return borrowing_card_Borrower_ID;
    }

    public void setborrowingcard_Borrower_ID(String borrowingcard_Borrower_ID) {
        this.borrowing_card_Borrower_ID = borrowingcard_Borrower_ID;
    }
    
   
    /*Hàm này để lấy về tất cả đối tượng thẻ mượn có trong hệ thống
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public ArrayList<BorrowingCard> getAllCard() throws ClassNotFoundException, SQLException {
        ArrayList<BorrowingCard> allCard = new ArrayList<>();
        connectDatabase();
        PreparedStatement st = conn.prepareStatement("SELECT * FROM QuanLyThuVien_3.themuon");
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            String idBorrowingCard = rs.getString("maThe");
            String borrowerId = rs.getString("maNguoiMuon");
            Borrower borrower = new Borrower().getBorrower(borrowerId);
            String activedCode = rs.getString("maKichHoat");
            String expriedDate = rs.getString("ngayHetHan");
            BorrowingCard card = new BorrowingCard(idBorrowingCard, activedCode, expriedDate);
            card.setborrowingcard_Borrower(borrower);
            allCard.add(card);

        }
        return allCard;

    }

    /*Hàm này để lấy các đối tượng thẻ mượn theo mã thẻ mượn được nhập
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public ArrayList<BorrowingCard> getCardById(String id) throws ClassNotFoundException, SQLException {
        ArrayList<BorrowingCard> allCard = new ArrayList<>();
        connectDatabase();
        PreparedStatement st = conn.prepareStatement("SELECT * FROM QuanLyThuVien_3.themuon where themuon.maThe like \'%" + id + "%\'");
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            String idBorrowingCard = rs.getString("maThe");
            String idBorrower = rs.getString("maNguoiMuon");
            Borrower borrower = new Borrower().getBorrower(idBorrower);
            String activedCode = rs.getString("maKichHoat");
            String expriedDate = rs.getString("ngayHetHan");
            BorrowingCard card = new BorrowingCard(idBorrowingCard, activedCode, expriedDate);
            card.setborrowingcard_Borrower(borrower);
            allCard.add(card);

        }
        return allCard;
    }

    /*Hàm này để lấy về thẻ mượn theo mã người mượn
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public BorrowingCard getCardByIdBorrower(String idBorrower) throws SQLException, ClassNotFoundException {
        BorrowingCard card = new BorrowingCard();
        connectDatabase();
        PreparedStatement st = conn.prepareStatement("SELECT * FROM QuanLyThuVien_3.themuon where themuon.maNguoiMuon = \"" + idBorrower + "\"");
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            String idBorrowingCard = rs.getString("maThe");
            //  Date sqlDate= rs.getDate("ngayHetHan");
            String expriedDate = rs.getString("ngayHetHan");
            //System.out.println(expriedDate);
            card = new BorrowingCard(idBorrowingCard, null, expriedDate);
        }
        conn.close();
        return card;

    }

    /**
     * Lấy tài khoản đã đăng kí online,để phát hành thẻ mượn
     * @param tenTaiKhoan
     * @return tài khoản đã tìm
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public User getAccount(String tenTaiKhoan) throws SQLException, ClassNotFoundException {
        connectDatabase();
        User userSearch = new User();
        ResultSet rs = null;
        PreparedStatement preparedStatement;
        String sql = "SELECT taikhoan.MaTK,taiKhoan.HoTen,taikhoan.gioiTinh,taikhoan.email,taikhoan.SDT\n"
                + "                FROM taikhoan\n"
                + "                where taikhoan.MaTK=?;";
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, tenTaiKhoan);
        rs = preparedStatement.executeQuery();
        try {

            rs.next();
            userSearch.setuser_Username(rs.getString(1));
            userSearch.setuser_Name(rs.getString(2));
            userSearch.setuser_isMale(rs.getString(3).equalsIgnoreCase("Nam"));
            userSearch.setuser_Email(rs.getString(4));
            userSearch.setuser_Phone(rs.getString(5));

//            CreateNewCardFrame.ici.getAccountInfoUi().setInfo();
            closeDatabase();
            return userSearch;
        } catch (SQLException ex) {
            Logger.getLogger(BorrowingCard.class.getName()).log(Level.SEVERE, "em chưa đăng kí!!!", "");
        }
        return userSearch;
    }

        /**
         * Cấp hành thẻ mượn
         * @param nm
         * @param tm
         * @param tk
         * @return Mã thẻ mượn
         * @throws SQLException
         * @throws ClassNotFoundException 
         */
    public String registerborrower(Borrower nm, BorrowingCard tm, User tk) throws SQLException, ClassNotFoundException {
        connectDatabase();
        String BorrowerID;
        ResultSet rs = null;
        PreparedStatement  preparedStatementGetMaNguoiMuon;
        String sqlGetMaNguoiMuon = "select nguoimuon.maNguoiMuon from nguoimuon where nguoimuon.MaTK=?;";
        preparedStatementGetMaNguoiMuon = conn.prepareStatement(sqlGetMaNguoiMuon);
        preparedStatementGetMaNguoiMuon.setString(1, tk.getuser_Username());
        rs = preparedStatementGetMaNguoiMuon.executeQuery();
        rs.next();
        BorrowerID=rs.getString(1);
        closeDatabase();
//       tạo người mượn mới
        connectDatabase();
        String sql = "UPDATE nguoimuon SET  mssv=?, TienCoc=?, GiaiDoanHoc=? WHERE maNguoiMuon=?;";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, nm.getborrower_Student_ID());
        preparedStatement.setString(2, String.valueOf(nm.getborrower_Deposit()));
        preparedStatement.setString(3, nm.getborrower_Study_Period());
        preparedStatement.setString(4, BorrowerID);
        System.out.println(tk.getuser_Username());
        preparedStatement.executeUpdate();
        closeDatabase();
// tạo thẻ mới        
        connectDatabase();
        String sql1 = "INSERT INTO themuon (MaThe, ngayHetHan, trangThai, maKichHoat, maNguoiMuon) VALUES (?,?,?,?,?);";
        PreparedStatement preparredStatementCreateNewCard = conn.prepareStatement(sql1);
        preparredStatementCreateNewCard.setString(1, BorrowerID);
        preparredStatementCreateNewCard.setString(2, tm.getborrowingcard_Expried_Date());
        preparredStatementCreateNewCard.setString(3, "1");
        preparredStatementCreateNewCard.setString(4, tm.getborrowingcard_Actived_Code());
        preparredStatementCreateNewCard.setString(5, BorrowerID);
        preparredStatementCreateNewCard.executeUpdate();
        closeDatabase();

        return BorrowerID;
    }
    
}
