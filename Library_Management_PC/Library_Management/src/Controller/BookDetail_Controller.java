package Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import Model.*;

public class BookDetail_Controller extends DataAccessHelper {

    public ArrayList<Object[]> HandleBookCopy(String maSach) throws SQLException, ClassNotFoundException {
        return new Book().handleBookCopy(maSach);
    }

}

