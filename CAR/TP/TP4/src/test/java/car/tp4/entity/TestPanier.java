package car.tp4.entity;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;

public class TestPanier extends TestCase {

	@Test
	public void testGetLivres() {
		Panier panier = new Panier();
		assertEquals(panier.getLivres(),new ArrayList<Book>());
	}
	
	@Test
	public void testGetLivresWithBook() {
		Panier panier = new Panier();
		Book book = new Book("Harry Potter", "JKRowling", 1997, 10);
		panier.addBook(book);
		ArrayList<Book> list = new ArrayList<Book>();
		list.add(book);
		assertEquals(panier.getLivres(),list);
	}
	
	@Test
	public void testGetQuantiteEmpty() {
		Panier panier = new Panier();
		assertEquals(panier.getQuantite(),0);
	}
	
	@Test
	public void testGetQuantiteWithBook() {
		Panier panier = new Panier();
		Book book = new Book("Harry Potter", "JKRowling", 1997, 10);
		panier.addBook(book);
		assertEquals(panier.getQuantte(),1);
	}

	
	@Test
	public void testSetQuantite() {
		Panier panier = new Panier();
		assertEquals(panier.getQuantite(),0);
		panier.setQuantite(1);
		assertEquals(panier.getQuantite(),1);
	}
	
	@Test
	public void testRemoveBook() {
		Panier panier = new Panier();
		Book book = new Book("Harry Potter", "JKRowling", 1997, 10);
		panier.addBook(book);
		ArrayList<Book> list = new ArrayList<Book>();
		list.add(book);
		assertEquals(panier.getBooks(),list);
		panier.removeBook(book);;
		list.remove(book);
		assertEquals(panier.getQuantite(),0);
		assertEquals(panier.getLivres(),list);
	}
	
}
