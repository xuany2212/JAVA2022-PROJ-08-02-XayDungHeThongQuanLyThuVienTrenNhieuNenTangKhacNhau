/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Model.*;
import java.sql.*;

public class Create_BorrowingCard_Controller extends DataAccessHelper{


    public  User getAccount(String tenTaiKhoan) throws SQLException, ClassNotFoundException {
        return new BorrowingCard().getAccount(tenTaiKhoan);
    }

    public String registerborrower(Borrower nm,BorrowingCard tm,User tk) throws SQLException, ClassNotFoundException  {
         return new BorrowingCard().registerborrower(nm, tm, tk);
    }
}
