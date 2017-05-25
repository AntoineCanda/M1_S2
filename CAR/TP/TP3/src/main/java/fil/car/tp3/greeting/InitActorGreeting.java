package fil.car.tp3.greeting;

import java.io.Serializable;

import akka.actor.ActorSelection;

/**
 * Classe permettant d'initialiser un acteur fils (et de l'ajouter à la liste des fils) par envoie de message
 * @author antoine
 *
 */
public class InitActorGreeting implements Serializable{

	/**
	 * L'acteur à ajouter
	 */
	private ActorSelection who;
	
	/**
	 * Constructeur
	 * @param who l'acteur à ajouter
	 */
	public InitActorGreeting(ActorSelection who){
		this.who = who;
	}
	
	/**
	 * Retourne l'acteur à ajouter
	 * @return l'acteur à ajouter
	 */
	public ActorSelection getWho() {
		// TODO Auto-generated method stub
		return this.who;
	}

}
