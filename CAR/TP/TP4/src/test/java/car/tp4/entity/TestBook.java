package car.tp4.entity;

import org.junit.Test;

import junit.framework.TestCase;

public class TestBook extends TestCase {

	@Test
	public void testGetTitle() {
		Book book = new Book("Harry Potter", "JKRowling", 1997, 10);
		assertEquals(book.getTitle(), "Harry Potter");
	}

	@Test
	public void testSetTitle() {
		Book book = new Book("Harry Potter", "JKRowling", 1997, 10);
		assertEquals(book.getTitle(), "Harry Potter");
		book.setTitle("Harry Potter 1");
		assertEquals(book.getTitle(), "Harry Potter 1");
	}

	@Test
	public void testGetAuthor() {
		Book book = new Book("Harry Potter", "JKRowling", 1997, 10);
		assertEquals(book.getAuthor(), "JKRowling");
	}

	@Test
	public void testSetAuthor() {
		Book book = new Book("Harry Potter", "JKRowling", 1997, 10);
		assertEquals(book.getAuthor(), "JKRowling");
		book.setAuthor("J K Rowling");
		assertEquals(book.getAuthor(), "J K Rowling");
	}

	@Test
	public void testGetAnnee() {
		Book book = new Book("Harry Potter", "JKRowling", 1997, 10);
		assertEquals(book.getYear(), 1997);
	}

	@Test
	public void testSetAnnee() {
		Book book = new Book("Harry Potter", "JKRowling", 1997, 10);
		assertEquals(book.getYear(), 1997);
		book.setAnnee(2000);
		assertEquals(book.getYear(), 2000);
	}

	@Test
	public void testGetQuantite() {
		Book book = new Book("Harry Potter", "JKRowling", 1997, 10);
		assertEquals(book.getQuantite(), 10);
	}
	
	@Test
	public void testSetQuantite() {
		Book book = new Book("Harry Potter", "JKRowling", 1997, 10);
		assertEquals(book.getQuantite(), 10);
		book.setQuantite(20);
		assertEquals(book.getQuantite(), 20);
	}
	
	@Test
	public void testEquals() {
		Book book = new Book("Harry Potter", "JKRowling", 1997, 10);
		Book book2 = new Book("Harry Potter", "JKRowling", 1997, 10);
		Book book3 = new Book("Horry Patter", "JKRowling", 1997, 10);

		assertTrue(book.equals(book));
		assertTrue(book2.equals(book));
		assertFalse(book3.equals(book));
		assertFalse(book.equals(null));
	}
	
	@Test
	public void testCompareTo() {
		Book book = new Book("Harry Potter", "JKRowling", 1997, 10);
		Book book = new Book("Harry Potter 2", "JKRowling", 1999, 10);
		Book book = new Book("Harry Potter 3", "JKRowling", 2001, 10);

		assertEquals(book.compareTo(book),0);
		assertEquals(book.compareTo(book2),-1);
		assertEquals(book3.compareTo(book),1);
	}
}
