package car.tp4.entity;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Classe Book
 * L'entite Book represente un livre. Ce dernier est defini par un titre, un auteur et une date de parution.
 * @author antoine
 *
 */
@Entity
public class Book implements Serializable, Comparable<Book> {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  /**
   *  identifiant du livre
   */
  private long id;
  /**
   *  auteur du livre
   */
  private String author;
  /**
   *  titre du livre
   */
  private String title;
  /**
   *  annee de parution
   */
  private int year;
  /**
   *  quantite de livre disponible
   */
  private int quantite;

  /**
   * Constructeur d'un Book
   */
  public Book(String author, String title, int year, int quantite) {
    this.author = author;
    this.title = title;
    this.year = year;
    this.quantite = quantite;
  }

  /**
   * Getter de l'auteur
   * @return l'auteur du livre
   */
  public String getAuthor() {
    return author;
  }

  /**
   * Setter de l'auteur
   * @param L'auteur du livre a ajouter
   */
  public void setAuthor(String author) {
    this.author = author;
  }

  /**
   * Getter du titre du livre
   * @return le titre du livre
   */
  public String getTitle() {
    return title;
  }
  
  /**
   * Setter du titre du livre
   * @param Le titre du livre a ajouter
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Getter de l'annee de parution du livre
   * @return l'annee de parution du livre
   */
  public int getYear() {
    return year;
  }
  
  /**
   * Setter de l'annee de parution du livre
   * @param L'annee de parution du livre a ajouter
   */
  public void setYear(int year) {
    this.year = year;
  }
  
  /**
   * Getter de la quantite de livres disponbile
   * @return la quantite de livres disponbile
   */
  public int getQuantite() {
    return this.quantite;
  }
  
  /**
   * Setter la quantite de livres disponbile
   * @param la quantite de livres disponbile � ajouter
   */
  public void setQuantite(int quantite) {
    this.quantite = quantite;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Book book = (Book) o;

    if (id != book.id) return false;
    if (!author.equals(book.author)) return false;
    if (year != book.year) return false;
    return title.equals(book.title);
  }

  @Override
  public int hashCode() {
    int result = (int) (id ^ (id >>> 32));
    result = 31 * result + author.hashCode();
    result = 31 * result + title.hashCode();
    result = 31 * result + (year >>> 32);
    return result;
  }

  @Override
  public String toString() {
    return "Book{" +
      "author = '" + author + '\'' +
      ", title = '" + title + '\'' +
      ", annee de parution = '" + year + '\''+
      ", quantite = '" + quantite + '\''+
      '}';
  }
  
  /**
   * Compare deux livres selon leur date de parution.
   * Si elles sont �gales retourne 0 sinon -1 ou 1 pour permettre un tri
   * @param Book b un livre � comparer
   * @return int valant 0, -1 ou 1
   */
  public int compareTo(Book b){
	  if(b.getYear() == this.getYear())
		  return 0;
	  else
		  int res = (b.getYear() > this.getYear())?-1:1;
	  
	  return res;
  }
}
