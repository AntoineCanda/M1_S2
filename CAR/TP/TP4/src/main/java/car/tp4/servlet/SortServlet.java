package car.tp4.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
 * Servlet pour le tri des livres selon l'année de parution
 * 
 * @author antoine
 *
 */
@WebServlet("/booksSort")
public class SortServlet extends HttpServlet {

	@EJB
	private BookBean bookBean;

	/**
	 * affiche la liste des livres de la base de donnée trié par ordre numérique
	 * croissant
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Book> booksSorted = new ArrayList<Book>(bookBean.getAllBooks());
		Collections.sort(booksSorted);
		request.setAttribute("books", booksSorted);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/booksort.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * permet grâce aux quatres champs du formulaire de rajouter un livre avec
	 * sa quantité, si jamais le livre existait auparavant ajoute la nouvelle
	 * quantité à l'ancienne sans ajouté de livre
	 * 
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/add.jsp");
		request.setAttribute("titre", request.getParameter("titre"));
		request.setAttribute("year", request.getParameter("year"));
		request.setAttribute("auteur", request.getParameter("auteur"));

		Book b = new Book(request.getParameter("auteur"), request.getParameter("titre"),
				Integer.parseInt(request.getParameter("year")), Integer.parseInt(request.getParameter("quantite")));
		bookBean.addBook(b);

		dispatcher.forward(request, response);
	}

}
