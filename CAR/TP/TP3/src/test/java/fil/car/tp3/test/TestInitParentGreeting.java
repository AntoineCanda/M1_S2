package fil.car.tp3.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

import akka.actor.ActorSelection;
import fil.car.tp3.greeting.InitParentGreeting;
import junit.framework.TestCase;

public class TestInitParentGreeting extends TestCase {

	@Test
	public void testInitParentGreeting(){
		ActorSelection acteur = Mockito.mock(ActorSelection.class);
		InitParentGreeting msg = new InitParentGreeting(acteur);
		assertEquals(acteur, msg.getWho());
	}
}
