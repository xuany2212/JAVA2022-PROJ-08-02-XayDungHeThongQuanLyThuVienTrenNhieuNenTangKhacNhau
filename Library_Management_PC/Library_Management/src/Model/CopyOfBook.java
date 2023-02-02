/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.*;
import java.text.*;
import java.util.logging.*;
import java.util.ArrayList;
import java.util.Date;

public class CopyOfBook extends DataAccessHelper {

    private String          copy_of_book_ID;
    private String             copy_of_book_Num_Of_Copy;
    private StatusOfCopy    copy_of_book_Status;

    public final String GET_BOOK_BY_ID_COPY_OF_BOOK = "SELECT * FROM QuanLyThuVien_3.bansaosach,sach where  MaSachCP=? and bansaosach.MaSach = sach.maSach";
    public final String GET_LIST_COPY_OF_BOOK_BY_ID_BOOK = "SELECT * FROM QuanLyThuVien_3.bansaosach where maSach =?";
    public final String ADD_COPY_OF_BOOK_BY_ID_BOOK = "INSERT INTO bansaosach VALUES (?,?,?,?)";
    public final String GET_COPY_OF_BOOK_BY_ID = "SELECT * FROM QuanLyThuVien_3.bansaosach where MaSachCP =?";
    public final String UPDATE_COPY_OF_BOOK = "UPDATE bansaosach SET trangThai=? WHERE maBanSao=?";

    public CopyOfBook() {
    }

    public CopyOfBook(String copy_of_book_ID, String copy_of_book_Num_Of_Copy, StatusOfCopy copy_of_book_Status) {
        this.copy_of_book_ID = copy_of_book_ID;
        this.copy_of_book_Num_Of_Copy = copy_of_book_Num_Of_Copy;
        this.copy_of_book_Status = copy_of_book_Status;
    }

    public String getcopy_of_book_ID() {
        return copy_of_book_ID;
    }

    public void setcopy_of_book_ID(String copy_of_book_ID) {
        this.copy_of_book_ID = copy_of_book_ID;
    }

    public String getcopy_of_book_Num_Of_Copy() {
        return copy_of_book_Num_Of_Copy;
    }

    public void setcopy_of_book_Num_Of_Copy(String copy_of_book_Num_Of_Copy) {
        this.copy_of_book_Num_Of_Copy = copy_of_book_Num_Of_Copy;
    }

    public StatusOfCopy getcopy_of_book_Status() {
        return copy_of_book_Status;
    }

    public void setcopy_of_book_Status(StatusOfCopy copy_of_book_Status) {
        this.copy_of_book_Status = copy_of_book_Status;
    }

    public static enum StatusOfCopy {
        AVAILABLE(0),
        LENT(1),
        BORROWED(2);

        private int status_of_copy_value;

        private StatusOfCopy(int status_of_copy_value) {
            this.status_of_copy_value = status_of_copy_value;
        }
    }

