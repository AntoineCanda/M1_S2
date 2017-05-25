package fil.car.tp3.test;

import org.junit.Test;
import org.mockito.Mockito;

import akka.actor.ActorSelection;
import fil.car.tp3.greeting.InitActorGreeting;
import junit.framework.TestCase;

public class TestInitActorGreeting extends TestCase {
	
	@Test
	public void testInitActorGreeting(){
		ActorSelection acteur = Mockito.mock(ActorSelection.class);
		InitActorGreeting msg = new InitActorGreeting(acteur);
		assertEquals(acteur, msg.getWho());
	}
}
