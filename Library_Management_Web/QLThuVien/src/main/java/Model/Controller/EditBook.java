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

@MultipartConfig
@WebServlet("/EditBook")
public class EditBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NxbDAO nxbDAO;
	private BookDAO bookDAO;

	public EditBook() {
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
		// TODO Auto-generated method stub
		if (request.getSession().getAttribute("User") == null) {
			String errorString = "Bạn cần đăng nhập trước";
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		} else {
			String id = (String) request.getParameter("id");
			System.out.println("id = " + id);

			String errorString = null;
			List<NhaPhatHanh> list = null;
			Book book = new Book();

			try {
				book = bookDAO.findBook(id);
				list = nxbDAO.getAllNxb();
			} catch (SQLException e) {
				e.printStackTrace();
				errorString = e.getMessage();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (request.getAttribute("errorString") != null) {
				errorString = (String) request.getAttribute("errorString");
			}

			request.setAttribute("book", book);
			request.setAttribute("listNXB", list);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/edit_book.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String masach = request.getParameter("masach");
		String tensach = request.getParameter("tensach");
		String tentacgia = request.getParameter("tentacgia");
		Double giathue = Double.parseDouble(request.getParameter("giathue"));// (double)
																				// Integer.parseInt(request.getParameter("giathue"));
		String mota = request.getParameter("mota");
		String vitri = request.getParameter("vitri");
		int slcp = Integer.parseInt(request.getParameter("slcp"));
		int manph = Integer.parseInt(request.getParameter("nxb"));

		Book book = new Book();
		book.setMaSach(masach);
		book.setTenSach(tensach);
		book.setTenTacGia(tentacgia);
		book.setGiaThue(giathue);
		book.setMoTa(mota);
		book.setViTri(vitri);
		book.setSoLuongCP(slcp);
		book.setMaNPH(manph);

		int result = 0;
		String errorString = null;
		try {
			result = bookDAO.updateBook(book);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errorString = e.getMessage();
		}
		if (result == 0 && errorString == null) {
			errorString = "Chỉnh sửa thất bại";
		}
		if (result == 1)
			errorString = "Chỉnh sửa thành công";
		// Lưu thông tin vào request attribute trước khi forward sang views.
		request.setAttribute("errorString", errorString);
		doGet(request, response);

	}
}
