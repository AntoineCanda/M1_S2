package fil.car.tp3.app;

import java.io.File;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import fil.car.tp3.actors.Acteur;
/**
 * Classe permettant de créer le système 2 sur le port 9001 avec 3 acteurs
 * @author antoine
 *
 */
public class System2 {
	public static void main(String[] args) throws InterruptedException {
		
		File conf = new File("system2.conf");
		Config configuration = ConfigFactory.parseFile(conf);
		ActorSystem actorSys = ActorSystem.create("System2", configuration);
		
		ActorRef actor2, actor3, actor4;
		
		actor2 = actorSys.actorOf(Props.create(Acteur.class), "actor2");
		actor3 = actorSys.actorOf(Props.create(Acteur.class), "actor3");
		actor4 = actorSys.actorOf(Props.create(Acteur.class), "actor4");

	}
}
