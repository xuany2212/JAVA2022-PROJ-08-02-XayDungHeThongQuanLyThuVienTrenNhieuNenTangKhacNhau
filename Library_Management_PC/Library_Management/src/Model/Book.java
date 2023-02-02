package Model;

import Controller.BookDetail_Controller;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.*;

public class Book extends DataAccessHelper {
    private String book_ID;
    private String book_Title;
    private String book_Author;
    private float  book_Price;
    private Publisher book_Publisher;
    private ArrayList<CopyOfBook> book_ListofCopy;
    private ArrayList<Author> book_ListofAuthor;

    private final String GET_LIST_IDAUTHOR = "SELECT * FROM QuanLyThuVien_3.ChiTietTacGia where MaSach=?";
    private final String GET_LIST_IDBOOK = "SELECT * FROM QuanLyThuVien_3.Sach where MaSach = ?";
    private final String ADD_BOOK = "INSERT INTO QuanLyThuVien_3.Sach VALUES (?,?,?,?)";
    private final String ADD_DETAIL_AUTHOR = "INSERT INTO QuanLyThuVien_3.ChiTietTacGia VALUES (?,?)";
    private final String SEARCH_BOOK = "SELECT * from sach where tenSach like ?";

    public Book() {
    }

    public Book(String book_ID, String book_Title, String book_Author, float book_Price,
    ArrayList<CopyOfBook> book_ListofCopy, Publisher book_Publisher) {
        this.book_ID = book_ID;
        this.book_Title = book_Title;
        this.book_Author = book_Author;
        this.book_Price = book_Price;
        this.book_Publisher = book_Publisher;
        this.book_ListofCopy = book_ListofCopy;
    }

    public String getbook_ID() {
        return book_ID;
    }

    public void setbook_ID(String book_ID) {
        this.book_ID = book_ID;
    }

    public String getbook_Title() {
        return book_Title;
    }

    public void setbook_Title(String book_Title) {
        this.book_Title = book_Title;
    }

    public float getbook_Price() {
        return book_Price;
    }

    public void setbook_Price(float book_Price) {
        this.book_Price = book_Price;
    }

    public String getbook_Author() {
        return book_Author;
    }

    public void setbook_Author(String book_Author) {
        this.book_Author = book_Author;
    }

    public Publisher getbook_Publisher() {
        return book_Publisher;
    }

    public void setbook_Publisher(Publisher book_Publisher) {
        this.book_Publisher = book_Publisher;
    }

    public ArrayList<CopyOfBook> getbook_ListofCopy() {
        return book_ListofCopy;
    }

    public void setbook_ListofCopy(ArrayList<CopyOfBook> book_ListofCopy) {
        this.book_ListofCopy = book_ListofCopy;
    }

    public ArrayList<Author> getbook_ListofAuthor() {
        return book_ListofAuthor;
    }

    public void setbook_ListofAuthor(ArrayList<Author> book_ListofAuthor) {
        this.book_ListofAuthor = book_ListofAuthor;
    }

