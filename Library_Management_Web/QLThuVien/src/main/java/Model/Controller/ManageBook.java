package Model.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Bean.Book;
import Model.DAO.BookDAO;

@WebServlet("/ManageBook")
public class ManageBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDAO bookDAO;

	public ManageBook() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		try {
			bookDAO = new BookDAO(jdbcURL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		if (request.getSession().getAttribute("User") == null) {
			String errorString = "Bạn cần đăng nhập trước";
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		} else {
			String errorString = null;
			List<Book> list = null;
			try {
				list = bookDAO.getAllBook();
			} catch (Exception e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
			if (request.getAttribute("errorString") != null) {
				errorString = (String) request.getAttribute("errorString");
			}
			// Lưu thông tin vào request attribute trước khi forward sang views.
			request.setAttribute("errorString", errorString);
			request.setAttribute("bookList", list);
			request.getSession().setAttribute("Check", "ManageBook");
			// Forward sang /WEB-INF/views/productListView.jsp
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/manage_book.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
