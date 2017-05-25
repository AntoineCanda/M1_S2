package car.tp4.servlet;

import javax.servlet.annotation.WebServlet;
import java.util.List;
import car.tp4.entity.Book;
import car.tp4.entity.BookBean;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;


/**
 * Servlet permettant l'ajout d'un livre dans la bdd
 * @author Antoine
 */
@WebServlet("/add")
public class AddServlet extends HttpServlet {

    @EJB
    private BookBean bookBean;

    

    /**
     * Affiche un formulaire permettant d'avoir les champs pour ajouter un livre a la bdd
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/add.jsp");
    	dispatcher.forward(request,response);
    }

    /**
     * Creer un nouveau livre dans la base de donnee a partir des informations receuilli via le formulaire d'ajout 
     * ou bien mise à jour de la quantite de livre si il existe dans la base
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/add.jsp");
    	
    	String titre = request.getParameter("titre");
    	String auteur = request.getParameter("auteur");
    	String annee = request.getParameter("year");
    	String quantite = request.getParameter("quantite");

    	request.setAttribute("titre", titre );
    	request.setAttribute("auteur", auteur);
    	request.setAttribute("year", annee);
    	request.setAttribute("quantite", quantite);
    	
    	List<Book> books = bookBean.getBook(titre,auteur, Interger.parseInt(annee));
    	if(books.isEmpty()){
    		Book b = new Book(titre,auteur,Interger.parseInt(annee),Interger.parseInt(quantite));
    		bookBean.addBook(b);
    	}
    	else{
    		bookBean.setQuantite(titre,auteur,Interger.parseInt(annee),Interger.parseInt(quantite)+books.get(0).getQuantite());
    	}
    	dispatcher.forward(request,response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet for add book";
    }

}
