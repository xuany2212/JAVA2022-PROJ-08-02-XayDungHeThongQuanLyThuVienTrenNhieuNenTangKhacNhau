package Model.Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.DAO.BookDAO;

@WebServlet("/DeleteBook")
public class DeleteBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDAO bookDAO;
	
	
	public DeleteBook() {
		super();
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
		String id = (String) request.getParameter("id");
		if (id == null) {
			int result;
			try {
				result = bookDAO.deleteAllBook();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			int result;
			try {
				result = bookDAO.deleteBook(id);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		request.setAttribute("errorString", "Đã xóa thành công");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ManageBook");
		dispatcher.forward(request, response);
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
