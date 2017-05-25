package fil.car.tp3.test;

import org.junit.Test;

import fil.car.tp3.greeting.Greeting;
import junit.framework.TestCase;

public class TestGreeting extends TestCase {
	
	@Test
	public void testGreeting(){
		Greeting msg = new Greeting("Test");
		assertEquals("Test", msg.getWho());
	}
}
