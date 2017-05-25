package fil.car.tp3.test;

import org.junit.Test;

import fil.car.tp3.greeting.ParentGreeting;
import junit.framework.TestCase;

public class TestParentGreeting extends TestCase {

	@Test
	public void testParentGreeting(){
		ParentGreeting msg = new ParentGreeting("Test");
		assertEquals("Test", msg.getWho());
	}
}
