package Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import Model.BorrowingCard;

public class BorrowingCard_Controller {

    BorrowingCard card = new BorrowingCard();

    public BorrowingCard_Controller() {
        card = new BorrowingCard();
    }

    public ArrayList<BorrowingCard> getAllCard() throws ClassNotFoundException, SQLException {
        return card.getAllCard();
    }

    public ArrayList<BorrowingCard> getCardSearchingList(String info) throws ClassNotFoundException, SQLException {
        return card.getCardById(info);
    }
}
