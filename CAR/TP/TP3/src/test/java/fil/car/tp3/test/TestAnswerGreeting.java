package fil.car.tp3.test;

import org.junit.Test;

import fil.car.tp3.greeting.AnswerGreeting;
import junit.framework.TestCase;

public class TestAnswerGreeting extends TestCase{

	@Test
	public void testAnswerGreeting(){
		AnswerGreeting msg = new AnswerGreeting();
		assertEquals("Answer ok", msg.getWho());
	}
}
