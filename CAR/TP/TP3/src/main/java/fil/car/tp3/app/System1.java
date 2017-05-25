package fil.car.tp3.app;

import java.io.File;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import fil.car.tp3.actors.Acteur;
/**
 * Classe permettant d'initialiser le premier système sur le port 9000 avec 3 acteurs
 * @author antoine
 *
 */
public class System1 {
	
	public static void main(String[] args) throws InterruptedException {
		
		File conf = new File("system1.conf");
		Config configuration = ConfigFactory.parseFile(conf);
		ActorSystem actorSys = ActorSystem.create("System1", configuration);
		
		ActorRef actor1, actor5, actor6;
		
		actor1 = actorSys.actorOf(Props.create(Acteur.class), "actor1");
		actor5 = actorSys.actorOf(Props.create(Acteur.class), "actor5");
		actor6 = actorSys.actorOf(Props.create(Acteur.class), "actor6");
		
	}
}
