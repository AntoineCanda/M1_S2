package car.tp4.servlet;

import car.tp4.entity.Book;
import car.tp4.entity.BookBean;

import javax.ejb.EJB;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet permettant l'affichage des listes de livre de la base
 * @author antoine
 *
 */
@WebServlet("/books")
public class BookServlet extends HttpServlet {

  @EJB
  private BookBean bookBean;
  
  @EJB
  private PanierBean panierBean;
  
  private Panier panier;

  /**
   * Initialisation de la base avec un livre
   */
  @Override
  public void init() throws ServletException {
	  bookBean.addBook(new Book("Harry Potter à l'ecole des soricer", "J.K.Rowling", 1997, 100));
  }
  /**
   * Creation d'un panier si il n'existe pas.
   * Affichage des livres de la base.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
	  this.panier = (Panier) request.getSession().getAttribute("panier");
	  
	  if(this.panier == null){
		  this.panier = new Panier();
		  request.getSession().setAttribute("panier", panier);
	  }
	  
	  List<Book> books = bookBean.getAllBooks();
	  
	  request.setAttribute("books", books);
	  
	  RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/book.jsp");
	  dispatcher.forward(request, response);
  }

	/**
	 * Validation du panier entraine sa sauvegarde dans la base de donnee Si
	 * annulation, restaure les quantites des livres dans la base de donnee
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String type = request.getParameter("type");
		if (type.equals("valide") && ( ! this.panier.getLivres().isEmpty())) {
			panierBean.save(this.panier);
		} else {
			ArrayList<Book> books = panier.getLivres();
			for (Book book : books) {
				Book b = bookBean.getBookById(book.getId()).get(0);
				bookBean.setQuantiteByID(book.getId(), b.getQuantte() + 1);
			}
		}
		this.panier = new Panier();
		request.getSession().setAttribute("panier", panier);
		List<Book> books = bookBean.getAllBooks();

		request.setAttribute("books", books);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/book.jsp");
		dispatcher.forward(request, response);
	}

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
      return "Servlet for getting the list of all books";
  }

}
