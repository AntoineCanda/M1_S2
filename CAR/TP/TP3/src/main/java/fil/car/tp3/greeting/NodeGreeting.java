package fil.car.tp3.greeting;

/**
 * Classe permettant de lancer un message à partir d'un noeud précis
 * @author antoine
 *
 */
@SuppressWarnings("serial")
public class NodeGreeting implements GreetingInterface {

	/**
	 * Message de l'acteur initialisateur 
	 */
	private String who;
	
	/**
	 * Constructeur 
	 * @param who message a envoyer
	 */
	public NodeGreeting(String who){
		this.who = who;
	}
	
	/**
	 * La methode retourne le message 
	 *@return retourne le message 
	 */
	public String getWho() {
		// TODO Auto-generated method stub
		return this.who;
	}

}
