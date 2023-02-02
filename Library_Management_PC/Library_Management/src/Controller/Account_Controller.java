/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.SQLException;
import Model.User;

public class Account_Controller {

    User user = new User();
    private static Account_Controller accountController;
    
    private Account_Controller() {
    }

    /**
     * Hàm này khởi tạo cho đối tượng dùng chung duy nhất của AccountController
     * @return đối tượng dùng chung duy nhất của AccountController
     */
    public static Account_Controller getInstance() {
        if (accountController == null) {
            accountController = new Account_Controller();
        }
        return accountController;
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
    public boolean checkLogin(String username, String pass) throws SQLException, ClassNotFoundException {
        return user.checkLogin(username, pass);
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
        return user.login(username, type);
    }

}
