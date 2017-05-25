package fil.car.tp3.graphe;

import java.io.File;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import fil.car.tp3.greeting.Greeting;
import fil.car.tp3.greeting.InitActorGreeting;
import fil.car.tp3.greeting.InitParentGreeting;

/**
 * Classe principale.
 * On considere un graphe d'acteur ici que l'on va créer dans deux systèmes.
 * On a deux systèmes Sys1 au port 9000 et Sys2 au port 9001
 * @author antoine
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
    	File config = new File("confMain.conf");
    	Config conf = ConfigFactory.parseFile(config);
    	
    	ActorSystem sys = ActorSystem.create("MySystem",conf);
    	
    	ActorSelection actor1 = sys.actorSelection("akka.tcp://System1@127.0.0.1:3552/user/actor1");
    	ActorSelection actor2 = sys.actorSelection("akka.tcp://System2@127.0.0.1:3553/user/actor2");
    	ActorSelection actor3 = sys.actorSelection("akka.tcp://System2@127.0.0.1:3553/user/actor3");
    	ActorSelection actor4 = sys.actorSelection("akka.tcp://System2@127.0.0.1:3553/user/actor4");
    	ActorSelection actor5 = sys.actorSelection("akka.tcp://System1@127.0.0.1:3552/user/actor5");
    	ActorSelection actor6 = sys.actorSelection("akka.tcp://System1@127.0.0.1:3552/user/actor6");
    	
    	// Creation des liaisons il n'y a plus de fils mais des voisins donc on envoie aussi aux anciens peres en tant que fils
    	
    	actor1.tell(new InitActorGreeting(actor2), ActorRef.noSender());
    	actor1.tell(new InitActorGreeting(actor5), ActorRef.noSender());
    	
    	actor2.tell(new InitActorGreeting(actor3), ActorRef.noSender());
    	actor2.tell(new InitActorGreeting(actor4), ActorRef.noSender());
    	actor2.tell(new InitActorGreeting(actor1), ActorRef.noSender());
    	
    	actor3.tell(new InitActorGreeting(actor2), ActorRef.noSender());
    	
    	actor4.tell(new InitActorGreeting(actor6), ActorRef.noSender());
    	actor4.tell(new InitActorGreeting(actor2), ActorRef.noSender());

    	actor5.tell(new InitActorGreeting(actor6), ActorRef.noSender());
    	actor5.tell(new InitActorGreeting(actor1), ActorRef.noSender());

    	actor6.tell(new InitActorGreeting(actor4), ActorRef.noSender());
    	actor6.tell(new InitActorGreeting(actor5), ActorRef.noSender());


    	System.out.println("Envoi message depuis l'acteur 1");
    	actor1.tell(new Greeting("hello"), ActorRef.noSender());

    }
}
