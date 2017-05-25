package car.tp4.entity;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Local
public class BookBean {

  @PersistenceContext(unitName = "book-pu")
  private EntityManager entityManager;

  /**
   * Ajoute un livre dans la base de donn�e
   * @param Book book le livre a ajouter
   */
  public void addBook(Book book) {
    entityManager.persist(book);
  }

  /**
   * Recupere la liste des livres de la base de donn�e
   * @return List<Book> la liste des livres
   */
  public List<Book> getAllBooks() {
    Query query = entityManager.createQuery("SELECT b from Book as b");
    return query.getResultList();
  }
  
  /**
   * Recupere la liste des auteurs des livres de la base de donn�e
   * @return List<String> la liste des auteurs des livres
   */
  public List<String> getAllAuteur(){
	  Query query = entityManager.createQuery("SELECT distinct book.auteur from Book as book");
	  return query.getResultList();
  }
  
	/**
	 * 
	 *Fonction permettant d'effectuer une requete afin de r�cuperer un livre en fonction d'un crit�re correspondant � un mot cl� en rapport avec titre, auteur.
	 * 
	 * @param attribut l'attribut que l'on va chercher, auteur ou titre
	 * @param keyword le nom de l'auteur ou du titre
	 * @return la liste des livres contenus dans la base de donn�e respectant le crit�re
	 */
	public List<Book> getBooksWithSearch(String attribut, String keyword) {
		Query query = entityManager.createQuery("SELECT b FROM Book as b WHERE b."+attribut+" like '%"+keyword+"%'");
		return query.getResultList();
	}
	
	/**
	 * 
	 * renvoie la liste des livres corresppondants aux crit�res
	 * 
	 * @param title nom du livre � trouver partiellement ou entierement
	 * @param author nom de l'auteur � trouver partiellement ou entierement
	 * @param year la date de l'ann�e � rechercher
	 * @return la liste des livres contenus dans la base de donn�e 
	 */
	public List<Book> getBook(String title, String author, int year) {
		Query query = entityManager.createQuery("SELECT b FROM Book as b where b.title like '%"+title+"%' and b.author like '%"+author+"%' and b.yaer="+year);
		return query.getResultList(); 		
	}
	
	/**
	 * 
	 * @param id l'identifiant du livre
	 * @return la liste des livres correspondant � l'id pass� en param�tre
	 */
	public List<Book> getBookById(long id) {
		Query query = entityManager.createQuery("SELECT b FROM Book as b where b.id="+id);
		return query.getResultList();	
		
	}
	
	/**
	 * mise � jour de la quantit� de livre disponible pour le livre corresppondant aux crit�res
	 * 
	 * @param title le nom du livre
	 * @param author le nom de l'auteur du livre
	 * @param year l'ann�e de parution du livre
	 * @param quantite la nouvelle quantit� restante du livre
	 */
	public void setQuantite(String title, String author, int year, int quantite) {
		Query query = entityManager.createQuery("UPDATE Book as b set b.quantite = "+quantite+" where b.title like '%"+title+"%' and b.author like '%"+author+"%' and b.year="+year);
		query.executeUpdate();
	}
	
	/**
 	 * mise � jour de la quantit� de livre disponible pour le livre corresppondant � l'id
	 * @param id l'identifiant du livre
	 * @param quantite la nouvelle quantit� restante du livre
	 */
	public void setQuantiteByID(long id,int quantite) {
		Query query = entityManager.createQuery("UPDATE Book as b set b.quantite = "+quantite+" where b.id="+id);
		query.executeUpdate();
	}

}