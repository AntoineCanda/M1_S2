package car.tp4.servlet;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.tp4.entity.Book;
import car.tp4.entity.BookBean;

/**
 * Servlet qui permet d'afficher le détail d'un livre
 * 
 * @author antoine
 *
 */
@WebServlet("/book")
public class DetailServlet extends HttpServlet {

	@EJB
	private BookBean bookBean;

	/**
	 * affiche le détail du livre sélectionné 
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/detail.jsp");
		
		List<Book> books = bookBean.getBookById(Long.parseLong(request.getParameter("id")));
		
		if (!books.isEmpty()) {
			request.setAttribute("id", books.get(0));
		}

		dispatcher.forward(request, response);
	}

}
