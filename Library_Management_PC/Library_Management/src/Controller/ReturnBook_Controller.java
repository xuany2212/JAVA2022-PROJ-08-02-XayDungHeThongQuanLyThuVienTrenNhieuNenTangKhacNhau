package Controller;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import Model.*;

public class ReturnBook_Controller extends DataAccessHelper {

    public Object[] getBookIsBorrowed(String maNguoiMuon) throws SQLException, ClassNotFoundException, ParseException {
        return new CopyOfBook().getBookIsBorrowed(maNguoiMuon);
    }

    public void ReturnBook(ArrayList<Object[]> dataTable, boolean[] id) throws SQLException, ClassNotFoundException {
        new CopyOfBook().ReturnBook(dataTable, id);
    }
   
    public void UpdateInfoBorrowTable(ArrayList<String> InfoBorrow) throws ClassNotFoundException, SQLException{
        new CopyOfBook().UpdateInfoBorrow(InfoBorrow);
    }

}
