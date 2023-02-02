package Controller;

import java.sql.SQLException;
import java.util.*;
import Model.*;

public class BorrowingInfo_Controller {
        
    private static BorrowingInfo_Controller controller;
    
    /**
     * Hàm này khởi tạo đối tượng duy nhất đại diện cho BorrowingInformationController
     * @return đối tượng duy nhất đại diện cho BorrowingInformationController
     */
    public static BorrowingInfo_Controller getInstance(){
        if (controller == null){
            controller = new BorrowingInfo_Controller();
        }
        return controller;
    }

    /**
     * Hàm này thực hiện chức năng lấy ra danh sách các thông tin mượn trả sách tương ứng với người mượn
     * @param borrower đối tượng Borrower muốn lấy thông tin mượn trả sách
     * @return danh sách các thông tin mượn trả cảu đối tượng người mượn ở trên
     */
    public ArrayList<BorrowingInfo> getListBorrowingInformationByIdBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
        return new BorrowingInfo().getListBorrowingInformationByBorrower(borrower);
    }

    public void deleteBorrowingInfor(String idBorrowInfor) throws ClassNotFoundException, SQLException {
        new BorrowingInfo().deleteBorrowingInfor(idBorrowInfor);
    }

    public boolean updateLent(BorrowingInfo borrowingInformation) throws ClassNotFoundException, SQLException {
       return borrowingInformation.updateLentBook();
    }

}
