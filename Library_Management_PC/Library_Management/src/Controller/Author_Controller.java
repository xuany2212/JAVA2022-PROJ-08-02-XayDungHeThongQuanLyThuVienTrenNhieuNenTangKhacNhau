package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Author;

public class Author_Controller {
    
    private static Author_Controller authorController;
    Author author = new Author();
    
    /**
     * Hàm này khởi tạo cho đối tượng dùng chung duy nhất của AuthorController
     * @return đối tượng dùng chung duy nhất của AuthorController
     */
    public static Author_Controller getInstance(){
        if (authorController == null){
            authorController = new Author_Controller();
        }
        return authorController;
    }
    
    /**
     * Hàm này lấy ra danh sách các tác giả có trong hệ thống.
     * @return danh sách các tác giả có trong hệ thống
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<Author> getListAuthor() throws ClassNotFoundException, SQLException{
        return author.getListAuthor();
    }

    /**
     * Hàm này lấy ra mã lớn nhất của các tác giả có trong hệ thống.
     * @return mã lớn nhất của tác giả có trong hệ thống
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String getMaxIdAuthor() throws ClassNotFoundException, SQLException {
        return author.getMaxIdAuthor();
    }

    /**
     * Hàm này thêm mới một tác giả vào trong hệ thống.
     * @param author Tác giả muốn thêm vào
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void addAuthor(Author author) throws ClassNotFoundException, SQLException {
        author.addAuthor();
    }
}
