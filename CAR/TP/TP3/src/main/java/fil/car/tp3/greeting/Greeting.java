package fil.car.tp3.greeting;
/**
 * Classe représentant un "simple" message 
 * @author antoine
 *
 */
@SuppressWarnings("serial")
public class Greeting implements GreetingInterface{

	/**
	 * Le message en question
	 */
	private String who;
	
	/**
	 * Constructeur
	 * @param who le message
	 */
	public Greeting(String who) {
		this.who = who;
	}

	/**
	 * Retourne le message
	 * @return le message
	 */
	public String getWho() {
		// TODO Auto-generated method stub
		return this.who;
	}


}
