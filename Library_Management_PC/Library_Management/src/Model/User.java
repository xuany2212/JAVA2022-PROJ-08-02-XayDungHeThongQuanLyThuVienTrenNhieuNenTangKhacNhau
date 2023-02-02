/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.LoginForm;
import java.sql.*;
import java.util.logging.*;

import static Model.DataAccessHelper.conn;

public class User extends DataAccessHelper {

    private String      user_Name;
    private String      user_Username;
    private String      user_Password;
    private String      user_Email;
    private boolean     user_isMale;
    private String      user_Phone;

    public User() {
    }

    public User(String user_Name, String user_Username, String user_Password, String user_Email, boolean user_isMale, String user_Phone) {
        this.user_Name = user_Name;
        this.user_Username = user_Username;
        this.user_Password = user_Password;
        this.user_Email = user_Email;
        this.user_isMale = user_isMale;
        this.user_Phone = user_Phone;
    }

    public String getuser_Name() {
        return user_Name;
    }

    public void setuser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getuser_Username() {
        return user_Username;
    }

    public void setuser_Username(String user_Username) {
        this.user_Username = user_Username;
    }

    public String getuser_Password() {
        return user_Password;
    }

    public void setuser_Password(String user_Password) {
        this.user_Password = user_Password;
    }

    public String getuser_Email() {
        return user_Email;
    }

    public void setuser_Email(String user_Email) {
        this.user_Email = user_Email;
    }

    public boolean getuser_isMale() {
        return user_isMale;
    }

    public void setuser_isMale(boolean user_isMale) {
        this.user_isMale = user_isMale;
    }

    public String getuser_Phone() {
        return user_Phone;
    }

    public void setuser_Phone(String user_Phone) {
        this.user_Phone = user_Phone;
    }

    /**
     * Hàm này lấy ra tài khoản người dùng khi đăng nhập thành công
     * @param username tên tài khoản của người dùng
     * @param type kiểu người dùng thủ thư hay là người mượn
     * @return User trả về một tài khoản cuảngười dùng
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public User login(String username, int type) throws SQLException, ClassNotFoundException {
        User user = null;
        if (type == LoginForm.BORROWER) {
            user = new Borrower().getBorrowerByUsername(username);
        } else if (type == LoginForm.LIBRARIAN) {
            user = new Librarian().getLibrarianByUserName(username);
        }
        return user;
    }

    /**
     * Hàm này kiểm tra thông tin đăng nhập của người dùng
     * @param username tên tài khoản của người dùng
     * @param password mật khẩu của người dùng
     * @return 
     *      true nếu username và pass thỏa mãn
     *      false nếu một trong 2 thông tin usernam và pass không đúng
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    public boolean checkLogin(String username, String password) throws SQLException, ClassNotFoundException {
        String mk = null;
        connectDatabase();
        PreparedStatement st = conn.prepareStatement("SELECT matKhau FROM QuanLyThuVien_3.taikhoan where MaTK =?");
        st.setString(1, username);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            mk = rs.getString("matKhau");
        }
        if (mk == null) {
            return false;
        }
        closeDatabase();
        return mk.equals(password);
    }
    
    /**
 * Thay đổi thông tin tài khoản và gia hạn thẻ
 * @param ten 
 * @param sdt
 * @param gtinh
 * @param nhhan
 * @param email
 * @param maNguoiMuon
 * @return 
 * @throws ClassNotFoundException
 * @throws SQLException 
 */
    public String updateInfoUser(String ten, String sdt, String gtinh, String nhhan, String email, String maNguoiMuon) throws ClassNotFoundException, SQLException {
        try {
            connectDatabase();
            String sqlQuery = "update taikhoan,nguoimuon,themuon Set taikhoan.HoTen=?,taikhoan.SDT=?,taikhoan.gioiTinh=?,themuon.ngayHetHan=?,taikhoan.email=? where taikhoan.MaTK= nguoimuon.MaTK and nguoimuon.maNguoiMuon= themuon.maNguoiMuon and nguoimuon.maNguoiMuon=?";
            PreparedStatement prepareStatement;
            prepareStatement = conn.prepareStatement(sqlQuery);
            prepareStatement.setString(1, ten);
            prepareStatement.setString(2, sdt);
            prepareStatement.setString(3, gtinh);
            prepareStatement.setString(4, nhhan);
            prepareStatement.setString(5, email);
            prepareStatement.setString(6, maNguoiMuon);
            prepareStatement.executeUpdate();
            return "THANHCONG";
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);

            return "THATBAI";
        }

    }

    public Object[] getAccount(String x) throws ClassNotFoundException, SQLException {
        connectDatabase();
        Object[] account = new Object[3];
        User userSearch;
        Borrower borrowerSearch;
        BorrowingCard borrowCardSearch;
        String sqlQuery = "select taikhoan.MaTK,taikhoan.matKhau,taikhoan.HoTen,taikhoan.gioiTinh,taikhoan.email,taikhoan.SDT,\n"
                + "nguoimuon.maNguoiMuon,nguoimuon.mssv,nguoimuon.TienCoc,nguoimuon.giaiDoanHoc,themuon.ngayHetHan\n"
                + "from taikhoan ,nguoimuon ,themuon where taikhoan.MaTK=nguoimuon.MaTK and nguoimuon.maNguoiMuon= \"" + x + "\" and nguoimuon.maNguoiMuon = themuon.maNguoiMuon\n";
        ResultSet rs = null;
        Statement statement;
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(sqlQuery);
            rs.next();
            userSearch = new User(rs.getString(3), rs.getString(1), rs.getString(2), rs.getString(5), rs.getString(4).equalsIgnoreCase("Nam") ? true : false, rs.getString(6));
            borrowerSearch = new Borrower(rs.getString(7), rs.getString(8), rs.getFloat(9), rs.getString(10), rs.getString(1));
            borrowCardSearch = new BorrowingCard();
            borrowCardSearch.setborrowingcard_Expried_Date(rs.getString(11));
            account[0] = userSearch;
            account[1] = borrowerSearch;
            account[2] = borrowCardSearch;
            closeDatabase();
            return account;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, "Không tìm thấy", ex);
        }
        closeDatabase();
        return account;
    }

}
