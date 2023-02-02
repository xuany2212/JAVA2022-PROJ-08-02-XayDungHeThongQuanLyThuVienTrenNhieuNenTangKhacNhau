package Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import Model.*;

public class Book_Controller {

    private Book book = new Book();
    private static Book_Controller bookController;
    
    private Book_Controller() {}
    
    /**
     * Hàm này khởi tạo cho đối tượng dùng chung duy nhất của BookController
     * @return đối tượng dùng chung duy nhất của BookController
     */
    public static Book_Controller getInstance(){
        if (bookController == null){
            bookController = new Book_Controller();
        }
        return bookController;
    }
    
    /**
     * Hàm này lấy quyển sách từ mã sách
     * @param idBook mã quyển sách muốn lấy thông tin
     * @return trả về quyển sách cần lấy ra
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Book getBookByIdBook(String idBook) throws ClassNotFoundException, SQLException{
        return book.getBookByIdBook(idBook);
    }
    
    /**
     * Hàm này lấy quyển sách từ mã bản sao sách
     * @param idCopyOfBook mã bản sao sách
     * @return trả về quyển sách cần lấy ra
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Book getBookByIdCopyOfBook(String idCopyOfBook) throws ClassNotFoundException, SQLException {
        return new CopyOfBook().getBookByIdCopyOfBook(idCopyOfBook);
    }
    
    /**
     * Hàm này thực hiện chức năng thêm một bản sao của một sách
     * @param idBook mã sách của cuốn sách muốn thêm các bản sao
     * @param copyOfBook bản sao sách muốn thêm vào
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public void addCopyOfBookByIdBook(String idBook,CopyOfBook copyOfBook) throws ClassNotFoundException, SQLException{
        copyOfBook.addCopyOfBookByIdBook(idBook);
    }
    
    /**
     * Hàm này thực hiện chức năng thêm một sách vào cơ sở dữ liệu
     * @param book sách muốn thêm vào
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public void addBook(Book book) throws ClassNotFoundException, SQLException {
        book.addBook();
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
        return new CopyOfBook().getCopyOfBookById(idCopyOfBook);
    }

    /**
     * Hàm này thực hiện chức năng cập nhật thông tin bản sao sách 
     * @param b  bản sao sách muốn cập nhật thông tin
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public void updateCopyOfBook(CopyOfBook book) throws ClassNotFoundException, SQLException {
        book.updateCopyOfBook();
    }

    public ArrayList<String> getNumberOfCopy(String idBorrower) throws ClassNotFoundException, SQLException {
        return new Book().getNumberOfCopy(idBorrower);
    }

    public CopyOfBook.StatusOfCopy getStatusByIdBook(String idBook, String numberOfCopy) throws ClassNotFoundException, SQLException {
       return new CopyOfBook().getStatusByIdBook(idBook,numberOfCopy);
    }
    
    
    public ArrayList<Book> getListBook() throws ClassNotFoundException, SQLException {
        return  new Book().getListBook();
    }

    /**
     * Hàm này thực hiện chức năng tìm kiếm sách theo tên sách trong CSDL
     * @param key từ khóa tìm kiếm 
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see ClassNotFoundException
     * @see SQLException
     */
    public ArrayList<Book> searchBook(String key) throws SQLException, ClassNotFoundException {
        return  book.searchBook(key);
    }

}
