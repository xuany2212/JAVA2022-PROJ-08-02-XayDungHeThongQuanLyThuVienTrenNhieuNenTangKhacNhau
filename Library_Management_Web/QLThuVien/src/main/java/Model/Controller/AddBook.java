package Model.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Bean.Book;
import Model.Bean.NhaPhatHanh;
import Model.DAO.BookDAO;
import Model.DAO.NxbDAO;

/**
 * Servlet implementation class AddBook
 */
@MultipartConfig
@WebServlet("/AddBook")
public class AddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDAO bookDAO;
	private NxbDAO nxbDAO;

	public AddBook() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		try {
			bookDAO = new BookDAO(jdbcURL);
			nxbDAO = new NxbDAO(jdbcURL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("User") == null) {
			String errorString = "Bạn cần đăng nhập trước";
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		} else {

			String errorString = null;
			List<NhaPhatHanh> list = null;
			
			try {
				list = nxbDAO.getAllNxb();
			} catch (Exception e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
			
			if (request.getAttribute("errorString") != null) {
				errorString = (String) request.getAttribute("errorString");
			}
			// Lưu thông tin vào request attribute trước khi forward sang views.
			request.setAttribute("errorString", errorString);
			request.setAttribute("listNXB", list);
			request.getSession().setAttribute("Check", "AddBook");
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/add_book.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String tensach = request.getParameter("tensach");
		String tentacgia = request.getParameter("tentacgia");
		Double giathue = (double) Integer.parseInt(request.getParameter("giathue"));
		String mota = request.getParameter("mota");
		String vitri = request.getParameter("vitri");
		int slcp = Integer.parseInt(request.getParameter("slcp"));
		int manph = Integer.parseInt(request.getParameter("nxb"));
		
		Book book = new Book();
		book.setTenSach(tensach);
		book.setTenTacGia(tentacgia);
		book.setGiaThue(giathue);
		book.setMoTa(mota);
		book.setViTri(vitri);
		book.setSoLuongCP(slcp);
		book.setMaNPH(manph);
		try {
			int result = bookDAO.insertBook(book);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("errorString", "Thêm sách thành công");
		doGet(request, response);
	}
}
