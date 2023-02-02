package Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import Model.Publisher;

public class Publisher_Controller {
    
    private static Publisher_Controller publisherController;
    Publisher publisher = new Publisher();
    
    public static Publisher_Controller getInstance(){
        if (publisherController == null){
            publisherController = new Publisher_Controller();
        }
        return publisherController;
    }
    
    /**
     * Hàm này lấy ra danh sách các nhà phát hành có trong hệ thống.
     * @return danh sách các nhà phát hành có trong hệ thống
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<Publisher> getListPublishers() throws ClassNotFoundException, SQLException{
        return publisher.getListPublishers();
    }

    /**
     * Hàm này thêm mới một nhà phát hành vào trong hệ thống.
     * @param publisher nhà phát hành muốn thêm vào
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void addPublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
        publisher.addPublisher();
    }
    
    /**
     * Hàm này lấy ra mã lớn nhất của các nhà phát hànhcó trong hệ thống.
     * @return mã lớn nhất của nhà phát hành có trong hệ thống
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int getMaxIdPublisher() throws ClassNotFoundException, SQLException {
        return publisher.getMaxIdPublisher();
    }
    
}
