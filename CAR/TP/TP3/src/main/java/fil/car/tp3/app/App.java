package fil.car.tp3.app;

import java.io.File;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import fil.car.tp3.actors.Acteur;
import fil.car.tp3.greeting.Greeting;
import fil.car.tp3.greeting.InitActorGreeting;
import fil.car.tp3.greeting.InitParentGreeting;
import fil.car.tp3.greeting.NodeGreeting;

/**
 * Classe principale.
 * On considere un arbre d'acteur ici que l'on va créer dans deux systèmes.
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
    	ActorRef client = sys.actorOf(Props.create(Acteur.class), "client");
    	
    	ActorSelection actor1 = sys.actorSelection("akka.tcp://System1@127.0.0.1:3552/user/actor1");
    	ActorSelection actor2 = sys.actorSelection("akka.tcp://System2@127.0.0.1:3553/user/actor2");
    	ActorSelection actor3 = sys.actorSelection("akka.tcp://System2@127.0.0.1:3553/user/actor3");
    	ActorSelection actor4 = sys.actorSelection("akka.tcp://System2@127.0.0.1:3553/user/actor4");
    	ActorSelection actor5 = sys.actorSelection("akka.tcp://System1@127.0.0.1:3552/user/actor5");
    	ActorSelection actor6 = sys.actorSelection("akka.tcp://System1@127.0.0.1:3552/user/actor6");
    	
    	System.out.println(" test ");
    	

    	actor1.tell(new InitActorGreeting(actor2), ActorRef.noSender());
    	actor1.tell(new InitActorGreeting(actor5), ActorRef.noSender());
    	actor2.tell(new InitActorGreeting(actor3), ActorRef.noSender());
    	actor2.tell(new InitActorGreeting(actor4), ActorRef.noSender());
    	actor5.tell(new InitActorGreeting(actor6), ActorRef.noSender());
    	

    	System.out.println("Envoi message depuis la racine de l'arbre");
    	actor1.tell(new Greeting("hello"), client);
  	
    	System.out.println("Envoi message depuis un autre noeud que la racine, à savoir le 2");
    	actor2.tell(new InitParentGreeting(actor1), client);
    	actor2.tell(new NodeGreeting("Bonjour"), client);
   }
}
