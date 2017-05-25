package car.tp4.entity;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * Classe Panier
 * L'entit� Panier repr�sente un panier electronique.
 * @author antoine
 *
 */
@Entity
public class Panier implements Serializable {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	/**
	 * id du panier
	 */
	private Long id;
	
	/** 
	 * nbre de livre du panier
	 */
	private int quantite;
	
	/**
	 * les livres de la commande
	 */
	private List<Book> livres;
	
	public Panier(){
		this.livres = new ArrayList<Book>();
		this.quantite = 0;
	}
	
	/**
	 * Getter de l'identifiant du panier
	 * @return l'identifiant du panier
	 */
	public long getId() {
		return this.id;
	}
	
	/**
	 * Getter de la liste des livres
	 * @return la liste des livres de la commande
	 */
	public List<Book> getLivres(){
		return this.livres;
	}
	
	/**
	 * Setter de la quantite de livres
	 * @param la quantite de livres de la commande
	 */
	public void setQuantite(int quantite){
		this.quantite = quantite;
	}
	
	/**
	 * Getter de la quantite de livres
	 * @return la quantite de livres de la commande
	 */
	public int getQuantite(){
		return this.quantite;
	}
	
	/**
	 * Setter de la liste des livres
	 * @param la liste des livres de la commande
	 */
	public void setLivres(List<Book> livres){
		this.livres = livres;
	}
	
	/**
	 * Methode permettant d'ajouter un livre � la commande, on incremente de un la quantite.
	 * @param Le livre a ajouter
	 */
	public void addBook(Book book){
		this.livres.add(book);
		this.quantite++;
	}
	
	/**
	 * Methode permettant de retirer un livre � la commande, on decremente de un la quantite.
	 * @param Le livre a retirer
	 */
	public void removeBook(Book book){
		if(this.livres.contains(book)){
			this.livres.remove(book);
			this.quantite--;
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Panier panier = (Panier) o;

		return id == panier.id;
	}

	  @Override
	  public int hashCode() {
	    int result = (int) (id ^ (id >>> 32));
	    return result;
	  }

	  @Override
	  public String toString() {
	    return "Panier{" +
	      "id='" + id + '\'' +
	      '}';
	  }
}
