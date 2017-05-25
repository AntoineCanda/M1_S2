package fil.car.tp3.greeting;

/**
 * Classe permettant d'envoyer un message au noeud parent
 * Ce message permet de ne pas recevoir à nouveau le message envoyé par le parent
 * @author antoine
 *
 */
@SuppressWarnings("serial")
public class ParentGreeting implements GreetingInterface {

	/**
	 * Le message
	 */
	private String who;
	
	/**
	 * Le constructeur
	 * @param who le message
	 */
	public ParentGreeting(String who) {
		// TODO Auto-generated constructor stub
		this.who = who;
	}

	/**
	 * Renvoie le message
	 * @return le message
	 */
	public String getWho() {
		// TODO Auto-generated method stub
		return this.who;
	}


}
