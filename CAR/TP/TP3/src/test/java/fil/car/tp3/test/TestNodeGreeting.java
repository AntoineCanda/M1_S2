package fil.car.tp3.test;

import org.junit.Test;

import fil.car.tp3.greeting.NodeGreeting;
import junit.framework.TestCase;

public class TestNodeGreeting extends TestCase {

	@Test
	public void testNodeGreeting(){
		NodeGreeting msg = new NodeGreeting("Test");
		assertEquals("Test", msg.getWho());
	}
}
