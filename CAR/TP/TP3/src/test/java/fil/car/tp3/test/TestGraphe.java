package fil.car.tp3.test;

import org.junit.Test;
import org.mockito.Mockito;

import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import fil.car.tp3.graphe.Acteur;
import fil.car.tp3.greeting.Greeting;
import fil.car.tp3.greeting.InitActorGreeting;
import akka.testkit.TestActorRef;

public class TestGraphe{

	@Test
	public void testActeurAFils() throws InterruptedException {
		ActorSystem sys = ActorSystem.create("Sys");
		final Props props = Props.create(Acteur.class);
		final TestActorRef<Acteur> fils = TestActorRef.create(sys,props, "test");
		
		InitActorGreeting message = Mockito.mock(InitActorGreeting.class);
		fils.tell(message,fils);
		Mockito.verify(message).getWho();		
	}
	
	@Test
	public void testActeurRecoisUnMessageAvecUnFils() throws InterruptedException {
		ActorSystem sys = ActorSystem.create("Sys");
		final Props props = Props.create(Acteur.class);
		final TestActorRef<Acteur> fils = TestActorRef.create(sys,props, "test");
		
		ActorSelection actor = Mockito.mock(ActorSelection.class);

		InitActorGreeting init = new InitActorGreeting(actor);
		Greeting message = Mockito.mock(Greeting.class);
		fils.tell(init,fils);
		fils.tell(message,fils);
		Mockito.verify(actor).tell(message, fils);
	}
	
}
