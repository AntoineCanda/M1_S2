package fil.car.tp3.greeting;

import java.io.Serializable;

import akka.actor.ActorSelection;

/**
 * Classe permettant d'envoyer un message dans le but d'initialiser un parent d'un acteur courant 
 * @author antoine
 *
 */
public class InitParentGreeting implements Serializable{

	/**
	 * L'acteur parent
	 */
	private ActorSelection who;
	
	/**
	 * Constructeur 
	 * @param actor l'acteur parent à ajouter
	 */
	public InitParentGreeting(ActorSelection actor){
		this.who = actor;
	}
	
	/**
	 * Methode permettant d'obtenir l'acteur parent 
	 * @return l'acteur parent
	 */
	public ActorSelection getWho() {
		// TODO Auto-generated method stub
		return this.who;
	}

}