    public Book getBookByIdCopyOfBook(String idCopyOfBook) throws ClassNotFoundException, SQLException {
        Book book = null;
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(GET_BOOK_BY_ID_COPY_OF_BOOK);
        ps.setString(1, idCopyOfBook);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            book = new Book();
            String idBook = rs.getString(1);
            String titleBook = rs.getString("tenSach");
            book.setbook_ID(idBook);
            book.setbook_Title(titleBook);
        }
        closeDatabase();
        return book;
    }

    /**
     * Hàm này thực hiện chức năng lấy danh sách bản sao sách của một sách
     *
     * @param idBook mã sách của cuốn sách muốn lấy ra các bản sao
     * @return danh sách bản sao của sách.
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public ArrayList<CopyOfBook> getListCopyOfBookByIdBook(String idBook) throws ClassNotFoundException, SQLException {
        ArrayList<CopyOfBook> listCopyOfBooks = new ArrayList<>();
        connectDatabase();
        PreparedStatement st = conn.prepareStatement(GET_LIST_COPY_OF_BOOK_BY_ID_BOOK);
        st.setString(1, idBook);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            String idCopyOfBook = rs.getString("MaSachCP");
            String numberOfCopy = rs.getString("ViTri");

            //trang thai ban sao
            StatusOfCopy statusOfCopy = null;
            if (rs.getInt("trangThai") == 0) {
                statusOfCopy = StatusOfCopy.AVAILABLE;
            } else if (rs.getInt("trangThai") == 1) {
                statusOfCopy = StatusOfCopy.LENT;
            } else if (rs.getInt("trangThai") == 2) {
                statusOfCopy = StatusOfCopy.BORROWED;
            }

            listCopyOfBooks.add(new CopyOfBook(idCopyOfBook, numberOfCopy, statusOfCopy));
        }
        closeDatabase();
        return listCopyOfBooks;
    }

    /**
     * Hàm này thực hiện chức năng thêm một bản sao của một sách
     * @param idBook mã sách của cuốn sách muốn thêm các bản sao
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     * 
     */
    public boolean addCopyOfBookByIdBook(String idBook) throws ClassNotFoundException, SQLException {
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(ADD_COPY_OF_BOOK_BY_ID_BOOK);
        ps.setString(1, copy_of_book_ID);
        ps.setString(2, copy_of_book_Num_Of_Copy);
        ps.setString(4, idBook);
        int isuccess = ps.executeUpdate();
        closeDatabase();
        return (isuccess == -1) ? false : true;
    }

    /**
     * Hàm này thực hiện chức năng lấy bản sao sách từ mã bản sao sách
     * @param idCopyOfBook mã bản sao sách muốn lấy ra
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public CopyOfBook getCopyOfBookById(String idCopyOfBook) throws ClassNotFoundException, SQLException {
        CopyOfBook b = null;
        connectDatabase();
        PreparedStatement st = conn.prepareStatement(GET_COPY_OF_BOOK_BY_ID);
        st.setString(1, idCopyOfBook);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {

            String numberOfCopy = rs.getString("ViTri");

            //trang thai ban sao
            StatusOfCopy statusOfCopy = null;
            if (rs.getInt("trangThai") == 0) {
                statusOfCopy = StatusOfCopy.AVAILABLE;
            } else if (rs.getInt("trangThai") == 1) {
                statusOfCopy = StatusOfCopy.LENT;
            } else if (rs.getInt("trangThai") == 2) {
                statusOfCopy = StatusOfCopy.BORROWED;
            }

            b = new CopyOfBook(idCopyOfBook, numberOfCopy, statusOfCopy);
        }
        closeDatabase();
        return b;
    }

    /**
     * Hàm này thực hiện chức năng cập nhật thông tin bản sao sách
     *
     * @param b bản sao sách muốn cập nhật thông tin
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public boolean updateCopyOfBook() throws ClassNotFoundException, SQLException {
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(UPDATE_COPY_OF_BOOK);
        if (copy_of_book_Status == StatusOfCopy.AVAILABLE) {
            ps.setInt(1, 0);
        } else if (copy_of_book_Status == StatusOfCopy.LENT) {
            ps.setInt(1, 1);
        } else if (copy_of_book_Status == StatusOfCopy.BORROWED) {
            ps.setInt(1, 2);
        }
        ps.setString(1, copy_of_book_ID);
        int isSuccess = ps.executeUpdate();
        closeDatabase();
        return (isSuccess == -1) ? false : true;
    }

    /**
     * Hàm này để lấy trạng thái của bản sao theo mã sách và số hiệu bản sao
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public StatusOfCopy getStatusByIdBook(String idBook, String soThuTu) throws ClassNotFoundException, SQLException {
        connectDatabase();
        String command = "SELECT bansaosach.trangThai FROM QuanLyThuVien_3.bansaosach where maSach =\"" + idBook + "\" and ViTri =" + soThuTu;
        //   System.out.println(command);
        PreparedStatement st = conn.prepareStatement(command);
        ResultSet rs = st.executeQuery();
        String status = "";
        while (rs.next()) {
            status = rs.getString("trangThai");
        }
        //System.out.println(status);
        if (status.equals("0")) {
            return StatusOfCopy.AVAILABLE;
        }
        if (status.equals("1")) {
            return StatusOfCopy.LENT;
        }
        if (status.equals("2")) {
            return StatusOfCopy.BORROWED;
        }

        return null;
    }

    /**
     * Lấy các cuốn sách đã mượn từ maNguoiMuon
     *
     * @param maNguoiMuon
     * @return 1 Object[] các quyển sách đã mượn
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Object[] getBookIsBorrowed(String maNguoiMuon) throws SQLException, ClassNotFoundException, ParseException {
        connectDatabase();
        boolean haveData = true;
        ResultSet rs = null;
        Object[] ReturnBookInfo = new Object[4];
        Object[] ReturnBookInfo1 = new Object[4];
        ArrayList<Object[]> dataTable = new ArrayList<>();
        ArrayList<String> IdInfoBorrow = new ArrayList<>();
        User taikhoan = new User();
        Borrower nguoimuon = new Borrower();
        PreparedStatement preparedStatement;
        String sql = "SELECT taikhoan.MaTK,taiKhoan.HoTen,taikhoan.SDT,nguoimuon.maNguoiMuon,thongtinmuontrasach.maTTMuonTra, chitietmuonsach.ngayMuon,thongtinmuontrasach.hanTra,bansaosach.MaSachCP,bansaosach.trangThai,sach.maSach,sach.tenSach\n"
                + "                from taikhoan,nguoimuon,thongtinmuontrasach,bansaosach,sach,chitietmuonsach\n"
                + "                where taikhoan.MaTK= nguoimuon.MaTK \n"
                + "                and nguoimuon.maNguoiMuon=?\n"
                + "                and thongtinmuontrasach.maNguoiMuon= nguoimuon.maNguoiMuon \n"
                + "                and thongtinmuontrasach.maTTMuonTra= chitietmuonsach.maTTMuonTra \n"
                + "                and chitietmuonsach.MaSachCP= bansaosach.MaSachCP \n"
                + "                and bansaosach.maSach= sach.maSach \n"
                + "                and chitietmuonsach.trangThai=\"1\"";
        preparedStatement = conn.prepareStatement(sql);

        preparedStatement.setString(1, maNguoiMuon);
        rs = preparedStatement.executeQuery();
        try {
            rs.next();
            System.out.println("MaTK:" + rs.getString(1) + "  HoTen :" + rs.getString(2) + "  SDT:" + rs.getString(3) + "   maNguoiMuon:" + rs.getString(4));
            taikhoan.setuser_Username(rs.getString(1));
            taikhoan.setuser_Name(rs.getString(2));
            taikhoan.setuser_Phone(rs.getString(3));
            nguoimuon.setborrower_ID(rs.getString(4));
            ReturnBookInfo[0] = taikhoan;
            ReturnBookInfo[1] = nguoimuon;
            rs.previous();
            Date currentDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            while (rs.next()) {

                Date ExperiedDate = sdf.parse(rs.getString(7));
                dataTable.add(new Object[]{rs.getString(5), rs.getString(8), rs.getString(11), rs.getString(6), rs.getString(7), currentDate.before(ExperiedDate) ? "đúng hạn" : "hết hạn", "", "0", false});
                IdInfoBorrow.add(rs.getString(5));
            }
            ReturnBookInfo[2] = dataTable;
            ReturnBookInfo[3] = IdInfoBorrow;
            closeDatabase();
//            return ReturnBookInfo;
            haveData = true;
        } catch (SQLException ex) {
            Logger.getLogger(CopyOfBook.class.getName()).log(Level.SEVERE, "Không mượn sách nhé,ahihi", "");

            User taikhoan1 = new User();
            String a = "KOMUON";
            taikhoan1.setuser_Name(a);
            ReturnBookInfo1[0] = taikhoan1;
//            return ReturnBookInfo1;
            haveData = false;
        }
        if (haveData) {
            return ReturnBookInfo;
        } else {
            return ReturnBookInfo1;
        }
    }

    /**
     * Trả những cuốn sách đã chọn
     *
     * @param dataTable
     * @param id
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void ReturnBook(ArrayList<Object[]> dataTable, boolean[] id) throws SQLException, ClassNotFoundException {
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String a = sdf.format(currentDate);
        System.out.println(a);
        for (int i = id.length - 1; i >= 0; i--) {
            if (id[i] == true) {
                connectDatabase();
                PreparedStatement preparedStatement;
                String sql = "UPDATE bansaosach,chitietmuonsach,thongtinmuontrasach SET bansaosach.trangThai=\"0\",chitietmuonsach.trangThai= \"2\",chitietmuonsach.ngayTra=?,chitietmuonsach.tienPhat=?,chitietmuonsach.lyDoPhat=? \n"
                        + "WHERE bansaosach.MaSachCP= ? \n"
                        + "and bansaosach.MaSachCP= chitietmuonsach.MaSachCP \n"
                        + "and chitietmuonsach.maTTMuonTra= thongtinmuontrasach.maTTMuonTra \n"
                        + "and thongtinmuontrasach.maTTMuonTra=?";
                preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setString(1, a);
                preparedStatement.setString(2, dataTable.get(i)[7].toString());
                preparedStatement.setString(3, dataTable.get(i)[6].toString());
                preparedStatement.setString(4, dataTable.get(i)[1].toString());
                preparedStatement.setString(5, dataTable.get(i)[0].toString());
                System.out.println(i);
                preparedStatement.executeUpdate();
                closeDatabase();
            }
        }
    }

    /**
     * Hàm này cập nhật trạng thái sách về có sẵn
     *
     * @param idCopyBook là mã bản sao sách
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void updateStateofBook(String idCopyBook) throws ClassNotFoundException, SQLException {
        connectDatabase();
        String sqlCommand = "UPDATE bansaosach SET trangThai=0 WHERE MaSachCP=?";
        PreparedStatement st = conn.prepareStatement(sqlCommand);
        st.setString(1, idCopyBook);
        st.execute();
        closeDatabase();
    }

    public void UpdateInfoBorrow(ArrayList<String> IdBorrower) throws ClassNotFoundException, SQLException {
        int result;
        for (int i = 0; i < IdBorrower.size(); i++) {
            result=1;
            connectDatabase();
            String sql = "select count(chitietmuonsach.maTTMuonTra) \n"
                    + "from thongtinmuontrasach,chitietmuonsach\n"
                    + "where thongtinmuontrasach.maTTMuonTra=? \n"
                    + "and thongtinmuontrasach.maTTMuonTra = chitietmuonsach.maTTMuonTra\n"
                    + "and chitietmuonsach.trangThai=\"1\" ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, IdBorrower.get(i));
            ResultSet rs = null;
            rs = ps.executeQuery();
            rs.next();
            result = rs.getInt(1);
            closeDatabase();

            if (result == 0) {
                connectDatabase();
                sql = "UPDATE thongtinmuontrasach SET trangThai='2' WHERE maTTMuonTra = ? ;";
                PreparedStatement ps1 = conn.prepareStatement(sql);
                ps1.setString(1, IdBorrower.get(i));
                ps1.executeUpdate();
                closeDatabase();
            }
        }

    }
}
