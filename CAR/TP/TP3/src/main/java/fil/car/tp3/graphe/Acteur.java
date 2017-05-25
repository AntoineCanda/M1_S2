package fil.car.tp3.graphe;


import java.util.ArrayList;
import java.util.List;
import akka.actor.ActorSelection;
import akka.actor.UntypedActor;
import fil.car.tp3.greeting.Greeting;
import fil.car.tp3.greeting.InitActorGreeting;


/**
 * La classe Acteur permet de definir un acteur de notre application avec notamment les reponses associes aux differents messages que l'on peut recevoir.
 * @author antoine
 *
 */
public class Acteur extends UntypedActor  {

	/**
	 * La liste des enfants de l'acteur considere
	 */
	private List<ActorSelection> voisins = new ArrayList<ActorSelection>();

	/**
	 * Marqueur permettant de savoir si on a deja visité l'acteur
	 */
	private boolean visited = false;
	
	/**
	 * Methode qui permet de specifier l'action a mener en fonction du type de message recu.
	 * @param msg le message recu qui peut être de different type amenant a differente reponse
	 */
	public void onReceive(Object msg) throws InterruptedException {
		// TODO Auto-generated method stub

		if(msg instanceof InitActorGreeting){
			InitActorGreeting voisin = (InitActorGreeting) msg;
			System.out.println(voisin);
			this.voisins.add(voisin.getWho());
		}
		
	
		if(msg instanceof Greeting){
			Greeting message = ((Greeting) msg);
			
			System.out.println("Message de : "+this.getSender()+" avec le contenu : "+message.getWho()+" pour "+this.getSelf());
			
			if(!visited){
				this.visited = true;
				for(ActorSelection voisin: this.voisins){
					System.out.println(voisin+ "  "+ this.getSender());
					if(!voisin.equals(this.getSender()))
						voisin.tell(message, this.getSelf());
				}
			}
			
		}
		else if(msg instanceof String){
			System.out.println((String) msg);
		}
		else {
			unhandled(msg);
		}
	}

}
