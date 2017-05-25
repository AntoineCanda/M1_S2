package fil.car.tp3.actors;

import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.UntypedActor;
import fil.car.tp3.greeting.AnswerGreeting;
import fil.car.tp3.greeting.Greeting;
import fil.car.tp3.greeting.InitActorGreeting;
import fil.car.tp3.greeting.InitParentGreeting;
import fil.car.tp3.greeting.NodeGreeting;
import fil.car.tp3.greeting.ParentGreeting;

/**
 * La classe Acteur permet de definir un acteur de notre application avec notamment les reponses associes aux differents messages que l'on peut recevoir.
 * @author antoine
 *
 */
public class Acteur extends UntypedActor  {

	/**
	 * La liste des enfants de l'acteur considere
	 */
	private List<ActorSelection> children= new ArrayList<ActorSelection>();
	/**
	 * Compteur du nombre d'enfant auxquel a ete envoyer un message
	 */
	private int cpt =0;
	/**
	 * Noeud parent de l'acteur
	 */
	private ActorSelection parent = null;


	
	/**
	 * Methode qui permet de specifier l'action a mener en fonction du type de message recu.
	 * @param msg le message recu qui peut être de different type amenant a differente reponse
	 */
	public void onReceive(Object msg) throws InterruptedException {
		// TODO Auto-generated method stub
		if(msg instanceof InitParentGreeting){
			System.out.println("je marche");
			this.parent = ((InitParentGreeting) msg).getWho();
		}
		
		if(msg instanceof NodeGreeting){
			NodeGreeting message = (NodeGreeting) msg;
			for(ActorSelection child: this.children){
				child.tell(new Greeting(message.getWho()), this.getSelf());
			}
			
			if(this.parent != null){
				this.parent.tell(new ParentGreeting(message.getWho()), this.getSelf());
			}
		}
		
		if(msg instanceof ParentGreeting){
			ParentGreeting message = (ParentGreeting) msg;
			System.out.println("Message de : " + this.getSender()+" avec le contenu : "+message.getWho()+ " pour le noeud : "+this.getSelf());
			for(ActorSelection child: this.children){
					child.tell(new Greeting(message.getWho()), this.getSelf());
			}
		}
		
		if(msg instanceof InitActorGreeting){
			InitActorGreeting child = (InitActorGreeting) msg;
			System.out.println(child);
			this.children.add(child.getWho());
		}
		
		if(msg instanceof AnswerGreeting){
			AnswerGreeting answer = (AnswerGreeting) msg;
			System.out.println("Reception de : "+this.getSender()+" avec le status : "+answer.getWho()+" provenant de : "+this.getSelf());
			this.cpt++;
			if(this.cpt == this.children.size()){
				if(this.getSender() != ActorRef.noSender()){
					this.getSender().tell(new AnswerGreeting(), this.getSelf());
				}
			}
		}
		
		if(msg instanceof Greeting){
			Greeting message = ((Greeting) msg);
			
			System.out.println("Message de : "+this.getSender()+" avec le contenu : "+message.getWho()+" pour "+this.getSelf());
			for(ActorSelection child: this.children){
				child.tell(message, this.getSelf());
			}
			if(this.children.size() == 0){
				if(this.getSender() != ActorRef.noSender()){
					this.getSender().tell(new AnswerGreeting(), this.getSelf());
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