    /**
     * lấy Tên tác giả của sách
     * 
     * @param book_ID mã quyển sách muốn lấy thông tin
     * @return trả về tên các tác giả của sách
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private String getNameAuthorByIdBook(String book_ID) throws ClassNotFoundException, SQLException {
        String nameofListAuthor = "";
        connectDatabase();
        PreparedStatement st = conn.prepareStatement(GET_LIST_IDAUTHOR);
        st.setString(1, book_ID);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            String maTacGia = rs.getString("MaTacGia");
            nameofListAuthor = (nameofListAuthor.isEmpty())
                    ? nameofListAuthor = new Author().getNameAuthorByIdAuthor(maTacGia)
                    : nameofListAuthor + ", " + new Author().getNameAuthorByIdAuthor(maTacGia);
        }
        closeDatabase();
        return nameofListAuthor;
    }

    /**
     * Lấy quyển sách từ mã sách
     * 
     * @param book_ID mã quyển sách muốn lấy thông tin
     * @return trả về quyển sách cần lấy ra
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Book getBookByIdBook(String book_ID) throws ClassNotFoundException, SQLException {
        Book book = new Book();
        connectDatabase();
        PreparedStatement st = conn.prepareStatement(GET_LIST_IDBOOK);
        st.setString(1, book_ID);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            String titleBook = rs.getString("TenSach");
            Publisher publisher = new Publisher().getPublisherByIdPublisher(rs.getInt("MaNhaPhatHanh"));
            String nameAuthor = getNameAuthorByIdBook(book_ID);
            float priceBook = rs.getFloat("GiaThue");
            ArrayList<CopyOfBook> listBanSao = new CopyOfBook().getListCopyOfBookByIdBook(book_ID);
            book = new Book(book_ID, titleBook, nameAuthor, priceBook, listBanSao, publisher);
        }
        closeDatabase();
        return book;
    }

    /**
     * Hàm này thực hiện chức năng thêm một sách vào cơ sở dữ liệu
     * 
     * @param book sách muốn thêm vào
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public void addBook() throws ClassNotFoundException, SQLException {
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(ADD_BOOK);
        ps.setString(1, book_ID);
        ps.setString(2, book_Title);
        ps.setInt(3, book_Publisher.getpublisher_ID());
        ps.executeUpdate();

        for (int i = 0; i < book_ListofAuthor.size(); i++) {
            ps = conn.prepareStatement(ADD_DETAIL_AUTHOR);
            ps.setString(1, book_ID);
            ps.setString(2, book_ListofAuthor.get(i).getauthor_ID());
            ps.executeUpdate();
        }
        closeDatabase();
    }

    /**
     * Hàm này thực hiện chức năng tìm kiếm sách theo tên sách trong CSDL
     * 
     * @param key từ khóa tìm kiếm
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public ArrayList<Book> searchBook(String key) throws SQLException, ClassNotFoundException {
        ArrayList<Book> arr = new ArrayList<>();
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement(SEARCH_BOOK);
        ps.setString(1, "%" + key + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Book book = new Book();
            book.setbook_ID(rs.getString("MaSach"));
            book.setbook_Title(rs.getString("TenSach"));
            Publisher publisher = new Publisher().getPublisherByIdPublisher(rs.getInt("MaNhaPhatHanh"));
            book.setbook_Publisher(publisher);
            String nameAuthor = getNameAuthorByIdBook(book.getbook_ID());
            book.setbook_Author(nameAuthor);
            arr.add(book);
        }
        closeDatabase();
        return arr;
    }

    /*
     * Hàm này để lấy tất cả các quyển sách người mượn đã đăng kí theo mã người dùng
     * 
     * @throws ClassNotFoundException
     * 
     * @throws SQLException
     * 
     * @see ClassNotFoundException
     * 
     * @see SQLException
     */
    public ArrayList<Book> getBooksByIdBorrower(String idBorrower) throws ClassNotFoundException, SQLException {
        Book book = new Book();
        ArrayList<Book> books = new ArrayList<>();
        connectDatabase();
        PreparedStatement st = conn.prepareStatement(
                "SELECT Sach.TenSach, Sach.MaSach, NhaPhatHanh.MaNPH from BanSaoSach,Sach,ThongTinMuonTraSach,NguoiMuon,ChiTietMuonSach,NhaPhatHanh where NguoiMuon.MaNguoiMuon = ? \n"
                        + "and NguoiMuon.MaNguoiMuon = ThongTinMuonTraSach.MaNguoiMuon\n"
                        + "and ThongTinMuonTraSach.MaTTMuonTra = ChiTietMuonSach.MaTTMuonTra\n"
                        + "and ChiTietMuonSach.MaSachCP = BanSaoSach.MaSachCP and ThongTinMuonTraSach.TrangThai =0\n"
                        + "and Sach.MaSach=BanSaoSach.MaSach\n"
                        + "and NhaPhatHanh.MaNPH = Sach.MaNPH");
        st.setString(1, idBorrower);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            String maSach = rs.getString("MaSach");
            Publisher tenNhaPhatHanh = new Publisher().getPublisherByIdPublisher(rs.getInt("MaNhaPhatHanh"));
            String tenSach = rs.getString("TenSach");
            float priceBook = rs.getFloat("GiaThue");
            String tenTacGia = book.getNameAuthorByIdBook(maSach);
            book = new Book(maSach, tenSach, tenTacGia, priceBook, null, tenNhaPhatHanh);

            // book.set
            books.add(book);
        }

