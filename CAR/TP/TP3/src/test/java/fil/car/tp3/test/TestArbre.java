package fil.car.tp3.test;

import org.junit.Test;
import org.mockito.Mockito;

import fil.car.tp3.actors.Acteur;
import fil.car.tp3.greeting.AnswerGreeting;
import fil.car.tp3.greeting.Greeting;
import fil.car.tp3.greeting.InitActorGreeting;
import fil.car.tp3.greeting.InitParentGreeting;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.TestActorRef;

public class TestArbre {

	@Test
	public void testActeurAFils() throws InterruptedException{
		ActorSystem sys = ActorSystem.create("Sys");
		final Props props = Props.create(Acteur.class);
		final TestActorRef<Acteur> fils = TestActorRef.create(sys,props, "test");
		
		InitActorGreeting msg = Mockito.mock(InitActorGreeting.class);
		fils.tell(msg,fils);
		Mockito.verify(msg).getWho();
	}
	
	
	@Test
	public void testActeurAOKdesFils() throws InterruptedException {
		ActorSystem sys = ActorSystem.create("Sys");
		final Props props = Props.create(Acteur.class);
		final TestActorRef<Acteur> fils = TestActorRef.create(sys,props, "test");
		
		AnswerGreeting ok = Mockito.mock(AnswerGreeting.class);
		
		InitActorGreeting init = Mockito.mock(InitActorGreeting.class);
		fils.tell(init,fils);
		fils.tell(ok,fils);
		Mockito.verify(ok).getWho();
	}
	
	
	@Test
	public void testActeurAMessageDeFils() throws InterruptedException {
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
	
	
	@Test
	public void testActeurRecoisUnMessageInitParent() throws InterruptedException {
		ActorSystem sys = ActorSystem.create("Sys");
		final Props props = Props.create(Acteur.class);
		final TestActorRef<Acteur> fils = TestActorRef.create(sys,props, "test");
		
		InitParentGreeting parent = Mockito.mock(InitParentGreeting.class);

		fils.tell(parent,fils);
		
		Mockito.verify(parent).getWho();

	}
}