        return books;
    }

    /*
     * Hàm này để lấy lần lượt số hiệu bản sao theo các quyển sách đã mượn
     * 
     * @throws ClassNotFoundException
     * 
     * @throws SQLException
     * 
     * @see ClassNotFoundException
     * 
     * @see SQLException
     */
    public ArrayList<String> getNumberOfCopy(String idBorrower) throws ClassNotFoundException, SQLException {
        ArrayList<String> numberofCopy = new ArrayList<>();
        connectDatabase();
        PreparedStatement st = conn.prepareStatement(
                "SELECT bansaoSach.ViTri from bansaosach,sach,thongtinmuontrasach,nguoimuon,chitietmuonsach,nhaphathanh where nguoimuon.maNguoiMuon = ? \n"
                        + "and nguoimuon.maNguoiMuon = thongtinmuontrasach.maNguoiMuon\n"
                        + "and thongtinmuontrasach.MaTTMuonTra = chitietmuonsach.MaTTMuonTra\n"
                        + "and chitietmuonsach.MaSachCP = bansaosach.MaSachCP\n"
                        + "and sach.maSach=bansaosach.maSach\n"
                        + "and nhaphathanh.MaNPH = sach.MaNPH");
        st.setString(1, idBorrower);

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            numberofCopy.add(rs.getString("ViTri"));
        }
        return numberofCopy;
    }

    /*
     * Hàm này để lấy danh sách sách theo thông tin từ các trường mã sách, tên sách,
     * tác giả hay tên nhà phát hành
     * 
     * @throws ClassNotFoundException
     * 
     * @throws SQLException
     * 
     * @see ClassNotFoundException
     * 
     * @see SQLException
     */
    public ArrayList<Book> getBookByInfo(String info) throws ClassNotFoundException, SQLException {
        ArrayList<Book> books = new ArrayList<>();
        connectDatabase();
        Book book;
        String sqlCommand = "SELECT sach.maSach,tenSach,tenNhaPhatHanh,tenTacGia,sach.GiaThue,nhaphathanh.maNhaPhatHanh FROM sach, tacgia,nhaphathanh,chitiettacgia where sach.maSach like '%"
                + info + "%' \n"
                + "and sach.maSach = chitiettacgia.maSach and chitiettacgia.maTacGia =tacgia.maTacGia\n"
                + "and sach.MaNPH = nhaphathanh.MaNPH group by sach.maSach\n"
                + "union\n"
                + "(SELECT sach.maSach,tenSach,tenNPH,tenTacGia,sach.GiaThue,nhaphathanh.maNPH FROM sach, tacgia,nhaphathanh,chitiettacgia where sach.tenSach like '%"
                + info + "%' \n"
                + "and sach.maSach = chitiettacgia.maSach and chitiettacgia.maTacGia =tacgia.maTacGia\n"
                + "and sach.maNPH = nhaphathanh.maNPH group by sach.maSach\n"
                + ")\n"
                + "union\n"
                + "(SELECT sach.maSach,tenSach,tenNhaPhatHanh,tenTacGia,sach.GiaThue,nhaphathanh.maNPH  FROM sach, tacgia,nhaphathanh,chitiettacgia where nhaphathanh.tenNPH like '%"
                + info + "%' \n"
                + "and sach.maSach = chitiettacgia.maSach and chitiettacgia.maTacGia =tacgia.maTacGia\n"
                + "and sach.maNPH = nhaphathanh.maNPH group by sach.maSach\n"
                + ")\n"
                + "union\n"
                + "(SELECT sach.maSach,tenSach,tenNPH,tenTacGia,sach.GiaThue,nhaphathanh.maNPH  FROM sach, tacgia,nhaphathanh,chitiettacgia where tacgia.tenTacGia like '%"
                + info + "%' \n"
                + "and sach.maSach = chitiettacgia.maSach and chitiettacgia.maTacGia =tacgia.maTacGia\n"
                + "and sach.maNPH = nhaphathanh.maNPH group by sach.maSach\n"
                + ")";
        PreparedStatement st = conn.prepareStatement(sqlCommand);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            String titleBook = rs.getString("tenSach");
            String idBook = rs.getString("maSach");
            Publisher namePublisher = new Publisher().getPublisherByIdPublisher(rs.getInt("maNhaPhatHanh"));
            String nameAuthor = getNameAuthorByIdBook(idBook);
            float priceBook = rs.getFloat("GiaThue");
            ArrayList<CopyOfBook> listBanSao = new CopyOfBook().getListCopyOfBookByIdBook(idBook);
            book = new Book(idBook, titleBook, nameAuthor, priceBook, listBanSao, namePublisher);
            books.add(book);
        }
        return books;
    }

    /*
     * Hàm này để cập nhật trang thái của sách sau khi đăng ký mượn sách
     * 
     * @throws ClassNotFoundException
     * 
     * @throws SQLException
     * 
     * @see ClassNotFoundException
     * 
     * @see SQLException
     */
    public void updateBookInfo(String MaSachCP) throws ClassNotFoundException, SQLException {
        connectDatabase();
        String sqlCommand = "UPDATE bansaosach SET trangThai='2' WHERE MaSachCP= ?";
        PreparedStatement st = conn.prepareStatement(sqlCommand);
        st.setString(1, MaSachCP);
        st.execute();
    }

    public ArrayList<Object[]> handleBookCopy(String maSach) throws SQLException, ClassNotFoundException {
        Object[] a = new Object[5];
        a = searchAuthor(maSach);
        ArrayList<Object[]> dataTable = new ArrayList<>();
        connectDatabase();
        ResultSet rs = null;
        PreparedStatement preparedStatement;
        String sql1 = "select sach.maSach,sach.tenSach,bansaosach.MaSachCP,bansaosach.trangThai,sach.GiaThue from  sach,bansaosach\n"
                + "where   sach.maSach=? and bansaosach.maSach= sach.maSach;";
        preparedStatement = conn.prepareStatement(sql1);
        preparedStatement.setString(1, maSach);
        rs = preparedStatement.executeQuery();
        try {
            while (rs.next()) {
                String s;
                if (Integer.valueOf(rs.getString(4).toString()) == 0) {
                    s = "Chưa mượn!";
                } else {
                    s = "Đang mượn!";
                }
                dataTable.add(new Object[] { a[0], a[1], a[2], a[3], a[4], rs.getString(3), s, rs.getString(5) });
            }
            closeDatabase();
            return dataTable;

        } catch (SQLException ex) {
            Logger.getLogger(BookDetail_Controller.class.getName()).log(Level.SEVERE, "Bản sao sách không tồn tại!", "");
        }
        return dataTable;
    }

    public Object[] searchAuthor(String maSach) throws SQLException, ClassNotFoundException {
        connectDatabase();
        String Author = "";
        Object[] a = new Object[5];
        ResultSet rs = null;
        PreparedStatement preparedStatement;
        String sql = "select sach.maSach,sach.tenSach,nhaphathanh.TenNPH,tacgia.tenTacGia,sach.GiaThue from  sach,tacgia,nhaphathanh,chitiettacgia \n"
                + "where sach.maSach= chitiettacgia.maSach and chitiettacgia.maTacGia= tacgia.maTacGia\n"
                + "and sach.maNPH = nhaphathanh.maNPH  and sach.maSach=?;";
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, maSach);
        rs = preparedStatement.executeQuery();

        try {
            rs.next();
            Author = Author + rs.getString(4);
            a[0] = rs.getString(1);
            a[1] = rs.getString(2);
            a[2] = rs.getString(3);
            a[4] = rs.getString(5);
            while (rs.next()) {
                Author += "\n   " + rs.getString(4);
            }
            System.out.println(Author);
            a[3] = Author;
            closeDatabase();
        } catch (SQLException ex) {
            Logger.getLogger(BookDetail_Controller.class.getName()).log(Level.SEVERE, "Sách không tồn tại!", "");
        }
        return a;
    }

    public ArrayList<Book> getListBook() throws ClassNotFoundException, SQLException {
        ArrayList<Book> arr = new ArrayList<>();
        connectDatabase();
        PreparedStatement ps = conn.prepareStatement("Select * from sach");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Book book = new Book();
            book.setbook_ID(rs.getString("maSach"));
            book.setbook_Title(rs.getString("tenSach"));
            Publisher publisher = new Publisher().getPublisherByIdPublisher(rs.getInt("maNPH"));
            book.setbook_Publisher(publisher);
            String nameAuthor = getNameAuthorByIdBook(book.getbook_ID());
            book.setbook_Author(nameAuthor);
            arr.add(book);
        }
        closeDatabase();
        return arr;
    }
}
